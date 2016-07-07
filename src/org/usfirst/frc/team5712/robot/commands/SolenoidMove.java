package org.usfirst.frc.team5712.robot.commands;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SolenoidMove extends Command {
	
	private boolean pullIn = false;
	
	/**
	 * Moves the solenoids. If true is passed in as a parameter, the solenoids will be moved out. Otherwise,
	 * they will be pulled in.
	 * 
	 * @param solenoidIn
	 * boolean - should the solenoids be pulled in
	 * 
	 * @author Seth Byrne
	 */
    public SolenoidMove(boolean solenoidIn) {
        requires(Robot.pneumaticSubsystem);
        
        this.pullIn = solenoidIn;
        
        setTimeout(.5);
    }

    protected void initialize() {
    }

    protected void execute() {
    	
    	if(pullIn) {
    		Robot.pneumaticSubsystem.in();
    	} else {
    		Robot.pneumaticSubsystem.out();
    	}
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
