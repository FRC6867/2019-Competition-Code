/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  //These aren't wiring settings, but they are common values that we play with all the time. They're going here.
  public static double masterThrottle = 0.7; //This is our global speed throttler. Adjusting this will make everything slower/faster. 0.7 is a good default for comps

  //DIO for drive encoders
  //We're using CIMcoders: https://www.andymark.com/products/cimcoder
  //And the wiring harness is here: https://www.andymark.com/products/encoder-cable-36-in-long-with-single-pin-connectors
  //Left is wired backwards to right because it's spinning in the opposite direction!
  public static int leftDriveEncoderPin1 = 0; //Blue
  public static int leftDriveEncoderPin2 = 1; //Yellow
  public static int rightDriveEncoderPin1 = 2; //Yellow
  public static int rightDriveEncoderPin2 = 3; //Blue

  //Ultrasonic (HC-SR04)
  public static int distanceSensorTriggerPin = 4;
  public static int distanceSensorEchoPin = 5;
  
  /*
  //We're not using this. It needs a 15V power supply that we can't easily do on this year's robot. 
  //This is for  the 874M Proximity Sensor: http://configurator.rockwellautomation.com/#/configurator/873M-D18AV300-D4/summary)
  //And connected via the yellow cable: http://configurator.rockwellautomation.com/#/configurator/889D-F4AE-2/summary
  //This needs a 12VDC supply, so it goes to/from the VRM
  //And this is an analog sensor (it returns a voltage, which we convert to distance, so it goes to the AI pins)
  public static int ultraOutPin = 1;
  public static int ultraInPin = 2;
  */

  //CAN IDs for drive motors
  public static int FRONT_LEFT_DRIVE_CAN = 11;
  public static int BACK_LEFT_DRIVE_CAN = 10;
  public static int FRONT_RIGHT_DRIVE_CAN = 21;
  public static int BACK_RIGHT_DRIVE_CAN = 20;

  //CAN IDs for CLIMB motors
  public static int CLIMB_MOTORL_CAN = 30;
  public static int CLIMB_MOTORR_CAN = 31;

  //CAN IDs for instake system
  public static int INTAKE_LR_CAN = 40;

 
  // Joystick Mapping
  // Y-Axis
  public static int leftStickAxisY = 1;
  public static int rightStickAxisY = 5;
  // X-Axis
  public static int leftStickAxisX = 0;
  public static int rightStickAxisX = 4;
  // Triggers
  public static int rightTrigger = 3;
  public static int leftTrigger = 2;
  // Bumpers
  public static int rightBumper = 6;
  public static int leftBumper = 5;
  // Buttons
  public static int aButton = 1;
  public static int bButton = 2;
  public static int xButton = 3;
  public static int yButton = 4;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}

