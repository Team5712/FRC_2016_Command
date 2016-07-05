package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InvertMotors extends Command {
	
	private boolean invert = false;
	
	/**
	 * Inverts the motors if given true as a parameter. Otherwise, the motors are normalized.
	 * 
	 * @param shouldInvert
	 * boolean - should the motors be inverted?
	 * 
	 * @author Seth Byrne
	 */
    public InvertMotors(boolean shouldInvert) {
    	requires(Robot.driveSubsystem);   
    	
    	invert = shouldInvert;
    	
    }

    protected void initialize() {
    	Robot.driveSubsystem.invertMotors(invert);
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
