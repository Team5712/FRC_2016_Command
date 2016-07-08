package org.usfirst.frc.team5712.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team5712.robot.RobotMap;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class DriveSubsystem extends Subsystem {
	
	public VictorSP leftFront, leftRear, rightFront, rightRear;
	
	public RobotDrive drive;
	
	Encoder leftDriveEncoder, rightDriveEncoder;
	
	public AHRS gyro;
	public SerialPort serial_port;
	
	byte updateRateHZ = 50;	
	
	private int driveTickGoal = 2 * -1000;
	private double degreesTurn = 0.0; // Helps prevent null by declaring a value
	private double speed = 0.5;
	
	public DriveSubsystem(){
		
		leftFront = new VictorSP(RobotMap.LEFT_FRONT_MOTOR);
		leftRear = new VictorSP(RobotMap.LEFT_REAR_MOTOR);
		rightFront = new VictorSP(RobotMap.RIGHT_FRONT_MOTOR);
		rightRear = new VictorSP(RobotMap.RIGHT_REAR_MOTOR);
		
		drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
		
		leftDriveEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_A, RobotMap.LEFT_DRIVE_ENCODER_B, false, Encoder.EncodingType.k4X);
		rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_A, RobotMap.RIGHT_DRIVE_ENCODER_B, false, Encoder.EncodingType.k4X);
	
		gyro = new AHRS(SerialPort.Port.kMXP);
		 
	}
	
	public void display() {
		SmartDashboard.putNumber("Encoder (Left Drive)", leftDriveEncoder.get());
		SmartDashboard.putNumber("Encoder (Right Drive)", rightDriveEncoder.get());
		SmartDashboard.putNumber("Gyro Yaw", gyro.getYaw());
		SmartDashboard.putNumber("Gyro X", gyro.getDisplacementX());
		SmartDashboard.putNumber("Gyro Y", gyro.getDisplacementY());
	}
	
	public void initDefaultCommand() {
		
		
		
	}
	
	
	public void resetDriveEncoders() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
	}
	
	public void resetGyro() {
		gyro.reset();
	}
    
	/**
	 * This method will determine if the motors should or should not be inverted.
	 * Depending on the boolean value passed in, this method will invert or normalize the motor
	 * direction.
	 * 
	 * @param shouldInvert
	 * boolean - true if the motors should be inverted, false for normalized motors
	 * 
	 * @author Seth Byrne
	 */
    public void invertMotors(boolean shouldInvert) {
    	drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, shouldInvert);
    	drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, shouldInvert);
    	drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, shouldInvert);
    	drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, shouldInvert);
    }
    
    public void driveStraightForward() {
    	leftFront.set(-speed); // -0.7
    	leftRear.set(-speed);
    	rightFront.set(speed); // 0.7
    	rightRear.set(speed);
    	
    	if(gyro.getYaw() < -2) {
    		leftFront.set(-(speed + 0.1)); // -0.8
    		leftRear.set(-(speed + 0.1));
    	}
    	if(gyro.getYaw() > 2) {
    		rightFront.set(speed + 0.1); // 0.8
    		rightRear.set(speed + 0.1);
    	}
    }
    
    public boolean isAtTarget() {
    	if(leftDriveEncoder.get() < driveTickGoal){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void stop() {
    	leftFront.set(0);
    	leftRear.set(0);
    	rightFront.set(0);
    	rightRear.set(0);
    }   
    
    /**
     * This method returns a boolean indicating if the robot is stopped.
     * 
     * @return
     * boolean - true, if the robot is stopped, otherwise false
     * 
     * @author Seth Byrne
     */
    public boolean isStopped() {
    	if((leftFront.get() == 0) && (leftRear.get() == 0) 
    		&& (rightFront.get() == 0) && (rightRear.get() == 0)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void turnXdegrees() {
    	if (gyro.getYaw() > -degreesTurn ) {
    		leftFront.set(-0.4 + (-degreesTurn - gyro.getYaw())/180); 
			leftRear.set(0.4 + (-degreesTurn - gyro.getYaw())/180);
			rightFront.set(-0.4 + (-degreesTurn - gyro.getYaw())/180);
			rightRear.set(-0.4 + (-degreesTurn - gyro.getYaw())/180);
		}
    	else if (gyro.getYaw() < (-degreesTurn - 3)) {
    		leftFront.set(0.4 - (-degreesTurn - gyro.getYaw())/180); 
			leftRear.set(-0.4 - (-degreesTurn - gyro.getYaw())/180);
			rightFront.set(0.4 - (-degreesTurn - gyro.getYaw())/180);
			rightRear.set(0.4 - (-degreesTurn - gyro.getYaw())/180);
    	}
    }
    
    public boolean isTurnedX() {
    	if((gyro.getYaw() < -degreesTurn) && (gyro.getYaw() > -degreesTurn - 3)) {
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    // Get / Set methods
    
    public int getDriveTickGoal() {
    	return driveTickGoal;
    }
    public void setDriveTickGoal(int driveTickGoal) {
		this.driveTickGoal = driveTickGoal;
	}
    
    public double getDegreesTurn() {
    	return degreesTurn;
    }
    public void setDegreesTurn(double degreesTurn) {
		this.degreesTurn = degreesTurn;
	}
    
    public double getSpeed() {
		return speed;
	}
    public void setSpeed(double speed) {
		this.speed = speed;
	}
    
}


