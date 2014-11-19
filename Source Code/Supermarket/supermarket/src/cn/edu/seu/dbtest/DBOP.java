package cn.edu.seu.dbtest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import cn.edu.seu.imageguide.IImageGuideDAO;
import cn.edu.seu.imageguide.ImageGuide;
import cn.edu.seu.imageguide.ImageGuideDAO;
import cn.edu.seu.supermarketprice.ISupermarketPriceDAO;
import cn.edu.seu.supermarketprice.SupermarketPrice;
import cn.edu.seu.supermarketprice.SupermarketPriceDAO;
import cn.edu.seu.xmlsave.IXmlSaveDAO;
import cn.edu.seu.xmlsave.XmlSave;
import cn.edu.seu.xmlsave.XmlSaveDAO;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;
import oracle.sql.CLOB;


public class DBOP {
	/*
	DBOP()
	{
		url="jdbc:oracle:thin:@localhost:1521/";
		SID="CITICUP";
		USERNAME="SYSTEM";
		PASSWORD="citicup";
	}
	
	DBOP(String My_Sid,String My_Username,String My_Password)
	{
		url="jdbc:oracle:thin:@localhost:1521/";
		SID=My_Sid;
		USERNAME=My_Username;
		PASSWORD=My_Password;
	}
	
	private Connection Connect_mall_db() throws ClassNotFoundException,SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");      
		Connection conn =DriverManager.getConnection(url+SID,USERNAME,PASSWORD);     
		conn.setAutoCommit(false);
		return conn;    
	}
	*/
	/*
	public void Query_all() throws ClassNotFoundException,SQLException
	{
		String query_result=null;
		Connection tempcon=Connect_mall_db();
		Statement stmt = tempcon.createStatement();    
		ResultSet rset =          stmt.executeQuery("select * from supermarket_price");    
		while (rset.next()) 
		{        
			System.out.println(rset.getString(1)+"  "+rset.getString(2)+"  "+rset.getString(3)); 
			}    
		stmt.close(); 
	}
	*/
	public String Query_price(String ID) throws ClassNotFoundException,SQLException
	{
		String price = null;
		/*
		Connection tempcon=Connect_mall_db();
		Statement stmt = tempcon.createStatement();    
		ResultSet rset =          stmt.executeQuery("select GOODSPRICE from supermarket_price where GOODSBARCODE='"+ID+"'");    
		while (rset.next()) 
		{        
			System.out.println(rset.getString(1)); 
			price=rset.getString(1);
			}    
		stmt.close();    
		*/
		
		ISupermarketPriceDAO supermarket_price_dao=new SupermarketPriceDAO();
		SupermarketPrice supermarket_price=supermarket_price_dao.findById(ID);
		price=supermarket_price.getGoodsprice();
		return price;
	}
	
	public Goods Query_goods(String ID) throws ClassNotFoundException,SQLException
	{
		Goods newgoods=new Goods("","","","");
/*		Connection tempcon=Connect_mall_db();
		Statement stmt = tempcon.createStatement();    
		ResultSet rset =          stmt.executeQuery("select * from supermarket_price where GOODSBARCODE='"+ID+"'"); 
		while(rset.next())
		{
			newgoods.setBarcode(ID);
			newgoods.setName(rset.getString(2));
			newgoods.setPrice(rset.getString(3));
		}
		stmt.close();*/
		
		ISupermarketPriceDAO supermarket_price_dao=new SupermarketPriceDAO();
		SupermarketPrice supermarket_price=supermarket_price_dao.findById(ID);
		if(supermarket_price!=null)
		{
			newgoods.setBarcode(ID);
			newgoods.setName(supermarket_price.getGoodsname());
			newgoods.setPrice(supermarket_price.getGoodsprice());
			return newgoods;
		}
		else
		{
			return newgoods;
		}
	}
	
	public String Cacu_price(ArrayList<String> IDList,ArrayList<String> quantityList) throws ClassNotFoundException,SQLException
	{
		String price = null;
		/*Connection tempcon=Connect_mall_db();
		Statement stmt = tempcon.createStatement();   
		*/ 
		int length=IDList.size();
		double sum_price=0;
		Double tem_price=new Double(0);
		for(int counter=0;counter<length;counter++)
		{
			String ID=(String) IDList.get(counter);
			String number=(String) quantityList.get(counter);
			int num=Integer.valueOf(number);
			
			ISupermarketPriceDAO supermarket_price_dao=new SupermarketPriceDAO();
			SupermarketPrice supermarket_price=supermarket_price_dao.findById(ID);
			price=supermarket_price.getGoodsprice();
			tem_price=num*Double.valueOf(price);
			sum_price+=tem_price.doubleValue();
			
			/*
		ResultSet rset =          stmt.executeQuery("select GOODSPRICE from supermarket_price where GOODSBARCODE='"+ID+"'");    
		while (rset.next()) 
		{        
			price=rset.getString(1);
			tem_price=num*Double.valueOf(price);
			sum_price+=tem_price.doubleValue();
			}    
			*/
		}
		price=price.valueOf(sum_price);
//		stmt.close();   
		return price;
	}
	
	public boolean Save_Xml(String xml) throws SQLException, ClassNotFoundException
	{
		String pointer=null;
		String newpointer=null;
		/*Connection tempcon=Connect_mall_db();
		Statement stmt = tempcon.createStatement(); 
		ResultSet rset =          stmt.executeQuery("select NUM from xml_save where NOTES='pointer'"); 
		while(rset.next())
		{
			pointer=rset.getString(1);
		}
		System.out.println(pointer);*/
		
		IXmlSaveDAO xml_save_dao=new XmlSaveDAO();
		XmlSave pointer_xml=xml_save_dao.findById("pointer");
		pointer=pointer_xml.getNum();
		newpointer=Integer.toString(Integer.parseInt(pointer)+1);
		
		/*stmt.executeUpdate("insert into xml_save values('"+newpointer+"',EMPTY_CLOB(),'xmldoc')");
		tempcon.commit();
		ResultSet rset2 =          stmt.executeQuery("select XMLDOC from xml_save where NUM='"+newpointer+"'"); 
		while(rset2.next())
		{
			CLOB tempclob=((OracleResultSet)rset2).getCLOB(1);
			tempclob.putChars(1, xml.toCharArray());
			PreparedStatement ps=tempcon.prepareStatement("update xml_save set xmldoc=? where NUM='"+newpointer+"'");
			ps.setClob(1, tempclob);
			ps.executeUpdate();
			tempcon.commit();
			tempclob.close();
			ps.close();
		}
		stmt.executeUpdate("update xml_save set NUM='"+newpointer+"' where NOTES='pointer'");
		tempcon.commit();
		stmt.close();*/
		
		XmlSave xml_save=new XmlSave();
		xml_save.setNum(newpointer);
		xml_save.setNotes("xmldoc"+newpointer);
		xml_save.setXmldoc(xml);
		xml_save_dao.save(xml_save);
		pointer_xml.setNum(newpointer);
		xml_save_dao.update(pointer_xml);
		
		return true;
	}
	
	public boolean Save_Image(ImageGuide imgg)
	{
		String pointer=null;
		String newpointer=null;
		IImageGuideDAO image_guide_dao=new ImageGuideDAO();
		ImageGuide pointer_image=image_guide_dao.findById("pointer");
		pointer=pointer_image.getNum();
		newpointer=Integer.toString(Integer.parseInt(pointer)+1);	
		ImageGuide image_save=new ImageGuide();
		image_save.setNum(newpointer);
		image_save.setNotes("image"+newpointer);
		image_save.setImagestream(imgg.getImagestream());
		image_save.setImagename(imgg.getImagename());
		image_save.setRemark("image");
		image_guide_dao.save(image_save);
		pointer_image.setNum(newpointer);
		image_guide_dao.update(pointer_image);
		return true;
	}
	
	public List<ImageGuide> Get_Image()
	{
		IImageGuideDAO image_guide_dao=new ImageGuideDAO();
		List<ImageGuide> get_image=image_guide_dao.findByRemark("image");
		return get_image;
	}
	
	public void Delete_Image(ImageGuide delete_image)
	{
		IImageGuideDAO image_guide_dao=new ImageGuideDAO();
		image_guide_dao.delete(delete_image);
	}
	
	private String url;
	private String SID;
	private String USERNAME;
	private String PASSWORD;
}
