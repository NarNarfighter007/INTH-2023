package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(group = "Main")
public class autotest extends LinearOpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    Servo Claw;
    OpenCvCamera Eye;

    private int Pos;

    public final static double cycle = 537.4;
    @Override
    public void runOpMode () {
// Defining motors
        frontLeft = hardwareMap.get(DcMotor.class, ("frontLeft"));
        frontRight = hardwareMap.get(DcMotor.class, ("frontRight"));
        backRight = hardwareMap.get(DcMotor.class, ("backRight"));
        backLeft = hardwareMap.get(DcMotor.class, ("backLeft"));
        Claw = hardwareMap.get(Servo.class, ("Claw"));

        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());


        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Pos = 0;

        waitForStart();
        telemetry.update();
        drive(537,0.4);
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



    private void stopDriving () {
        frontRight.setPower(0);
        backLeft.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
    }

    }