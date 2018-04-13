package com.tinymesh.vicinity.adapter.model;

public class DigitalIO {
    private int gpio_0;
    private int gpio_1;
    private int gpio_2;
    private int gpio_3;
    private int gpio_4;
    private int gpio_5; // digital IO state of GPIO 5; our movement sensor
    private int gpio_6;
    private int gpio_7;

    public int getGpio_0() {
        return gpio_0;
    }

    public void setGpio_0(int gpio_0) {
        this.gpio_0 = gpio_0;
    }

    public int getGpio_1() {
        return gpio_1;
    }

    public void setGpio_1(int gpio_1) {
        this.gpio_1 = gpio_1;
    }

    public int getGpio_2() {
        return gpio_2;
    }

    public void setGpio_2(int gpio_2) {
        this.gpio_2 = gpio_2;
    }

    public int getGpio_3() {
        return gpio_3;
    }

    public void setGpio_3(int gpio_3) {
        this.gpio_3 = gpio_3;
    }

    public int getGpio_4() {
        return gpio_4;
    }

    public void setGpio_4(int gpio_4) {
        this.gpio_4 = gpio_4;
    }

    public int getGpio_5() {
        return gpio_5;
    }

    public void setGpio_5(int gpio_5) {
        this.gpio_5 = gpio_5;
    }

    public int getGpio_6() {
        return gpio_6;
    }

    public void setGpio_6(int gpio_6) {
        this.gpio_6 = gpio_6;
    }

    public int getGpio_7() {
        return gpio_7;
    }

    public void setGpio_7(int gpio_7) {
        this.gpio_7 = gpio_7;
    }

    @Override
    public String toString() {
        return "DigitalIO{" +
                "gpio_0=" + gpio_0 +
                ", gpio_1=" + gpio_1 +
                ", gpio_2=" + gpio_2 +
                ", gpio_3=" + gpio_3 +
                ", gpio_4=" + gpio_4 +
                ", gpio_5=" + gpio_5 +
                ", gpio_6=" + gpio_6 +
                ", gpio_7=" + gpio_7 +
                '}';
    }
}
