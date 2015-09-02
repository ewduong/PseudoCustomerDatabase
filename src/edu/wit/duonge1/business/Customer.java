package edu.wit.duonge1.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.wit.duonge1.business.Customer;

public class Customer {
	private int id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String beginDate;
	private String endDate;
	private static int lastId = 100;
	
	public Customer() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		id = lastId++;
		name = "None";
		address = "None";
		phone = "None";
		email = "None";
		beginDate = dateFormat.format(date);
		endDate = "None";
	}
	
	public Customer(String name, String address, String phone, String email, String beginDate, String endDate) {
		this.id = lastId++;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}
	
	public Customer(int id, String name, String address, String phone, String email, String beginDate, String endDate) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.beginDate = beginDate;
		this.endDate = endDate;
	
		lastId = this.id;
		this.id = lastId++;
	}
	
	public Customer(Customer c) {
		id = c.getId();
		name = c.getName();
		address = c.getAddress();
		phone = c.getPhone();
		email = c.getEmail();
		beginDate = c.getBeginDate();
		endDate = c.getEndDate();
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public void endCustomer() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		setEndDate(dateFormat.format(date));
	}
	
	public void sendMsg(String message) {
		
	}

	public String toString() {
		return "ID: "+id+" Name: "+name+" Address: "+address+" Phone: "+phone+" E-Mail: "+email+" Begin Date: "+beginDate+" End Date: "+endDate;
	}
	
}

class ChargeCustomer extends Customer implements CreditAccount {
	private double balance;
	
	public ChargeCustomer(Customer c, double balance) {
		super(c);
		this.balance = balance;
	}

	@Override
	public double getBalance() {
		return balance;
	}
	
	@Override
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public void recordPayment(double payment) {
		balance += payment;
	}

	@Override
	public void recordCharge(double charge) {
		balance -= charge;
	}
	
	@Override
	public String toString() {
		return "ID: "+getId()+" Name: "+getName()+" Address: "+getAddress()+" Phone: "+getPhone()+" E-Mail: "+getEmail()+" Begin Date: "+getBeginDate()+" End Date: "+getEndDate()+" Balance: "+balance;
	}
	
}