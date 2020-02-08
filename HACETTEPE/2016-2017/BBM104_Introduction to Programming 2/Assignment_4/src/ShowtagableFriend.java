import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Panel;



import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


public class ShowtagableFriend {

	private JFrame frame;
	private JList<String>	showfrienlist;
	public String selected;
	public JButton  btntagfriend;
	ArrayList<String> withnameusername;
	/**
	 * Launch the application.
	 */
	public static void ShowtagableFriend() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowtagableFriend window = new ShowtagableFriend();
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
	public ShowtagableFriend() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 325, 349);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle("show");
		frame.getContentPane().setLayout(null);
		
		
		
		JButton btntagfriend = new JButton("Tag Friend");
		btntagfriend.setBounds(10, 276, 289, 23);
		frame.getContentPane().add(btntagfriend);
		
		Panel panel = new Panel();
		panel.setBounds(10, 46, 299, 220);
		frame.getContentPane().add(panel);
		
		
		DefaultListModel<String> model1 = new DefaultListModel<>();
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 299, 220);
		panel.add(scrollPane);
		showfrienlist = new JList<>( model1 );
		scrollPane.setViewportView(showfrienlist);
		showfrienlist.setValueIsAdjusting(true);
		showfrienlist.setBorder(new LineBorder(new Color(0, 0, 0)));
		showfrienlist.setVisibleRowCount(10);
		showfrienlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{showfrienlist}));
		
//		
//		for (User online : UserCollection.userlogin) {
//			for(Friends fro: UserCollection.friends){
//				if(online.getUser_name().equals(fro.getusername())){
//					for(String arkadas: fro.getfriend()){
//						
//						System.out.println("model mal ekleniyor");
//						model1.addElement(arkadas);
//					}
//				}
//			}
//			
//
//		}
		
	
	
	
	
		showfrienlist.addListSelectionListener(new ListSelectionListener() {

			

			public void valueChanged(ListSelectionEvent event) {

				if (event.getValueIsAdjusting() == true) {
					
					
					
					
					
					}
				
				}
		
			
			});
		
		JLabel lblNewLabel = new JLabel("Taggable Friends");
		lblNewLabel.setBounds(10, 11, 289, 29);
		frame.getContentPane().add(lblNewLabel);
		
		String idofbutton=null;
		ArrayList<String >  purelist=new   ArrayList<String>();
		Component[] components = Profilpage.getpanel().getComponents();
		for(int i=0;i<components.length;i++){
			try{
			JLabel lbl = (JLabel)Profilpage.getpanel().getComponent(i);
			
			
			
			JButton btn = (JButton)lbl.getComponent(0);
			
		
			
		
			if(btn.getText().equals("Clicked")){
						
				idofbutton=btn.getName();
						
			
						
					
				
				}
			}catch(Exception f){
					
				}
			}
			
			
			int a=0;
			if(a==0){
			String adam=null;
			for(User online: UserCollection.userlogin){
				
				adam=online.getUser_name();
				//System.out.println("online user"+adam);
			}
			
			ArrayList<String >  taglenenarkalar=new   ArrayList<String>();
			for(TextPost  post: UserCollection.posts){
				if(Integer.toString(post.getid()).equals(idofbutton)){
					if(post.getUsername().equals(adam)){
						for(User taglenenler: post.getTaggedfriend()){
							
							taglenenarkalar.add(taglenenler.getUser_name());
							//System.out.println("taglenenler"+taglenenler.getUser_name());
						}
					}
				}
			}
			ArrayList<String >  arkalar=new   ArrayList<String>();
			
			for(Friends friend: UserCollection.friends){
				if(adam.equals(friend.getusername())){
					for(String arka:friend.getfriend()){
						
						arkalar.add(arka);
						//System.out.println("arkadslar"+arka);
					}
				
				}
			}
			ArrayList<String >  taglenebiliuserneme=new   ArrayList<String>();
			for(String  arkadas: arkalar){
				int bb=0;
				for(String  olmayacak: taglenenarkalar){
					if(arkadas.equals(olmayacak)){
						bb=1;
						
					}
				}
				if(bb==0){
					taglenebiliuserneme.add(arkadas);
					//System.out.println("taglenebiliuserneme   "+arkadas);
				}
			}
			
			for(User user : UserCollection.userlist){
				for(String getn :taglenebiliuserneme){
					if(user.getUser_name().equals(getn)){
						purelist.add(user.getnamesurname());
					}
				}
			}
			
			
			
		}
			a=1;
		
		for(String  add: purelist){
		
			//System.out.println("model mal ekleniyor"+add);
			model1.addElement(add);
		}
		
		btntagfriend.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				
				if((showfrienlist.getSelectedValue()==null)==false){
				Profilpage.tagfriend(showfrienlist.getSelectedValue(),Profilpage.getpanel());
				//System.out.println("tagfrienclassindasin");
				Profilpage.deletelabals(Profilpage.getpanel());
				
				for(User uu: UserCollection.userlogin){
				Profilpage.showmymessage(Profilpage.getpanel(),uu.getUser_name());
				}
					
				JOptionPane.showMessageDialog(frame, "Sucess");
				Profilpage.getpanel().repaint();
				frame.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(frame, "select user");
				}
				
				
			}});
		
	
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	
		    	try{
		    	Component[] components =  Profilpage.getpanel().getComponents();
				for(int i=0;i<components.length;i++){
					
					JLabel lbl = (JLabel) Profilpage.getpanel().getComponent(i);
					
					
					JButton btn = (JButton)lbl.getComponent(0);
					btn.setText("Tag Friend");
				
				}
		    	}catch(Exception f){
					
				}
//		    	int selectedOption = JOptionPane.showConfirmDialog(
//						null, "Are you sure you do not want tag any friend?"
//								, "Choose",
//						JOptionPane.YES_NO_OPTION);
//		    	
//		        if (selectedOption == JOptionPane.YES_OPTION){
//		        	frame.setVisible(false);
//		        }
//		        if(selectedOption==JOptionPane.NO_OPTION){
//		        	
//		        	frame.setVisible(false);
//		        }
		    }
		});
		frame.setVisible(true);
	}
	
		
}
