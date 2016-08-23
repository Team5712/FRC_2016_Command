package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	@author Team 5712
 */
public class TurnXdegrees extends Command {

    public TurnXdegrees() {
    	requires(Robot.driveSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.driveSubsystem.turnXdegrees();
    }

    protected boolean isFinished() {
        return Robot.driveSubsystem.isTurnedX();
    }

    protected void end() {
    	Robot.driveSubsystem.stop();
    }

    protected void interrupted() {
    	System.out.println("Turning to " + "X" + "degrees has been interrupted");
    	end();
    }
}
