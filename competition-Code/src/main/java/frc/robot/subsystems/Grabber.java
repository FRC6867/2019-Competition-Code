/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class Grabber extends Command {
    public Grabber() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_subsystem);
    DoubleSolenoid SolenDouble = new SolenoidBase(){

                public void initSendable(SendableBuilder builder) {
                SolenDouble.set(DoubleSolenoid.Value.kOff);
                SolenDouble.set(DoubleSolenoid.Value.kForward);
                SolenDouble.set(DoubleSolenoid.Value.kReverse);   
            }
        }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
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
