package kuvaldis.ev3.play;

import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class TouchPlay {
    public void run() {
        final EV3 ev3 = (EV3) BrickFinder.getLocal();
        final TextLCD lcd = ev3.getTextLCD();
        final Keys keys = ev3.getKeys();

//        lcd.drawString("Hello EV3", 2, 4);
//        lcd.drawString("by Kuvaldis", 2, 5);
//        keys.waitForAnyPress();
//        lcd.scroll();
//        keys.waitForAnyPress();
//        lcd.clear();

//        final GraphicsLCD g = ev3.getGraphicsLCD();
//        g.drawArc(50, 50, 100, 100, 90, 180);
//        keys.waitForAnyPress();

        final Port ps1 = ev3.getPort("S1");
        final EV3TouchSensor touchSensor = new EV3TouchSensor(ps1);
        lcd.drawString("Available modes: ", 2, 4);
        for (String mode : touchSensor.getAvailableModes()) {
            Delay.msDelay(1000);
            lcd.scroll();
            lcd.drawString(mode, 2, 4);
        }

        keys.waitForAnyPress();
        lcd.clear();
        final SampleProvider touchMode = touchSensor.getTouchMode();
        lcd.scroll();
        lcd.drawString("Sample size: " + touchSensor.sampleSize(), 2, 4);
        final float[] sample = new float[touchMode.sampleSize()];
        keys.waitForAnyPress();
        lcd.clear();
        final Key escape = ev3.getKey("Escape");
        lcd.drawString("Sample: 0.0", 2, 4);
        float value = 0.0f;
        while (!escape.isDown()) {
            touchMode.fetchSample(sample, 0);
            // for touch sensor only first cell matters
            if (sample[0] != value) {
                value = sample[0];
                // have no clue why ant sshexec doesn't capture stdout
                System.err.println("New sample value: " + value);
                lcd.clear(10, 4, 3);
                lcd.drawString(String.valueOf(value), 10, 4);
            }
            Delay.msDelay(50);
        }
        touchSensor.close();
    }
}
