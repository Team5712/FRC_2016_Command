package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardEncoders extends Command {

    public DriveForwardEncoders() {
    	requires(Robot.driveSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.driveSubsystem.driveStraightForward();
    }

    protected boolean isFinished() {
        return Robot.driveSubsystem.isUnderLowbar();
    }

    protected void end() {
    	Robot.driveSubsystem.stop();
    }

    protected void interrupted() {
    	System.out.println("Driving Interupted");
    	end();
    }
}
