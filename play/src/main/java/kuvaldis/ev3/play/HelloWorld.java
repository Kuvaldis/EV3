package kuvaldis.ev3.play;

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;

public class HelloWorld {
    public static void main(String[] args) {
        final EV3 ev3 = (EV3) BrickFinder.getLocal();
        final TextLCD lcd = ev3.getTextLCD();
        final Keys keys = ev3.getKeys();

        lcd.drawString("Hello EV3", 2, 4);
        lcd.drawString("by Kuvaldis", 2, 5);
        keys.waitForAnyPress();
    }
}
