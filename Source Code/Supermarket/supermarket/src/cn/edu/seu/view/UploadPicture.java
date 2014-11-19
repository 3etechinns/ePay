package cn.edu.seu.view;


import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import cn.edu.seu.dbtest.DBOP;
import cn.edu.seu.imageguide.ImageGuide;

public class UploadPicture extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JButton submit;
	private JButton uploadpicture;
	private JButton deletepicture;
	private JLabel img;
	private JPanel outpanel;
	private JList list;
	private DefaultListModel listmodel;
	private int index;
	private DBOP dbop;
	private List<ImageGuide> imglist;
	
	public UploadPicture(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(430,300,500,400);
		this.setResizable(false);
		
		Container container = this.getContentPane();
		
		outpanel = new JPanel();
		outpanel.setBackground(Color.WHITE);
		outpanel.setLayout(null);
		
		listmodel = new DefaultListModel();
		list = new JList();
		dbop = new DBOP();
		
		imglist = dbop.Get_Image();
		for(int i = 0 ; i < imglist.size() ; i++){
			ImageGuide imgguide = imglist.get(i);
			if(imgguide != null){
				listmodel.addElement(new String(imgguide.getImagename()));
				list.setModel(listmodel);
			}
		}
		
		list.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent lse) {
		        
		        if (lse.getValueIsAdjusting()) {
		        	index = list.getSelectedIndex();
		        	byte [] imgbyte = imglist.get(index).getImagestream();
		        	ImageIcon icon = new ImageIcon(imgbyte);
		        	icon.setImage(icon.getImage().getScaledInstance(120,200,Image.SCALE_DEFAULT));
		        	img.setIcon(icon);
		        	System.out.println(index);
		        	System.out.println(imglist.get(index).getImagename());
		        }
		      }
		    });
		
		JScrollPane scrollpanel = new JScrollPane(list,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpanel.setBackground(Color.WHITE);
		scrollpanel.setSize(350,100);
		scrollpanel.setLocation(10,20);
		
		img = new JLabel();
		img.setSize(120, 200);
		img.setLocation(370, 20);
		outpanel.add(img);
		
		submit = new JButton("确定");
		submit.setSize(100, 30);
		submit.setLocation(290, 220);
		submit.addActionListener(new ButtonListener1());
		
		uploadpicture = new JButton("浏览");
		uploadpicture.setSize(100, 30);
		uploadpicture.setLocation(30, 220);
		uploadpicture.addActionListener(new ButtonListener2());
		
		deletepicture = new JButton("删除");
		deletepicture.setSize(100, 30);
		deletepicture.setLocation(160, 220);
		deletepicture.addActionListener(new ButtonListener3());
		
		outpanel.add(scrollpanel);
		outpanel.add(submit);
		outpanel.add(uploadpicture);
		outpanel.add(deletepicture);
		container.add(outpanel);
		this.setVisible(true);
	}
	private class ButtonListener1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			UploadPicture.this.dispose();
		}

	}
	
	private class ButtonListener2 implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fDialog = new JFileChooser();
			//设置文件选择框的标题 
			fDialog.setAcceptAllFileFilterUsed(false);
			fDialog.addChoosableFileFilter(new FileFilter(){

				@Override
				public boolean accept(File f) {
					if(f.getName().endsWith(".bmp")||f.getName().endsWith(".jpg")||
							f.getName().endsWith(".jpeg")||f.getName().endsWith(".gif")
							||f.getName().endsWith(".png")||f.isDirectory())return true;
					return false;
				}

				@Override
				public String getDescription() {
					// TODO Auto-generated method stub
					return "图像文件(*.bmp;.*jpg;.*jpeg;.*gif;.*png)";
				}
				
			});
			fDialog.setDialogTitle("请选择图片");
			//弹出选择框
			int returnVal = fDialog.showOpenDialog(null);
			// 如果是选择了文件
			if(JFileChooser.APPROVE_OPTION == returnVal){
			       //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
				String url = fDialog.getSelectedFile().toString();
				System.out.println(url);
				String temp=url.replace("\\", " ");
				String [] arr = temp.split(" ");
				String name = arr[arr.length-1];
				ImageGuide imgd = null;
				try {
					imgd = new ImageGuide(null , name , null , ImageOperate.toByteArray(url) , null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dbop.Save_Image(imgd);
				imglist = dbop.Get_Image();
				listmodel.clear();
				for(int i = 0 ; i < imglist.size() ; i++){
					ImageGuide imgguide = imglist.get(i);
					if(imgguide != null){
						listmodel.addElement(new String(imgguide.getImagename()));
						list.setModel(listmodel);
					}
				}
				list.setModel(listmodel);
				ImageIcon icon = new ImageIcon(url);
				icon.setImage(icon.getImage().getScaledInstance(120,160,Image.SCALE_DEFAULT));
				img.setIcon(icon);
				
				index = -1;
			}
		}
	}
	private class ButtonListener3 implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(index > -1){
				System.out.println(imglist.get(index).getImagename());
				dbop.Delete_Image(imglist.get(index));
				listmodel.remove(index);
				list.setModel(listmodel);
				imglist.remove(index);
				img.setIcon(null);
				index = -1;
			}
		}
	}
	
	public static void main(String[] args){
		LoginWindow loginwindow = new LoginWindow();
	}
}