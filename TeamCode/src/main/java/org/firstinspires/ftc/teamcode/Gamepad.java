package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;

@TeleOp(group = "Main")

public class Gamepad extends OpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor Slippy;
    Servo Claw;
    OpenCvCamera Eye;


    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotor.class, ("frontLeft"));
        frontRight = hardwareMap.get(DcMotor.class, ("frontRight"));
        backRight = hardwareMap.get(DcMotor.class, ("backRight"));
        backLeft = hardwareMap.get(DcMotor.class, ("backLeft"));
        Slippy = hardwareMap.get(DcMotor.class, ("Slippy"));
        Claw = hardwareMap.get(Servo.class, ("Claw"));

        telemetry.addData("Servo Position:", Claw.getPosition());
        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder backLeft:", backLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder frontRight:", frontRight.getCurrentPosition());
        telemetry.addData("Motor Encoder backRight:", backRight.getCurrentPosition());
        telemetry.addData("Slippy Encoder:", Slippy.getCurrentPosition());

        Slippy.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      /*  frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); */
    }

    boolean changed = false; //Outside of loop()

    @Override

    public void loop() {
        frontLeft.setPower((0.5) * (gamepad1.left_stick_y + -gamepad1.left_stick_x + -gamepad1.right_stick_x));
        frontRight.setPower((-0.5) * (gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x));
        backRight.setPower((0.5) * (-gamepad1.left_stick_y + gamepad1.left_stick_x + -gamepad1.right_stick_x));
        backLeft.setPower((0.5) * (gamepad1.left_stick_y + gamepad1.left_stick_x + -gamepad1.right_stick_x));

        telemetry.update();

        telemetry.addData("Servo Position:", Claw.getPosition());
        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder backLeft:", backLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder frontRight:", frontRight.getCurrentPosition());
        telemetry.addData("Motor Encoder backRight:", backRight.getCurrentPosition());
        telemetry.addData("Slippy Encoder:", Slippy.getCurrentPosition());


   /*     if(gamepad1.dpad_right && !changed) {
            if(Claw.getPosition() == 0.3) Claw.setPosition(0);
            else Claw.setPosition(0.3);
            changed = true;
        } else if(!gamepad1.dpad_right) changed = false; */


        if (gamepad1.dpad_up) {
            Slippy.setPower(-1);
        }

        Slippy.setPower(0);

        if (gamepad1.dpad_down) {
            Slippy.setPower(0.5);
        }

        Slippy.setPower(0);

        if (gamepad1.dpad_right) {
            Claw.setPosition(0.535); //Open;
            telemetry.addData("Position:", Claw.getPosition());
        } else {
            Claw.setPosition(0.45); //Closed
            telemetry.addData("Position:", Claw.getPosition());
        }

        }
    }






