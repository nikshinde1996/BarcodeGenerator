package main;

import helper.*;
import java.awt.*;
import javax.swing.*;

public class guiFrame extends JFrame{
	private static int FRAME_WIDTH,FRAME_HEIGHT;
    private static JPanel bcpanel;
    private static JPanel cpanel;
    private static JTextField content,encontent,alttext;
    private static JTextField width,height;
    private static JButton encode,save,loadXml,saveXml;
    private static JTextArea bgans;
    private static JButton fcolor,bcolor;
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    
    public guiFrame(){
    	initDimensions();
        setSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));	

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
        bcpanel.setBorder(BorderFactory.createTitledBorder("Barcode"));
        bcpanel.add(new JScrollPane(bgans = new JTextArea(10,50),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),new GBC(0,0));
        
        
    }
    
    public void setCPanel(){
    	cpanel = new JPanel();
    	cpanel.setLayout(new GridBagLayout());
    	cpanel.setBorder(BorderFactory.createTitledBorder("Barcode Configuration"));
    	
    	cpanel.add(new JLabel("Value to Encode"),new GBC(0,0));
        cpanel.add(new JScrollPane(bgans = new JTextArea(3,10),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),new GBC(0,1));
    	cpanel.add(new JLabel("Foreground"),new GBC(1,0));
    	cpanel.add(fcolor = new JButton(""),new GBC(1,1));
    	fcolor.setBackground(Color.white);
    
    	cpanel.add(new JLabel("Background"),new GBC(2,0));
    	cpanel.add(bcolor = new JButton(""),new GBC(2,1));
        bcolor.setBackground(Color.black);
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
