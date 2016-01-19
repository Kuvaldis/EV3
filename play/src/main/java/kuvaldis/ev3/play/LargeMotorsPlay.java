package kuvaldis.ev3.play;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class LargeMotorsPlay {
    public void run() {
        final EV3LargeRegulatedMotor motor = new EV3LargeRegulatedMotor(MotorPort.A);
        motor.forward();
        Delay.msDelay(1000);
        motor.stop(true);
        motor.setSpeed(100);
        motor.forward();
        for (int i = 2; i < 5; i++) {
            motor.setSpeed(i * 100);
            Delay.msDelay(1000);
        }
        motor.backward();
        Delay.msDelay(2000);
        motor.forward();
        Delay.msDelay(1000);
        motor.flt(true);
        Delay.msDelay(1000);
        motor.close();
    }
}
