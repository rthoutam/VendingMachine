package com.virtusa.repo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.virtusa.vend.Bucket;
import com.virtusa.vend.NotSufficientChangeException;
import com.virtusa.vend.SoldOutException;

public class VendingMachineRepositoryImpl implements VendingMachineRepository {
	private Inventory<Coin> cashInventory = new Inventory<Coin>();
	private Inventory<Product> itemInventory = new Inventory<Product>();

	public VendingMachineRepositoryImpl() {
		initialize();
	}

	private void initialize() {
		// initialize machine with 5 coins of each denomination
		// and 5 cans of each Item
		for (Coin c : Coin.values()) {
			cashInventory.put(c, 100);
		}

		for (Product i : Product.values()) {
			itemInventory.put(i, 25);
		}

	}

	@Override
	public int getProductPrice(Product product) throws Exception {
		if (itemInventory.hasItem(product)) {
			return product.getPrice();
		}
		throw new SoldOutException("Sold Out, Please buy another item");
	}

	@Override
	public void insertCoin(Coin coin) {
		cashInventory.add(coin);
	}
	
	@Override
	public void insertCoins(List<Coin> coins) {
		for(Coin c: coins){
			cashInventory.add(c);
		}
	}

	@Override
	public Bucket<Product, List<Coin>> collectProductAndChange(Product prod, int amountInBasket) throws Exception {
		Product item = collectItem(prod);
		int retAmount = amountInBasket - prod.getPrice();
		List<Coin> change = returnCoins(retAmount);

		return new Bucket<Product, List<Coin>>(item, change);
	}

	public  Product collectItem(Product prod) throws Exception
	{
		if(!hasItem(prod))
			throw new Exception("Item Not there");
		
		itemInventory.deduct(prod);
		return prod;
	}
	
	

	public void printStats() {
		System.out.println("Current Item Inventory : " + itemInventory);
		System.out.println("Current Cash Inventory : " + cashInventory);
	}

	private void updateCashInventory(List<Coin> change) {
		for (Coin c : change) {
			cashInventory.deduct(c);
		}
	}

	@Override
	public boolean hasItem(Product product) {
		if (itemInventory.hasItem(product))
			return true;
		return false;
	}
	
	
	
	public List<Coin> returnCoins(int f) throws NotSufficientChangeException {
		List<Coin> changes = Collections.EMPTY_LIST;

		if (f > 0) {
			changes = new ArrayList<Coin>();
			int balance = f;
			while (balance > 0) {
				if (balance >= Coin.QUARTER.getDenomination() && cashInventory.hasItem(Coin.QUARTER)) {
					changes.add(Coin.QUARTER);
					balance = balance - Coin.QUARTER.getDenomination();
					continue;
				} else if (balance >= Coin.DIME.getDenomination() && cashInventory.hasItem(Coin.DIME)) {
					changes.add(Coin.DIME);
					balance = balance - Coin.DIME.getDenomination();
					continue;

				} else if (balance >= Coin.NICKLE.getDenomination() && cashInventory.hasItem(Coin.NICKLE)) {
					changes.add(Coin.NICKLE);
					balance = balance - Coin.NICKLE.getDenomination();
					continue;

				} else if (changes.isEmpty()) {
					throw new NotSufficientChangeException("NotSufficientChange, Please try another product");
				} else {
					break;
				}
			}
		}

		return changes;
	}
	
	
	public boolean hasChange(int f) {
		boolean hasChange = true;

		if (f > 0) {
			int balance = f;
			while (balance > 0) {
				if (balance >= Coin.QUARTER.getDenomination() && cashInventory.hasItem(Coin.QUARTER)) {
					balance = balance - Coin.QUARTER.getDenomination();
					continue;
				} else if (balance >= Coin.DIME.getDenomination() && cashInventory.hasItem(Coin.DIME)) {
					balance = balance - Coin.DIME.getDenomination();
					continue;

				} else if (balance >= Coin.NICKLE.getDenomination() && cashInventory.hasItem(Coin.NICKLE)) {
					balance = balance - Coin.NICKLE.getDenomination();
					continue;

				} else if(balance > 0){
					return false;
				}
			}
		}

		return hasChange;
	}	
	
	
}
