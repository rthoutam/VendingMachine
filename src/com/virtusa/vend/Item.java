package com.virtusa.vend;

public enum Item {
	COLA("COLA", 100), CHIPS("CHIPS", 50), CANDY("CANDY", 65);
	private String name;
	private int price;

	private Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
}
