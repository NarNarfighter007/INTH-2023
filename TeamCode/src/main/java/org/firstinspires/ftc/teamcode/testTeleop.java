package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group = "Main")

public class testTeleop extends OpMode {

    Servo Claw;


    @Override
    public void init() {


        Claw = hardwareMap.get(Servo.class, ("Claw"));
    }
    boolean changed = false; //Outside of loop()
    @Override

    public void loop() {


        if(gamepad1.dpad_right && !changed) {
            if(Claw.getPosition() == 0.25) Claw.setPosition(0);
            else Claw.setPosition(0.25);
            changed = true;
        } else if(!gamepad1.dpad_right) changed = false;

    }
}