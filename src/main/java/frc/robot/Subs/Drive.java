package frc.robot.Subs;

import frc.robot.Constants;
import frc.robot.Lib.Subsystem;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drive implements Subsystem {
    // HARDWARE

    //   m_leftMotor = new CANSparkMax(leftDeviceID, MotorType.kBrushless);     rightDeviceID, MotorType.kBrushless
    //   m_rightMotor = new CANSparkMax(rightDeviceID, MotorType.kBrushless);
    public CANSparkMax leftMaster_ = new CANSparkMax(Constants.Drive.leftMaster, MotorType.kBrushless);
    public CANSparkMax leftPawn_ = new CANSparkMax(Constants.Drive.leftPawn_,MotorType.kBrushless);

    public CANSparkMax rightMaster_ = new CANSparkMax(Constants.Drive.rightMaster, MotorType.kBrushless);
    public CANSparkMax rightPawn_ = new CANSparkMax(Constants.Drive.rightPawn_, MotorType.kBrushless);
    

    //private DifferentialDrive drive_ = new DifferentialDrive(
/*
    new SpeedControllerGroup(
            leftMaster_,
            leftSlave1_,
        new SpeedControllerGroup(
            rightMaster_,
            rightSlave1_,
        );
*/  
            
    private DifferentialDrive drive_ = new DifferentialDrive(leftMaster_, rightMaster_);

    // STATE VARIABLES
    private Value shift_ = Value.kOff;
    private boolean isTank = false;
    private double modifier_ = 1;
    private double lpower_ = 0;
    private double rpower_ = 0;
    private double speed_ = 0;
    private double turn_ = 0;

    // CONSTRUCTOR
    public Drive(){
        leftMaster_.restoreFactoryDefaults();
        leftPawn_.restoreFactoryDefaults();
//For some reason .configFactoryDefaults was here, but not a member of CANSparkMax
        rightMaster_.restoreFactoryDefaults();
        rightPawn_.restoreFactoryDefaults();


        /*
        leftSlave1_.follow(leftMaster_);
        leftSlave2_.follow(leftMaster_);
        rightSlave1_.follow(rightMaster_);
        rightSlave2_.follow(rightMaster_);
        */

        //leftMaster_.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        //rightMaster_.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        /*
        leftMaster_.enableCurrentLimit(true);
        leftMaster_.configPeakCurrentLimit(40);
        leftMaster_.configContinuousCurrentLimit(40);

        rightMaster_.enableCurrentLimit(true);
        rightMaster_.configPeakCurrentLimit(40);
        rightMaster_.configContinuousCurrentLimit(40);
        */
/*
        leftMaster_.loop(limit, chopCycles) //.configOpenloopRamp(Constants.Drive.rampRate, 
                                       Constants.System.CANTimeout);
        leftPawn_.configOpenloopRamp(Constants.Drive.rampRate,
                                       Constants.System.CANTimeout);
        rightMaster_.configOpenloopRamp(Constants.Drive.rampRate,
                                        Constants.System.CANTimeout);
        rightPawn_.configOpenloopRamp(Constants.Drive.rampRate,
                                        Constants.System.CANTimeout);
*/

                                    }

    // PUBLIC INPUT OUTPUT
    public void setArcade(double turn, double speed){
        turn_ = turn;
        speed_ = speed;
        isTank = false;
    }
    public void setTank(double right, double left){
        rpower_ = right;
        lpower_ = left;
        isTank = true;
    }
    
    public double getRightVel(){
        return //rightMaster_.(sensorType, counts_per_rev)* 
               ((360 * Constants.Drive.wheelDiameter)/4096);
    }
    
    public double getLeftVel() {
        return //leftMaster_.getSelectedSensorVelocity(0) *
               ((360 * Constants.Drive.wheelDiameter)/4096);
    }

    public void toggle(){
        shift_ = (shift_ == Value.kForward) ? Value.kReverse : Value.kForward;
    }

    public void setGear(boolean high){
        shift_ = (high) ? Value.kForward : Value.kReverse;
    }

    public void setModifier(double mod){
        modifier_ = mod;
    }

    // SUBSYSTEM IMPL
    @Override public void start(){
        shift_ = Value.kForward;
        //shifter_.set(shift_);
    }

    @Override public void update(){
        if(isTank){
            drive_.tankDrive(modifier_ * lpower_, 
                             modifier_ * rpower_);
        } else {
            drive_.arcadeDrive(modifier_ * speed_,
                               modifier_ * turn_);
        }
        //if(shifter_.get() != shift_) { shifter_.set(shift_); }
    }

    @Override public void stop(){
        drive_.stopMotor();
        //shifter_.set(Value.kForward);
    }

    
    @Override public void log(){
        SmartDashboard.putNumber("Drive Left Vel", getLeftVel());
        SmartDashboard.putNumber("Drive Right Vel", getRightVel());
        SmartDashboard.putNumber("Drive Left Speed", leftMaster_.get());
        SmartDashboard.putNumber("Drive Right Speed", rightMaster_.get());
    }
    
}