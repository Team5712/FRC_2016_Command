package org.usfirst.frc.team5712.robot.commands.autonomous;

import org.usfirst.frc.team5712.robot.commands.DriveForwardEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RampartsAutonomous extends CommandGroup {
    
    public  RampartsAutonomous() {
       addSequential(new DriveForwardEncoders());
    }
}