package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SolenoidIn extends Command {
	
    public SolenoidIn() {
        requires(Robot.pneumaticSubsystem);
        setTimeout(.5);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.pneumaticSubsystem.in();
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    	System.out.println("solenoidIn: Interrupted");
    	end();
    }
}
