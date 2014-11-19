package cn.edu.seu.view;


import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.awt.AWTUtilities;


public class Window extends JFrame{
	private boolean isMoved; 
	private Point pre_point;  
	private Point end_point;  
	private Button closebutton;
	private Button settingbutton;
	private Button stopbutton;
	private Button searchbutton;
	public static JTextArea area;
	public Window()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container parentContain = getContentPane();
		parentContain.setBackground(Color.WHITE);
		setSize(400,300);
		setResizable(false);
		setDragable(this); 
		setUndecorated(true);
		int Width = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
		int Height = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
		setLocation((int)(Width-getWidth())/2,(int)(Height-getHeight())/2);
		setVisible(true);
	}
	
	 private void setDragable(final Component lui) {  
	        this.addMouseListener(new java.awt.event.MouseAdapter() 
	        {  
	            public void mouseReleased(java.awt.event.MouseEvent e)
	            {  
	                isMoved = false;
	                lui.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
	            }  
	  
	            public void mousePressed(java.awt.event.MouseEvent e)
	            {  
	                isMoved = true;  
	                pre_point = new Point(e.getX(), e.getY());// �õ�����ȥ��λ��  
	                lui.setCursor(new Cursor(Cursor.MOVE_CURSOR));  
	            }  
	        });  
	        //�϶�ʱ��ǰ������ȥ��갴��ȥʱ����꣬���ǽ�����Ҫ�ƶ���������  
	        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
	        {  
	            public void mouseDragged(java.awt.event.MouseEvent e) 
	            {  
	                if (isMoved)
	                {// �ж��Ƿ������ק  
	                    end_point = new Point(lui.getLocation().x + e.getX() - pre_point.x,  
	                            lui.getLocation().y + e.getY() - pre_point.y);  
	                    lui.setLocation(end_point);  
	                }  
	            }  
	        });  
	    }  
	 public static void main(String argv[])
	 {
			Window window= new Window();
	 }
	
}
