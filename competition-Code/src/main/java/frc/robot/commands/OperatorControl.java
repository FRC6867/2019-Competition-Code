/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Robot;
import frc.robot.OI;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;


/**
 * This command gives basic drive controls to the user through a two-stick gamepad.
 */
public class OperatorControl extends Command {

  public OperatorControl() {
  // Use requires() here to declare subsystem dependencies
  requires(Robot.m_drivetrain);
  requires(Robot.m_intake);
  requires(Robot.m_pixycam);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      //JT: This is where the driver code is actually going to go!

      if(Robot.m_oi.operator.getRawButton(RobotMap.leftBumper)) {
        if (RobotMap.masterThrottle > 0.05) {
          SmartDashboard.putNumber("Throttle", SmartDashboard.getNumber("Throttle", 0.7) - 0.05) ;
          RobotMap.masterThrottle = SmartDashboard.getNumber("Throttle", 0.7);
          Robot.wait1MSec(100);
        }
      }
      else if(Robot.m_oi.operator.getRawButton(RobotMap.rightBumper)) {
        if (RobotMap.masterThrottle < 0.95) {
          SmartDashboard.putNumber("Throttle", SmartDashboard.getNumber("Throttle", 0.7) + 0.05) ;
          RobotMap.masterThrottle = SmartDashboard.getNumber("Throttle", 0.7);
          Robot.wait1MSec(100);
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
  protected  void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
