package cn.edu.seu.bankserver;

import java.math.*;
import java.sql.SQLException;
import java.util.*;

import cn.edu.seu.interfaces.IRsaDbDAO;
import cn.edu.seu.rsadb.RsaDb;
import cn.edu.seu.rsadb.RsaDbDAO;

public class Rsa{
 /**
  * @param args
 * @return 
  */
	Rsa()
	{
		 n=new BigInteger("552242638982356744711324281679");
		 e=new BigInteger("2309916916024662023");
		 d=new BigInteger("435982084263274353850676993687");
	}
	
	Rsa(BigInteger o_e,BigInteger o_d)
	{
		 n=new BigInteger("552242638982356744711324281679");
		 e=o_e;
		 d=o_d;
	}
	
	Rsa(String message)
	{
		n=new BigInteger("552242638982356744711324281679");
		if(message.equals("create"))
		{
			String exist="";
			int counter=1;
			do{
				IRsaDbDAO rsa_db_dao=new RsaDbDAO();
				RsaDb rsadb=rsa_db_dao.findById(String.valueOf(counter));
				e=new BigInteger(rsadb.getKeye());
				d=new BigInteger(rsadb.getKeyd());
				exist=rsadb.getTheidu();
				counter++;
				if(exist.equals("nu"))
				{
					rsadb.setTheidu("u");
					rsa_db_dao.update(rsadb);
				}
			}while(exist.equals("u"));
		}
		else
		{
			e=BigInteger.ZERO;
			d=BigInteger.ZERO;
		}
	}
	
	public String getN()
	{
		return n.toString();
	}
	
	public String getE()
	{
		return e.toString();
	}
	
	public String getD()
	{
		return d.toString();
	}
	
 public String Setrsa(String code) {
  // TODO Auto-generated method stub
	
  BigInteger mescode=new BigInteger(code);
  BigInteger sec=mescode.modPow(e, n);
  return sec.toString();
 }
 
 public Boolean Checkrsa(String money,String cipher)
 {
	 BigInteger sec=new BigInteger(cipher);
	  BigInteger seco=sec.modPow(d, n);
	  String checkcode=seco.toString();
	  if(checkcode.endsWith(money))
	  	return true;
	  else
		 return false;
 }
 
 public String Rersa(String cipher)
 {
  BigInteger sec=new BigInteger(cipher);
//  System.out.println("cip="+cipher);
//  System.out.println("d="+d);
//  System.out.println("n="+n);
  BigInteger seco=sec.modPow(d, n);
  return seco.toString();
 }
 
 private BigInteger n;
 private BigInteger e;
 private BigInteger d;
}