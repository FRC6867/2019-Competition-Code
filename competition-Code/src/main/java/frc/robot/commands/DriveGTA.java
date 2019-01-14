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
public class DriveGTA extends Command {


  public DriveGTA() {
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
      //speed
      double speed = 0.8;
      //forward
      Robot.m_drivetrain.leftDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightTrigger) + Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick) * speed);
      Robot.m_drivetrain.rightDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightTrigger) + Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick) * speed);
      //backwards 
      Robot.m_drivetrain.leftDrive(Robot.m_oi.gamepad.getRawAxis(-RobotMap.leftTrigger) + Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick) * speed);
      Robot.m_drivetrain.rightDrive(Robot.m_oi.gamepad.getRawAxis(-RobotMap.leftTrigger) + Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick) * speed);
      //turning
      Robot.m_drivetrain.leftDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick) * speed);
      Robot.m_drivetrain.rightDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick) * speed);
       
      /*
      Robot.m_drivetrain.leftDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.leftAxisStick));
      Robot.m_drivetrain.rightDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick));
      */
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
