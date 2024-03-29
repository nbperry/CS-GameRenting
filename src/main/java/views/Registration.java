package views;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import entities.Console;
import entities.Game;
import entities.Person;
import entities.Rental;
import manager.ConsoleManager;
import manager.GameManager;
import manager.PersonManager;
import manager.RentalManager;

import java.util.List;

public class Registration extends JPanel implements ActionListener {
	protected JButton buttonSubmit, buttonCancel;
	JLabel labelRITusername, labelFirst, labelMiddle, labelLast, labelEmail,
			labelPhone;//, labelPosition;
	JTextField fieldRITusername, fieldFirst, fieldMiddle, fieldLast,
			fieldEmail, fieldPhone;//, fieldPosition;
	static JFrame frame;
	static String strRITusername;
	static String strFirst;
	static String strMiddle;
	static String strLast;
	static String strEmail;
	static String strPhone;
	//static String strPosition;
	static String argKey;

	public Registration() {
		super(new GridBagLayout());
		labelRITusername = new JLabel("RIT username");
		labelFirst = new JLabel("First name");
		labelMiddle = new JLabel("Middle name");
		labelLast = new JLabel("Last name");
		labelEmail = new JLabel("Email");
		labelPhone = new JLabel("Phone Number");
		//labelPosition = new JLabel("Position");

		fieldRITusername = new JTextField(8);
		fieldRITusername.setText(strRITusername);

		fieldFirst = new JTextField(64);
		fieldFirst.setText(strFirst);

		fieldMiddle = new JTextField(64);
		fieldMiddle.setText(strMiddle);

		fieldLast = new JTextField(64);
		fieldLast.setText(strLast);

		fieldEmail = new JTextField(128);
		fieldEmail.setText(strEmail);

		fieldPhone = new JTextField(11);
		fieldPhone.setText(strPhone);

		//fieldPosition = new JTextField(64);
		//fieldPosition.setText(strPosition);

		buttonSubmit = new JButton("Submit/Update");
		buttonSubmit.setVerticalTextPosition(AbstractButton.CENTER);
		buttonSubmit.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonSubmit.setActionCommand("Submit");
		buttonSubmit.setPreferredSize(new Dimension(145, 30));
		buttonSubmit.addActionListener(this);

		buttonCancel = new JButton("Cancel");
		buttonCancel.setVerticalTextPosition(AbstractButton.CENTER);
		buttonCancel.setHorizontalTextPosition(AbstractButton.CENTER);
		buttonCancel.setActionCommand("Cancel");
		buttonCancel.setPreferredSize(new Dimension(145, 30));
		buttonCancel.addActionListener(this);

		JPanel textPane = new JPanel();
		// textPane.setLayout(new BoxLayout(textPane,
		// BoxLayout.LINE_AXIS));

		textPane.setPreferredSize(new Dimension(190, 395));

		JPanel inputPane = new JPanel();
		// inputPane.setLayout(new BoxLayout(inputPane,
		// BoxLayout.LINE_AXIS));
		inputPane.setPreferredSize(new Dimension(190, 395));

		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;

		c.fill = GridBagConstraints.HORIZONTAL;

		add(labelRITusername);
		add(fieldRITusername, c);

		add(labelFirst);
		add(fieldFirst, c);

		add(labelMiddle);
		add(fieldMiddle, c);

		add(labelLast);
		add(fieldLast, c);

		add(labelEmail);
		add(fieldEmail, c);

		add(labelPhone);
		add(fieldPhone, c);

		//add(labelPosition);
		//add(fieldPosition, c);

		add(textPane);
		add(inputPane);
		add(buttonSubmit);
		add(buttonCancel);

		// fieldRITusername.addActionListener(textListener);
		// fieldRITusername.getDocument().addDocumentListener(textListener);
	}

	public void actionPerformed(ActionEvent e) {
		if ("Submit".equals(e.getActionCommand())) {
			System.out.println("Submit:");

			if (!fieldRITusername.getText().equals("")
					&& !fieldFirst.getText().equals("")
					&& !fieldMiddle.getText().equals("")
					&& !fieldLast.getText().equals("")
					&& !fieldEmail.getText().equals("")
					&& !fieldPhone.getText().equals(""))
					//&& !fieldPosition.getText().equals("")) 
			{
				System.out.println(fieldRITusername.getText());
				System.out.println(fieldFirst.getText());
				System.out.println(fieldMiddle.getText());
				System.out.println(fieldLast.getText());
				System.out.println(fieldEmail.getText());
				System.out.println(fieldPhone.getText());
				//System.out.println(fieldPosition.getText());
				
				if(argKey != null && !argKey.isEmpty() && !argKey.equals(""))
				{
					PersonManager pm = new PersonManager();
					pm.updatePerson(fieldRITusername.getText(), 
									fieldFirst.getText(), 
									fieldMiddle.getText(), 
									fieldLast.getText(), 
									fieldEmail.getText(), 
									fieldPhone.getText());
				}
				else
				{
					PersonManager pm = new PersonManager();
					pm.createPerson(fieldFirst.getText(), 
									fieldMiddle.getText(), 
									fieldLast.getText(), 
									fieldEmail.getText(), 
									fieldPhone.getText());
				}
				
				
			} else {
				System.out.println("ERROR: Field is empty");
			}
		} else if ("Cancel".equals(e.getActionCommand())) {
			System.out.println("Cancel");
			frame.dispose();
		}
	}

	private static boolean retrieveEditInformation(String str) {
		if (str != null && !str.isEmpty() && !str.equals("")) {
			argKey = str;
			
			PersonManager pm = new PersonManager();
			
			Person person = pm.selectPerson(argKey);

			strFirst = person.getFirstName();
			strMiddle = person.getMiddleName();
			strLast = person.getLastName();
			strEmail = person.getEmail();
			strPhone = person.getPhoneNumber();
			
			strRITusername = argKey;
			
			return true;
		}

		return false;
	}

	public static void createAndShowGUI(String str) {

		// Create and set up the window.
		retrieveEditInformation(str);
		frame = new JFrame("Registration Screen");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Create and set up the content pane.
		Registration newContentPane = new Registration();
		newContentPane.setOpaque(true); // content panes must be opaque
		newContentPane.setPreferredSize(new Dimension(300, 200));
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI("");
			}
		});
	}
}
