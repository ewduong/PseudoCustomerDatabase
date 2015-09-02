package edu.wit.duonge1.business;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class LogFile {
	private String path;
	private boolean appendToFile = false;
	
	public LogFile(String filePath) {
		path = filePath;
	}
	
	public LogFile(String filePath, boolean appendValue) {
		path = filePath;
		appendToFile = appendValue;
	}
	
	public void writeToLog(String compName, Customer oldCustomer, Customer customer, String oldCompanyName, Company company, String filePathName) throws IOException {
		try {
			FileWriter write = new FileWriter(path, appendToFile);
			PrintWriter printLine = new PrintWriter(write);
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat ("E MM/dd/yyyy 'at' hh:mm:ss a zzz");
			String textLine = "Error in text logging";
			
			if (compName.equals("Add")) {
				if (customer instanceof ChargeCustomer) { 
					textLine = "[Customer] Added: "+((ChargeCustomer)customer).toString()+"\n";
				} else {
					textLine = "[Customer] Added: "+customer.toString()+"\n";
				}
			} else if (compName.equals("Delete")) {
				if (customer instanceof ChargeCustomer) {
					textLine = "[Customer] Deleted: "+((ChargeCustomer)customer).toString()+"\n";
				} else {
					textLine = "[Customer] Deleted: "+customer.toString()+"\n";
				}
			} else if (compName.equals("Save")) {
				if (customer instanceof ChargeCustomer) {
					textLine = "[Customer] "+oldCustomer.toString()+"\nSaved to: "+((ChargeCustomer)customer).toString();
				} else {
					textLine = "[Customer] "+oldCustomer.toString()+"\nSaved to: "+customer.toString();
				}
			} else if (compName.equals("Payment")) {
				textLine = "[Customer] "+customer.getName()+" balance payment made.\n";
			} else if (compName.equals("Charge")) {
				textLine = "[Customer] "+customer.getName()+" balance charge made.\n";
			} else if (compName.equals("Create")) {
				textLine = "[Company] Created: "+company.getName();
			} else if (compName.equals("Set Name")) {
				textLine = "[Company] Name: '"+oldCompanyName+"' set name to '"+company.getName()+"'";
			} else if (compName.equals("Open...")) {
				textLine = "[Company] "+filePathName+" opened.";
			} else if (compName.equals("Save") || compName.equals("Save As...")) {
				textLine = "[Company] "+company.getName()+" saved to "+path;
			} else if (compName.equals("New")) {
				textLine = "[Company] New company made.";
			}
			
			printLine.printf("["+dateFormat.format(date)+"] "+"%s"+"%n", textLine);
			printLine.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
