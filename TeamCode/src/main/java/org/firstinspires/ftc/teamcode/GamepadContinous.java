package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;

@TeleOp(group = "Main")

public class GamepadContinous extends OpMode {

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
        telemetry.addData("Right Trigger", gamepad2.right_trigger);
        telemetry.addData("Left Trigger", gamepad2.left_trigger);
        //       telemetry.addData("imu", imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES));


        //    SlippyRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //    SlippyLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlippyRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        SlippyLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      /*  frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); */

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    boolean changed = false; //Outside of loop()


    @Override

    public void loop() {


       /* frontLeft.setPower((0.35) * ((1.5 * gamepad1.left_stick_y) + -gamepad1.left_stick_x + -gamepad1.right_stick_x));
        frontRight.setPower((-0.35) * ((1.5 * gamepad1.left_stick_y) + gamepad1.left_stick_x + gamepad1.right_stick_x));
        backRight.setPower((0.35) * ((-1.5 * gamepad1.left_stick_y) + gamepad1.left_stick_x + -gamepad1.right_stick_x));
        backLeft.setPower((0.35) * ((1.5 * gamepad1.left_stick_y) + gamepad1.left_stick_x + -gamepad1.right_stick_x)); */

        double powerFactor = 0.5; // Set the motor power factor
        double accelerationFactor = 3; // Set the acceleration factor (2 for a quadratic curve)

// Apply the acceleration curve to the stick input values
        double leftStickY = Math.pow(gamepad2.left_stick_y, accelerationFactor);
        double leftStickX = Math.pow(gamepad2.left_stick_x, accelerationFactor);
        double rightStickX = Math.pow(gamepad2.right_stick_x, accelerationFactor);

// Calculate the motor powers using the modified stick input values
        frontLeft.setPower(powerFactor * ((leftStickY) + -leftStickX + -rightStickX));
        frontRight.setPower(-powerFactor * ((leftStickY) + leftStickX + rightStickX));
        backRight.setPower(powerFactor * ((-leftStickY) + leftStickX + -rightStickX));
        backLeft.setPower(powerFactor * ((leftStickY) + leftStickX + -rightStickX));

        telemetry.update();

        telemetry.addData("Servo Position:", Claw.getPosition());
        telemetry.addData("Motor Encoder frontLeft:", frontLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder backLeft:", backLeft.getCurrentPosition());
        telemetry.addData("Motor Encoder frontRight:", frontRight.getCurrentPosition());
        telemetry.addData("Motor Encoder backRight:", backRight.getCurrentPosition());
        telemetry.addData("SlippyRight Encoder:", SlippyRight.getCurrentPosition());
        telemetry.addData("SlippyLeft Encoder:", SlippyLeft.getCurrentPosition());
        telemetry.addData("Right Trigger", gamepad2.right_trigger);
        telemetry.addData("Left Trigger", gamepad2.left_trigger);


   /*     if(gamepad1.dpad_right && !changed) {
            if(Claw.getPosition() == 0.3) Claw.setPosition(0);
            else Claw.setPosition(0.3);
            changed = true;
        } else if(!gamepad1.dpad_right) changed = false; */

        //low level = slippyRight = 1460
        //medium level = slippyRight = 2416
        //top level = slippyRight = 3400

 /*       if (gamepad2.a) { //low
            telemetry.addLine("a button pressed");
            lift(1460,1);


        }

        if (gamepad2.b) { //bring lift down
            telemetry.addLine("b button pressed");
            lift(0,-1);


        }

  */



           /* if (SlippyLeft.getCurrentPosition() > 0) {
                SlippyRight.setPower(-1);
                SlippyLeft.setPower(1);
            } else {
                SlippyRight.setPower(0);
                SlippyLeft.setPower(0);

            */
        //}


//        if ((SlippyLeft.getCurrentPosition() > -3582 && SlippyRight.getCurrentPosition() < 50) || (SlippyRight.getCurrentPosition() < 3582 && SlippyRight.getCurrentPosition() > -50)) {

   /*     if (gamepad2.left_trigger > 0.1 && (SlippyLeft.getCurrentPosition() > -2000 && SlippyLeft.getCurrentPosition() < 50) || (SlippyRight.getCurrentPosition() < 2000 && SlippyRight.getCurrentPosition() > -50)) { //lift down       ETHAN SAY THIS NEEDS TO BE ELSE IF CAUSE POWER IS SET TO 0 IF EITHER TRIGGER IS 0
            telemetry.addLine("lift down");
            SlippyRight.setPower(-gamepad2.left_trigger);
            SlippyLeft.setPower(gamepad2.left_trigger);
            //          }
        } else if (gamepad2.right_trigger > 0.1 && (SlippyLeft.getCurrentPosition() > -2000 && SlippyLeft.getCurrentPosition() < 50) || (SlippyRight.getCurrentPosition() < 2000 && SlippyRight.getCurrentPosition() > -50)) {   //lift up
            telemetry.addLine("lift up");
            SlippyRight.setPower(gamepad2.right_trigger);
            SlippyLeft.setPower(-gamepad2.right_trigger);
        } else if (gamepad2.left_trigger > 0.1 && (SlippyLeft.getCurrentPosition() < -2000 || SlippyRight.getCurrentPosition() > 2000)) { //lift down only should be allowed
            telemetry.addLine("lift down only");
            SlippyRight.setPower(-gamepad2.left_trigger);
            SlippyLeft.setPower(gamepad2.left_trigger);
        } else if (gamepad2.right_trigger > 0.1 && (SlippyLeft.getCurrentPosition() > 50 || SlippyRight.getCurrentPosition() < -50)) { //lift up only should be allowed
            telemetry.addLine("lift up only");
            SlippyRight.setPower(gamepad2.right_trigger);
            SlippyLeft.setPower(-gamepad2.right_trigger);
        } else  {
            SlippyRight.setPower(0);
            SlippyLeft.setPower(0);
        }
     */
      /*  if (SlippyLeft.getCurrentPosition() > -3370 || SlippyRight.getCurrentPosition() > 3370) {
            if (gamepad2.left_trigger > 0) { //lift down
                telemetry.addLine("lift down");
                SlippyRight.setPower(-gamepad2.left_trigger + 0.2);
                SlippyLeft.setPower(gamepad2.left_trigger - 0.2);
            }
        } */

//0.55 = open
      if (SlippyRight.getCurrentPosition() <= 3400 && SlippyRight.getCurrentPosition() >= -25){
          SlippyRight.setPower(gamepad1.right_trigger-(0.8*gamepad1.left_trigger));
          SlippyLeft.setPower(-(gamepad1.right_trigger-(0.8*gamepad1.left_trigger)));
      } else if (SlippyRight.getCurrentPosition() > 3400){
          SlippyRight.setPower(-0.8*gamepad1.left_trigger);
          SlippyLeft.setPower(0.8*gamepad1.left_trigger);
      } else if (SlippyRight.getCurrentPosition() < -25){
          SlippyRight.setPower(gamepad1.right_trigger);
          SlippyLeft.setPower(-gamepad1.right_trigger);
      }

            if (gamepad2.right_bumper) {
                gamepad2.setLedColor(100, 100, 100, 100);
                Claw.setPosition(.2); //Open;
                telemetry.addData("Position:", Claw.getPosition());
                gamepad2.rumbleBlips(1);
                gamepad1.rumbleBlips(1);
            } else {
                Claw.setPosition(0); //Closed;
            }
            if (gamepad1.share && gamepad1.options) {
                telemetry.addLine("KILL SWITCH ACTIVE");
                System.exit(0); // Terminate the program
            }
            if (gamepad2.share && gamepad2.options) {
                telemetry.addLine("KILL SWITCH ACTIVE");
                System.exit(0); // Terminate the program
            }
        /*else if (gamepad2.right_trigger > 0) {
                gamepad2.setLedColor(100, 100, 100, 100);
                gamepad2.rumbleBlips(1);
                Claw.setPosition(0); //Closed
                telemetry.addData("Position:", Claw.getPosition());
            }*/


        }
 /*   private void lift(int Postarget, double speed) { //find encoder values for different levels!!!!
        Pos = Postarget;
        SlippyRight.setTargetPosition(height);
        SlippyRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SlippyRight.setPower(speed);


        while (SlippyRight.isBusy()) {
            SlippyLeft.setPower(-speed);
        }
*/



    }

