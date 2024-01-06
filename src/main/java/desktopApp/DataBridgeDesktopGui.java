package desktopApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import modernfonts.ModernFonts;
import requestHandler.Calls;
import requestHandler.Timer;


public class DataBridgeDesktopGui {
	
	static JScrollPane scrollPane= null;
	static String selectedFile="";
	public static void main(String[]args) {
		
		Color frameColor=new Color(250,250,250);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,600);
		frame.getContentPane().setBackground(frameColor);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setTitle("DataBridgeDesktop");
		
		
	 	RoundPanel dropPanel = new RoundPanel();
        dropPanel.setBackground(new Color(120, 136, 150));
        dropPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        dropPanel.setBounds(20,60,300,450);
        JLabel text = new JLabel("Drag and Drop");
        text.setFont(ModernFonts.loadFont("Roboto-Regular", 13));
//        text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        text.setForeground(Color.WHITE );
        dropPanel.add(text,constraints);
        DnDTransferHandler dropHandler= new DnDTransferHandler(text);
        dropPanel.setTransferHandler(dropHandler);
        
               
        JCheckBox aliveBox = new JCheckBox("Keep api alive");
        aliveBox.setBounds(20, 24, 150, 30);
        aliveBox.setBorderPainted(false);
        aliveBox.setFocusPainted(false);
        aliveBox.setFont(ModernFonts.loadFont("OpenSans-SemiBold",13));
        aliveBox.setBackground(frameColor);
        aliveBox.addItemListener(new ItemListener() {
        	Timer myrun= new Timer();
        	
			@Override
			public void itemStateChanged(ItemEvent e) {
				Thread alive = new Thread(myrun);				
				if(e.getStateChange()==ItemEvent.SELECTED) {
					myrun.setAllowTimer(true);
					alive.start();
					System.out.println("ALIVE active");
				}else if(e.getStateChange()==ItemEvent.DESELECTED) {
					myrun.setAllowTimer(false);
					
				}
			}
        });
        
        

        RoundButton upload = new RoundButton("Upload");
        upload.setBounds(340,65,120,35);
        upload.setFocusPainted(false);
        upload.setBorderPainted(false);

        upload.setFont(ModernFonts.loadFont("OpenSans-SemiBold",14));
        upload.setBackground(new Color(101, 88, 245));
        upload.setForeground(Color.WHITE );
        upload.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String loc=dropHandler.getFilePath();
				System.out.println("Got File path : "+loc);
				if(loc!=null) {
				Calls.uploadFile(loc);}else {System.out.println("No file selected!");}
			}});
        
        
        
        RoundedProgressBar progress = new RoundedProgressBar(0,100);
//        JProgressBar progress = new JProgressBar(0,100);
        progress.setBounds(180,30,280,16);
        progress.setStringPainted(true);
        progress.setValue(44);
        progress.setBorderPainted(false);
        progress.setBackground(frameColor);
        progress.setForeground(new Color(31, 110, 250));
        
//        JTextField search = new JTextField();
        RoundedCornerTextField search = new RoundedCornerTextField();
        search.setBounds(25,515,290,30);
        search.setBorder(null);
        search.setBackground(new Color(200,200,200));
        search.setFont(ModernFonts.loadFont("OpenSans-SemiBold",13));
        
        
        RoundButton searchButton = new RoundButton("Search");
        searchButton.setBounds(325,515,120,30);
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);

        searchButton.setFont(ModernFonts.loadFont("OpenSans-SemiBold",14));
        searchButton.setBackground(new Color(101, 88, 245));
        searchButton.setForeground(Color.WHITE );
        
        
        
        
        
        RoundButton listFiles = new RoundButton("File List"); 
        listFiles.setBounds(340,230,120,35);
        listFiles.setFocusPainted(false);
        listFiles.setFont(ModernFonts.loadFont("OpenSans-SemiBold",14));
        listFiles.setBackground(new Color(101, 88, 245));
        listFiles.setForeground(Color.WHITE );
        listFiles.setBorderPainted(false);
        listFiles.addActionListener(new ActionListener() {
        	// later on switch this functionality to a different button
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if((listFiles.getText()).equals("File List")) {
				
				dropPanel.setVisible(false);
				JPanel listPanel= new JPanel();
				listPanel.setLayout(new BoxLayout(listPanel,BoxLayout.Y_AXIS));
				listPanel.setBackground(new Color(120, 136, 150));
				
				scrollPane = new JScrollPane(listPanel);				
		        scrollPane.setBounds(20,60,300,450);
		        scrollPane.getVerticalScrollBar().setUI(null);
		        scrollPane.getHorizontalScrollBar().setUI(null);
		        scrollPane.setBorder(null);
		        
		        List<List<String>> data= Calls.getFileList();
		        
//				for(String i:arr) {
		        for(List<String> i :data) {
		        	
					JButton item = new JButton(i.get(0));
					item.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							System.out.println(item.getText());
							selectedFile=item.getText();
						}
						
					});
					item.setFont(ModernFonts.loadFont("OpenSans-SemiBold", 15));
					item.setBackground(null);
					item.setFocusable(false);
					item.setForeground(frameColor);
					item.setBorderPainted(false);
					
					listPanel.add(item);
					
				}
				
				frame.add(scrollPane);
								
				listFiles.setText("Hide List");
			}else {
				frame.remove(scrollPane);
				dropPanel.setVisible(true);
				listFiles.setText("File List");}
				}
        	
        });
        
        
        
        RoundButton download = new RoundButton("Download");
        download.setBounds(340,150,120,35);
        download.setFocusPainted(false);
        download.setFont(ModernFonts.loadFont("OpenSans-SemiBold",14));
        download.setBackground(new Color(101, 88, 245));
        download.setForeground(Color.WHITE );
        download.setBorderPainted(false);
        download.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Calls.downloadFile(selectedFile);
				
			}
        	
        });

               
        frame.add(listFiles);
        frame.add(dropPanel);
        frame.add(upload);
        frame.add(download);
        frame.add(aliveBox);
//        frame.add(progress);
//        frame.add(search);
//        frame.add(searchButton);
        
		
		frame.setVisible(true);
	}
	
}
