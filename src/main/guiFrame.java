package main;

import helper.*;
import java.awt.*;
import javax.swing.*;

public class guiFrame extends JFrame{
	private static int FRAME_WIDTH,FRAME_HEIGHT;
    private static JPanel bcpanel;
    private static JPanel cpanel;
    private static JTextField content,encontent,alttext;
    private static JTextField width,height,label;
    private static JButton encode,save,loadXml,saveXml;
    private static JTextArea bgans,cont;
    private static JButton fcolor,bcolor;
    private static JComboBox enctype,rotation,alignment,labelalign;
    private static JCheckBox labeled;
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    
    public guiFrame(){
    	initDimensions();
        setSize(new Dimension(FRAME_WIDTH+200,FRAME_HEIGHT));	

        setBcPanel();
        setCPanel();
        
        setLayout(new GridBagLayout());
    	
    	add(cpanel,new GBC(0,1));
    	add(bcpanel,new GBC(0,0));
    	pack();
    }
    
    public void initDimensions(){
    	FRAME_WIDTH = d.width*1/2;
    	FRAME_HEIGHT = d.height*2/3;
    }
    
    public void setBcPanel(){
        bcpanel = new JPanel();
        bcpanel.setLayout(new GridBagLayout());
        bcpanel.setBorder(BorderFactory.createTitledBorder("Barcode Image"));
       // bcpanel.setSize(new Dimension(FRAME_WIDTH,(int)(FRAME_HEIGHT*0.5)));
        bcpanel.add(new JScrollPane(bgans = new JTextArea(10,50),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),new GBC(0,0));  
    }
    
    public void setCPanel(){
    	cpanel = new JPanel();
    	cpanel.setLayout(new GridBagLayout());
    	cpanel.setBorder(BorderFactory.createTitledBorder("Barcode Configuration"));

    	cpanel.add(new JLabel("Value to Encode"),new GBC(0,0).setInsets(0,0,0,200));
    	cpanel.add(new JScrollPane(cont = new JTextArea(3,27),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),new GBC(0,1).setInsets(0,5,0,5));
    	String lol[] = {"Love","Faith","Unity"};
    	JPanel temp = new JPanel();
    	temp.setLayout(new GridBagLayout());
    	temp.add(new JLabel("Encoding "),new GBC(0,0).setInsets(0,0,0,50));
    	temp.add(enctype=new JComboBox(lol),new GBC(0,1));
    	temp.add(new JLabel("Rotation "),new GBC(0,2).setInsets(0,0,0,50));
    	temp.add(rotation=new JComboBox(lol),new GBC(0,3));
    	temp.add(new JLabel("Alignment"),new GBC(0,4).setInsets(0,0,0,50));
    	temp.add(alignment=new JComboBox(lol),new GBC(0,5));
    	
    	enctype.setPreferredSize(new Dimension(100,25));
    	rotation.setPreferredSize(new Dimension(100,25));
    	alignment.setPreferredSize(new Dimension(100,25));
    	
    	JPanel temp1 = new JPanel();
    	temp1.setLayout(new GridBagLayout());
    	temp1.add(labeled = new JCheckBox("Generate Label"),new GBC(0,0).setInsets(0,0,0,10));
    	temp1.add(new JLabel("Alternate Label Text"),new GBC(0,1).setInsets(5,0,0,0));
    	temp1.add(label=new JTextField(10),new GBC(0,2));
    	temp1.add(new JLabel("Label location"),new GBC(0,3).setInsets(5,0,0,30));
    	temp1.add(labelalign=new JComboBox(lol),new GBC(0,4).setInsets(0,0,0,15));
    	
    	labelalign.setPreferredSize(new Dimension(100,25));
    	
    	JPanel temp2 = new JPanel();
    	temp2.setLayout(new GridBagLayout());
    	temp2.add(new JLabel("Background"),new GBC(0,0).setInsets(0,0,0,75));
    	temp2.add(bcolor = new JButton(""),new GBC(0,1).setInsets(0,0,0,10));
    	temp2.add(new JLabel("Foreground"),new GBC(1,0).setInsets(0,0,0,70));
    	temp2.add(fcolor = new JButton(""),new GBC(1,1).setInsets(0,0,0,0));    	
    	
    	fcolor.setPreferredSize(new Dimension(140,30));
    	bcolor.setPreferredSize(new Dimension(140,30));
    	
    	cpanel.add(temp,new GBC(1,0,1,3).setInsets(0,5,0,5));
        cpanel.add(temp1,new GBC(2,0,1,3).setInsets(0,5,0,5));
        cpanel.add(temp2,new GBC(0,2));
    }
    
    public static void main(String args[]){
    	EventQueue.invokeLater(new Runnable(){
    		public void run(){
    			JFrame jframe = new guiFrame();
    			jframe.setVisible(true);
    			jframe.setTitle("Barcode Generator");
    			jframe.setLocationRelativeTo(null);
    		}
    	});
    }
}
