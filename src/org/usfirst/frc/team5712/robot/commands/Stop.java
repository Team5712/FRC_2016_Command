package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Stop extends Command {

    public Stop() {
        requires(Robot.driveSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.driveSubsystem.stop();
    }

    protected boolean isFinished() {
        return Robot.driveSubsystem.isStopped();
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
