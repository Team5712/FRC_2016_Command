package org.usfirst.frc.team5712.robot.commands.autonomous;

import org.usfirst.frc.team5712.robot.commands.DriveForwardEncoders;
import org.usfirst.frc.team5712.robot.commands.LowerArmAutonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowbarAutonomous extends CommandGroup {
    
    public  LowbarAutonomous() {
      addSequential(new LowerArmAutonomous());
      addSequential(new DriveForwardEncoders());
    }
}