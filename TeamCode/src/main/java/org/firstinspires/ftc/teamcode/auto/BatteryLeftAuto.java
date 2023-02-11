package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;

//import org.firstinspires.ftc.robotcontroller.external.samples.SensorBNO055IMU;


@Autonomous(group = "Main")
public class BatteryLeftAuto extends LinearOpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor SlippyRight;
    DcMotor SlippyLeft;
    Servo Claw;


    private int Pos;
    private int height;

    @Override
    public void runOpMode() throws InterruptedException {
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

        SlippyLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SlippyRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        SlippyRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        Pos = 0;

        waitForStart();
        telemetry.update();
        //sees qr code 1 from left side of alliance (Right wheels lined up with right edge of square mat)
        height=0;
        Claw.setPosition(0.3);

      //  drive(228, 500);  //driving forward 5 inches = 228 //
        sleep(500);
        TurnCC(0.4,500); //test this
      //  StrafeRight(900,500); //strafing right 18 inches

        /*
        drive(2285, 400); //driving forward 50 inches
        TurnCC(0.4,500); //turning CC 45 degrees
        drive(274, 400); //driving forward 6 inches
        lift(1000,1); //test this!!!!!
        drive(46,200);
        Claw.setPosition(0.3); //fix claw position
        sleep(500);
        Claw.setPosition(0);
        drive(-46,200); //check negatives
        lift(0,-1); //dropping lift
        drive(-274,400); //driving 6 inches backward
        TurnCC(0.4,500); //turning CC 45 degrees
        StrafeLeft(.4,500); //strafe left 4.5 inches
        lift(700,1); //lift claw 7 inches
        Claw.setPosition(0.3);
        drive(2514,400); //driving forward 55 inches
        Claw.setPosition(0);
        lift(700,1); //lift cone from top of stack
        drive(-2742,300); //driving backwards
        TurnCC(0.4,1); //turning 120 degrees counterclockwise
        drive(594,300); //driving to large size pole (13 inches)
        lift(3000,1); //raise lift to level of large size pole
        Claw.setPosition(0.3);
        sleep(500);
        Claw.setPosition(0);
        lift(0,1);
        drive(-91,400); //drives backwards to park



//sees qr code number 2 from left side of alliance
        drive(228, 0.4); //driving forward 5 inches
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
*/}


    public void drivewithoutencoders(double power, long millis) {

        frontRight.setPower(power);
        backLeft.setPower(power);
        frontLeft.setPower(power);
        backRight.setPower(power);

        sleep(millis);

        frontRight.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);

    }


    public void TurnCC(double power, long millis) {
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        sleep(millis);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    private void driveCC (int Postarget, double speed) {
        Pos = Postarget;
        frontLeft.setTargetPosition(-Pos);
        frontRight.setTargetPosition(Pos);
        backRight.setTargetPosition(Pos);
        backLeft.setTargetPosition(-Pos);

        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setPower(speed);
        backLeft.setPower(-speed);
        frontLeft.setPower(-speed);
        backRight.setPower(speed);

        while (opModeIsActive() && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
            idle();
        }
    }
        private void driveC (int Postarget, double speed) {
            Pos = Postarget;
            frontLeft.setTargetPosition(Pos);
            frontRight.setTargetPosition(-Pos);
            backRight.setTargetPosition(-Pos);
            backLeft.setTargetPosition(Pos);

            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            frontRight.setPower(-speed);
            backLeft.setPower(speed);
            frontLeft.setPower(speed);
            backRight.setPower(-speed);

            while (opModeIsActive() && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
                idle();
            }
        }

            public void TurnC ( double power, long millis){
                frontLeft.setPower(power);
                frontRight.setPower(-power);
                backLeft.setPower(power);
                backRight.setPower(-power);

                sleep(millis);

                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }

        private void lift(int Postarget, double speed) { //find encoder values for different levels!!!!
        Pos = Postarget; //test negative sign
        SlippyRight.setTargetPosition(height);
        while (opModeIsActive() && SlippyRight.isBusy()) {
             //   idle();
                SlippyLeft.setPower(-speed);
            }
            SlippyRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            SlippyRight.setPower(speed);




        }



    private void drive (int Postarget, double speed){
        Pos = Postarget;
        frontLeft.setTargetPosition(Pos);
        frontRight.setTargetPosition(Pos);
        backRight.setTargetPosition(Pos);
        backLeft.setTargetPosition(Pos);

        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setPower(speed);
        backLeft.setPower(speed);
        frontLeft.setPower(speed);
        backRight.setPower(speed);

        while (opModeIsActive() && frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy()) {
            idle();
        }
    }

}