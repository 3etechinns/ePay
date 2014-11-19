package cn.edu.seu.view;


import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.jdom2.JDOMException;

import cn.edu.seu.dbtest.BusinessInfo;
import cn.edu.seu.dbtest.Xml_Parse;

public class LoginWindow extends Window{

	private static final long serialVersionUID = 1L;
	private JLabel loginbutton,exit;
	private JTextField username;
	private JTextField password;
	private JLabel result;
	private JLabel connection;
	private JLabel usernamel;
	private JLabel passwordl;
	private JPanel outpanel;
	
	public static BusinessInfo businessinfo;
	
	public LoginWindow(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LineBorder lineBorder = (LineBorder)BorderFactory.createLineBorder(Color.black);
		this.setResizable(false);
		setSize(400,300);
		Container container = this.getContentPane();
		usernamel = new JLabel("用户名");
		usernamel.setSize(40, 30);
		usernamel.setLocation(80, 100);
		
		passwordl = new JLabel("密码");
		passwordl.setSize(30, 30);
		passwordl.setLocation(80, 150);
		
		

		result = new JLabel("");
		result.setSize(60, 30);
		result.setLocation(80, 200);
		
		connection = new JLabel("");
		connection.setSize(180, 25);
		connection.setLocation(161, 174);
		connection.setForeground(Color.red);
		
		outpanel = new JPanel();
		outpanel.setBackground(Color.WHITE);
		outpanel.setLayout(null);
		outpanel.setLocation(180, 100);
		
		username =  new JTextField(20);
		username.setSize(150,30);
		username.setLocation(160, 100);
		
		password =  new JTextField(20);
		password.setSize(150,30);
		password.setLocation(160, 150);
		
		ImageIcon iilogin=new ImageIcon("resources/login.png");
		loginbutton = new JLabel(iilogin);
		loginbutton.addMouseListener(new Listener());
		loginbutton.setSize(200, 40);
		loginbutton.setLocation(120, 200);
		
		ImageIcon iiexit=new ImageIcon("resources/exit.png");
		exit = new JLabel(iiexit);
		exit.addMouseListener(new Listener());
		exit.setSize(200, 40);
		exit.setLocation(120, 240);
		
		outpanel.add(username);
		outpanel.add(connection);
		outpanel.add(password);
		outpanel.add(usernamel);
		outpanel.add(passwordl);
		outpanel.add(loginbutton);
		outpanel.add(exit);
		outpanel.add(result);
		container.add(outpanel);
		this.setVisible(true);
	}
	private class Listener extends  MouseAdapter
	{
		public void mouseClicked(MouseEvent action) {
				
				if(action.getSource()==loginbutton)
				{
					Xml_Parse xp = new Xml_Parse();
					String u = username.getText();
					String p = password.getText();
					System.out.println(u+p);
					try {
						businessinfo = xp.Get_Business_Info(u, p);
						if(businessinfo.getUsername().equals("Failed")){
							result.setText("错误");
						}
						else{
							MainWindow mainwindow = new MainWindow();
							LoginWindow.this.setVisible(false);
						}
					} catch (Exception e) {
						connection.setText("网络连接失败！");
						e.printStackTrace();
					}
				}
				else if(action.getSource()==exit)
				{
					System.exit(0);
				}
			}
		public void mouseEntered(MouseEvent action)
		{
			if(action.getSource()==loginbutton)
			{
				ImageIcon ii=new ImageIcon("resources/login_hover.png");
				loginbutton.setIcon(ii);
			}
			else if(action.getSource()==exit)
			{
				ImageIcon ii=new ImageIcon("resources/exit_hover.png");
				exit.setIcon(ii);
			}
		}
		public void mouseExited(MouseEvent action)
		{
			if(action.getSource()==loginbutton)
			{
				ImageIcon ii=new ImageIcon("resources/login.png");
				loginbutton.setIcon(ii);
			}
			else if(action.getSource()==exit)
			{
				ImageIcon ii=new ImageIcon("resources/exit.png");
				exit.setIcon(ii);
			}
		}
			
		}
	public static void main(String[] args){
		LoginWindow loginwindow = new LoginWindow();
	}
}
