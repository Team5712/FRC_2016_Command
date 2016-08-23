package org.usfirst.frc.team5712.robot.subsystems;

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
		
	byte update_rate_hz = 50;
	public double degreesTurn;
	
	public int driveTickGoal = 2 * -1000;
	
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
	
	public void display(){
		SmartDashboard.putNumber("Encoder (Left Drive)", leftDriveEncoder.get());
		SmartDashboard.putNumber("Encoder (Right Drive)", rightDriveEncoder.get());
		SmartDashboard.putNumber("Gyro Yaw", gyro.getYaw());
		SmartDashboard.putNumber("Gyro X", gyro.getDisplacementX());
		SmartDashboard.putNumber("Gyro Y", gyro.getDisplacementY());
	}
	
	public void initDefaultCommand() {
	}
	
	public void resetDriveEncoders(){
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
	}
	
	public void resetGyro(){
		gyro.reset();
	}
    
    public void invertMotorsTrue(){
    	drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    	drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
    	drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
    	drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
    }
    
    public void invertMotorsFalse(){
    	drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
    	drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
    	drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
    	drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
    }
    
    public void driveStraightForward(){
    	leftFront.set(-0.7);
    	leftRear.set(-0.7);
    	rightFront.set(0.7);
    	rightRear.set(0.7);
    	if(gyro.getYaw() > 2){
    		rightFront.set(0.8);
    		rightRear.set(0.8);
    	}
    	if(gyro.getYaw() < -2){
    		leftFront.set(-0.8);
    		leftRear.set(-0.8);
    	}
    }
    
    public boolean isUnderLowbar(){
    	if(leftDriveEncoder.get() > driveTickGoal){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void stop(){
    	leftFront.set(0);
    	leftRear.set(0);
    	rightFront.set(0);
    	rightRear.set(0);
    }   
    
    public boolean isStopped(){
    	if((leftFront.get() == 0) && (leftRear.get() == 0) 
    		&& (rightFront.get() == 0) && (rightRear.get() == 0)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void turnXdegrees(){
    	if (gyro.getYaw() > -degreesTurn ) {
    		leftFront.set(-0.4 + (-degreesTurn - gyro.getYaw())/180); 
			leftRear.set(0.4 + (-degreesTurn - gyro.getYaw())/180);
			rightFront.set(-0.4 + (-degreesTurn - gyro.getYaw())/180);
			rightRear.set(-0.4 + (-degreesTurn - gyro.getYaw())/180);
		}
    	else if (gyro.getYaw() < (-degreesTurn - 3)){
    		leftFront.set(0.4 - (-degreesTurn - gyro.getYaw())/180); 
			leftRear.set(-0.4 - (-degreesTurn - gyro.getYaw())/180);
			rightFront.set(0.4 - (-degreesTurn - gyro.getYaw())/180);
			rightRear.set(0.4 - (-degreesTurn - gyro.getYaw())/180);
    	}
    }
    
    public boolean isTurnedX(){
    	if((gyro.getYaw() < -degreesTurn) && (gyro.getYaw() > -degreesTurn - 3)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
}


