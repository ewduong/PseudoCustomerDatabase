package edu.wit.duonge1.business;

import java.util.ArrayList;

public class Company {
	private String name;
	private ArrayList<Customer> customer;
	
	public Company () {	
		customer = new ArrayList<Customer>();
		this.name = "None";
	}
	
	public Company (String name) {
		customer = new ArrayList<Customer>();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Customer> getCustomers() {
		return customer;
	}
	
	public void newCustomer(Customer c) {
		customer.add(c);
	}
	
	public void sendBroadcast (String message) {
		for (Customer c : getCustomers())
			c.sendMsg(message);
	}
	
	public static void main(String[] args) {
		CompanyFrame.main(args);
	}
	
}
