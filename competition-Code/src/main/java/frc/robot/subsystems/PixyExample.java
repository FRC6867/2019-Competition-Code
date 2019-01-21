package frc.robot.subsystems;

import frc.robot.Robot;
import frc.robot.subsystems.PixyPacket;
import frc.robot.subsystems.DriveTrain;

public class PixyExample {
	M_I2C i2c = new M_I2C();//setup the i2c interface
	PixyPacket pkt = i2c.getPixy();//create a pixy packet to hold data

	public void centerOnObject(){
		System.out.println("Center on Object is called");
		pkt = i2c.getPixy();
	if(pkt.x != -1){//if data is exist
		System.out.println("I see something");//print the data just to see
		if(pkt.x < .48 || pkt.x > .52){
			//and the 'object', whatever it may be is not in the center
		    //the code on the arduino decides what object to send
			while(pkt.x < .48 || pkt.x > .52){//while it is not center
				
				if(pkt.x < .48){//if its on the left side of robot, turn left
					
					Robot.m_drivetrain.leftDrive(0.20);//this is our left side of tank drive
					Robot.m_drivetrain.rightDrive(-0.20);//you drive code might differ
					

				}
				if(pkt.x > .52){//if its on the right side of robot, turn right
					
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