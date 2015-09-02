package edu.wit.duonge1.business;

import javax.swing.JOptionPane;

public class TestCompany {
	private static Company company;

	public static void main(String[] args) {
		String a;
		a = JOptionPane.showInputDialog("Input Company Name:");
		company = new Company(a);
		/*JOptionPane.showMessageDialog(null, "Welcome to your new company "+company.getName());
		JOptionPane.showMessageDialog(null, "Now to add a customer");
		company.newCustomer(new Customer("Eric Duong", "10 Charlotte Lane", "781-888-2561", "duonge1@wit.edu", "10/8/13", "10/9/13"));
		company.newCustomer(new Customer());
		for (Customer c : company.getCustomers()) {
			JOptionPane.showMessageDialog(null, "List of Customers\n"+c.toString());
		}*/
		//company.viewEditMode();
	}

}
