package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InvertMotorsTrue extends Command {

    public InvertMotorsTrue() {
    	requires(Robot.driveSubsystem); 
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.driveSubsystem.invertMotorsTrue();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	System.out.println("Inverting Interupted");
    }
}
