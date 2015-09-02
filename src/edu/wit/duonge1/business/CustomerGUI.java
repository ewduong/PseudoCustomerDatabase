package edu.wit.duonge1.business;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CustomerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextField;
	private Customer currentCustomer;
	private ChargeCustomer chargeCustomer;
	private DefaultListModel dlm;
	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTextField emailTextField;
	private JTextField bDateTextField;
	private JTextField eDateTextField;
	private String nameSearch, subName;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnPayment;
	private JButton btnCharge;
	private JList nameList;
	private JButton btnSave;
	private JButton btnSearch;
	private MouseListener mouseListener;
	private JTextField balanceTextField;
	private JCheckBox chckbxHasBalance;
	private JLabel lblBalance;
	private JTextField amountTextField;
	private JScrollPane nameScrollPane;
	private JPanel balancePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final boolean viewEdit, final Company company, final String filePath) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI frame = new CustomerGUI(viewEdit, filePath, company);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setViewEditAll (boolean type) {
		nameTextField.setEditable(type);
		addressTextField.setEditable(type);
		phoneTextField.setEditable(type);
		emailTextField.setEditable(type);
		bDateTextField.setEditable(type);
		eDateTextField.setEditable(type);
		btnAdd.setVisible(type);
		btnDelete.setVisible(type);
		btnSave.setVisible(type);
		balanceTextField.setEditable(type);
		chckbxHasBalance.setEnabled(type);
	}
	
	public void clearAllField () {
		nameTextField.setText(null);
		addressTextField.setText(null);
		phoneTextField.setText(null);
		emailTextField.setText(null);
		bDateTextField.setText(null);
		eDateTextField.setText(null);
		balanceTextField.setText(null);
		chckbxHasBalance.setSelected(false);
		amountTextField.setText(null);
	}

	/**
	 * Create the frame.
	 */
	public CustomerGUI(final boolean viewEdit, final String filePath, final Company company) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Company: "+company.getName());
		setBounds(100, 100, 616, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(179, 33, 282, 20);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		JLabel lblSearchName = new JLabel("Name Search");
		lblSearchName.setBounds(179, 11, 85, 14);
		contentPane.add(lblSearchName);
		
		JPanel customerNamePanel = new JPanel();
		customerNamePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customer IDs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		customerNamePanel.setBounds(10, 11, 159, 245);
		contentPane.add(customerNamePanel);
		customerNamePanel.setLayout(null);
		
		nameScrollPane = new JScrollPane();
		nameScrollPane.setBounds(15, 20, 130, 210);
		customerNamePanel.add(nameScrollPane);
		
		JPanel customerInfoPanel = new JPanel();
		customerInfoPanel.setBorder(new TitledBorder(null, "Customer Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		customerInfoPanel.setBounds(179, 64, 411, 245);
		contentPane.add(customerInfoPanel);
		customerInfoPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 24, 46, 14);
		customerInfoPanel.add(lblName);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(109, 21, 292, 20);
		customerInfoPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 49, 59, 14);
		customerInfoPanel.add(lblAddress);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(109, 46, 292, 20);
		customerInfoPanel.add(addressTextField);
		addressTextField.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(10, 74, 92, 14);
		customerInfoPanel.add(lblPhoneNumber);
		
		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(109, 71, 292, 20);
		customerInfoPanel.add(phoneTextField);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 99, 46, 14);
		customerInfoPanel.add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(109, 96, 292, 20);
		customerInfoPanel.add(emailTextField);
		
		JLabel lblBeginDate = new JLabel("Begin Date:");
		lblBeginDate.setBounds(10, 127, 71, 14);
		customerInfoPanel.add(lblBeginDate);
		
		bDateTextField = new JTextField();
		bDateTextField.setColumns(10);
		bDateTextField.setBounds(109, 124, 106, 20);
		customerInfoPanel.add(bDateTextField);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(225, 127, 71, 14);
		customerInfoPanel.add(lblEndDate);
		
		eDateTextField = new JTextField();
		eDateTextField.setColumns(10);
		eDateTextField.setBounds(295, 127, 106, 20);
		customerInfoPanel.add(eDateTextField);
		
		balancePanel = new JPanel();
		balancePanel.setBorder(new TitledBorder(null, "Balance", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		balancePanel.setBounds(10, 152, 391, 87);
		customerInfoPanel.add(balancePanel);
		balancePanel.setLayout(null);
		
		lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(10, 26, 61, 14);
		balancePanel.add(lblBalance);
		
		balanceTextField = new JTextField();
		balanceTextField.setBounds(67, 22, 106, 20);
		balancePanel.add(balanceTextField);
		balanceTextField.setColumns(10);
		
		btnPayment = new JButton("Payment");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (amountTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nothing was entered into the amount textfield");
				} else {
					Object[] options = {"Yes", "No"};
					int n = JOptionPane.showOptionDialog(null,
					        "Are you sure you want to make this payment?",
					        "Balance Payment",
					        JOptionPane.YES_NO_OPTION,
					        JOptionPane.QUESTION_MESSAGE,
					        null,
					        options,
					        options[0]);
					if (n == JOptionPane.YES_OPTION) {
						double payment = Double.parseDouble(amountTextField.getText());
						((ChargeCustomer)currentCustomer).recordPayment(payment);
						balanceTextField.setText(String.valueOf(((ChargeCustomer)currentCustomer).getBalance()));
						amountTextField.setText(null);
						try {
							if (filePath == null) {
								LogFile logFile = new LogFile("companylog.txt", true);
								logFile.writeToLog(btnPayment.getText(), null, currentCustomer, null, company, null);
							} else {
								LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
								logFile.writeToLog(btnPayment.getText(), null, currentCustomer, null, company, null);
							}
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
			}
		});
		btnPayment.setBounds(180, 52, 89, 23);
		btnPayment.setEnabled(false);
		balancePanel.add(btnPayment);
		
		btnCharge = new JButton("Charge");
		btnCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (amountTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nothing was entered into the amount textfield");
				} else {
					Object[] options = {"Yes", "No"};
					int n = JOptionPane.showOptionDialog(null,
					        "Are you sure you want to make this charge?",
					        "Balance Charge",
					        JOptionPane.YES_NO_OPTION,
					        JOptionPane.QUESTION_MESSAGE,
					        null,
					        options,
					        options[0]);
					if (n == JOptionPane.YES_OPTION) {
						double charge = Double.parseDouble(amountTextField.getText());
						((ChargeCustomer)currentCustomer).recordCharge(charge);
						balanceTextField.setText(String.valueOf(((ChargeCustomer)currentCustomer).getBalance()));
						amountTextField.setText(null);
						try {
							if (filePath == null) {
								LogFile logFile = new LogFile("companylog.txt", true);
								logFile.writeToLog(btnCharge.getText(), null, currentCustomer, null, company, null);
							} else {
								LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
								logFile.writeToLog(btnCharge.getText(), null, currentCustomer, null, company, null);
							}
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
			}
		});
		btnCharge.setBounds(279, 52, 89, 23);
		balancePanel.add(btnCharge);
		
		amountTextField = new JTextField();
		amountTextField.setColumns(10);
		amountTextField.setBounds(67, 53, 106, 20);
		btnCharge.setEnabled(false);
		balancePanel.add(amountTextField);
		
		JLabel lblAmount = new JLabel("Amount: ");
		lblAmount.setBounds(11, 56, 60, 14);
		balancePanel.add(lblAmount);
		
		chckbxHasBalance = new JCheckBox("Has Balance?");
		chckbxHasBalance.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chckbxHasBalance.isSelected()) {
					balanceTextField.setEditable(true);
					amountTextField.setEditable(true);
					btnPayment.setEnabled(true);
					btnCharge.setEnabled(true);
				} else if (!chckbxHasBalance.isSelected()) {
					balanceTextField.setEditable(false);
					amountTextField.setEditable(false);
					btnPayment.setEnabled(false);
					btnCharge.setEnabled(false);
				}
			}
		});
		chckbxHasBalance.setBounds(189, 22, 124, 23);
		balancePanel.add(chckbxHasBalance);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(45, 267, 89, 23);
		btnAdd.setVisible(false);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(309, 320, 89, 23);
		btnDelete.setVisible(false);
		contentPane.add(btnDelete);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(189, 320, 89, 23);
		btnSave.setVisible(false);
		contentPane.add(btnSave);
		
		if (viewEdit == true) {
			setViewEditAll(viewEdit);
			setBounds(100, 100, 616, 390);
			balanceTextField.setEditable(false);
			amountTextField.setEditable(false);
			setTitle("Company: "+company.getName()+" (EDIT MODE)");
		} else if (viewEdit == false) {
			setViewEditAll(viewEdit);
			setBounds(100, 100, 616, 340);
			balancePanel.setBounds(10, 182, 391, 55);
			btnCharge.setVisible(false);
			btnPayment.setVisible(false);
			amountTextField.setVisible(false);
			setTitle("Company: "+company.getName()+" (VIEW MODE)");
		}

		dlm = new DefaultListModel();
			for (Customer c : company.getCustomers()) {
					dlm.addElement(c.getId()+" - "+c.getName());
			}
		nameList = new JList(dlm);
		mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // double click
					int index = nameList.getSelectedIndex();
					//currentCustomer = company.getCustomers().get(index);
					if (index == -1) {
						JOptionPane.showMessageDialog(null, "No customer to select");
					} else {
						currentCustomer = company.getCustomers().get(index);
						clearAllField();
						nameTextField.setText(currentCustomer.getName());
						addressTextField.setText(currentCustomer.getAddress());
						phoneTextField.setText(currentCustomer.getPhone());
						emailTextField.setText(currentCustomer.getEmail());
						bDateTextField.setText(currentCustomer.getBeginDate());
						eDateTextField.setText(currentCustomer.getEndDate());
						if (currentCustomer instanceof ChargeCustomer) {
							balanceTextField.setText(String.valueOf(((ChargeCustomer)currentCustomer).getBalance()));
							chckbxHasBalance.setSelected(true);
							if (viewEdit == true) {
								balanceTextField.setEditable(true);
							} else if (viewEdit == false) {
								balanceTextField.setEditable(false);
							}
						}
					}
				}
			}
		};
		nameScrollPane.setViewportView(nameList);
		nameList.addMouseListener(mouseListener);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int p = 0;
				currentCustomer = new Customer(nameTextField.getText(), addressTextField.getText(), phoneTextField.getText(), emailTextField.getText(), bDateTextField.getText(), eDateTextField.getText());
				String[] textFieldNames = {nameTextField.getText(), addressTextField.getText(), phoneTextField.getText(), emailTextField.getText(), bDateTextField.getText(), eDateTextField.getText()};
				for (int i=0; i<textFieldNames.length; i++) {
					if (textFieldNames[i].equals("")) {
						p++;
					}
				}
				if (p>0) {
					Object[] options = {"Yes", "No"};
					int n = JOptionPane.showOptionDialog(null,
					        "One or more fields are empty, are you sure you want to add this customer?",
					        "Customer Add",
					        JOptionPane.YES_NO_OPTION,
					        JOptionPane.QUESTION_MESSAGE,
					        null,
					        options,
					        options[0]);
					if (n == JOptionPane.YES_OPTION) {
							if (chckbxHasBalance.isSelected()) {
								try {
									Double.parseDouble(balanceTextField.getText());
								} catch (NumberFormatException e) {
									e.getMessage();
									JOptionPane.showMessageDialog(null, "Balance field is not a valid integer");
								}
								chargeCustomer = new ChargeCustomer(currentCustomer, Double.parseDouble(balanceTextField.getText()));
								company.newCustomer(chargeCustomer);
								dlm.addElement(chargeCustomer.getId()+" - "+chargeCustomer.getName());
								clearAllField();
							} else {
								company.newCustomer(currentCustomer);
								dlm.addElement(currentCustomer.getId()+" - "+currentCustomer.getName());
								clearAllField();
							}
							try {
								if (filePath == null) {
									LogFile logFile = new LogFile("companylog.txt", true);
									logFile.writeToLog(btnAdd.getText(), null, currentCustomer, null, company, null);
								} else {
									LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
									logFile.writeToLog(btnAdd.getText(), null, currentCustomer, null, company, null);
								}
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						} else {
							return;
						}
				} else {
					Object[] options = {"Yes", "No"};
					int n = JOptionPane.showOptionDialog(null,
					        "Are you sure you want to add this?",
					        "Customer Add",
					        JOptionPane.YES_NO_OPTION,
					        JOptionPane.QUESTION_MESSAGE,
					        null,
					        options,
					        options[0]);
					if (n == JOptionPane.YES_OPTION) {
						if (chckbxHasBalance.isSelected()) {
							currentCustomer = new ChargeCustomer(currentCustomer, Double.parseDouble(balanceTextField.getText()));
						}
							company.newCustomer(currentCustomer);
							dlm.addElement(currentCustomer.getId()+" - "+currentCustomer.getName());
							clearAllField();
						try {
							if (filePath == null) {
								LogFile logFile = new LogFile("companylog.txt", true);
								logFile.writeToLog(btnAdd.getText(), null, currentCustomer, null, company, null);
							} else {
								LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
								logFile.writeToLog(btnAdd.getText(), null, currentCustomer, null, company, null);
							}
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					} else {
						return;
					}
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = nameList.getSelectedIndex();
				int n = 0;
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "No customer selected from list");
					return;
				} else {
					Object[] options = {"Yes", "No"};
					n = JOptionPane.showOptionDialog(null,
					        "Are you sure you want to delete "+company.getCustomers().get(index).getName()+" (ID: "+company.getCustomers().get(index).getId()+") ?",
					        "Customer Delete",
					        JOptionPane.YES_NO_OPTION,
					        JOptionPane.QUESTION_MESSAGE,
					        null,
					        options,
					        options[0]);
				}
				if (n == JOptionPane.YES_OPTION) {
						Customer oldCustomer = company.getCustomers().get(index);
						company.getCustomers().remove(index);
						dlm.removeElementAt(index);
						try {
							if (filePath == null) {
								LogFile logFile = new LogFile("companylog.txt", true);
								logFile.writeToLog(btnDelete.getText(), null, oldCustomer, null, company, null);
							} else {
								LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
								logFile.writeToLog(btnDelete.getText(), null, oldCustomer, null, company, null);
							}
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
				} else {
					return;
				}
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dlm.size() == 0) {
					JOptionPane.showMessageDialog(null, "There are no customers to save");
				} else {
					Object[] options = {"Yes", "No"};
					int n = JOptionPane.showOptionDialog(null,
					        "Are you sure you want to save this user?",
					        "Customer Modify",
					        JOptionPane.YES_NO_OPTION,
					        JOptionPane.QUESTION_MESSAGE,
					        null,
					        options,
					        options[0]);
					if (n == JOptionPane.YES_OPTION) {
							int index = nameList.getSelectedIndex();
							Customer oldSaveCustomer = new Customer(company.getCustomers().get(index));
							currentCustomer = company.getCustomers().get(index);
							currentCustomer.setName(nameTextField.getText());
							currentCustomer.setAddress(addressTextField.getText());
							currentCustomer.setPhone(phoneTextField.getText());
							currentCustomer.setEmail(emailTextField.getText());
							currentCustomer.setBeginDate(bDateTextField.getText());
							currentCustomer.setEndDate(eDateTextField.getText());
							if (chckbxHasBalance.isSelected()) {
								try {
									Double.parseDouble(balanceTextField.getText());
									if (currentCustomer instanceof ChargeCustomer) {
										((ChargeCustomer) currentCustomer).setBalance(Double.parseDouble(balanceTextField.getText()));
									} else {
										currentCustomer = new ChargeCustomer(currentCustomer, Double.parseDouble(balanceTextField.getText()));
										company.getCustomers().set(index, currentCustomer);
									}
								} catch (NumberFormatException e1) {
									e1.getMessage();
									JOptionPane.showMessageDialog(null, "Balance field is not a valid integer");
								}
							} else {
								if (currentCustomer instanceof ChargeCustomer) {
									currentCustomer = new Customer(currentCustomer);
									company.getCustomers().set(index, currentCustomer);
								}
							}
							
							dlm.setElementAt(company.getCustomers().get(index).getId()+" - "+company.getCustomers().get(index).getName(), index);
							try {
								if (filePath == null) {
									LogFile logFile = new LogFile("companylog.txt", true);
									logFile.writeToLog(btnSave.getText(), oldSaveCustomer, currentCustomer, null, company, null);
								} else {
									LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
									logFile.writeToLog(btnSave.getText(), oldSaveCustomer, currentCustomer, null, company, null);
								}
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage());
							}
						}
					}
				}
		});
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameSearch = searchTextField.getText();
				if (nameSearch.equals("") || nameSearch == null) {
					JOptionPane.showMessageDialog(null, "Nothing was inputted into the textbox. Please Try again.");
				} else {
					Object[] options = {"Yes", "No"};
					int index = 0;
						for (Customer c : company.getCustomers()) {
								nameSearch = nameSearch.toLowerCase();
								index = company.getCustomers().indexOf(c);
								currentCustomer = company.getCustomers().get(index);
								
								if (nameSearch.contains(c.getName().substring(0, 3).toLowerCase())) {
									nameTextField.setText(currentCustomer.getName());
									addressTextField.setText(currentCustomer.getAddress());
									phoneTextField.setText(currentCustomer.getPhone());
									emailTextField.setText(currentCustomer.getEmail());
									bDateTextField.setText(currentCustomer.getBeginDate());
									eDateTextField.setText(currentCustomer.getEndDate());
									if (currentCustomer instanceof ChargeCustomer) {
										balanceTextField.setText(String.valueOf(((ChargeCustomer)currentCustomer).getBalance()));
									}
									
									int n = JOptionPane.showOptionDialog(null,
									        "Is this who you searched?\n",
									        "Customer Search",
									        JOptionPane.YES_NO_OPTION,
									        JOptionPane.QUESTION_MESSAGE,
									        null,
									        options,
									        options[0]);
									if (n == JOptionPane.YES_OPTION) {
										nameTextField.setText(currentCustomer.getName());
										addressTextField.setText(currentCustomer.getAddress());
										phoneTextField.setText(currentCustomer.getPhone());
										emailTextField.setText(currentCustomer.getEmail());
										bDateTextField.setText(currentCustomer.getBeginDate());
										eDateTextField.setText(currentCustomer.getEndDate());
										if (currentCustomer instanceof ChargeCustomer) {
											balanceTextField.setText(String.valueOf(((ChargeCustomer)currentCustomer).getBalance()));
										}
									} else if (n == JOptionPane.NO_OPTION && index == company.getCustomers().size()-1) {
										JOptionPane.showMessageDialog(null, "Customer not found");
										clearAllField();
									}
								} else if (!(nameSearch.contains(c.getName().substring(0, 3))) && index == company.getCustomers().size()-1) {
									JOptionPane.showMessageDialog(null, "Customer not found");
									clearAllField();
								}
						}
				}
			}
		});
		btnSearch.setBounds(474, 32, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnClear = new JButton("Clear Text Fields");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAllField();
			}
		});
		btnClear.setBounds(426, 320, 137, 23);
		contentPane.add(btnClear);
	}
}
