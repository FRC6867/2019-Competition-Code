/*
 * We are indebted to FRC 5188 for this. We've really just adapted their code to our project
 * https://github.com/FRC5188/ArduinoPixyAndRoboRIO 
 */  

package frc.robot.subsystems;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid; //JT: The green LED ring needs a 12V power supply. The PCM has 12V channels. Therefore...
import frc.robot.subsystems.PixyCamera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeSubsystem;
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

	public void pixyBroadcast() {
		//Function to update SmartDashboard with pixy data.
		pkt = i2c.getPixy();
		SmartDashboard.putNumber("Pixy x1", pkt.x1);
		SmartDashboard.putNumber("Pixy x2", pkt.x2);
		SmartDashboard.putNumber("Pixy mid", pkt.midline);

	}
  
	public void centerOnObject(){
		//JT: I'm not sure if I'd rather have this in a separate command, but it works for now.
		System.out.println("Center on Object is called");
		if(pkt.x1 != -1){//if data is exist
			System.out.print("Targets acquired. Midline @ ");
			System.out.println(pkt.midline);
			if(pkt.midline  < .45 || pkt.midline > .55){
				//We have some play. We might make the deadzone more generous
				while(pkt.midline < .45 || pkt.midline > .55){ //As long as we're off-center
					if(pkt.midline < .45){    //if its on the left side of robot, turn left	
						Robot.m_intake.slideToTheRight();
					}
					if(pkt.midline > .55){    //if its on the right side of robot, turn right	
						Robot.m_intake.slideToTheLeft();
					}
					if(pkt.midline == -1) {//If we lose the targets we should stop.
						break;
					}
					pkt = i2c.getPixy();//refresh the data
					System.out.println("Midline: " + pkt.midline);//print the data just to see
				}
			}
			Robot.m_intake.takeItBackNowYAll(); //And turn off the slider when we're done.
	}
}

}
