package com.virtusa.repo;

public class VendingMachineFactory {
	public static VendingMachineRepository createVendingMachine() {
		return new VendingMachineRepositoryImpl();
	}
}
