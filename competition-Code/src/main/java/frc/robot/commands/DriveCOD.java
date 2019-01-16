/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Robot;
import frc.robot.OI;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;

/**
 * This command gives basic drive controls to the user through a two-stick gamepad.
 */
public class DriveCOD extends Command {


  public DriveCOD() {
  // Use requires() here to declare subsystem dependencies
  requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      //JT: This is where the driver code is actually going to go!
      
      //speedCap
      //JT: I know this is a temp until we put a master variable somewhere (in RobotMap or in the subsystem?) but this isn't a helpful variable name. Let's call it speedThrottle or speedLimit or speedReducer or speedCap or something like that
      double speedCap = 0.8;
      
    
      //Forward & backward
      if (Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisY) >= 0.5)
      {
      Robot.m_drivetrain.leftDrive((Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisY) + Robot.m_oi.gamepad.getRawAxis(RobotMap.leftStickAxisX)) * speedCap);
      Robot.m_drivetrain.rightDrive((Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisY) + Robot.m_oi.gamepad.getRawAxis(RobotMap.leftStickAxisX)) * speedCap);
      }
      //turning
      else if ((Robot.m_oi.gamepad.getRawAxis((RobotMap.leftStickAxisX) >= 0.5) || (Robot.m_oi.gamepad.getRawAxis((RobotMap.leftStickAxisX) <= 0.5))
      {
        Robot.m_drivetrain.leftDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisX));
        Robot.m_drivetrain.rightDrive(-Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisX));  
      }
      //very confused as to why this is underlined?
      else
      {
        Robot.m_drivetrain.leftDrive(0);
        Robot.m_drivetrain.rightDrive(0);
      }
    }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
