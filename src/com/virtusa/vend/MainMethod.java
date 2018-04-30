package com.virtusa.vend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.virtusa.repo.Coin;
import com.virtusa.repo.Product;

public class MainMethod {

	public static void main(String[] args) {
		VendingMachineService serv = new VendingMachineService();
		System.out.println("Insert Coins");
		
		while (true) {
			try {
				// Insert coin
				reset(serv);
				insertCoin(serv);

				// SelectProduct
				Product p = selectProduct(serv);

				if (p != null) {
					// purchase
					Bucket<Product, List<Coin>> output = serv.purchaseProduct(p);

					System.out.println("Thank you and Please Collect your Product" + output.getFirst().getName());
					for (Coin c : output.getSecond()) {
						System.out.println("Collect Coin " + c.name());
					}
				} else {
					throw new RuntimeException("Invalid product entered! Please select a vlaid product..");
				}

			} catch (Exception e) {
				System.out.println("Exception!!!  " + e.getMessage());
				if (serv.getTotalAmountInserted() > 0) {

					System.out.println("Please collect your money" + " " + serv.getTotalAmountInserted());
				}

			}

		}
		
	}

	public static void insertCoin(VendingMachineService serv) 
 {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean comeout = true;
		try {
			while (comeout) {
				System.out.println("Insert coin size, weight with enterkey");
				String size = br.readLine();
				String weight = br.readLine();
				serv.insertCoin(Integer.valueOf(size), Integer.valueOf(weight));
				System.out.println("Sucessfully inserted");

				System.out.println("Insert more coin Y:N");
				String s = br.readLine();
				if (s.equalsIgnoreCase("N")) {
					comeout = false;
				}
			}

		} catch (Exception e) {
			System.out.println("Try again, Wrong Entry");
		}

		System.out.println("Total money inserted " + serv.getTotalAmountInserted());
	}
	
	public static void printProducts(VendingMachineService serv)
	{
		System.out.println("Products Available");
		List<Product> products = serv.getAllProduct();
		for(Product p: products){
			System.out.println("Product"+ p.name()+ " Price "+ p.getPrice());
		}
	}
	
	public static void reset(VendingMachineService serv){
		serv.reset();
		
		System.out.println("Insert Coin to start");
	}
	
	public static Product selectProduct(VendingMachineService serv) {
		printProducts(serv);

		System.out.println("Select your Product:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			String input = br.readLine();
			return Product.resolve(input);
		} catch (Exception e) {
			System.out.println("Try again, Wrong Entry");
		}
		return null;
	}
	
}
