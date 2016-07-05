package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardEncoders extends Command {
	
	/**
	 * Drives forward until the tick goal (specified in the drive subsystem) is met.
	 * 
	 * @author Seth Byrne
	 */
    public DriveForwardEncoders() {
    	requires(Robot.driveSubsystem);
    }
    
    
    protected void initialize() {
    }

    protected void execute() {
    	Robot.driveSubsystem.driveStraightForward();
    }

    protected boolean isFinished() {
        return Robot.driveSubsystem.isAtTarget();
    }
    
    protected void end() {
    	Robot.driveSubsystem.stop();
    }

    protected void interrupted() {
    	System.out.println("Driving Interupted");
    	end();
    }
}
