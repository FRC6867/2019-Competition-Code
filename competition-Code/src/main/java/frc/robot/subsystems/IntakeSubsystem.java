/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * This subsystem (belovedly known as our Krab Klaw) has the Left/Right slider and the claw mechanism.
 */
public class IntakeSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //public VictorSPX intakeLR = new VictorSPX(RobotMap.INTAKE_LR_CAN); // Left-Right movement of intake slider
  public TalonSRX intakeLR = new TalonSRX(RobotMap.INTAKE_LR_CAN); // And we're actually on a Talon now.
  public DoubleSolenoid krabKlaw = new DoubleSolenoid(1,2); //The one double solenoid is connected to two cylinders via Y-splitters
  public DoubleSolenoid falconPunch = new DoubleSolenoid(3,4); //The one double solenoid is connected to two cylinders via Y-splitters

  public static double intakeMotorSpeed = 0.2; //Move this into RobotMap later. Or move it to the Dashboard?

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    //Not sure if this works, but this should be the line to zero the encoder.
    //intakeLR.getSensorCollection().setQuadraturePosition(0, 15);
  }

  public void closeKlaw() {
    //Simple method for closing the claw
    krabKlaw.set(DoubleSolenoid.Value.kForward);
  }
  
  public void openKlaw() {
    //Simple method for opening the claw
    krabKlaw.set(DoubleSolenoid.Value.kReverse);
  }

  /* There's no robot to test this on, but I think this is right. Not sure which way the values are running.
     I don't know what the range of values is, or which way is positive or negative. I pulled the < 500 & > -500 out of the air

  public void slideToTheLeft() {
    if(intakeLR.getSensorCollection().getQuadraturePosition() < 500) {
      intakeLR.set(ControlMode.PercentOutput, RobotMap.krabSpeed);
    }
  }
  
  public void slideToTheRight() {
    if(intakeLR.getSensorCollection().getQuadraturePosition() > -500) {
      intakeLR.set(ControlMode.PercentOutput, -RobotMap.krabSpeed);
    }
  }

  */

  public void slideToTheLeft() {
		intakeLR.set(ControlMode.PercentOutput, RobotMap.krabSpeed);
  }
  
  public void slideToTheRight() {
		intakeLR.set(ControlMode.PercentOutput, -RobotMap.krabSpeed);
  }
  
  public void takeItBackNowYAll() {
    intakeLR.set(ControlMode.PercentOutput, 0);
  }

  public void falconPunchOut() {
    falconPunch.set(DoubleSolenoid.Value.kForward);
  }

  public void falconPunchIn() {
    falconPunch.set(DoubleSolenoid.Value.kReverse);
  }
}
