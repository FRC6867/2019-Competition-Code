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
public class DriveWithController extends Command {

  public DriveWithController() {
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

      //Intake Klaw control
      if(Robot.m_oi.gamepad.getRawButton(RobotMap.xButton)) {
        Robot.m_intake.openKlaw();
      }
      else if(Robot.m_oi.gamepad.getRawButton(RobotMap.bButton)) {
        Robot.m_intake.closeKlaw();
      }

      //Falcon Punch control
      if(Robot.m_oi.gamepad.getRawButton(RobotMap.yButton)) {
        Robot.m_intake.falconPunchOut();
      }
      else if(Robot.m_oi.gamepad.getRawButton(RobotMap.aButton)) {
        Robot.m_intake.falconPunchIn();
      }

      //Intake L/R slider control
      if(RobotMap.krabOwnedbyOp == false) { //The driver can only have this if the operator isn't using it.
        if(Robot.m_oi.gamepad.getRawButton(RobotMap.leftBumper) && Robot.m_intake.intakeLR.getSensorCollection().getQuadraturePosition() < RobotMap.krabLeftStop) {
          RobotMap.krabOwnedbyDrive = true;
          Robot.m_intake.slideToTheLeft();
        }
        else if(Robot.m_oi.gamepad.getRawButton(RobotMap.rightBumper) && Robot.m_intake.intakeLR.getSensorCollection().getQuadraturePosition() > RobotMap.krabRightStop) {
          RobotMap.krabOwnedbyDrive = true;
          Robot.m_intake.slideToTheRight();
        }
        else {
          RobotMap.krabOwnedbyDrive = false;
          Robot.m_intake.takeItBackNowYAll(); //If no input stop the motor
        }
      }
      
      //Tank code. If the stick is clicked it'll apply the cut.
      if(Robot.m_oi.gamepad.getRawButton(RobotMap.leftStickClick)) {
        Robot.m_drivetrain.leftDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.leftStickAxisY) * RobotMap.cutThrottle);
      }
      else {
        Robot.m_drivetrain.leftDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.leftStickAxisY));
      }

      if(Robot.m_oi.gamepad.getRawButton(RobotMap.rightStickClick)) {
        Robot.m_drivetrain.rightDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.leftStickAxisY) * RobotMap.cutThrottle);
      }
      else {
        Robot.m_drivetrain.rightDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightStickAxisY));
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
