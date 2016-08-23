package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AdjustArmUpEncoder extends Command {
	
    public AdjustArmUpEncoder() {
        requires(Robot.shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterSubsystem.armUp();
    }

    protected boolean isFinished() {
        return Robot.shooterSubsystem.isUp();
    }

    protected void end() {
    	Robot.shooterSubsystem.stopArm();
    }

    protected void interrupted() {
    	System.out.println("Adjustment of Shooter Arm Up: Interrupted");
    	end();
    }
}
