
package org.usfirst.frc.team5712.robot;

import org.usfirst.frc.team5712.robot.subsystems.*;
import org.usfirst.frc.team5712.robot.commands.autonomous.*;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import org.usfirst.frc.team5712.robot.OI;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * 
 * @author Team 5712
 * 
 */

public class Robot extends IterativeRobot {

	public static boolean IS_COMPETITION_ROBOT;
	
	public static DriveSubsystem driveSubsystem = new DriveSubsystem();	
	public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static PneumaticSubsystem pneumaticSubsystem = new PneumaticSubsystem();
	
	public static OI oi;
	
    //Autonomous commands
    CommandGroup autonomousExecuteCommand;
    double angleSelected = 0.0;
    
    // Autonomous variables
    String defenseSelected = "";
    int positionSelected = 0;
    boolean willShoot = true;
    
    //Autonomous Selector
    SendableChooser defenseChooser, positionChooser, shootChooser;
    
    //Camera Variables
    public int sessionFront;
    public Image frame;
    
    public void robotInit() {
    	oi = new OI(); 
		
    	defenseChooser = new SendableChooser();
//		autoChooser.addDefault("Lowbar", new LowbarAutonomous());
//		autoChooser.addObject("Moat", new MoatAutonomous());
    	defenseChooser = addAutoOptions(defenseChooser, "defense");
		SmartDashboard.putData("Defense Chooser", defenseChooser);
		
		positionChooser = new SendableChooser();
		positionChooser = addAutoOptions(positionChooser, "position");
		SmartDashboard.putData("Position Chooser", positionChooser);
		
		shootChooser = new SendableChooser();
		shootChooser.addDefault("Shoot", true); // Add the objects. Should the robot shoot or not in autonomous
		shootChooser.addObject("No Shoot", false);
		SmartDashboard.putData("Shoot Chooser", shootChooser);
		
		pneumaticSubsystem.compressor.setClosedLoopControl(true);
		
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		sessionFront = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(sessionFront);
		
		driveSubsystem.resetGyro();
		driveSubsystem.resetDriveEncoders();
		shooterSubsystem.resetShooterEncoder();
    }
	
    public void disabledInit() {

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        System.out.println("Defense Selected: " + defenseChooser.getSelected());
        System.out.println("Position Selected: " + positionChooser.getSelected());
        
    	pneumaticSubsystem.in();
        driveSubsystem.resetDriveEncoders();
        driveSubsystem.resetGyro();
        shooterSubsystem.resetShooterEncoder();
        
//        autonomousSelected = (CommandGroup) autoChooser.getSelected();
//        angleSelected = (int) angleChooser.getSelected();
//        autonomousSelected.start();
        defenseSelected = (String) defenseChooser.getSelected();
        positionSelected = (int) positionChooser.getSelected();
        willShoot = (boolean) shootChooser.getSelected();
                
        autonomousExecuteCommand = new DriveAutonomous(defenseSelected, positionSelected, willShoot);
        autonomousExecuteCommand.start();
        
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        driveSubsystem.display();
        shooterSubsystem.display();
    }

    public void teleopInit() {
    	if (autonomousExecuteCommand != null) autonomousExecuteCommand.cancel();
    	
    	driveSubsystem.resetDriveEncoders();
    	driveSubsystem.resetGyro();	
    }
    
    @Override
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    	
    	driveSubsystem.drive.arcadeDrive(oi.driveStick);
    	shooterSubsystem.shooter.set(oi.shootStick.getRawAxis(1));
    	
    	driveSubsystem.display();
    	shooterSubsystem.display();
		
		NIVision.IMAQdxGrab(sessionFront, frame, 0);
		CameraServer.getInstance().setImage(frame);
    }
    
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
    // Custom Methods
    
    /**
     * This method will add all of the defenses and position selections that are desired. This eliminates
     * a large block of statements in the robotInit method.
     * 
     * @param chooser
     * SendableChooser - the chooser to add the selections to
     * @param optionsToAdd
     * String - the options to add (defense / position)
     * @return
     * The updated SendableChooser object
     * 
     * @author Seth Byrne
     */
    private SendableChooser addAutoOptions(SendableChooser chooser, String optionsToAdd) {
    	
    	// Use proper English and capitalization for these names, as they will
    	// be displayed on the SmartDashboard. Later, we can make these lower-case
    	// and replace the spaces with underscores. This is basically to keep consistent
    	// to programming naming
    	final String[] defenses = {"Portcullis", "Cheval de Frise", // A
    								"Ramparts", "Moat", // B
    								"Drawbridge", "Sally Port", // C
    								"Rock Wall", "Rough Terrain", // D
    								"Low Bar", // Required
    								"No Cross"}; // Custom
    	
    	final Integer[] positions = {1, 2, 3, 4, 5};
    	
    	
    	if(optionsToAdd.equalsIgnoreCase("defense")) {
    		
    		// Defenses
    		for(int d = 0; d < defenses.length; d++) {
    			// Instead of storing a command, we will store the name of the defense.
    			// This will be passed in as a parameter in the AutonomousPlanner class
    			// to determine how we operate during autonomous
    			chooser.addObject(defenses[d], defenses[d].toLowerCase().replaceAll(" ", "_"));
    			// For the object, make it lower-case and replace the spaces with underscores
    		}
    		
    	} else {
    		
    		// Positions
    		for(int d = 0; d < positions.length; d++) {
    			chooser.addObject("Position #" + positions[d], positions[d]);
    		}
    		
    	}
    	
    	// Return the chooser with the options added
    	return chooser;
    	
    }
    
}
