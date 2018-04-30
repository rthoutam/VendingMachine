package com.virtusa.vend;

public enum Product {
	COLA("COLA", 1), CHIPS("CHIPS", 0.50f), CANDY("CANDY", 0.65f);
	private String name;
	private float price;

	private Product(String name, float price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+"   "+this.price;
	}
}
