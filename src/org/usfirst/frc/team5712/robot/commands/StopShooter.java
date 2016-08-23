package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopShooter extends Command {

    public StopShooter() {
        requires(Robot.shooterSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterSubsystem.stopShooter();
    }

    protected boolean isFinished() {
        return Robot.shooterSubsystem.isStopped();
    }

    protected void end() {
    }

    protected void interrupted() {
    	System.out.println("Stopping Interupted");
    	end();
    }
}
