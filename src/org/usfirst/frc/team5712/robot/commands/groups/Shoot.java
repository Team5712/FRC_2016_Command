package org.usfirst.frc.team5712.robot.commands.groups;

import org.usfirst.frc.team5712.robot.commands.SolenoidIn;
import org.usfirst.frc.team5712.robot.commands.SolenoidOut;
import org.usfirst.frc.team5712.robot.commands.SpeedUpShooters;
import org.usfirst.frc.team5712.robot.commands.StopShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Shoot extends CommandGroup {
    
    public  Shoot() {
       addSequential(new SpeedUpShooters());
       addSequential(new SolenoidOut());
       addParallel(new StopShooter());
       addSequential(new SolenoidIn());
    }
}
