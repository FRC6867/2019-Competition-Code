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
import com.ctre.phoenix.motorcontrol.ControlMode;
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

      //Master throttle control
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

      //Throttle control for Mr Krab
      if(Robot.m_oi.operator.getRawButton(RobotMap.aButton)) {
        if (RobotMap.krabSpeed > 0.05) {
          SmartDashboard.putNumber("Krab Speed", SmartDashboard.getNumber("Krab Speed", 0.3) - 0.05) ;
          RobotMap.masterThrottle = SmartDashboard.getNumber("Krab Speed", 0.3);
          Robot.wait1MSec(100);
        }
      }
      else if(Robot.m_oi.operator.getRawButton(RobotMap.bButton)) {
        if (RobotMap.krabSpeed < 0.95) {
          SmartDashboard.putNumber("Krab Speed", SmartDashboard.getNumber("Krab Speed", 0.3) + 0.05) ;
          RobotMap.masterThrottle = SmartDashboard.getNumber("Krab Speed", 0.3);
          Robot.wait1MSec(100);
        }
      } 

      //Toggle for the ring light. Just so we can reset the pixy if we need it.
      if(Robot.m_oi.operator.getRawButton(RobotMap.xButton)) {
        Robot.m_pixycam.cameraLEDRing.set(false);
      }
      else {
        Robot.m_pixycam.cameraLEDRing.set(true);
      }

      //Manual override for Mr Krab
      if(Robot.m_oi.operator.getRawAxis(RobotMap.rightStickAxisX) > 0.2 || Robot.m_oi.operator.getRawAxis(RobotMap.rightStickAxisX) < -0.2) {
        Robot.m_intake.intakeLR.set(ControlMode.PercentOutput, Robot.m_oi.operator.getRawAxis(RobotMap.rightStickAxisX));
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
