/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Grabber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  public DoubleSolenoid grab = new DoubleSolenoid(1,2);
// not so sure that this is in the right place. ^^^^  
  public void Grabber() {
    DoubleSolenoid grab = new DoubleSolenoid(1,2);
    grab.set(DoubleSolenoid.Value.kOff);
    grab.set(DoubleSolenoid.Value.kForward);
    grab.set(DoubleSolenoid.Value.kReverse);
  }
  
  
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
