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
		shiftGear_DriveStickTrigger = new JoystickButton(driveStick, 1);
		shiftGear_DriveStickTrigger.whileHeld(new ShiftGear());
		
		invertMotors_DriveStickButton = new JoystickButton(driveStick, 3);
		invertMotors_DriveStickButton.whenPressed(new InvertMotorsTrue());
		normalizeMotors_DriveStickButton = new JoystickButton(driveStick, 4);
		normalizeMotors_DriveStickButton.whenPressed(new InvertMotorsFalse());
		
		turnXdegrees_DriveStickButton = new JoystickButton(driveStick, 6);
		turnXdegrees_DriveStickButton.whenPressed(new TurnXdegrees());
		
		solenoidOut_DriveStickButton = new JoystickButton(driveStick, 7);
		solenoidOut_DriveStickButton.whenPressed(new SolenoidOut());
		solenoidIn_DriveStickButton = new JoystickButton(driveStick, 8);
		solenoidIn_DriveStickButton.whenPressed(new SolenoidIn());
		
		//shootStick Button Commands
		shoot_ShootStickTrigger = new JoystickButton(shootStick, 1);
		shoot_ShootStickTrigger.whenPressed(new Shoot());
		intake_ShootStickButton = new JoystickButton(shootStick, 2);
		intake_ShootStickButton.whileHeld(new Intake());
		
		adjustArmUpEncoder_ShootStickButton = new JoystickButton(shootStick, 5);
		adjustArmUpEncoder_ShootStickButton.whenPressed(new AdjustArmUpEncoder());
		adjustArmDownEncoder_ShootStickButton = new JoystickButton(shootStick, 6);
		adjustArmDownEncoder_ShootStickButton.whenPressed(new AdjustArmDownEncoder());
		
		solenoidOut_ShootStickButton = new JoystickButton(shootStick, 10);
		solenoidOut_ShootStickButton.whenPressed(new SolenoidOut());
		solenoidIn_ShootStickButton = new JoystickButton(shootStick, 11);
		solenoidIn_ShootStickButton.whenPressed(new SolenoidIn());
		
	}
}
