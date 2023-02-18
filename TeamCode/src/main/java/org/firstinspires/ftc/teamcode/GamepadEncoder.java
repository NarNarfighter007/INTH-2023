package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;


@TeleOp(group = "Main")

public class GamepadEncoder extends OpMode {

    BNO055IMU imu;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor SlippyRight;
    DcMotor SlippyLeft;
    Servo Claw;
    OpenCvCamera Eye;

    private int Pos;
    private int height;



    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotor.class, ("frontLeft"));
        frontRight = hardwareMap.get(DcMotor.class, ("frontRight"));
        backRight = hardwareMap.get(DcMotor.class, ("backRight"));
        backLeft = hardwareMap.get(DcMotor.class, ("backLeft"));
        SlippyRight = hardwareMap.get(DcMotor.class, ("SlippyRight"));
        SlippyLeft = hardwareMap.get(DcMotor.class, ("SlippyLeft"));
        Claw = hardwareMap.get(Servo.class, ("Claw"));

        telemetry.addData("Servo Position:", Claw.getPosition());
        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder backLeft:", backLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder frontRight:", frontRight.getCurrentPosition());
        telemetry.addData("Motor Encoder backRight:", backRight.getCurrentPosition());
        telemetry.addData("SlippyRight Encoder:", SlippyRight.getCurrentPosition());
        telemetry.addData("SlippyLeft Encoder:", SlippyLeft.getCurrentPosition());
        //       telemetry.addData("imu", imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES));


        //    SlippyRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //    SlippyLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlippyRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      /*  frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); */
    }

    boolean changed = false; //Outside of loop()

    private static final int LIFT_SPEED = -1;

    private int liftEncoderValue = 0;

    @Override

    public void loop() {


        frontLeft.setPower((0.35) * ((1.5 * gamepad1.left_stick_y) + -gamepad1.left_stick_x + -gamepad1.right_stick_x));
        frontRight.setPower((-0.35) * ((1.5 * gamepad1.left_stick_y) + gamepad1.left_stick_x + gamepad1.right_stick_x));
        backRight.setPower((0.35) * ((-1.5 * gamepad1.left_stick_y) + gamepad1.left_stick_x + -gamepad1.right_stick_x));
        backLeft.setPower((0.35) * ((1.5 * gamepad1.left_stick_y) + gamepad1.left_stick_x + -gamepad1.right_stick_x));

        telemetry.update();

        telemetry.addData("Servo Position:", Claw.getPosition());
        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder backLeft:", backLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder frontRight:", frontRight.getCurrentPosition());
        telemetry.addData("Motor Encoder backRight:", backRight.getCurrentPosition());
        telemetry.addData("SlippyRight Encoder:", SlippyRight.getCurrentPosition());
        telemetry.addData("SlippyLeft Encoder:", SlippyLeft.getCurrentPosition());


   /*     if(gamepad1.dpad_right && !changed) {
            if(Claw.getPosition() == 0.3) Claw.setPosition(0);
            else Claw.setPosition(0.3);
            changed = true;
        } else if(!gamepad1.dpad_right) changed = false; */

        //low level = slippyRight = 1460
        //medium level = slippyRight = 2416
        //top level = slippyRight = 3400

        if (gamepad2.a) { //low
            telemetry.addLine("a button pressed");
            lift(1460,1);
        }

        if (gamepad2.b) { //bring lift down
            telemetry.addLine("b button pressed");
            lift(0,-1);
        }

        if (gamepad2.y) { //high
            telemetry.addLine("y button pressed");
            lift(3400,1);
        }

        if (gamepad2.x) { //medium
            telemetry.addLine("x button pressed");
            lift(2416,1);
        }



//0.55 = open


        if (gamepad2.right_trigger > 0) {
                gamepad2.setLedColor(100, 100, 100, 100);
                gamepad2.rumbleBlips(1);
                Claw.setPosition(.55); //Closed
                telemetry.addData("Position:", Claw.getPosition());
            }
        else {
            Claw.setPosition(0);
            telemetry.addData("Position:", Claw.getPosition());
        }


    }
    private void lift(int Postarget, double speed) { //find encoder values for different levels!!!!
        SlippyRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        height = Postarget; //test negative sign
        SlippyRight.setTargetPosition(height);
        SlippyLeft.setTargetPosition(-height);
        SlippyRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SlippyLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SlippyRight.setPower(speed);
        SlippyLeft.setPower(-speed);
        if (SlippyRight.getCurrentPosition() == height || SlippyLeft.getCurrentPosition() == -height){
            SlippyLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            SlippyRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            SlippyRight.setPower(0);
            SlippyLeft.setPower(0);
        }
        //while (opModeIsActive() && SlippyRight.isBusy()) {
        // idle();
        // }
    }

}



