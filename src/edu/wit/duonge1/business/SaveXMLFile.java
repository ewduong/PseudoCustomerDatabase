package edu.wit.duonge1.business;

import java.io.File;
import javax.swing.JOptionPane;
import javax.xml.transform.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 

import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class SaveXMLFile {
 
	public static void main(String[] args, String fileSavePath, Company company) {
 
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("company");
		rootElement.setAttribute("name", company.getName());
		doc.appendChild(rootElement);
		
		for (Customer c : company.getCustomers()) {
		Element customer = doc.createElement("customer");
		rootElement.appendChild(customer);
		
		// id elements
		Element id = doc.createElement("id");
		id.appendChild(doc.createTextNode(Integer.toString(c.getId())));
		customer.appendChild(id);
		
		// name elements
		Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode(c.getName()));
		customer.appendChild(name);
 
		// address elements
		Element address = doc.createElement("address");
		address.appendChild(doc.createTextNode(c.getAddress()));
		customer.appendChild(address);
 
		// phone elements
		Element phone = doc.createElement("phone");
		phone.appendChild(doc.createTextNode(c.getPhone()));
		customer.appendChild(phone);
 
		// email elements
		Element email = doc.createElement("email");
		email.appendChild(doc.createTextNode(c.getEmail()));
		customer.appendChild(email);
		
		// beginDate elements
		Element beginDate = doc.createElement("beginDate");
		beginDate.appendChild(doc.createTextNode(c.getBeginDate()));
		customer.appendChild(beginDate);
		
		// endDate elements
		Element endDate = doc.createElement("endDate");
		endDate.appendChild(doc.createTextNode(c.getEndDate()));
		customer.appendChild(endDate);
		
		// balance elements
		Element balance = doc.createElement("balance");
		if (c instanceof ChargeCustomer) {
			balance.setAttribute("type", "yes");
			balance.appendChild(doc.createTextNode(String.valueOf(((ChargeCustomer)c).getBalance())));
		} else {
			balance.setAttribute("type", "no");
		}
		customer.appendChild(balance);
		}
 
		// write the content into xml file
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.setOutputProperty(OutputKeys.METHOD, "xml");
		tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);
		if (!fileSavePath.endsWith(".xml")) {
		    fileSavePath += ".xml";
			StreamResult result = new StreamResult(new File(fileSavePath));
			tf.transform(source, result);
		} else {
			StreamResult result = new StreamResult(new File(fileSavePath));
			tf.transform(source, result);
		}
 
		JOptionPane.showMessageDialog(null, "File Saved successfully!");
 
	  } catch (ParserConfigurationException pce) {
		  pce.printStackTrace();
	  } catch (TransformerException tfe) {
		  tfe.printStackTrace();
	  }
	}
}