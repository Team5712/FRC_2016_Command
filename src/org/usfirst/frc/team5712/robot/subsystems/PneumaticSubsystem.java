package org.usfirst.frc.team5712.robot.subsystems;

import org.usfirst.frc.team5712.robot.RobotMap;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticSubsystem extends Subsystem {
    
	public DoubleSolenoid shifterSolenoid, shooterSolenoid;
	
	public Compressor compressor;
	
	public PneumaticSubsystem(){
		
		shifterSolenoid = new DoubleSolenoid(RobotMap.SHIFTER_SOLENOID_A, RobotMap.SHIFTER_SOLENOID_B);
	
		shooterSolenoid = new DoubleSolenoid(RobotMap.SHOOTER_SOLENOID_A, RobotMap.SHOOTER_SOLENOID_B);

		compressor = new Compressor(RobotMap.COMPRESSOR);
	}
	
    public void initDefaultCommand() {
    }
    
    public void shiftHigh(){
    	shifterSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void shiftLow(){
    	shifterSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void in(){
    	shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void out(){
    	shooterSolenoid.set(DoubleSolenoid.Value.kForward);
    }
}

