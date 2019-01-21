/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.AnalogInput; //This is for taking an analog input off the Rockwell 873M
import edu.wpi.first.wpilibj.Ultrasonic;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.RobotMap;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  //JT: WPI removed support for the Talon SRX's, and the toolchains need to be installed separately.
  //JT: Our programmers will need to download and install the framework themselves.
  //JT: http://www.ctr-electronics.com/control-system/hro.html#product_tabs_technical_resources


  //JT: Which drive base are we on? Comment out depending on which one we want
  /*
  public TalonSRX frontLeftDrive = new TalonSRX(RobotMap.FRONT_LEFT_DRIVE_CAN);
  public TalonSRX backLeftDrive = new TalonSRX(RobotMap.BACK_LEFT_DRIVE_CAN);
  public TalonSRX frontRightDrive = new TalonSRX(RobotMap.FRONT_RIGHT_DRIVE_CAN);
  public TalonSRX backRightDrive = new TalonSRX(RobotMap.BACK_RIGHT_DRIVE_CAN);   
  */

  public VictorSPX frontLeftDrive = new VictorSPX(RobotMap.FRONT_LEFT_DRIVE_CAN);
  public VictorSPX backLeftDrive = new VictorSPX(RobotMap.BACK_LEFT_DRIVE_CAN);
  public VictorSPX frontRightDrive = new VictorSPX(RobotMap.FRONT_RIGHT_DRIVE_CAN);
  public VictorSPX backRightDrive = new VictorSPX(RobotMap.BACK_RIGHT_DRIVE_CAN);
  //climb motors
  public VictorSPX climbMotorL = new VictorSPX(RobotMap.CLIMB_MOTORL_CAN);   
  public VictorSPX climbMotorR = new VictorSPX(RobotMap.CLIMB_MOTORR_CAN);   

  //Set up drive encoders            
  Encoder leftEncoder = new Encoder(RobotMap.leftDriveEncoderPin1,RobotMap.leftDriveEncoderPin2,false,Encoder.EncodingType.k4X);
  Encoder rightEncoder = new Encoder(RobotMap.rightDriveEncoderPin1,RobotMap.rightDriveEncoderPin2,false,Encoder.EncodingType.k4X);
  

  //Set up the HC-SR04
  Ultrasonic distanceSensor = new Ultrasonic(RobotMap.distanceSensorTriggerPin, RobotMap.distanceSensorEchoPin);

  //Set up the Rockwell ultrasonic
  //AnalogInput distanceSensor = new AnalogInput(RobotMap.ultraInPin);
  

  //JT: NavX needs to be initialized here.
  

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    distanceSensor.setAutomaticMode(true);
  }

  public void init() {

  }

  public void rightDrive(double speed) {
		frontRightDrive.set(ControlMode.PercentOutput, speed);
    backRightDrive.set(ControlMode.PercentOutput, speed);
    //Debug: System.out.println("rightEncoder: " + Double.toString(rightEncoder.getRaw()));
    //Debug: System.out.println("distanceSensor: " + Double.toString(distanceSensor.getRangeMM()));
	}
	
	public void leftDrive(double speed) {
		frontLeftDrive.set(ControlMode.PercentOutput, -speed);
    backLeftDrive.set(ControlMode.PercentOutput, -speed);
    //Debug: System.out.println("leftEncoder: " + Double.toString(leftEncoder.getRaw()));

  }
  public void climbMotor(double speed) {
    climbMotorL.set(ControlMode.PercentOutput, speed);
    climbMotorR.set(ControlMode.PercentOutput, -speed);
  }



  public void driveUntilNear(double speed, double distance) {
    //JT: This void needs to be completed, and we need to put some kind of straightening code in here.
    while(distanceSensor.getRangeMM() > distance) {
      leftDrive(speed);
      rightDrive(speed);
    }
    leftDrive(0);
    rightDrive(0);
  }

  public void driveForDistance(double speed, double distance) {
    //JT: This is just a skeleton.
    //JT: Figure out conversion so we can input value in millimeters and convert it to encoder ticks
    //JT: There's also no straightening code
    //JT: And this is a hard take-off + landing so we skid
    //JT: And we have no way of interrupting it once it takes off.
    leftEncoder.reset();
    rightEncoder.reset();
    while(leftEncoder.getRaw() < distance) {
      leftDrive(speed);
      rightDrive(speed);
    }
    leftDrive(0);
    rightDrive(0);
  }
  
}
