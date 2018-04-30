package com.virtusa.vend;

import java.util.ArrayList;
import java.util.List;


import com.virtusa.repo.Coin;
import com.virtusa.repo.Product;
import com.virtusa.repo.VendingMachineRepository;
import com.virtusa.repo.VendingMachineRepositoryImpl;

public class VendingMachineService {

	public List<Coin> coinsInserted;
	
	//get Singleton instance of VendingMachinService
	public VendingMachineRepository vendingMachine;
	
	
	public VendingMachineService()
	{
		//get singleton here
		vendingMachine = new VendingMachineRepositoryImpl();
		reset();
	}
	
	public void reset(){
		coinsInserted = new ArrayList<Coin>();
	}
	
		
	public void insertCoin(int size, int weight) throws Exception
	{
		Coin c = Coin.resolveCoin(size, weight);
		coinsInserted.add(c);
	}
	
	
	public boolean hasEnoughbalanceToReturn(int f)
	{
		return vendingMachine.hasChange(f);
	}
		
	public Bucket<Product, List<Coin>> purchaseProduct(Product product) throws Exception
	{
		//Get the amount in
		int totalAmount = getTotalAmountInserted();
		int prodPrice = product.getPrice();
		int returnAmount = totalAmount - prodPrice;
		
				
		//check availability and buy product
		if(!vendingMachine.hasItem(product))
			throw new Exception("Product not Available");
		
		
		if(totalAmount < prodPrice){
			// throw exception not enough money
			throw new Exception("not enough money inserted");
		}
		
		//Check enough balance to give
		if(!vendingMachine.hasChange(returnAmount)){
			throw new NotSufficientChangeException("cant return change") ;
		}
		
		//Buy product
		Bucket<Product, List<Coin>> returnBucket = vendingMachine.collectProductAndChange(product, totalAmount);
		
		//Return change
        //Product p = returnBucket.getFirst();
        //List<Coin> retrCoins = returnBucket.getSecond();
		//despence change
		return returnBucket;
	}
	
	public int getTotalAmountInserted()
	{
		int total= 0;
		for(Coin c:coinsInserted){
			total = total+ c.getDenomination();
		}
//		Math.round(double*100.0)/100.0
		return total;
	}
	
	public List<Product> getAllProduct(){
		List<Product> l = new ArrayList<Product>();
		l.add(Product.CANDY);		
		l.add(Product.CHIPS);
		l.add(Product.COLA);
		return l;
	}
	
}
