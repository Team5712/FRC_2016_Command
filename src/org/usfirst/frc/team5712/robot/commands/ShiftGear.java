package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftGear extends Command {

    public ShiftGear() {
    	requires(Robot.pneumaticSubsystem);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.pneumaticSubsystem.shiftHigh();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.pneumaticSubsystem.shiftLow();
    }

    protected void interrupted() {
    	System.out.println("Shifting Interupted");
    	end();
    }
}
