/*
 * We are indebted to FRC 5188 for this. We've really just adapted their code to our project
 * https://github.com/FRC5188/ArduinoPixyAndRoboRIO 
 */  

package frc.robot.subsystems;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid; //JT: The green LED ring needs a 12V power supply. The PCM has 12V channels. Therefore...
import frc.robot.subsystems.PixyCamera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.RobotMap;


/**
 * This subsystem indirectly interacts with the Pixy (via the arduino)
 */

public class PixyCamera extends Subsystem {
	M_I2C i2c = new M_I2C();//setup the i2c interface
	PixyPacket pkt = i2c.getPixy();//create a pixy packet to hold data
	public Solenoid cameraLEDRing = new Solenoid(7);

	@Override
	public void initDefaultCommand() {
	  // Set the default command for a subsystem here.
	  // setDefaultCommand(new MySpecialCommand());
	}
  
	public void centerOnObject(){
		//JT: I'm not sure if I'd rather have this in a separate command, but it works for now.
		//System.out.println("Center on Object is called");
		pkt = i2c.getPixy();
		if(pkt.x != -1){//if data is exist
			//System.out.println("I see something");
			if(pkt.x < .48 || pkt.x > .52){
				//and the 'object', whatever it may be is not in the center
				//the code on the arduino decides what object to send
				while(pkt.x < .48 || pkt.x > .52){ //Sets a deadzone for "centered enough"
					
					if(pkt.x < .48){    //if its on the left side of robot, turn left	
						Robot.m_drivetrain.leftDrive(0.20);//this is our left side of tank drive
						Robot.m_drivetrain.rightDrive(-0.20);//you drive code might differ
					}
					if(pkt.x > .52){    //if its on the right side of robot, turn right	
						Robot.m_drivetrain.leftDrive(-0.20);
						Robot.m_drivetrain.rightDrive(0.20);	
					}
					if(pkt.y == -1)//Restart if ball lost during turn
						break;
					pkt = i2c.getPixy();//refresh the data
					System.out.println("XPos: " + pkt.x);//print the data just to see
				}

			}
	}
}

}
