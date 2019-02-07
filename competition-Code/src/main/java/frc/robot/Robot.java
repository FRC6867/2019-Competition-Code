/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PixyCamera;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;
import frc.robot.subsystems.*;
import frc.robot.OI;
import frc.robot.subsystems.PixyCamera;
import edu.wpi.first.wpilibj.CameraServer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Command driverControls;
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static DriveTrain m_drivetrain = new DriveTrain();
  public static PixyCamera m_pixycam = new PixyCamera();
  public static IntakeSubsystem m_intake = new IntakeSubsystem();
  public static PneumaticSubsystem m_pneumatics = new PneumaticSubsystem();
  public static OI m_oi;


  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  SendableChooser<Command> m_driveselect = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_oi.init();
    m_drivetrain.init();
    m_pneumatics.init();

    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    //Our drive select chooser
    m_driveselect.setDefaultOption("Tank Controls", new DriveWithController());
    m_driveselect.addOption("GTA Controls", new DriveGTA());
    m_driveselect.addOption("COD Controls", new DriveCOD());
    m_driveselect.addOption("TahirGTA Controls", new DriveGTAInverted());
    SmartDashboard.putData("Driver mode", m_driveselect);
    SmartDashboard.putNumber("Throttle", RobotMap.masterThrottle);
    SmartDashboard.putData(m_drivetrain);

    CameraServer.getInstance().startAutomaticCapture();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when teleop starts running.
    // If you want the autonomous to continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    
    Command driverControls = m_driveselect.getSelected();
    RobotMap.masterThrottle = SmartDashboard.getNumber("Throttle", 0.7); //Get value for the throttle. Take 0.7 as a default.
    //Command driverControls = new DriveWithController();
    driverControls.start();
  
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    Scheduler.getInstance().run();
    m_pixycam.cameraLEDRing.set(true); //Turn on LED ring. This should be tied to a button later.
    m_pixycam.centerOnObject();

    //JT: Just in case, if for some reason we lose control this restarts the control command
    if (Robot.m_drivetrain.getCurrentCommand() == null) {
      Scheduler.getInstance().add(new DriveWithController());
    }

  
    /*
    m_drivetrain.rightDrive(0.5);
    m_drivetrain.leftDrive(0.5);
    */
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
