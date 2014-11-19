package cn.edu.seu.view;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


public class ImageOperate {

	public static byte[] toByteArray(String url) throws Exception {
	/*	File imageFile = new File(url);
		  BufferedImage img = ImageIO.read(imageFile);
		  ByteArrayOutputStream buf = new ByteArrayOutputStream((int) imageFile.length());
		  try {
		   ImageIO.write(img, "png", buf);
		  } catch (Exception e) {
		   e.printStackTrace();
		   return null;
		  }
		  return buf.toByteArray();*/
		File  imageFile = new File(url);
		FileInputStream fs=new FileInputStream(imageFile);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
	       byte[] buffer = new byte[1024];
	       int len;
	       try {
	           while ((len = fs.read(buffer)) >= 0) {
	               os.write(buffer, 0, len);
	           }
	       } catch (java.io.IOException e) {
	       }
	       return os.toByteArray();
		 }

}
