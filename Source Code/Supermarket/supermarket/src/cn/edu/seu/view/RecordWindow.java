package cn.edu.seu.view;


import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RecordWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	public RecordWindow(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(430,300,500,400);
		this.setResizable(false);
		
		Container container = this.getContentPane();
		
		Object [][] data = {{"01","huangk","lanyadizhi1","imei1","piaruihua","lanyadizhi2","imei2","100","7.11"}};
		String[] columnName = {"交易编号","付款方用户名","付款方蓝牙地址","付款方串号","收款方用户名","收款方蓝牙地址","收款方串号","交易金额","交易时间"};
		JTable table = new JTable(data, columnName);
		table.setRowHeight(25);

		JScrollPane outpanel = new JScrollPane(table);
		outpanel.setBackground(Color.WHITE);
		
		container.add(outpanel);
		this.setVisible(true);
	}
}
