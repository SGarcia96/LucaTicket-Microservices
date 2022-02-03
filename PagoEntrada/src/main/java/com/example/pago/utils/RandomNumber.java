package com.example.pago.utils;

public class RandomNumber {

	public static int creaNumeroRandom() {
		int min = 1;
		int max = 10;

		int randomNumber = (int) (Math.random() * (max - min)) + min;
		
		return randomNumber;
	}
	
}
