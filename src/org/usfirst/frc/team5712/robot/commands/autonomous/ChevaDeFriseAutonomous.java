package org.usfirst.frc.team5712.robot.commands.autonomous;

import org.usfirst.frc.team5712.robot.commands.DriveForwardEncoders;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ChevaDeFriseAutonomous extends CommandGroup {
    
    public  ChevaDeFriseAutonomous() {
       addSequential(new DriveForwardEncoders());
    }
}