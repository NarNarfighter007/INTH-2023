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
    Servo Claw;
    OpenCvCamera Eye;


    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotor.class, ("frontLeft"));
        frontRight = hardwareMap.get(DcMotor.class, ("frontRight"));
        backRight = hardwareMap.get(DcMotor.class, ("backRight"));
        backLeft = hardwareMap.get(DcMotor.class, ("backLeft"));
        Claw = hardwareMap.get(Servo.class, ("Claw"));

        boolean changed = false; //Outside of loop()
    }
    boolean changed = false; //Outside of loop()
    @Override

    public void loop() {
        frontLeft.setPower((-0.5)*( gamepad1.left_stick_y + -gamepad1.left_stick_x + -gamepad1.right_stick_x));
        frontRight.setPower((-0.5)*(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x));
        backRight.setPower((0.5)*(-gamepad1.left_stick_y + gamepad1.left_stick_x + -gamepad1.right_stick_x));
        backLeft.setPower((0.5)*(gamepad1.left_stick_y + gamepad1.left_stick_x + -gamepad1.right_stick_x));

        //Claw.scaleRange(, maxPos);
    }




    }
