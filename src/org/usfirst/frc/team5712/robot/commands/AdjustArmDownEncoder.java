package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AdjustArmDownEncoder extends Command {
    
	public AdjustArmDownEncoder() {
    	requires(Robot.shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterSubsystem.armDown();
    }

    protected boolean isFinished() {
        return Robot.shooterSubsystem.isDown();
    }

    protected void end() {
    	Robot.shooterSubsystem.stopArm();
    }

    protected void interrupted() {
    	System.out.println("Adjustment of Shooter Arm Down: Interrupted");
    	end();
    }
}
