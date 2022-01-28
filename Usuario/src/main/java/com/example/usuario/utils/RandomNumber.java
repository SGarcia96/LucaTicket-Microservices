package com.example.usuario.utils;

public class RandomNumber {

	public static int creaNumeroRandom() {
		int min = 1;
		int max = 10;
		int randomNumber = (int) (Math.random() * (max - min)) + min;
		
		return randomNumber;
	}
	
	public static float creaFloatRandom(float min, float max) {
		float randomNumber = (float) (Math.random() * (max - min)) + min;
		
		return Math.round((randomNumber*100)/100);
	}
}
