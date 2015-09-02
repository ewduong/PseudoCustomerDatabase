package edu.wit.duonge1.business;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
 
public class ReadXMLFile {
 
	public static void main(String[] args, String filePath, Company company) {
		try {
			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
			company.setName(doc.getDocumentElement().getAttribute("name"));
			
			NodeList nList = doc.getElementsByTagName("customer");
				for (int i=0; i<nList.getLength(); i++) {
					Node nNode = nList.item(i);
			 
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						
						int customerId = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
						String customerName = eElement.getElementsByTagName("name").item(0).getTextContent();
						String customerAddress = eElement.getElementsByTagName("address").item(0).getTextContent();
						String customerPhone = eElement.getElementsByTagName("phone").item(0).getTextContent();
						String customerEmail = eElement.getElementsByTagName("email").item(0).getTextContent();
						String customerBeginDate = eElement.getElementsByTagName("beginDate").item(0).getTextContent();
						String customerEndDate = eElement.getElementsByTagName("endDate").item(0).getTextContent();
						if (eElement.getElementsByTagName("balance").item(0).getAttributes().getNamedItem("type").getNodeValue().equals("yes")) {
							double customerBalance = Double.parseDouble(doc.getElementsByTagName("balance").item(0).getFirstChild().getNodeValue());
							Customer customer = new Customer(customerId, customerName, customerAddress, customerPhone, customerEmail, customerBeginDate, customerEndDate);
							ChargeCustomer chargeCustomer = new ChargeCustomer(customer, customerBalance);
							company.newCustomer(chargeCustomer);
						} else if (eElement.getElementsByTagName("balance").item(0).getAttributes().getNamedItem("type").getNodeValue().equals("no")) {
							Customer customer = new Customer(customerId, customerName, customerAddress, customerPhone, customerEmail, customerBeginDate, customerEndDate);
							company.newCustomer(customer);
						}
					}
				}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
 
}