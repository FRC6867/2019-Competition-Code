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
public class DriveGTAInverted extends Command {


  public DriveGTAInverted() {
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
            
      //Right trigger to go forward
      if (Robot.m_oi.gamepad.getRawAxis(RobotMap.rightTrigger) >= 0.1)
      {
      Robot.m_drivetrain.leftDrive(-(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightTrigger) + Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisX)));
      Robot.m_drivetrain.rightDrive(-(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightTrigger) -  Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisX)));
      }
    
      //Left trigger to go backward      
      else if (Robot.m_oi.gamepad.getRawAxis(RobotMap.leftTrigger) >= 0.1)
      {
      Robot.m_drivetrain.leftDrive((Robot.m_oi.gamepad.getRawAxis(RobotMap.leftTrigger) - Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisX)));
      Robot.m_drivetrain.rightDrive((Robot.m_oi.gamepad.getRawAxis(RobotMap.leftTrigger) + Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisX)));
      }
      //Stopping
      else
      {
        
        if ((Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisX) >= 0.1) || (Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisX) <= -0.1))
          {
          Robot.m_drivetrain.leftDrive(-Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisX));
          Robot.m_drivetrain.rightDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisX));
          }
          else
          {
            Robot.m_drivetrain.leftDrive(0);
            Robot.m_drivetrain.rightDrive(0);    
          }
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
