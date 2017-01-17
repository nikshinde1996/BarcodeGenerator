package main;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.onbarcode.barcode.*;

public class EncodeBarcode {
	
	public static BarcodeTypes bctypes = new BarcodeTypes();
    public static String[] enctypes = bctypes.getEncodingTypes();
	
	public EncodeBarcode() {
		
	}
	
	public static ImageIcon getImageIcon(String enctypeStr,String data) {
		BufferedImage image = null;
		if(enctypeStr.equals(enctypes[0])) {
		   //  For Codabar type encoding 
			System.out.println("Enc type : "+enctypeStr );
			image = getCodabar(data);
		}else if(enctypeStr.equals(enctypes[1])) {
		   //  For Code 11 type encoding	
		   image = getCode11(data);
		}
        else if(enctypeStr.equals(enctypes[2])) {
           //  For Code 2 of 5 type encoding	
           image = getCode25(data);
        }
        else if(enctypeStr.equals(enctypes[3])) {
           //  For Code 39 type encoding
           image = getCode39(data);	
        }
        else if(enctypeStr.equals(enctypes[4])) {
           //  For Code 93 type encoding
           image = getCode93(data);
        }
		return new ImageIcon(image);
	}
	
	public static BufferedImage getCodabar(String data) {
		Codabar barcode = new Codabar();
		BufferedImage image = null;
		try {
			barcode.setData(data);
			image = barcode.drawBarcode();
            System.out.println("Data : "+data );
            barcode.drawBarcode("E:\\BarcodeTest\\lol.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static BufferedImage getCode11(String data) {
		Code11 barcode = new Code11();
		BufferedImage image = null;
		try {
			barcode.setData(data);
			image = barcode.drawBarcode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static BufferedImage getCode25(String data) {
		Code25 barcode = new Code25();
		BufferedImage image = null;
		try {
			barcode.setData(data);
			image = barcode.drawBarcode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static BufferedImage getCode39(String data) {
		Code39 barcode = new Code39();
		BufferedImage image = null;
		try {
			barcode.setData(data);
			image = barcode.drawBarcode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public static BufferedImage getCode93(String data) {
		Code93 barcode = new Code93();
		BufferedImage image = null;
		try {
			barcode.setData(data);
			image = barcode.drawBarcode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
