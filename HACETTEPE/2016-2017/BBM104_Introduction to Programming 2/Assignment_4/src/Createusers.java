import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Createusers {

	private JFrame frame;
	private JTextField takeusername;
	private JPasswordField takepassword;
	private JTextField takerepassword;
	private JTextField takenamesurname;
	private JTextField takeschool;
	private JTextField takebirthdate;

	/**
	 * Launch the application.
	 */
	public static void Createusers() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Createusers window = new Createusers();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Createusers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(250, 100, 429, 485);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		JLabel lusername = new JLabel("Username");
		lusername.setBounds(10, 132, 160, 23);
		frame.getContentPane().add(lusername);
		
		JLabel lpassword = new JLabel("Password");
		lpassword.setBounds(10, 169, 160, 23);
		frame.getContentPane().add(lpassword);
		
		JLabel lrepassword = new JLabel("Password(re-type)");
		lrepassword.setBounds(10, 203, 160, 23);
		frame.getContentPane().add(lrepassword);
		
		JLabel lnamesurname = new JLabel("Name Surname");
		lnamesurname.setBounds(10, 242, 160, 23);
		frame.getContentPane().add(lnamesurname);
		
		JLabel lschool = new JLabel("School graduated");
		lschool.setBounds(10, 272, 160, 23);
		frame.getContentPane().add(lschool);
		
		JLabel lbirthdate = new JLabel("Birth Date");
		lbirthdate.setBounds(10, 308, 160, 23);
		frame.getContentPane().add(lbirthdate);
		
		JLabel lreloship = new JLabel("Relotionship Status");
		lreloship.setBounds(10, 342, 160, 23);
		frame.getContentPane().add(lreloship);
		
		JLabel lcreateusertext = new JLabel("Create User");
		lcreateusertext.setFont(new Font("Tahoma", Font.BOLD, 20));
		lcreateusertext.setHorizontalAlignment(SwingConstants.CENTER);
		lcreateusertext.setBounds(114, 98, 194, 23);
		frame.getContentPane().add(lcreateusertext);
		
		takeusername = new JTextField();
		takeusername.setBounds(201, 135, 86, 20);
		frame.getContentPane().add(takeusername);
		takeusername.setColumns(10);
		
		takepassword = new JPasswordField();
		takepassword.setBounds(201, 166, 86, 20);
		frame.getContentPane().add(takepassword);
		takepassword.setColumns(10);
		
		takerepassword = new JPasswordField();
		takerepassword.setBounds(201, 204, 86, 20);
		frame.getContentPane().add(takerepassword);
		takerepassword.setColumns(10);
		
		takenamesurname = new JTextField();
		takenamesurname.setBounds(201, 243, 188, 20);
		frame.getContentPane().add(takenamesurname);
		takenamesurname.setColumns(10);
		
		takeschool = new JTextField();
		takeschool.setBounds(201, 273, 188, 20);
		frame.getContentPane().add(takeschool);
		takeschool.setColumns(10);
		
		takebirthdate = new JTextField();
		takebirthdate.setBounds(201, 309, 86, 20);
		frame.getContentPane().add(takebirthdate);
		takebirthdate.setColumns(10);
		
		JComboBox takerelationship = new JComboBox();
		takerelationship.setModel(new DefaultComboBoxModel(new String[] {"Single", "Complicated", "Divorced", "In relationship"}));
		takerelationship.setBounds(201, 343, 118, 20);
		frame.getContentPane().add(takerelationship);
		
		
		
		
		JButton bcrareusers = new JButton("Create ");
		bcrareusers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					
					String namesurname=takenamesurname.getText().toString();
					String username=takeusername.getText().toString();
					String password=takepassword.getText().toString();
					
					String repassword=takerepassword.getText().toString();
					String birthdate=takebirthdate.getText().toString();
					String school=takeschool.getText().toString();
					String relationship=takerelationship.getSelectedItem().toString();
					
					//System.out.println("bURADA");
					
					if(namesurname.equals("")==false && username.equals("")==false && password.equals("")==false &&repassword.equals("")==false &&birthdate.equals("")==false &&school.equals("")==false){
						if(password.equals(repassword)){
							UserCollection.addUser(namesurname,username, password, birthdate, school, relationship);
							
							//System.out.print("adam eklendi");
							JOptionPane.showMessageDialog(frame, "Succees!");
							loginpage b=new loginpage();
							
							frame.setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(frame, "password mismatch");
						}
						
					}else{
						JOptionPane.showMessageDialog(frame, "fill all of gaps");
					}
				
			}
		});
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	int selectedOption = JOptionPane.showConfirmDialog(
						null, "Are you sure to close this window?"
								, "Choose",
						JOptionPane.YES_NO_OPTION);
		    	
		        if (selectedOption == JOptionPane.YES_OPTION){
		        	loginpage b=new loginpage();
		        	frame.setVisible(false);
		        }
		        if(selectedOption==JOptionPane.NO_OPTION){
		        	
		        	
		        }
		    }
		});
		bcrareusers.setBounds(139, 412, 148, 23);
		frame.getContentPane().add(bcrareusers);
		
		JLabel lfacebookfoto = new JLabel("");
		lfacebookfoto.setHorizontalAlignment(SwingConstants.CENTER);
		Image icon=new ImageIcon(this.getClass().getResource("/Facebooklogo1.png")).getImage();
		lfacebookfoto.setIcon(new ImageIcon(icon));
		lfacebookfoto.setBounds(10, 11, 393, 76);
		frame.getContentPane().add(lfacebookfoto);
		
		frame.setVisible(true);
		
		
	}
}
