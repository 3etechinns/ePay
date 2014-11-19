package cn.edu.seu.view;


import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.edu.seu.dbtest.PCServerCon;


public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JButton querybutton;
	private JButton uploadpicture;
	private JButton run;
	private JLabel img;
	private JPanel outpanel;
	
	public MainWindow(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(430,300,500,400);
		this.setResizable(false);
		
		Container container = this.getContentPane();
		
		outpanel = new JPanel();
		outpanel.setBackground(Color.WHITE);
		outpanel.setLayout(null);
		
		
	/*	
		querybutton = new JButton("查看交易记录");
		querybutton.setSize(120, 30);
		querybutton.setLocation(180, 80);
		querybutton.addActionListener(new ButtonListener1());
		*/
		uploadpicture = new JButton("上传图片");
		uploadpicture.setSize(120, 30);
		uploadpicture.setLocation(180, 160);
		uploadpicture.addActionListener(new ButtonListener2());
		
		run = new JButton("运行");
		run.setSize(120, 30);
		run.setLocation(180, 240);
		run.addActionListener(new ButtonListener3());
		
		//outpanel.add(querybutton);
		outpanel.add(uploadpicture);
		outpanel.add(run);
		container.add(outpanel);
		this.setVisible(true);
	}
	private class ButtonListener1 implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			RecordWindow recordwindow = new RecordWindow();
		}
	}
	
	private class ButtonListener2 implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			UploadPicture uploadpicture = new UploadPicture();
		}
	}
	
	private class ButtonListener3 implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			new Thread(){
				public void run(){
					new PCServerCon();
				}
			}.start();
		}
	}
	
	public static void main(String[] args){
		LoginWindow loginwindow = new LoginWindow();
	}
}
