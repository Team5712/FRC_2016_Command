package org.usfirst.frc.team5712.robot.commands.autonomous;

import java.util.HashMap;

import org.usfirst.frc.team5712.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutonomousPlanner extends Command {
	
	private String defense;
	private int position;
	
	private HashMap<Integer, Integer> encoderInfo = new HashMap<Integer, Integer>();
	private HashMap<Integer, Double> angleInfo = new HashMap<Integer, Double>();
	private HashMap<String, Double> speedInfo = new HashMap<String, Double>();
	
	/**
	 * This CommandGroup will modify the default values within the DriveSubsystem to reach a certain
	 * spot on the field. It will take the information from HashMaps, filled with pre-programmed values. The values
	 * that will be modified include: drive ticks, angle to turn to, and the speed at which the robot will
	 * drive.
	 * 
	 * @param defense
	 * String - the defense that the robot will cross
	 * @param position
	 * integer - the position of the defense that will be crossed
	 * 
	 * @author Seth Byrne
	 */
	public AutonomousPlanner(String defense, int position) {
		
		requires(Robot.driveSubsystem);
		
		// This "saves" or copies the objects that are passed in as parameters to objects that are declared
		// within the class. This is needed because these objects and the selected options need to be
		// referenced in the execute method.
		this.defense = defense;
		this.position = position;
		
	}
	
	// Override methods
	
	@Override
	protected void initialize() {
		initInformation();
	}

	@Override
	protected void execute() {
		
		loadDriveInfo(defense, position);
		
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
	
	// Custom methods
	
	/**
	 * This method will fill the HashMaps with all of the values programmed.
	 * 
	 * @author Seth Byrne
	 */
	private void initInformation() {
		
		// Set up the values for the HashMaps
		// Encoder
		encoderInfo.put(1, 198 * 17); // 17 is the ticks / inch ratio
		encoderInfo.put(2, 233 * 17);
		encoderInfo.put(3, 144 * 17);
		encoderInfo.put(4, 144 * 17);
		encoderInfo.put(5, 238 * 17);
		
		// Degrees
		angleInfo.put(1, 60.0);
		angleInfo.put(2, 60.0);
		angleInfo.put(3, 15.0);
		angleInfo.put(4, 6.5);
		angleInfo.put(5, 60.0);
		
		// Speed
		speedInfo.put("default", 0.7); // This will be the default. If a defense needs a custom speed, we can add it
		
	}
	
    /**
     * This method will get the pre-programmed angle, drive distance, and speed from the HashMaps. You can then call the 
     * isAtTarget and isTurnedX methods to use these values in operation.
     * 
     * @param defense
     * String - the defense that the robot will be confronting
     * @param position
     * integer - the position (1-5) of where the robot is
     * 
     * @author Seth Byrne
     */
    private void loadDriveInfo(String defense, int position) {
    	
    	// These variables are used mainly for clarity; they aren't needed.
    	int newDriveTickGoal = 0;
    	double newDegreesTurn = 0.0;
    	double newSpeed = 0.0;
    	
    	newDriveTickGoal = encoderInfo.get(position);
    	newDegreesTurn = angleInfo.get(position);
    	newSpeed = speedInfo.get(defense);
    	
    	Robot.driveSubsystem.setDriveTickGoal(newDriveTickGoal);
    	Robot.driveSubsystem.setDegreesTurn(newDegreesTurn);
    	Robot.driveSubsystem.setSpeed(newSpeed);
    	
    }
	
}
