package cn.edu.seu.dbtest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;

import javax.xml.stream.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import cn.edu.seu.dbtest.DBOP;
import cn.edu.seu.imageguide.ImageGuide;
import cn.edu.seu.view.LoginWindow;

import org.jdom2.*;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.StAXStreamBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Xml_Parse {
	
	public Xml_Parse()
	{
		xml_doc=new Document();
		price_xml=new Document();
		save_doc=new Document();
	}

	public Xml_Parse(byte [] xml_file) throws JDOMException, IOException, XMLStreamException, TransformerException {
		DOMResult docResult=new DOMResult();
		Transformer tran=TransformerFactory.newInstance().newTransformer();
		tran.transform(new StreamSource(new ByteArrayInputStream(xml_file)), docResult);
		  DOMBuilder builder = new DOMBuilder();
//		  InputStream file = new FileInputStream("src/xml/test.xml");
//		  xml_doc = builder.build(xs);
		org.w3c.dom.Document Oxml_doc=(org.w3c.dom.Document)docResult.getNode();
		xml_doc=builder.build(Oxml_doc);
		price_xml=new Document();
		save_doc=builder.build(Oxml_doc);
//		  System.out.print(xml_doc.toString());
		 }
	
	public void Xml_deal(OutputStream out_result) throws IOException, JDOMException, ClassNotFoundException, SQLException, XMLStreamException, TransformerException
	{
		if(xml_doc.hasRootElement()==true)
		{
			Element root = xml_doc.getRootElement();//��ø�ڵ�
			String event=root.getAttributeValue("event");
			if(event.equals("getPrice"))
			{
				One_Goods(out_result);
			}
			else if(event.equals("getTotalPrice"))
			{
				Total_Price(out_result);
			}
			else if(event.equals("confirmTrade"))
			{
				Buyer_Pay(out_result);
			}
			else if(event.equals("sentence"))
			{
				Xml_DealSen(out_result,event);
			}
			else if(event.equals("strangebalance"))
			{
				Xml_Send(out_result,xml_doc);
			}
			else if(event.equals("guide"))
			{
				Guide_Image(out_result);
			}
			else
			{
				Xml_Error(out_result);
			}
		}
		else
		{
			Xml_Error(out_result);
		}
	}
	
	public BusinessInfo Get_Business_Info(String name,String password) throws IOException, JDOMException, XMLStreamException, TransformerException
	{
		Element root=new Element("information");
		Element xml_name=new Element("name");
		Element xml_password=new Element("password");
		xml_name.setText(name);
		xml_password.setText(password);
		root.setAttribute("event", "supermarketLogin");
		root.addContent(xml_name);
		root.addContent(xml_password);
		Document login_doc=new Document(root);
		Socket market_to_bank=createSocket();
		OutputStream market_to_bank_out=market_to_bank.getOutputStream();
		InputStream bank_to_market_in=market_to_bank.getInputStream();
		Xml_Send(market_to_bank_out,login_doc);
		int xml_len=readXML(bank_to_market_in);
		byte[] xml_buf=new byte[xml_len];
		bank_to_market_in.read(xml_buf);
		byte[] new_xml_buf=new byte[xml_len];
		System.arraycopy(xml_buf, 0, new_xml_buf,0,xml_len);
		System.out.println("\n"+new String(new_xml_buf));
		Xml_Parse input_xml_deal=new Xml_Parse(xml_buf);
		BusinessInfo ret=input_xml_deal.Deal_Business();
		return ret;
	}
	
	private BusinessInfo Deal_Business() {
		// TODO Auto-generated method stub
		if(xml_doc.hasRootElement()==true)
		{
			Element root = xml_doc.getRootElement();//��ø�ڵ�
			String event=root.getAttributeValue("event");
			if(event.equals("businessInfo"))
			{
				BusinessInfo ret=new BusinessInfo();
				ret.setStorename(root.getChildText("storename"));
				ret.setCardnum(root.getChildText("cardnum"));
				ret.setBalance(root.getChildText("balance"));
				ret.setUsername(root.getChildText("username"));
				return ret;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}


	private void Guide_Image(OutputStream out_result) throws IOException {
		// TODO Auto-generated method stub
		DBOP images_db=new DBOP();
		List<ImageGuide> image_list=images_db.Get_Image();
		int size=image_list.size();
		Document size_xml=new Document();
		String string_image=String.valueOf(size);
		Element image_root = new Element("information");
		image_root.setAttribute("event", "size");
		image_root.addContent(string_image);
		size_xml.setRootElement(image_root);
//		System.out.println("HuangK"+price_xml.toString());
		Xml_Send(out_result,size_xml);
		for(ImageGuide intor:image_list)
			Send_Byte_Array_Xml(intor.getImagestream(),out_result);
	}

	private void Send_Byte_Array_Xml(byte[] imagestream, OutputStream out_result) throws IOException {
		// TODO Auto-generated method stub
		int length=imagestream.length;
		String len=Integer.toString(length);
		byte[] leng=len.getBytes();
		byte[] sendlen=new byte[16];
		int lit=16-leng.length;
		for(int counter=0;counter<leng.length;counter++)
			sendlen[lit+counter]=leng[counter];
		out_result.write(sendlen);
		out_result.flush();		
		out_result.write(imagestream);
		out_result.flush();
		System.out.println("莫名其妙的黄K "+len+" 真的莫名其妙 "+new String(imagestream));
	}

	private void Xml_DealSen(OutputStream out_result,String event) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(event);
		Xml_Send(out_result,xml_doc);
	}

	public void Xml_Send(OutputStream out_result,Document xml_to_send) throws IOException
	{
		XMLOutputter out = new XMLOutputter();
		Format format=out.getFormat();
		format.setEncoding("utf-8");
		out.setFormat(format);
		String Xml_Str=out.outputString(xml_to_send);
		System.out.println("test"+Xml_Str);
		int length=Xml_Str.getBytes().length;
		String len=Integer.toString(length);
		byte[] leng=len.getBytes();
		byte[] sendlen=new byte[16];
		int lit=16-leng.length;
		for(int counter=0;counter<leng.length;counter++)
			sendlen[lit+counter]=leng[counter];
//		System.out.println(new String(sendlen));
//		System.out.println(Xml_Str);
		out_result.write(sendlen);
		out_result.flush();		
		out_result.write(Xml_Str.getBytes());
		out_result.flush();
	}
	
	public void Xml_Save_To_Database(Document xml_to_save) throws IOException, SQLException, ClassNotFoundException
	{
		XMLOutputter out = new XMLOutputter();
		Format format=out.getFormat();
		format.setEncoding("utf-8");
		out.setFormat(format);
		String Xml_Str=out.outputString(xml_to_save);
		Xml_Str.replace("\n", "");
		System.out.println("test"+Xml_Str);
		DBOP save=new DBOP();
		save.Save_Xml(Xml_Str);
	}
	
	public void Xml_Error(OutputStream out_result) throws IOException
	{
		String error="Not standard Xml doc.";
		int length=error.getBytes().length;
		String len=Integer.toString(length);
		byte[] leng=len.getBytes();
		byte[] sendlen=new byte[16];
		int lit=16-leng.length;
		for(int counter=0;counter<leng.length;counter++)
			sendlen[lit+counter]=leng[counter];
		System.out.println(new String(sendlen));
		System.out.println(error);
		out_result.write(sendlen);
		out_result.flush();		
		out_result.write(error.getBytes());
	}
	
	public Boolean One_Goods(OutputStream out_result) throws JDOMException, FileNotFoundException, IOException, ClassNotFoundException, SQLException
	{
		Element root = xml_doc.getRootElement();//��ø�ڵ�
		Element gl=root.getChild("goodslist");
		List<Element> list = gl.getChildren();
		DBOP Qurry_Db =new DBOP();
		for(Element e:list) {
			   if(e.getName()=="goods")
			   {
				   Goods goods=new Goods(Qurry_Db.Query_goods(e.getChildText("barcode")));
				   if(goods.getName().equals("")||goods.getName().isEmpty())
				   {
					   Non_Goods(out_result);
					   return false;
				   }
				   System.out.println(e.getChildText("barcode")+goods.getName()+" "+goods.getPrice());
				   e.getChild("name").setText(goods.getName());
				   e.getChild("price").setText(goods.getPrice());
			   }
			  }
		Xml_Send(out_result,xml_doc);
		return true;
		/*
		XMLOutputter out = new XMLOutputter();
		String Xml_Str=out.outputString(xml_doc);
		int length=Xml_Str.getBytes().length;
		String len=Integer.toString(length);
		byte[] leng=len.getBytes();
		byte[] sendlen=new byte[16];
		int lit=16-leng.length;
		for(int counter=0;counter<leng.length;counter++)
			sendlen[lit+counter]=leng[counter];
		System.out.println(new String(sendlen));
		System.out.println(Xml_Str);
		out_result.write(sendlen);
		out_result.flush();		
		out_result.write(Xml_Str.getBytes());
//		out.output(xml_doc, out_result);
//		String test="test";
//		out_result.write(test.getBytes());
		out_result.flush();
		*/
	}
	
	public boolean Total_Price(OutputStream out_result)throws JDOMException, FileNotFoundException, IOException, ClassNotFoundException, SQLException
	{
		double temp_price=0;
		Element root = xml_doc.getRootElement();//��ø�ڵ�
		Element gl=root.getChild("goodslist");
		List<Element> list = gl.getChildren();
		DBOP Qurry_Db =new DBOP();
		ArrayList<String> goods_list=new ArrayList();
		ArrayList<String> quantity_list=new ArrayList();
		for(Element e:list) {
			   if(e.getName()=="goods")
			   {
				   if(Qurry_Db.Query_goods(e.getChildText("barcode")).getName().equals("")||Qurry_Db.Query_goods(e.getChildText("barcode")).getName().isEmpty())
				   {
					   Non_Goods(out_result);
					   return false;
				   }
				   goods_list.add(e.getChildText("barcode"));
				   quantity_list.add(e.getChildText("quantity"));
			   }
			  }
		temp_price+=Double.valueOf(Qurry_Db.Cacu_price(goods_list,quantity_list));
		String price=null;
		price=price.valueOf(temp_price);
//		Document price_xml=new Document();
		Element price_root = new Element("information");
		price_root.setAttribute("event", "sendTotalPrice");
		Element ele_price=new Element("totalprice");
		ele_price.setText(price);
		price_root.addContent(ele_price);
		price_xml.setRootElement(price_root);
		Xml_Send(out_result,price_xml);
		return true;
		/*
		File file=new File("C:\\po.xml");
		OutputStream fileout=new FileOutputStream(file);
		XMLOutputter out = new XMLOutputter();
		out.output(price_xml,fileout);
		fileout.close();
		*/
		/*
		XMLOutputter out = new XMLOutputter();
		String Xml_Str=out.outputString(price_xml);
		int length=Xml_Str.getBytes().length;
		String len=Integer.toString(length);
		byte[] leng=len.getBytes();
		byte[] sendlen=new byte[16];
		int lit=16-leng.length;
		for(int counter=0;counter<leng.length;counter++)
			sendlen[lit+counter]=leng[counter];
		System.out.println(new String(sendlen));
		System.out.println(Xml_Str);
		out_result.write(sendlen);
		out_result.flush();		
		out_result.write(Xml_Str.getBytes());
		out_result.flush();
		*/
	}
	private Socket createSocket()
	{
		Socket market_to_bank=null;
		Properties server=PropertyInfo.getProperties();
		
		
		try
		{
			 market_to_bank=new Socket(server.getProperty("serverAddress"),Integer.parseInt(server.getProperty("serverPort")));

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return market_to_bank;
	}
	public boolean Buyer_Pay(OutputStream out_result) throws IOException, ClassNotFoundException, SQLException, XMLStreamException, TransformerException
	{
		Element root = xml_doc.getRootElement();
		Element trade=root.getChild("trade");
		Element store_account=new Element("storenum");
		store_account.setText(LoginWindow.businessinfo.getCardnum());
		trade.addContent(store_account);
		Element send_to_server=root;
		send_to_server.removeChildren("goodslist");
		price_xml.setRootElement(send_to_server.clone());
		System.out.println("point 1");
		Socket market_to_bank=createSocket();
		OutputStream market_to_bank_out=market_to_bank.getOutputStream();
		InputStream bank_to_market_in=market_to_bank.getInputStream();
		Xml_Send(market_to_bank_out,price_xml);
		int xml_len=readXML(bank_to_market_in);
		byte[] xml_buf=new byte[xml_len];
		bank_to_market_in.read(xml_buf);
		byte[] new_xml_buf=new byte[xml_len];
		System.arraycopy(xml_buf, 0, new_xml_buf,0,xml_len);
		System.out.println("\n"+new String(new_xml_buf));
		try {
		Xml_Parse input_xml_deal=new Xml_Parse(xml_buf);
		input_xml_deal.Xml_deal(out_result);
		Xml_Save_To_Database(xml_doc);
		return true;
	} catch (JDOMException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	}
	
	public int readXML(InputStream bank_to_market_in) throws IOException
	{
		byte[] buffer=new byte[16];
		bank_to_market_in.read(buffer);
		if(!new String(buffer).equals(null))
		{
		int counter=0;
		for(;counter<16;counter++)
			if(buffer[counter]!='\0')
				break;
		byte[] length=new byte[16-counter];
		for(int con=0;con<16-counter;con++)
			length[con]=buffer[counter+con];
		String Str_Len=new String(length);
		int xml_len=0;
		try
		{
			xml_len=Integer.valueOf(Str_Len);
			return xml_len;
		}
		catch(NumberFormatException n)
		{
			n.getStackTrace();
			return 0;
		}
		}
		else
			return 0;
	}
	
	public void Non_Goods(OutputStream out_result) throws IOException
	{
		Element price_root = new Element("information");
		price_root.setAttribute("event", "sentence");
		price_root.addContent("条形码不存在");
		price_xml.setRootElement(price_root);
		System.out.println("HuangK"+price_xml.toString());
		Xml_Send(out_result,price_xml);
	}
	
	public void Pay_Default(OutputStream out_result) throws IOException
	{
		Element price_root = new Element("information");
		price_root.setAttribute("event", "sentence");
		price_root.addContent("付款失败");
		price_xml.setRootElement(price_root);
		Xml_Send(out_result,price_xml);
	}
	
	private Document xml_doc;
	private Document price_xml;
	private Document save_doc;
}

