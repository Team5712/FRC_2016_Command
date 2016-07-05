package org.usfirst.frc.team5712.robot.subsystems;

import org.usfirst.frc.team5712.robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
    
	public VictorSP shooterL, shooterR;
	
	public Encoder shooterEncoder;
	
	public VictorSP shooter;
		
	public double shootTickGoal = 10 * -140; //tick to degree ratio (degrees/tick) * angle desired
	
    public ShooterSubsystem(){
    	
    	shooterR = new VictorSP(RobotMap.SHOOTER_RIGHT_MOTOR);
    	shooterL = new VictorSP(RobotMap.SHOOTER_LEFT_MOTOR);
    	
    	shooterEncoder = new Encoder(RobotMap.SHOOTER_ENCODER_A, RobotMap.SHOOTER_ENCODER_B, false, Encoder.EncodingType.k4X);
    	
    	shooter = new VictorSP(RobotMap.SHOOTER_LIFT_MOTOR); 	
    }
      
	protected void initDefaultCommand() {
	}
	
    public void display(){
    	SmartDashboard.putNumber("Left Shooter Output", shooterL.get());
    	SmartDashboard.putNumber("Right Shooter Output", shooterR.get());
    	
    	SmartDashboard.putNumber("Shooter Encoder", shooterEncoder.get());
    }
    
    public void resetShooterEncoder(){
    	shooterEncoder.reset();
    }
    
    public void armDown(){
		shooter.set(-0.25);	
    }
    
    public boolean isDownAutonomous(){
    	if(shooterEncoder.get() > 1400){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public boolean isDown(){
    	if(shooterEncoder.get() > 0){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void armUp(){
    	shooter.set(0.25);
    }
    
    public boolean isUpAutonomous(){
    	if(shooterEncoder.get() < -10){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public boolean isUp(){
    	if(shooterEncoder.get() < shootTickGoal){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void stopArm(){
    	shooter.set(0.0);
    }
    
    public void intake(){
    	shooterL.set(-.5);
    	shooterR.set(.5);
    }
    
    public void stopShooter(){
    	shooterL.set(0); //stops the left shooting motor
    	shooterR.set(0); //stops the right shooting motor
    }
    
    public boolean isStopped(){
    	if((shooterL.get() == 0) && (shooterR.get() == 0)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public void shoot(){
		shooterL.set(.5); 
		shooterR.set(-.5);
    }
}

