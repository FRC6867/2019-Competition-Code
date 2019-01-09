/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


//JT: I'm working from BadRobots1014's code https://github.com/BadRobots1014/BadRobot2013/blob/master/src/com/badrobot/subsystems/DriveTrain.java

public class DriveTrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    TalonSRX frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive;
    Encoder leftEncoder;
    Encoder rightEncoder;
    public static DriveTrain instance;

    public static DriveTrain getInstance() {
        if (instance == null) {
            instance = new DriveTrain();
        }
        return instance;
    }


    public DriveTrain() {
    }


    public void initialize() {
        
            //JT: Copying configuration from last year's robot. Move this to RobotMap later to make things easier. Updating subsystems individually will be a pain.
            leftEncoder = new Encoder(0,1,false,Encoder.EncodingType.k4X);
            rightEncoder = new Encoder(2,3,false,Encoder.EncodingType.k4X);

            //JT: NavX needs to be initialized here.

            //JT: Line trackers and ultrasonics should also initialized here.

            //JT: WPI removed support for the Talon SRX's, and the toolchains need to be installed separately.
            //JT: Our programmers will need to download and install the framework themselves.
            //JT: http://www.ctr-electronics.com/control-system/hro.html#product_tabs_technical_resources
            //JT: These should also be moved to RobotMap
            frontLeftDrive = new TalonSRX(11);
            backLeftDrive = new TalonSRX(10);
            frontRightDrive = new TalonSRX(21);
            backRightDrive = new TalonSRX(20);   

    }


    public void rightDrive(double speed) {
		frontRightDrive.set(ControlMode.PercentOutput, -speed);
		backRightDrive.set(ControlMode.PercentOutput, -speed);
	}
	
	public void leftDrive(double speed) {
		frontLeftDrive.set(ControlMode.PercentOutput, speed);
		backLeftDrive.set(ControlMode.PercentOutput, speed);
	}

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
