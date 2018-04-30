package com.virtusa.repo;

public enum Product {
	COLA("COLA", 1), CHIPS("CHIPS", 50), CANDY("CANDY", 65);
	private String name;
	private int price;

	private Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+"   "+this.price;
	}
	
	public static Product resolve(String name) throws Exception{
		if(Product.CANDY.name.equalsIgnoreCase(name))
			return Product.CANDY;
		else if(Product.COLA.name.equalsIgnoreCase(name))
			return Product.COLA;
		else if(Product.CHIPS.name.equalsIgnoreCase(name))
			return Product.CHIPS;
		
		throw new Exception("Wrong name");
		
	}
	
}
