package org.usfirst.frc.team5712.robot.commands.autonomous;

import java.util.HashMap;

import org.usfirst.frc.team5712.robot.Robot;
import org.usfirst.frc.team5712.robot.commands.DriveForwardEncoders;
import org.usfirst.frc.team5712.robot.commands.LowerArmAutonomous;
import org.usfirst.frc.team5712.robot.commands.TurnXDegrees;
import org.usfirst.frc.team5712.robot.commands.groups.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * 
 */
 public class DriveAutonomous extends CommandGroup {
    
	/*
	 * Unknowns that will affect the operations and outcome of this command group:
	 * 
	 * Defense to Cross (String - name):   Do we cross it, and how fast?
	 * Defense Position (Integer - position): What angle do we cross, and how far do we drive?
	 * Shoot (Boolean - shouldShoot): Are we going to shoot once we arrive at the "destination"
	 */
	
	/**
	 * The constructor will determine whether or not to lower the shooter arm. This constructor will always execute
	 * the DriveForwardEncoders command, which makes the robot drive forward until the tick goal is reached. The tick
	 * goal is NOT assigned in this CommandGroup.
	 * 
	 * @param defense
	 * String - the defense selected by the defense chooser
	 * @param position
	 * integer - the position of the robot that is selected by the position chooser
	 * @param shouldShoot
	 * boolean - should the robot try and shoot a goal when the destination is reached
	 * 
	 * @author Seth Byrne
	 */
    public DriveAutonomous(String defense, int position, boolean shouldShoot) {
    	
    	addSequential(new AutonomousPlanner(defense, position));
    	
    	if(defense.equalsIgnoreCase("low_bar")) {
    		addSequential(new LowerArmAutonomous()); // Should this be sequential?
    	}
    	addSequential(new DriveForwardEncoders());
    	addSequential(new TurnXDegrees());
    	if(shouldShoot) {
    		addSequential(new Shoot());
    	}
    	
    }
    
    
}