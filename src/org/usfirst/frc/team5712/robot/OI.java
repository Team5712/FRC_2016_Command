package org.usfirst.frc.team5712.robot;

import org.usfirst.frc.team5712.robot.commands.*;
import org.usfirst.frc.team5712.robot.commands.groups.Shoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
 * @author Team 5712
 *
 */
public class OI {
	
	//Joysticks
	public Joystick driveStick = new Joystick(RobotMap.DRIVESTICK_JOYSTICK);
	public Joystick shootStick = new Joystick(RobotMap.SHOOTSTICK_JOYSTICK);

	//Buttons
	//driveStick Buttons (Driver 1):
	public Button shiftGear_DriveStickTrigger; //1
	
	public Button invertMotors_DriveStickButton; //3
	public Button normalizeMotors_DriveStickButton; //4
	
	public Button turnXdegrees_DriveStickButton; //6 
	
	public Button solenoidOut_DriveStickButton; //7
	public Button solenoidIn_DriveStickButton; //8
	
	//shootStick Buttons (Driver 2):
	public Button shoot_ShootStickTrigger; //1
	public Button intake_ShootStickButton; //2
	
	public Button adjustArmUpEncoder_ShootStickButton; //5
	public Button adjustArmDownEncoder_ShootStickButton; //6
	
	public Button solenoidOut_ShootStickButton; //10
	public Button solenoidIn_ShootStickButton; //11
	
	public OI() {
		
		//driveStick Button Commands
		shiftGear_DriveStickTrigger = new JoystickButton(driveStick, RobotMap.SHIFT_GEAR_TRIGGER);
		shiftGear_DriveStickTrigger.whileHeld(new ShiftGear());
		
		invertMotors_DriveStickButton = new JoystickButton(driveStick, RobotMap.INVERT_MOTORS_BUTTON);
		invertMotors_DriveStickButton.whenPressed(new InvertMotors(true));
		normalizeMotors_DriveStickButton = new JoystickButton(driveStick, RobotMap.NORMALIZE_MOTORS_BUTTON);
		normalizeMotors_DriveStickButton.whenPressed(new InvertMotors(false));
		
		turnXdegrees_DriveStickButton = new JoystickButton(driveStick, RobotMap.TURN_DEGREES_BUTTON);
		turnXdegrees_DriveStickButton.whenPressed(new TurnXDegrees());
		
		solenoidOut_DriveStickButton = new JoystickButton(driveStick, RobotMap.SOLENOID_OUT_BUTTON);
		solenoidOut_DriveStickButton.whenPressed(new SolenoidMove(false));
		solenoidIn_DriveStickButton = new JoystickButton(driveStick, RobotMap.SOLENOID_IN_BUTTON);
		solenoidIn_DriveStickButton.whenPressed(new SolenoidMove(true));
		
		//shootStick Button Commands
		shoot_ShootStickTrigger = new JoystickButton(shootStick, RobotMap.SHOOT_TRIGGER);
		shoot_ShootStickTrigger.whenPressed(new Shoot());
		intake_ShootStickButton = new JoystickButton(shootStick, RobotMap.INTAKE_BUTTON);
		intake_ShootStickButton.whileHeld(new Intake());
		
		adjustArmUpEncoder_ShootStickButton = new JoystickButton(shootStick, RobotMap.ARM_UP_BUTTON);
		adjustArmUpEncoder_ShootStickButton.whenPressed(new AdjustArmUpEncoder());
		adjustArmDownEncoder_ShootStickButton = new JoystickButton(shootStick, RobotMap.ARM_DOWN_BUTTON);
		adjustArmDownEncoder_ShootStickButton.whenPressed(new AdjustArmDownEncoder());
		
		solenoidOut_ShootStickButton = new JoystickButton(shootStick, RobotMap.SOLENOID_OUT_BUTTON_2);
		solenoidOut_ShootStickButton.whenPressed(new SolenoidMove(false));
		solenoidIn_ShootStickButton = new JoystickButton(shootStick, RobotMap.SOLENOID_IN_BUTTON_2);
		solenoidIn_ShootStickButton.whenPressed(new SolenoidMove(true));
		
	}
}
