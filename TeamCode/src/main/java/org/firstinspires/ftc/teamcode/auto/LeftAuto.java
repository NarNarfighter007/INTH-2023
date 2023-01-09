package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(group = "Main")
public class LeftAuto extends LinearOpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor Slippy;
    Servo Claw;
    OpenCvCamera Eye;

    private int Pos;
    private int height;

    public final static double cycle = 537.4;

    @Override
    public void runOpMode() {
// Defining motors
        frontLeft = hardwareMap.get(DcMotor.class, ("frontLeft"));
        frontRight = hardwareMap.get(DcMotor.class, ("frontRight"));
        backRight = hardwareMap.get(DcMotor.class, ("backRight"));
        backLeft = hardwareMap.get(DcMotor.class, ("backLeft"));
        Slippy = hardwareMap.get(DcMotor.class, ("Slippy"));
        Claw = hardwareMap.get(Servo.class, ("Claw"));

        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());


        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Slippy.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Pos = 0;
        height=0;

        waitForStart();
        telemetry.update();
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
        drive(-274,0.4); //driving 6 inches backward
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
        drive(-2514,0.3);
        stopDriving();
        resetEncoders();
        TurnCC(0.4,500); //turning 45 degrees counterclockwise
        drive(457,.3); //driving to medium size pole (10 inches)
        stopDriving();
        resetEncoders();
        lift(800,1); //raise lift to level of medium size pole
        Claw.setPosition(0.3);
        sleep(500);
        Claw.setPosition(0);
        lift(0,1);
        drive(91,0.4);
        stopDriving();
        resetEncoders();








    }


    private void drive(int Postarget, double speed) {
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

    private void stopDriving() {
        frontRight.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        Slippy.setPower(0);
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

    public void TurnC(double power, long millis) {
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
    public void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Pos = 0;
    }
    private void lift(int Postarget, double speed) { //find encoder values for different levels!!!!
        Pos = Postarget; //test negative sign
        Slippy.setTargetPosition(height);
        Slippy.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Slippy.setPower(speed);


        while (opModeIsActive() && Slippy.isBusy()) {
            idle();
        }
    }
    public void StrafeRight(double power, long millis){
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        sleep(millis);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }
    public void StrafeLeft(double power, long millis){
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(-power);

        sleep(millis);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }

}