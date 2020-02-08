package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.PowerDistributionPanel;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Lib.SubsystemManager;
import frc.robot.Lib.Input.XboxRunner;
//import frc.robot.Lib.Actions.MacroExecutor;
import frc.robot.Subs.*;
//import frc.robot.Auto.Actions.*;

public class Robot extends TimedRobot {
  // SUBSYSTEMS
  public static Drive drive;


  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private static final int leftDeviceID = 1; 
  private static final int rightDeviceID = 2;
  private CANSparkMax leftMaster_;
  private CANSparkMax leftPawn_;
  private CANSparkMax rightPawn_;
  private CANSparkMax rightMaster_;

  // MANAGERS
  //private MacroExecutor executor_;

  
  private SubsystemManager sm_;

  // CONTROLLERS
  public static XboxRunner driver = new XboxRunner(Constants.Input.drive);
  //public static JoystickRunner rightDrive = new JoystickRunner(Constants.Input.driveRight);
  //public static JoystickRunner leftDrive  = new JoystickRunner(Constants.Input.driveLeft);
  public static XboxRunner operator = new XboxRunner(Constants.Input.operator);

  // PDP
  //private PowerDistributionPanel pdp_ = new PowerDistributionPanel();
  private Compressor comp_ = new Compressor();

  // ROBOT
  @Override public void robotInit() {
    /**
       * SPARK MAX controllers are intialized over CAN by constructing a CANSparkMax object
       * 
       * The CAN ID, which can be configured using the SPARK MAX Client, is passed as the
       * first parameter
       * 
       * The motor type is passed as the second parameter. Motor type can either be:
       *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless
       *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed
       * 
       * The example below initializes four brushless motors with CAN IDs 1 and 2. Change
       * these parameters to match your setup
       */
      leftMaster_ = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
      leftPawn_ = new CANSparkMax(leftDeviceID, MotorType.kBrushless);
      rightMaster_ = new CANSparkMax(rightDeviceID, MotorType.kBrushless);
      rightPawn_ = new CANSparkMax(rightDeviceID, MotorType.kBrushless);
      /**
       * The RestoreFactoryDefaults method can be used to reset the configuration parameters
       * in the SPARK MAX to their factory default state. If no argument is passed, these
       * parameters will not persist between power cycles
       */
      leftPawn_.restoreFactoryDefaults();
      leftMaster_.restoreFactoryDefaults();
      rightPawn_.restoreFactoryDefaults();
      rightMaster_.restoreFactoryDefaults();
  
      m_myRobot = new DifferentialDrive(leftMaster_, rightMaster_);
  
      m_leftStick = new Joystick(0);
      m_rightStick = new Joystick(1);
    }
  
    @Override
    public void teleopPeriodic() {
      m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
    
  

    
    drive = new Drive();

    sm_ = new SubsystemManager(
      //struc,
      drive
    );

    //executor_ = new MacroExecutor(4);
    UsbCamera cam = CameraServer.getInstance().startAutomaticCapture("Camera 1", "/dev/video1");
    cam.setVideoMode(PixelFormat.kMJPEG, 265, 144, 30);
    UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture("Camera 2", "/dev/video0");
    cam2.setVideoMode(PixelFormat.kMJPEG, 265, 144, 30);

  }
  @Override public void robotPeriodic() {
    sm_.log();
  }
  

  //@Override public void teleopInit() { start(); }
  @Override public void autonomousInit() { start(); }
  //@Override public void teleopPeriodic() { periodic(); }
  @Override public void autonomousPeriodic() { periodic(); }


  @Override public void testInit() {}
  @Override public void testPeriodic() {
  } 

  private void start(){
    //struc.start();
    sm_.start();
    comp_.clearAllPCMStickyFaults();
  }

  private void periodic(){
    sm_.update();
    
    
    // Drive
    drive.setArcade(-driver.getX(Hand.kLeft), -driver.getY(Hand.kRight));
    driver.whenTriggerThreshold(Hand.kLeft, .9, () -> drive.setGear(true));
    driver.whenTriggerThreshold(Hand.kRight, .9, () -> drive.setGear(false));
  }
}
    /*
    // Operator
    if(operator.getRawButton(6)){
      operator.whenPressed(4, () -> cargo.setAngle(170));
      operator.whenPressed(3, () -> cargo.setAngle(145));
      operator.whenPressed(2, () -> cargo.setAngle(100));
      operator.whenPressed(1, () -> cargo.setAngle(90));
    } else {
      operator.whenPressed(8, () -> hatch.setAngle(230));
      operator.whenPressed(4, () -> hatch.setAngle(220));
      //operator.whenPressed(2, () -> executor_.execute("Hatch Delivery", new HatchDelivery()));
      operator.whenPressed(3, () -> hatch.toggleClamp());;
      operator.whenPressed(1, () -> hatch.setAngle(180));
    }
    operator.whenPressed(5, () -> cargo.lock());
    
    if(operator.getTriggerAxis(Hand.kLeft) > .75){
      cargo.setIntake(-.8);
    } else if(operator.getTriggerAxis(Hand.kRight) > .75){
      cargo.setIntake(.75);
    } else {
      cargo.setIntake(.15);
      */
    //@Override public void robotInit() {
      