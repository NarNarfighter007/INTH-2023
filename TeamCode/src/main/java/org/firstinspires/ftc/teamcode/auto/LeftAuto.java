package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//import org.firstinspires.ftc.robotcontroller.external.samples.SensorBNO055IMU;


@Autonomous(group = "Main")
public class LeftAuto extends LinearOpModeTest {

    private int Pos;
    private int height;

    public final static double cycle = 537.4; //encoder value = 11.87 inches

    @Override
    public void runOpMode() {
// Defining motors
        frontLeft = hardwareMap.get(DcMotor.class, ("frontLeft"));
        frontRight = hardwareMap.get(DcMotor.class, ("frontRight"));
        backRight = hardwareMap.get(DcMotor.class, ("backRight"));
        backLeft = hardwareMap.get(DcMotor.class, ("backLeft"));
        SlippyLeft = hardwareMap.get(DcMotor.class, ("SlippyLeft"));
        SlippyRight = hardwareMap.get(DcMotor.class, ("SlippyRight"));
        Claw = hardwareMap.get(Servo.class, ("Claw"));

        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());


        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlippyRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile = "BNO055IMUCalibration.json";
            parameters.loggingEnabled = true;
            parameters.loggingTag = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

            imu = hardwareMap.get(BNO055IMU.class, "imu");
            imu.initialize(parameters);



        Pos = 0;
        height=0;
        Claw.setPosition(0.3);

        waitForStart();
        telemetry.update();
        //sees qr code 1 from left side of alliance (left wheels lined up with edge of square mat)
        resetEncoders();
        drive(1000, 0.4);  //driving forward 5 inches = 228
        stopDriving();
        resetEncoders();
        sleep(500);
        TurnC(.5,1000); //turning clockwise 90 degrees !! TEST THIS
     //   resetEncoders();
        /*
        drive(1371, 0.4); //driving forward 20 inches
        stopDriving();
        resetEncoders();
        TurnCC(0.4,1000); //turning CC 90 degrees
        drive(2285, 0.4); //driving forward 50 inches
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning CC 45 degrees
        drive(274, 0.4); //driving forward 6 inches
        stopDriving();
        lift(1000,1); //test this!!!!!
        resetEncoders();
        drive(46,0.2);
        stopDriving();
        resetEncoders();
        Claw.setPosition(0.3); //fix claw position
        sleep(500);
        Claw.setPosition(0);
        drive(-46,-0.2); //check negatives
        stopDriving();
        resetEncoders();
        lift(0,-1); //dropping lift
        resetEncoders();
        drive(-274,-0.4); //driving 6 inches backward
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning CC 45 degrees
        StrafeLeft(.4,500); //strafe left 4.5 inches
        lift(700,1); //lift claw 7 inches
        Claw.setPosition(0.3);
        drive(2514,0.4); //driving forward 55 inches
        stopDriving();
        Claw.setPosition(0);
        lift(700,1); //lift cone from top of stack
        resetEncoders();
        drive(-2742,-0.3); //driving backwards
        stopDriving();
        resetEncoders();
        TurnCC(0.4,1); //turning 120 degrees counterclockwise
        drive(594,.3); //driving to large size pole (13 inches)
        stopDriving();
        resetEncoders();
        lift(3000,1); //raise lift to level of large size pole
        Claw.setPosition(0.3);
        sleep(500);
        Claw.setPosition(0);
        lift(0,1);
        drive(-91,-0.4); //drives backwards to park
        stopDriving();
        resetEncoders();


//sees qr code number 2 from left side of alliance
      /*  drive(228, 0.4); //driving forward 5 inches
        stopDriving();
        TurnC(0.4,1000); //turning clockwise 90 degrees !! TEST THIS
        resetEncoders();
        drive(914, 0.4); //driving forward 20 inches
        stopDriving();
        resetEncoders();
        TurnCC(0.4,1000); //turning CC 90 degrees
        drive(2285, 0.4); //driving forward 50 inches
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning CC 45 degrees
        drive(274, 0.4); //driving forward 6 inches
        stopDriving();
        lift(1000,1); //test this!!!!!
        resetEncoders();
        drive(46,0.2);
        stopDriving();
        resetEncoders();
        Claw.setPosition(0.3); //fix claw position
        sleep(500);
        Claw.setPosition(0);
        drive(-46,-0.2); //check negatives
        stopDriving();
        resetEncoders();
        lift(0,-1); //dropping lift
        resetEncoders();
        drive(-274,-0.4); //driving 6 inches backward
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning CC 45 degrees
        StrafeLeft(.4,500); //strafe left 4.5 inches
        lift(700,1); //lift claw 7 inches
        Claw.setPosition(0.3);
        drive(2514,0.4); //driving forward 55 inches
        stopDriving();
        Claw.setPosition(0);
        lift(700,1); //lift cone from top of stack
        resetEncoders();
        drive(-2742,-0.3); //driving backwards
        stopDriving();
        resetEncoders();
        TurnCC(0.4,1000); //turning 120 degrees counterclockwise
        drive(594,.3); //driving to large size pole (13 inches)
        stopDriving();
        resetEncoders();
        lift(3000,1); //raise lift to level of large size pole
        Claw.setPosition(0.3);
        sleep(500);
        Claw.setPosition(0);
        lift(0,1);
        drive(-91,-0.4); //drives backwards to park
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning 60 degrees
        drive(-1371, 0.4);
        stopDriving();
        resetEncoders();
*/

        //sees qr code number 3 from left side of alliance
        /* drive(228, 0.4); //driving forward 5 inches
        stopDriving();
        TurnC(0.4,1000); //turning clockwise 90 degrees !! TEST THIS
        resetEncoders();
        drive(914, 0.4); //driving forward 20 inches
        stopDriving();
        resetEncoders();
        TurnCC(0.4,1000); //turning CC 90 degrees
        drive(2285, 0.4); //driving forward 50 inches
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning CC 45 degrees
        drive(274, 0.4); //driving forward 6 inches
        stopDriving();
        lift(1000,1); //test this!!!!!
        resetEncoders();
        drive(46,0.2);
        stopDriving();
        resetEncoders();
        Claw.setPosition(0.3); //fix claw position
        sleep(500);
        Claw.setPosition(0);
        drive(-46,-0.2); //check negatives
        stopDriving();
        resetEncoders();
        lift(0,-1); //dropping lift
        resetEncoders();
        drive(-274,-0.4); //driving 6 inches backward
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning CC 45 degrees
        StrafeLeft(.4,500); //strafe left 4.5 inches
        lift(700,1); //lift claw 7 inches
        Claw.setPosition(0.3);
        drive(2514,0.4); //driving forward 55 inches
        stopDriving();
        Claw.setPosition(0);
        lift(700,1); //lift cone from top of stack
        resetEncoders();
        drive(-2742,-0.3); //driving backwards
        stopDriving();
        resetEncoders();
        TurnCC(0.4,1000); //turning 120 degrees counterclockwise
        drive(594,.3); //driving to large size pole (13 inches)
        stopDriving();
        resetEncoders();
        lift(3000,1); //raise lift to level of large size pole
        Claw.setPosition(0.3);
        sleep(500);
        Claw.setPosition(0);
        lift(0,1);
        drive(-91,-0.4); //drives backwards to park
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning 60 degrees
        drive(-3108, -0.4);
        stopDriving();
        resetEncoders();
*/



    }


    private void drive(int Postarget, double speed) {

        //backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        resetEncoders();

        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        Pos = Postarget;
        frontLeft.setTargetPosition(Pos);
        frontRight.setTargetPosition(Pos);
        backRight.setTargetPosition(Pos);
        backLeft.setTargetPosition(Pos);

        telemetry.addData("currentPos", frontLeft.getCurrentPosition());
        telemetry.addData("target", frontLeft.getTargetPosition());
        telemetry.update();
        sleep(3000);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setPower(speed);
        backLeft.setPower(speed);
        frontLeft.setPower(speed);
        backRight.setPower(speed);

        resetEncoders();

       while (opModeIsActive() && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
           idle();
        }
    }

    private void stopDriving() {
        resetEncoders();
        frontRight.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        SlippyLeft.setPower(0);
        SlippyRight.setPower(0);
        resetEncoders();
    }


    /* public void TurnCC(double power, long millis) {
        resetEncoders();
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        sleep(millis);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        resetEncoders();
    }
    */

    public void TurnC(double power, long millis) {
        resetEncoders();
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);

        sleep(millis);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        resetEncoders();

    }
    public void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Pos = 0;
    }
    /*private void lift(int Postarget, double speed) { //find encoder values for different levels!!!!
        Pos = Postarget; //test negative sign
        Slippy.setTargetPosition(height);
        Slippy.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Slippy.setPower(speed);


        while (opModeIsActive() && Slippy.isBusy()) {
            idle();
        }


    }

     */
    public void StrafeRight(double power, long millis){
        resetEncoders();
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        sleep(millis);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        resetEncoders();
    }

    public void StrafeLeft(double power, long millis) {
        resetEncoders();
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(-power);

        sleep(millis);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        resetEncoders();
    }

    public void setMotorPower(double fL, double fR, double bL, double bR){
        frontLeft.setPower(fL);
        frontRight.setPower(fR);
        backLeft.setPower(bL);
        backRight.setPower(bR);
    }
/*
    public void TurnCC(double turnAngle, long millis) { //turnCC=turnleft
        sleep(500);
        double firstAngle;
        Object angles = firstAngle;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, BNO055IMU.AngleUnit.DEGREES);
        doublespeed = .5;
        doubleoldDegreesLeft = turnAngle;
        doublescaledSpeed = speed;
        doubletargetHeading = angles.firstAngle + turnAngle;
        doubleoldAngle = angles.firstAngle;
        if (targetHeading < -180) {
            targetHeading += 360;
        }
        if (targetHeading > 180) {
            targetHeading -= 360;
        }
        doubledegreesLeft = ((int) (Math.signum(angles.firstAngle - targetHeading) + 1) / 2)
        (360 - Math.abs(angles.firstAngle - targetHeading)) + (int) (Math.signum(targetHeading - angles.firstAngle) + 1) / 2
        Math.abs(angles.firstAngle - targetHeading);
        runtime.reset();
        while (opModeIsActive() &&
                runtime.seconds() < timeoutS &&
                degreesLeft > 1 &&
                oldDegreesLeft - degreesLeft >= 0) { //check to see if we overshot target
            scaledSpeed = degreesLeft / (100 + degreesLeft) speed;
            if (scaledSpeed > 1) {
                scaledSpeed = .1;
            }
            robot.leftBack.setPower(scaledSpeed1 .3); //extra power to back wheels
            robot.rightBack.setPower(-1scaledSpeed1 .3); //due to extra weight
            robot.leftFront.setPower(scaledSpeed);
            robot.rightFront.setPower(-1scaledSpeed);
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            oldDegreesLeft = degreesLeft;
            degreesLeft = ((int) (Math.signum(angles.firstAngle - targetHeading) + 1) / 2)
            (360 - Math.abs(angles.firstAngle - targetHeading)) + (int) (Math.signum(targetHeading - angles.firstAngle) + 1) / 2
            Math.abs(angles.firstAngle - targetHeading);
            if (Math.abs(angles.firstAngle - oldAngle) < 1) {
                speed = 1.1;
            }. //bump up speed to wheels in case robot stalls before reaching target
            oldAngle = angles.firstAngle;
        }
        stopDriving(); //our helper method to set all wheel motors to zero
        sleep(250); //small pause at end of turn
    }

*/
}