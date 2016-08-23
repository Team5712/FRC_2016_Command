package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InvertMotorsFalse extends Command {
	
	
    public InvertMotorsFalse() {
    	requires(Robot.driveSubsystem);        
    }

    protected void initialize() {
    	Robot.driveSubsystem.invertMotorsFalse();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	System.out.println("Un-Inverting Interupted");
    }
}
