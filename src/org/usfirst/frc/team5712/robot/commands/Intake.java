package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Intake extends Command {

    public Intake() {
       requires(Robot.shooterSubsystem);
       setTimeout(1);
    }
    
    protected void initialize() {
    }

    protected void execute() {
		Robot.shooterSubsystem.intake();
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Robot.shooterSubsystem.stopShooter();
    }

    protected void interrupted() {
    	System.out.println("Intake Interrupted");
    	end();
    }
}
