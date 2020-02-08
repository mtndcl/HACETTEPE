import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class AddVideopost {

	private JFrame frame;
	private JTextField mesage;
	private JTextField latitudet;
	private JTextField longtitudet;
	private JTextField filenamet;
	private JTextField durationt;
	private JTextField height;
	private JTextField textwith;

	/**
	 * Launch the application.
	 */
	public static void AddVideopost() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVideopost window = new AddVideopost();
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
	public AddVideopost() {
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
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Add	Post");
		JLabel lblNewLabel = new JLabel("Post Type");
		lblNewLabel.setBounds(10, 29, 68, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TextPost",  "ImagePost" , "VideoPost"}));
		comboBox.setBounds(86, 29, 126, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Text:");
		lblNewLabel_1.setBounds(10, 85, 68, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		mesage = new JTextField();
		mesage.setBounds(86, 82, 314, 20);
		frame.getContentPane().add(mesage);
		mesage.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Latitude");
		lblNewLabel_2.setBounds(10, 139, 68, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		latitudet = new JTextField();
		latitudet.setBounds(86, 136, 86, 20);
		frame.getContentPane().add(latitudet);
		latitudet.setColumns(10);
		
		JLabel lblLongtitude = new JLabel("Longtitude");
		lblLongtitude.setBounds(180, 139, 68, 14);
		frame.getContentPane().add(lblLongtitude);
		
		longtitudet = new JTextField();
		longtitudet.setBounds(260, 136, 86, 20);
		frame.getContentPane().add(longtitudet);
		longtitudet.setColumns(10);
		
		JLabel lblfilename = new JLabel("Filename");
		lblfilename.setBounds(10, 178, 68, 14);
		frame.getContentPane().add(lblfilename);
		
		filenamet = new JTextField();
		filenamet.setBounds(86, 175, 260, 20);
		frame.getContentPane().add(filenamet);
		filenamet.setColumns(10);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(10, 224, 68, 14);
		frame.getContentPane().add(lblDuration);
		
		durationt = new JTextField();
		durationt.setBounds(86, 221, 86, 20);
		frame.getContentPane().add(durationt);
		durationt.setColumns(10);
		
		JButton bot = new JButton("Add Post");
		bot.setBounds(291, 25, 89, 23);
		frame.getContentPane().add(bot);
		
		JLabel with = new JLabel("Width");
		with.setBounds(10, 224, 68, 17);
		frame.getContentPane().add(with);
		
		JLabel lblheight = new JLabel("Height");
		lblheight.setBounds(180, 224, 68, 14);
		frame.getContentPane().add(lblheight);
		
		height = new JTextField();
		height.setBounds(260, 221, 86, 20);
		frame.getContentPane().add(height);
		height.setColumns(10);
		
		textwith = new JTextField();
		textwith.setBounds(86, 221, 86, 20);
		frame.getContentPane().add(textwith);
		textwith.setColumns(10);
		
		
		bot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String useranemofpost = null;
				for(User onlineuser: UserCollection.userlogin){
					useranemofpost=onlineuser.getUser_name();
				}
				String message=mesage.getText().toString();
				String typemessage=comboBox.getSelectedItem().toString();
				String latitude=latitudet.getText().toString();
				String longtitude=longtitudet.getText().toString();
				String filename=filenamet.getText().toString();
				String duration=durationt.getText().toString();
				String heightb=height.getText().toString();
				String withhh=textwith.getText().toString();
				
				
				
		
			if(typemessage.equals("VideoPost")){
				
				try{
				if(message.equals("")==false && latitude.equals("")==false && longtitude.equals("")==false  && filename.equals("")==false && duration.equals("")==false ){
					String line="ADDTO-VIDEO\t"+useranemofpost+"\t"+message+"\t"+longtitude+"\t"+latitude+"\t\t"+filename+"\t"+duration;
	
					if(Integer.parseInt(duration) <=10){
						UserCollection.addvideo(line);
						//System.out.print("bu video mesaage  eklendi  :");
						//System.out.print(line);
						JOptionPane.showMessageDialog(frame, "Succes!");
						Profilpage.deletelabals(Profilpage.getpanel());
						
	
						Profilpage.showmymessage(Profilpage.getpanel(),useranemofpost);
						Profilpage.getpanel().repaint();
						frame.setVisible(false);
						
					}
					else{
						JOptionPane.showMessageDialog(frame, "Duration is bigger than 10");
					}
					
				}else{
					JOptionPane.showMessageDialog(frame, "fill all of gaps");
				}
				}catch(Exception f){
					JOptionPane.showMessageDialog(frame, "longtitude,witdh ,height , latitude and duration have to be number");

				}
			}
				if(typemessage.equals("ImagePost")){
					try{
					if(message.equals("")==false && latitude.equals("")==false && longtitude.equals("")==false  && filename.equals("")==false && heightb.equals("")==false && withhh.equals("")==false ){
						String line="ADDTO-IMAGE\t"+useranemofpost+"\t"+message+"\t"+longtitude+"\t"+latitude+"\t\t"+filename+"\t"+withhh+"<x>"+heightb;
						UserCollection.addimage(line);
						//System.out.print(" bu image mesaage  eklendi  :");
						//
						//System.out.print(line);
						JOptionPane.showMessageDialog(frame, "Succes!");
						Profilpage.deletelabals(Profilpage.getpanel());
						
						
						Profilpage.showmymessage(Profilpage.getpanel(),useranemofpost);
						Profilpage.getpanel().repaint();
						frame.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(frame, "fill all of gaps");
					}
					}catch(Exception d){
						JOptionPane.showMessageDialog(frame, "longtitude,witdh ,height and latitude have to be number");
					}
				}
			
			if(typemessage.equals("TextPost")){
				try{
					if(message.equals("")==false && latitude.equals("")==false && longtitude.equals("")==false){
					String line="ADDTO-TEXT\t"+useranemofpost+"\t"+message+"\t"+longtitude+"\t"+latitude+"\tmetin";
					UserCollection.addtext(line);
					//System.out.println("bu text  mesaage  eklendi");
				//	System.out.println(line);
					/*
					 * 
					 * for(User adamad: UserCollection.userlogin){
						Profilpage.showmymessage(mypostpanel, adamad.getUser_name());
						System.out.print("metinmetinmetinmetinmetinmetin");
					}
					 */
					JOptionPane.showMessageDialog(frame, "Succes!");
					Profilpage.deletelabals(Profilpage.getpanel());
					
					
					Profilpage.showmymessage(Profilpage.getpanel(),useranemofpost);
					Profilpage.getpanel().repaint();
					frame.setVisible(false);
					
					
					
					
				}
					else{
						JOptionPane.showMessageDialog(frame, "fill all of gaps");
					}
				
			}catch(Exception f){
				JOptionPane.showMessageDialog(frame, "longtitude and latitude have to be number");
				
			}
				}
				
			}
			
		});
		lblDuration.setVisible(false);
		lblfilename.setVisible(false);
		filenamet.setVisible(false);
		durationt.setVisible(false);
		with.setVisible(false);
		height.setVisible(false);
		lblheight.setVisible(false);
		textwith.setVisible(false);
		////////////////////////////////////////////////////////////////////////////////////////////////////
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String typ=comboBox.getSelectedItem().toString();
				//System.out.println(typ);
				
				
				
				if(typ.equals("ImagePost")){
					
					lblDuration.setVisible(false);
					lblfilename.setVisible(true);
					filenamet.setVisible(true);
					durationt.setVisible(false);
					with.setVisible(true);
					height.setVisible(true);
					lblheight.setVisible(true);
					textwith.setVisible(true);
		        	
		        	
		        }
				if(typ.equals("VideoPost")){
					
					
					lblDuration.setVisible(true);
					lblfilename.setVisible(true);
					filenamet.setVisible(true);
					durationt.setVisible(true);
					with.setVisible(false);
					height.setVisible(false);
					lblheight.setVisible(false);
					textwith.setVisible(false);
					
		       
			
		        	
		        	
		        }
				if(typ.equals("TextPost")){
					
					
					lblDuration.setVisible(false);
					lblfilename.setVisible(false);
					filenamet.setVisible(false);
					durationt.setVisible(false);
					with.setVisible(false);
					height.setVisible(false);
					lblheight.setVisible(false);
					textwith.setVisible(false);
					
		       
			
		        	
		        	
		        }
			
		    }
		});
	}
}
