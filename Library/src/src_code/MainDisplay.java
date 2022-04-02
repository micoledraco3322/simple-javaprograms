package src_code;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MainDisplay extends JFrame {
	
	private static String tempCurrent, tempIssue, name = "";
	private static int remainingDays = 0;
	private static double number_of_pages = 0, pages_read = 0, percentage_read = 0, pages_left = 0, pages_per_day = 0;
	
	
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblHowManyPages_1;
	private JTextField textField_1;
	private JLabel label0_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDisplay frame = new MainDisplay();
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
	public MainDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name = JOptionPane.showInputDialog("Name: ");
		
		JLabel lblWelcome = new JLabel("Welcome "+name);
		lblWelcome.setBounds(171, 12, 121, 15);
		contentPane.add(lblWelcome);
		
		JLabel lblHowManyPages = new JLabel("How many pages does the book have?");
		lblHowManyPages.setBounds(24, 69, 316, 23);
		contentPane.add(lblHowManyPages);
		
		JLabel label0 = new JLabel("Issue Date:");
		label0.setBounds(24, 160, 316, 23);
		contentPane.add(label0);
		
		UtilDateModel model = new UtilDateModel();
		Properties prop = new Properties();
		prop.put("text.today", "Today");
		prop.put("text.month", "Month");
		prop.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, prop);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.setBounds(24, 180, 146, 20);
		contentPane.add(datePicker);
		
		textField = new JTextField();
		textField.setBounds(303, 69, 95, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblHowManyPages_1 = new JLabel("How many pages have you read?");
		lblHowManyPages_1.setBounds(24, 104, 316, 23);
		contentPane.add(lblHowManyPages_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(303, 104, 95, 23);
		contentPane.add(textField_1);
		
		label0_1 = new JLabel("Due Date:");
		label0_1.setBounds(247, 160, 316, 23);
		contentPane.add(label0_1);
		
		//Set the date picker format
		UtilDateModel model2 = new UtilDateModel();
		Properties prop2 = new Properties();
		prop2.put("text.today", "Today");
		prop2.put("text.month", "Month");
		prop2.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, prop2);
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
		datePicker2.setBounds(247, 180, 146, 20);
		contentPane.add(datePicker2);
		
		JButton calc = new JButton("Calculate");
		calc.setBounds(163, 230, 100, 30);
		contentPane.add(calc);
		
		calc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					
					Date dueDay = (Date) datePicker2.getModel().getValue(); // Retrieve the selected date and pick up only the due day
					DateFormat dateFormat1 = new SimpleDateFormat("dd"); // This will be the format to be use 
					String dueDate = dateFormat1.format(dueDay); // Store the value
					
					number_of_pages = Integer.parseInt(textField.getText()); //Retrieve the number of pages
					pages_read = Integer.parseInt(textField_1.getText()); //Retrieve the number of pages read
					
					DateFormat tempDate1 = new SimpleDateFormat("yyyy-MM-dd"); // Format to be use for issue date (for getting the remaining days)
					tempIssue = tempDate1.format(dueDay); // Get the due day and use the format "tempDate1"
					
					DateTimeFormatter tempDate2 = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Format to be use for current date (for getting the remaining days)
					tempCurrent = tempDate2.format(LocalDate.now()); // Get the current day and use the format "tempDate2"
					
					// Parse the retrieved issue and current date from string to date format
					LocalDate local1 = LocalDate.parse(tempCurrent); 
					LocalDate local2 = LocalDate.parse(tempIssue);
								
					//Formula
					remainingDays = Period.between(local1, local2).getDays(); // Counting the remaining days between current date and issue day 				
					percentage_read = (pages_read/number_of_pages)*100;
					pages_left = number_of_pages - pages_read;
					pages_per_day = pages_read/remainingDays;
					
					//Checking if the code works
					System.out.println("Current Date: "+tempCurrent);
					System.out.println("Issue Date: "+tempIssue);
					System.out.println("Percentage: "+percentage_read);
					System.out.println("Number of pages: "+number_of_pages);
					System.out.println("Pages read: "+pages_read);
				
					//If all are correct, this message will show
					JOptionPane.showMessageDialog(contentPane,"You have read "+Math.round(percentage_read)/*Decimal to whole number*/+"% of the book.\nYou have "+remainingDays+" day(s) remaining to return the book.\nYou still have "+Math.round(pages_left)+" page(s) left and you should read "+Math.round(pages_per_day)+" pages a day.\n");
					
				}catch(Exception e) {
					// If something is missing or incorrect from the input, this message will show
					JOptionPane.showMessageDialog(contentPane,"Something is wrong. Kindly check the values that you've entered.");
				}
				
			}
		});
	}
}