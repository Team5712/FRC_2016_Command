package org.usfirst.frc.team5712.robot.commands.groups;

import org.usfirst.frc.team5712.robot.commands.SolenoidMove;
import org.usfirst.frc.team5712.robot.commands.SpeedUpShooters;
//import org.usfirst.frc.team5712.robot.commands.SpeedUpShooters;
import org.usfirst.frc.team5712.robot.commands.StopShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Shoot extends CommandGroup {
    
    public  Shoot() {
       addSequential(new SpeedUpShooters());
       addSequential(new SolenoidMove(false)); // false = out
       addParallel(new StopShooter());
       addSequential(new SolenoidMove(true)); // true = in
    }
}
