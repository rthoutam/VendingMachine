package com.virtusa.repo;

import java.util.List;

import com.virtusa.vend.Bucket;
import com.virtusa.vend.NotSufficientChangeException;

public interface VendingMachineRepository {
	public int getProductPrice(Product product) throws Exception;

	public void insertCoin(Coin coin);
	
	public void insertCoins(List<Coin> coins);

	public List<Coin> returnCoins(int f) throws NotSufficientChangeException;

	public Bucket<Product, List<Coin>> collectProductAndChange(Product prod, int amountInBasket) throws Exception;

	public  Product collectItem(Product prod) throws Exception;
	
	public boolean hasItem(Product product);  
	
	public boolean hasChange(int f);
	
	
}
