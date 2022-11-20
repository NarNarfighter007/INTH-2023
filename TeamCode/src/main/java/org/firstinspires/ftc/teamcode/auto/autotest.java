package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(group = "Main")
public class autotest extends LinearOpMode {

    Servo Claw;
    double ClawPosition = 0.0;

    @Override
    public void runOpMode (){
// Defining motors
        double clawP = 0;
        Claw = hardwareMap.get(Servo.class, ("Claw"));
        telemetry.addData("Servo Position:", Claw.getPosition());
        telemetry.update();

        waitForStart();
        while (clawP <= 0.4) {
        telemetry.update();
        Claw.setPosition(clawP);
        clawP = clawP + 0.05;
        telemetry.addData("Position:", clawP);
        sleep(100);
        telemetry.update();
        }
        sleep(10000);
        telemetry.update();
        Claw.setPosition(0.01);
        telemetry.addData("Position:",0);
        telemetry.update();
        /*telemetry.addLine("Hello World");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        Claw.setPosition(0.5);
        sleep(5000);
        telemetry.addData("Servo Status", "Servo moving to 0.8");
        Claw.setPosition(0.8);
        telemetry.update();
        telemetry.addData("Servo Status", "Servo moving to 0.02");
        telemetry.update();
        sleep( 5000);
        Claw.setPosition(0.02); */
    }


    }