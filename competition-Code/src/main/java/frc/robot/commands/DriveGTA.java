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
      //JT: I know this is a temp until we put a master variable somewhere (in RobotMap or in the subsystem?) but this isn't a helpful variable name. Let's call it speedThrottle or speedLimit or speedReducer or speedCap or something like that
      double speed = 0.8;
      
    
      //forward
      //JT: Ok, so if right trigger is pulled go forward. This is ok.
      if (Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick) >= 0.5)
      {
      Robot.m_drivetrain.leftDrive((Robot.m_oi.gamepad.getRawAxis(RobotMap.rightTrigger) + Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick)) * speed);
      Robot.m_drivetrain.rightDrive((Robot.m_oi.gamepad.getRawAxis(RobotMap.rightTrigger) + Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick)) * speed);
      }
    
    
      //backwards 
      //JT: And here you've got a couple problems. First, you're looking at the right trigger again. You probably want the left trigger, no?
      //JT: And then you have a second problem. The triggers only ever go from 0 to 1 (there's no negative on them), so this statement is always true.
      //JT: And as a last point... Is the math for a reverse turn the same as the math for a forward turn? I'll let you test and find out...
      if (Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick) >= -0.5)
      {
      Robot.m_drivetrain.leftDrive((Robot.m_oi.gamepad.getRawAxis(-RobotMap.leftTrigger) + Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick)) * speed);
      Robot.m_drivetrain.rightDrive((Robot.m_oi.gamepad.getRawAxis(-RobotMap.leftTrigger) + Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick)) * speed);
      }
      
      //turning
      //JT: And as a last point, these two lines shouldn't be here. execute() is going to run these two lines every time you go through the loop, so as soon as we get the code inside the if-statements we're going to be resetting to this again
      //JT: Just take this out. Try it and see.
      Robot.m_drivetrain.leftDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick));
      Robot.m_drivetrain.rightDrive(Robot.m_oi.gamepad.getRawAxis(RobotMap.rightAxisStick));

      //JT: And I know these are commented out, but as a code clean-up you can just get rid of them.
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
