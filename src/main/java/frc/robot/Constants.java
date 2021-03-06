package frc.robot;

public class Constants {

    public class System {
        public static final double dt = 0.02;
        public static final int CANTimeout = 100; //use for on the fly updates
    }

    public class Input {
        public static final int drive = 0;
        //public static final int driveLeft = 0;
        //public static final int driveRight = 1;
        public static final int operator = 1;//2;
    }

    public class Drive {
        // Motors
        public static final int leftMaster = 2;
        public static final int rightMaster = 2;
        public static final int leftPawn_ = 2;
        public static final int rightPawn_ = 2;
 
        // PIDs
        public static final double posP = 0;
        public static final double posI = 0;
        public static final double posD = 0;
        public static final double posF = 0;
        public static final double velP = 0;
        public static final double velI = 0;
        public static final double velD = 0;
        public static final double velF = 0;
        // Misc
        public static final double rampRate = 0.3;
        public static final int shift1 = 4;
        public static final int shift2 = 5;
        public static final double wheelDiameter = 6;
    }

    public class Hatch {
        public static final int rotator = 4; // Motor for rotating
        public static final int pop1 = 2;
        public static final int pop2 = 3;
        public static final int clamp1 = 0; // solenoid ports
        public static final int clamp2 = 1;
        public static final int zeroer = 2;
        public static final int grab = 0;
        public static final int potPort = 1;
        public static final int potRange = 36000;
        public static final int potOff = -34120; // Placeholder
        public static final double zeroSpeed = -0.1;
        public static final double armRatio = 1;
        //public static final double offset = -30;
        public static final double zeroAngle = 90 + 155;//247;

        // PDFVA
        public static final double kP = 0.008;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        public static final double kmP = 0;
        public static final double kmI = 0;
        public static final double kmD = 0;
        public static final double kF = -0.;
        public static final double kV = 0;
        public static final double kA = 0;
        // Motion Profile
        public static final double maxVel = 5;
        public static final double maxAcc = 5;
    }

    public class Cargo {
        public static final int intake = 5;
        public static final int rotator = 3;
        public static final int zeroer = 1;
        public static final double zeroSpeed = 0.25;
        public static final double armRatio = 0.285714;//(24/84);
        public static final double zeroAngle = 95;
        // PDFVA
        public static final double kP = .009;
        public static final double kI = 0.0001;
        public static final double kD = .000;
        public static final double kmP = 0;
        public static final double kmI = 0;
        public static final double kmD = 0;
        public static final double kF = .31;//.292;
        public static final double kV = 0;
        public static final double kA = 0;
        // Motion Profile
        public static final double maxVel = 5;
        public static final double maxAcc = 5;
    }

    public class Fourbar {
        public static final int leftLift = 9;
        public static final int rightLift = 10;
    }

    public class SuperStructure {
        public static final double cargoSafe = 140;
        public static final double hatchSafe = 200;
        public static final double cargoDefault = 90;
        public static final double hatchDefault = 180;
        public static final double cargoRetract = 200;
        public static final double hatchRetract = 220;
        public static final double warningPower = 180;
        public static final double driveModifier = .7;
    }
}