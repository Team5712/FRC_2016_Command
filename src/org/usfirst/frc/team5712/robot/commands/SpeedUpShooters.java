package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpeedUpShooters extends Command {

    public SpeedUpShooters() {
       requires(Robot.shooterSubsystem);
       setTimeout(1);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.shooterSubsystem.shoot();
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    	System.out.println("Shooting Interrupted");
    	end();
    }
}
