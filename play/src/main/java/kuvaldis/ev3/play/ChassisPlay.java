package kuvaldis.ev3.play;

import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.utility.Delay;

public class ChassisPlay {
    public void run() {
        final Wheel wheelA = WheeledChassis.modelWheel(Motor.A, 43.2).offset(-72);
        final Wheel wheelB = WheeledChassis.modelWheel(Motor.B, 43.2).offset(72);
        final WheeledChassis chassis = new WheeledChassis(new Wheel[]{wheelA, wheelB}, WheeledChassis.TYPE_DIFFERENTIAL);
        // arc moving
        chassis.arc(100.0, 100.0);
        chassis.waitComplete();
        chassis.rotate(90);
        chassis.waitComplete();
        chassis.setVelocity(100.0, 0.0);
        Delay.msDelay(2000);
        chassis.stop();
    }
}
