
package org.usfirst.frc.team5712.robot;

import org.usfirst.frc.team5712.robot.subsystems.*;
import org.usfirst.frc.team5712.robot.commands.autonomous.*;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import org.usfirst.frc.team5712.robot.OI;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
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
    CommandGroup autonomousSelected;
    double angleSelected;
    
    //Autonomous Selector
    SendableChooser autoChooser, angleChooser;
    
    //Camera Variables
    public int sessionFront;
    public Image frame;
    
    public void robotInit() {
		oi = new OI(); 
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Lowbar", new LowbarAutonomous());
		autoChooser.addObject("Moat", new MoatAutonomous());
		SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
		
		angleChooser = new SendableChooser();
		angleChooser.addDefault("120", 120);
		angleChooser.addObject("150", 150);
		SmartDashboard.putData("Angle Chooser", angleChooser);
		
		pneumaticSubsystem.compressor.setClosedLoopControl(true);
		
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		sessionFront = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(sessionFront);
		
		driveSubsystem.resetGyro();
		driveSubsystem.resetDriveEncoders();
		shooterSubsystem.resetShooterEncoder();
    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        System.out.println("Autonomous Selected: " + autoChooser.getSelected());
        System.out.println("Angle Selected: " + angleChooser.getSelected());
        
    	pneumaticSubsystem.in();
        driveSubsystem.resetDriveEncoders();
        driveSubsystem.resetGyro();
        shooterSubsystem.resetShooterEncoder();
        
        autonomousSelected = (CommandGroup) autoChooser.getSelected();
        angleSelected = (int) angleChooser.getSelected();
        autonomousSelected.start();
        
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        driveSubsystem.display();
        shooterSubsystem.display();
    }

    public void teleopInit() {
    	if (autonomousSelected != null) autonomousSelected.cancel();
    	
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
    
}
