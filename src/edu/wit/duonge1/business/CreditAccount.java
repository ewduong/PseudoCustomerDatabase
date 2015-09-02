package edu.wit.duonge1.business;

public interface CreditAccount {
	double getBalance();
	void setBalance(double balance);
	void recordPayment(double payment);
	void recordCharge(double charge);
}