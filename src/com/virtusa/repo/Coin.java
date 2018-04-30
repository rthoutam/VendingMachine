package com.virtusa.repo;

public enum Coin {
	 NICKLE(5), DIME(10), QUARTER(25);
	private int denomination;

	private Coin(int denomination) {
		this.denomination = denomination;
	}

	public int getDenomination() {
		return denomination;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(getDenomination());
	}
	
	
	public static Coin resolveCoin(int size, int weight) throws Exception
	{
		//Use config or static value instaed 
		if(size == 5 && weight == 5)
			return Coin.NICKLE;
		else if(size == 10 && weight == 10)
			return Coin.DIME;
		else if (size==25 && weight ==25 )
			return Coin.QUARTER;
		
		throw new Exception("Change this to coin not supported Exception type");
	}
}
