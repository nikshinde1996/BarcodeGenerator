package main;

import helper.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class guiFrame extends JFrame{
	
	public static JPanel barcodePanel,configPanel;
	public static int FRAME_WIDTH,FRAME_HEIGHT;
	public static JTextArea toEncodeText,encodedText;
	public static JComboBox encoding,alignment,labelLocation;
	public static String[] alignmentTypes = {" Left "," Right "," Top "," Bottom "," Center "};
	public static String[] labelLocationsTypes = {" Left "," Right "," Top "," Bottom "," Center "};
	public static ColorChooserButton fcolor,bcolor;
	public static JButton encode,saveAs,savexml;
	public static JScrollPane toEncodeSPane,encodedSPane;
	public static JCheckBox label;
	public static JTextField labelText;
	public static String enctext;
	public static Color foreColor,backColor;
	public static BarcodeTypes bctypes = new BarcodeTypes();
	public static EncodeBarcode encbarcode = new EncodeBarcode();
	public static JFileChooser fileChooser = new JFileChooser();
	public static boolean ENCODEFLAG;
	public static ImageIcon image;
	public static BufferedImage bi;
	
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	
	public guiFrame(){
		initDimensions();
		populateConfigPanel();
		populateBarcodePanel();
	    setListeners();
	    setDefaultValues();
	    
		setSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
		setLayout(new GridBagLayout());
		
		add(barcodePanel,new GBC(0,0).setWeight(1,0.8).setFill(GBC.BOTH));
		add(configPanel,new GBC(0,1).setWeight(1,0.2).setFill(GBC.BOTH));

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void initDimensions() {
		FRAME_WIDTH = d.width/3;
		FRAME_HEIGHT = 2*d.height/3;
	}
	
	public static void setDefaultValues() {
		foreColor = Color.BLACK;
		backColor = Color.WHITE;
	    fcolor = new ColorChooserButton(foreColor);
		bcolor = new ColorChooserButton(backColor);
		ENCODEFLAG = false;
		toEncodeText.setText("");
		encodedText.setText("");
		barcodePanel.removeAll();
		barcodePanel.revalidate();
		fcolor.setSelectedColor(foreColor);
		bcolor.setSelectedColor(backColor);
	}
	
	public static void populateBarcodePanel(){
		barcodePanel = new JPanel();
		barcodePanel.setBorder(BorderFactory.createTitledBorder("  Barcode Image  "));
	    barcodePanel.setLayout(new BorderLayout());
	}
	
	public static void populateConfigPanel(){
		configPanel = new JPanel();
		configPanel.setBorder(BorderFactory.createTitledBorder("  Barcode Configuration  "));
	    configPanel.setLayout(new GridBagLayout());
	    
	    configPanel.add(new JLabel("Value to Encode"),new GBC(0,0,2,1).setInsets(0,0,3,120));
	    configPanel.add(new JLabel("Encoded Value"),new GBC(2,0,2,1).setInsets(0,0,3,120));
		configPanel.add(toEncodeSPane=new JScrollPane(toEncodeText = new JTextArea(3,20),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),new GBC(0,1,2,1).setInsets(0,0,3,5));
		configPanel.add(encodedSPane=new JScrollPane(encodedText = new JTextArea(3,20),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),new GBC(2,1,2,1));
	    encodedText.setEditable(false);
		
    	toEncodeSPane.setPreferredSize(new Dimension((int)(FRAME_WIDTH*0.45),(int)(FRAME_HEIGHT*0.2)));
    	encodedSPane.setPreferredSize(new Dimension((int)(FRAME_WIDTH*0.45),(int)(FRAME_HEIGHT*0.2)));
    		
		configPanel.add(new JLabel("Encoding"),new GBC(0,2).setInsets(5,0,2,50));
	    configPanel.add(encoding = new JComboBox(bctypes.encodingTypes),new GBC(0,3));
	    encoding.setPreferredSize(new Dimension(100,25));
	    configPanel.add(new JLabel("ForeGround"),new GBC(1,2).setInsets(5,0,2,10));
	    configPanel.add(fcolor = new ColorChooserButton(Color.BLACK),new GBC(1,3));
	    fcolor.setPreferredSize(new Dimension(80,25));
	    
	    configPanel.add(new JLabel("Alignment"),new GBC(0,4).setInsets(10,0,2,45));
	    configPanel.add(alignment = new JComboBox(alignmentTypes),new GBC(0,5));
	    alignment.setPreferredSize(new Dimension(100,25));
	    configPanel.add(new JLabel("BackGround"),new GBC(1,4).setInsets(10,0,2,10));
	    configPanel.add(bcolor = new ColorChooserButton(Color.WHITE),new GBC(1,5));
	    bcolor.setPreferredSize(new Dimension(80,25));
	    
	    configPanel.add(encode = new JButton(" Encode "),new GBC(0,6).setInsets(15,7,0,10));
	    encode.setPreferredSize(new Dimension(100,25));
	    
	    configPanel.add(saveAs = new JButton("Save As"),new GBC(1,6).setInsets(15,0,0,0));
	    saveAs.setPreferredSize(new Dimension(80,25));
	    
	    configPanel.add(new JLabel("Alternate Label Text"),new GBC(2,2,2,1).setInsets(5,0,2,90));
	    configPanel.add(labelText = new JTextField(18),new GBC(2,3,2,1));
	    labelText.setEnabled(false);
	    configPanel.add(new JLabel("Label location"),new GBC(2,4,2,1).setInsets(10,0,2,120));
	    configPanel.add(labelLocation = new JComboBox(labelLocationsTypes),new GBC(2,5,2,1));
	    labelLocation.setEnabled(false);
	    labelLocation.setPreferredSize(new Dimension(200,25));
	    
	    configPanel.add(savexml = new JButton("SaveXML"),new GBC(2,6).setInsets(15,0,0,0));
	    savexml.setPreferredSize(new Dimension(80,25));
	    configPanel.add(label = new JCheckBox("Generate Label"),new GBC(3,6).setInsets(15,0,0,0));
		
	}
	
	public  void setListeners() {
	    
		label.addActionListener(e->{
	        if(label.isSelected()) {
	        	labelText.setEnabled(true);
		        labelLocation.setEnabled(true);	
	        }else {
	        	labelText.setEnabled(false);
		        labelLocation.setEnabled(false);		
	        }
	    });
		
		encode.addActionListener(e->{
			if(toEncodeText.getText().equals("")) {
			   JOptionPane.showMessageDialog(this, "Please enter value to encode !!!");	
			}else {
	           encodeImage();
	           ENCODEFLAG = true;
			}
		});
		
		saveAs.addActionListener(e->{
			
			if(!ENCODEFLAG || image==null) {
				JOptionPane.showMessageDialog(this, "Please Encode image before saving !!!");
			}else {
			   saveImage();		
			}
		});
		
		fcolor.addActionListener(e->{
			foreColor = fcolor.getSelectedColor();
			System.out.println("FCOlor : "+foreColor.toString());
		});
		
		bcolor.addActionListener(e->{
			backColor = bcolor.getSelectedColor();
			System.out.println("BCOlor : "+backColor.toString());
		});
	}
	
	public void saveImage() {
		fileChooser.setDialogTitle("Select folder to save Image");
        fileChooser.setCurrentDirectory(new File("."));
		int userSelection = fileChooser.showSaveDialog(this);	
		if(userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
		
			System.out.println(fileToSave.getAbsolutePath());
			try {
				ImageIO.write(bi,"png",fileToSave);
				JOptionPane.showMessageDialog(this,"Barcode Image saved Successfully !!!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setDefaultValues();
	}
	
	public static void encodeImage() {
	     String encodType = encoding.getSelectedItem().toString();
	     String alignmentType = alignment.getSelectedItem().toString();
	     
	     barcodePanel.removeAll();
	     int typeno = bctypes.getValue(encoding.getSelectedItem().toString());
	     image =  encbarcode.getImageIcon(encodType,toEncodeText.getText(),backColor,foreColor);
         bi = (BufferedImage) image.getImage();
	     JLabel labelImage = new JLabel("",image,JLabel.CENTER);
         barcodePanel.add(labelImage,BorderLayout.CENTER);
         barcodePanel.revalidate();
	     ENCODEFLAG = true;
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				JFrame jframe = new guiFrame();
				jframe.setVisible(true);
				jframe.setTitle("Barcode Generator");
				jframe.setLocationRelativeTo(null);
			    jframe.setIconImage(new ImageIcon("C:\\Users\\Nikhil Shinde\\workspace-java\\BarcodeGenerator\\res\\appIcon.jpg").getImage());
			}
		});
	}
}