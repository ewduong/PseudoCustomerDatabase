package edu.wit.duonge1.business;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import java.awt.FlowLayout;
import java.io.IOException;

public class CompanyFrame extends JFrame {

	private JPanel contentPane;
	private JTextField companyNameTextField;
	private static Company company;
	private Customer customer;
	private String filePathName;
	private String companyName;
	private String filePath;
	private JFileChooser fc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompanyFrame frame = new CompanyFrame();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CompanyFrame() {
		company = new Company();
		setTitle("Company: "+company.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 135);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		final JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Yes", "No"};
				int n = JOptionPane.showOptionDialog(null,
				        "Are you sure you want to start a new company?",
				        "New Company",
				        JOptionPane.YES_NO_OPTION,
				        JOptionPane.QUESTION_MESSAGE,
				        null,
				        options,
				        options[0]);
				if (n == JOptionPane.YES_OPTION) {
					dispose();
					CompanyFrame.main(null);
						try {
							if (filePath == null) {
								LogFile logFile = new LogFile("companylog.txt", true);
								logFile.writeToLog(mntmNew.getText(), null, null, null, null, null);
							} else {
								LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
								logFile.writeToLog(mntmNew.getText(), null, null, null, null, null);
							}
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						return;
				} else {
					return;
				}
			}
		});
		mnFile.add(mntmNew);
		
		final JMenuItem mntmOpen = new JMenuItem("Open...");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Documents", "xml");
				fc.addChoosableFileFilter(xmlFilter);
				fc.setFileFilter(xmlFilter);
				fc.setAcceptAllFileFilterUsed(false);
			    // Handle open button action.
			    int returnVal = fc.showOpenDialog(contentPane);
			        if (returnVal == fc.APPROVE_OPTION) {
			        	filePathName = fc.getSelectedFile().getAbsolutePath();
			        	filePath = fc.getSelectedFile().getParent();
			        	company = new Company();
			            ReadXMLFile.main(null, filePathName, company);
			            setTitle("Company: "+company.getName());
			            try {
							if (filePath == null) {
								LogFile logFile = new LogFile("companylog.txt", true);
								logFile.writeToLog(mntmOpen.getText(), null, customer, null, company, filePathName);
							} else {
								LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
								logFile.writeToLog(mntmOpen.getText(), null, customer, null, company, filePathName);
							}
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
			        } else if (returnVal == fc.CANCEL_OPTION){
			            return;
			        } else {
			        	JOptionPane.showMessageDialog(null, "Error");
			        }
			   }
			});
		mnFile.add(mntmOpen);
		
		JSeparator seperatorOpenSave = new JSeparator();
		mnFile.add(seperatorOpenSave);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (company.getName().equals("None")) {
					JOptionPane.showMessageDialog(null, "Please set a company name before saving");
				} else {
			        SaveXMLFile.main(null, filePathName, company);
				}
			}
		});
		mnFile.add(mntmSave);
		
		final JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc = new JFileChooser();
				FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML Documents", "xml");
				fc.addChoosableFileFilter(xmlFilter);
				fc.setFileFilter(xmlFilter);
				fc.setAcceptAllFileFilterUsed(false);
			    // Handle save button action.
			    int returnVal = fc.showSaveDialog(contentPane);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			        	String fileSavePath = fc.getSelectedFile().getAbsolutePath();
			        	SaveXMLFile.main(null, fileSavePath, company);
			        	try {
							if (filePath == null) {
								LogFile logFile = new LogFile("companylog.txt", true);
								logFile.writeToLog(mntmSaveAs.getText(), null, customer, null, company, null);
							} else {
								LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
								logFile.writeToLog(mntmSaveAs.getText(), null, customer, null, company, null);
							}
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
			        } else if (returnVal == fc.CANCEL_OPTION) {
			            return; 
			        } else {
			            JOptionPane.showMessageDialog(null, "Error");
			        }
			   }
			});
		mnFile.add(mntmSaveAs);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel custPanel = new JPanel();
		custPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Customers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(custPanel, BorderLayout.WEST);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					CustomerGUI.main(null, false, company, filePath);
			}
		});
		custPanel.add(btnView);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					CustomerGUI.main(null, true, company, filePath);
			}
		});
		btnEdit.setHorizontalAlignment(SwingConstants.LEFT);
		custPanel.add(btnEdit);
		
		JPanel compPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) compPanel.getLayout();
		compPanel.setBorder(new TitledBorder(null, "Company", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(compPanel, BorderLayout.EAST);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		compPanel.add(lblCompanyName);
		
		companyNameTextField = new JTextField();
		compPanel.add(companyNameTextField);
		companyNameTextField.setColumns(20);
		
		final JButton btnCreateCompany = new JButton("Create");
		btnCreateCompany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				companyName = companyNameTextField.getText();
				if (companyName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nothing was entered into the textbox. Please enter a company name.");
				} else {
					Object[] options = {"Yes", "No"};
					int n = JOptionPane.showOptionDialog(null,
					        "Are you sure you want to make the company name: "+companyName,
					        "Customer Add",
					        JOptionPane.YES_NO_OPTION,
					        JOptionPane.QUESTION_MESSAGE,
					        null,
					        options,
					        options[0]);
					if (n == JOptionPane.YES_OPTION) {
						company = new Company(companyName);
						setTitle("Company: "+company.getName());
						JOptionPane.showMessageDialog(null, "Successfully created company: "+companyName);
						try {
							if (filePath == null) {
								LogFile logFile = new LogFile("companylog.txt", true);
								logFile.writeToLog(btnCreateCompany.getText(), null, customer, null, company, null);
							} else {
								LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
								logFile.writeToLog(btnCreateCompany.getText(), null, customer, null, company, null);
							}
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					} else {
						return;
					}
				}
			}
		});
		compPanel.add(btnCreateCompany);
		
		final JButton btnSetName = new JButton("Set Name");
		btnSetName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				companyName = companyNameTextField.getText();
				if (companyName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nothing was entered into the textbox. Please enter a company name.");
				} else {
					Object[] options = {"Yes", "No"};
					int n = JOptionPane.showOptionDialog(null,
					        "Are you sure you want to change the company name from '"+company.getName()+"'"+"to '"+companyName+"'",
					        "Company Rename",
					        JOptionPane.YES_NO_OPTION,
					        JOptionPane.QUESTION_MESSAGE,
					        null,
					        options,
					        options[0]);
					if (n == JOptionPane.YES_OPTION) {
						String oldCompanyName = company.getName();
						company.setName(companyName);
						JOptionPane.showMessageDialog(null, "Company successfully set to: "+companyName);
						setTitle("Company: "+company.getName());
						try {
							System.out.println(filePath);
							if (filePath == null) {
								LogFile logFile = new LogFile("companylog.txt", true);
								logFile.writeToLog(btnSetName.getText(), null, customer, oldCompanyName, company, null);
							} else {
								LogFile logFile = new LogFile(filePath+"\\companylog.txt", true);
								logFile.writeToLog(btnSetName.getText(), null, customer, oldCompanyName, company, null);
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
		compPanel.add(btnSetName);
	}

}
