package com.example.dns.calculator;

public class DivideNullException extends Exception{
    private double number;

    public double getNumber() {
        return number;
    }

    public DivideNullException(String message, double number)
    {
        super(message);
        this.number = number;
    }
}