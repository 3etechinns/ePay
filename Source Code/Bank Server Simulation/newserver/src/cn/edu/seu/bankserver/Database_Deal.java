package cn.edu.seu.bankserver;

import java.math.BigInteger;
import java.sql.*;
import java.util.List;

import cn.edu.seu.bankaccountinfo.BankAccountInfo;
import cn.edu.seu.bankaccountinfo.BankAccountInfoDAO;
import cn.edu.seu.banktrade.BankTrade;
import cn.edu.seu.banktrade.BankTradeDAO;
import cn.edu.seu.businessinfo.BusinessInfo;
import cn.edu.seu.businessinfo.BusinessInfoDAO;
import cn.edu.seu.elenoteinfo.ElenoteInfo;
import cn.edu.seu.elenoteinfo.ElenoteInfoDAO;
import cn.edu.seu.interfaces.IBankAccountInfoDAO;
import cn.edu.seu.interfaces.IBankTradeDAO;
import cn.edu.seu.interfaces.IBusinessInfoDAO;
import cn.edu.seu.interfaces.IElenoteInfoDAO;
import cn.edu.seu.interfaces.IPersonDepositInfoDAO;
import cn.edu.seu.interfaces.IPersonInfoDAO;
import cn.edu.seu.interfaces.IPersonInterestInfoDAO;
import cn.edu.seu.interfaces.IXmlSaveDAO;
import cn.edu.seu.persondepositinfo.PersonDepositInfo;
import cn.edu.seu.persondepositinfo.PersonDepositInfoDAO;
import cn.edu.seu.personinfo.PersonInfo;
import cn.edu.seu.personinfo.PersonInfoDAO;
import cn.edu.seu.personinterestinfo.PersonInterestInfo;
import cn.edu.seu.personinterestinfo.PersonInterestInfoDAO;
import cn.edu.seu.xmlsave.XmlSave;
import cn.edu.seu.xmlsave.XmlSaveDAO;







public class Database_Deal {
/*
	Database_Deal()
	{
		url="jdbc:oracle:thin:@localhost:1521/";
		SID="CITICUP";
		USERNAME="SYSTEM";
		PASSWORD="citicup";
	}
	
	Database_Deal(String My_Sid,String My_Username,String My_Password)
	{
		url="jdbc:oracle:thin:@localhost:1521/";
		SID=My_Sid;
		USERNAME=My_Username;
		PASSWORD=My_Password;
	}
	
	public Connection Connect_bank_db() throws ClassNotFoundException,SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");      
		Connection conn =DriverManager.getConnection(url+SID,USERNAME,PASSWORD);     
		conn.setAutoCommit(false);
		return conn;    
	}
*/	
	public boolean Transfer_Out_Person(String cardnumber,String amount) throws ClassNotFoundException, SQLException
	{
		String query_result=null;
		/*
		Connection tempcon=Connect_bank_db();
		Statement stmt = tempcon.createStatement();    
		ResultSet rset =          stmt.executeQuery("select BALANCE from person_info where CARDNUM='"+cardnumber+"'");    
		while (rset.next()) 
		{        
			query_result=rset.getString(1);
			}    
			*/
	
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		List<PersonInfo> person_info_list=person_info_dao.findByCardnum(cardnumber);
		PersonInfo person_info=new PersonInfo();
		for(PersonInfo intor:person_info_list)
			person_info=intor;
		query_result=person_info.getBalance();
		
		if(Double.parseDouble(query_result)<Double.parseDouble(amount))
		{
			return false;
		}
		else
		{
			double newbalance=Double.parseDouble(query_result)-Double.parseDouble(amount);
			query_result=Double.toString(newbalance);
//			stmt.executeUpdate("update person_info set BALANCE='"+query_result+"' where CARDNUM='"+cardnumber+"'");
		
			person_info.setBalance(query_result);
			person_info_dao.update(person_info);
			
//			tempcon.commit();
//			stmt.close(); 
			return true;
		}
	}
	
	public boolean Transfer_Ont_Store(String cardnumber,String amount) throws ClassNotFoundException, SQLException
	{
		String query_result=null;
		/*
		Connection tempcon=Connect_bank_db();
		Statement stmt = tempcon.createStatement();    
		ResultSet rset =          stmt.executeQuery("select BALANCE from business_info where CARDNUM='"+cardnumber+"'");    
		while (rset.next()) 
		{        
			query_result=rset.getString(1);
			}    
			*/
		
		IBusinessInfoDAO business_info_dao=new BusinessInfoDAO();
		List<BusinessInfo> business_info_list=business_info_dao.findByCardnum(cardnumber);
		BusinessInfo business_info=new BusinessInfo();
		for(BusinessInfo intor:business_info_list)
			business_info=intor;
		query_result=business_info.getBalance();
		
		if(Double.parseDouble(query_result)<Double.parseDouble(amount))
		{
			return false;
		}
		else
		{
			double newbalance=Double.parseDouble(query_result)-Double.parseDouble(amount);
			query_result=Double.toString(newbalance);
//			stmt.executeUpdate("update business_info set BALANCE='"+query_result+"' where CARDNUM='"+cardnumber+"'");
			
			business_info.setBalance(query_result);
			business_info_dao.update(business_info);
			
//			tempcon.commit();
//			stmt.close(); 
			return true;
		}
	}
	
	public boolean Transfer_In_Person(String cardnumber,String amount) throws ClassNotFoundException, SQLException
	{
		String query_result=null;
		/*
		Connection tempcon=Connect_bank_db();
		Statement stmt = tempcon.createStatement();    
		ResultSet rset =          stmt.executeQuery("select BALANCE from person_info where CARDNUM='"+cardnumber+"'");    
		while (rset.next()) 
		{        
			query_result=rset.getString(1);
			}    
			*/
		
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		List<PersonInfo> person_info_list=person_info_dao.findByCardnum(cardnumber);
		PersonInfo person_info=new PersonInfo();
		for(PersonInfo intor:person_info_list)
			person_info=intor;

		
		if(person_info==null)
		{
//			stmt.close(); 
			return false;
		}
		else
		{
			query_result=person_info.getBalance();
			double newbalance=Double.parseDouble(query_result)+Double.parseDouble(amount);
			query_result=Double.toString(newbalance);
//			stmt.executeUpdate("update person_info set BALANCE='"+query_result+"' where CARDNUM='"+cardnumber+"'");

			person_info.setBalance(query_result);
			person_info_dao.update(person_info);
			
//			tempcon.commit();
//			stmt.close(); 
			return true;
		}
	}
	
	public boolean Transfer_In_Store(String cardnumber,String amount) throws ClassNotFoundException, SQLException
	{
		String query_result=null;
		/*
		Connection tempcon=Connect_bank_db();
		Statement stmt = tempcon.createStatement();    
		ResultSet rset =          stmt.executeQuery("select BALANCE from business_info where CARDNUM='"+cardnumber+"'");    
		while (rset.next()) 
		{        
			query_result=rset.getString(1);
			} 
			*/
		IBusinessInfoDAO business_info_dao=new BusinessInfoDAO();
		List<BusinessInfo> business_info_list=business_info_dao.findByCardnum(cardnumber);
		BusinessInfo business_info=new BusinessInfo();
		for(BusinessInfo intor:business_info_list)
			business_info=intor;
		if(business_info==null)
		{
//			stmt.close(); 
			return false;
		}
		else
		{
			query_result=business_info.getBalance();
			double newbalance=Double.parseDouble(query_result)+Double.parseDouble(amount);
			query_result=Double.toString(newbalance);
//			stmt.executeUpdate("update business_info set BALANCE='"+query_result+"' where CARDNUM='"+cardnumber+"'");

			business_info.setBalance(query_result);
			business_info_dao.update(business_info);
			
//			tempcon.commit();
//			stmt.close(); 
			return true;
		}
	}
	
	public Rsa Query_Rsa(String cardnumber) throws ClassNotFoundException, SQLException
	{
//		System.out.println("cardnum="+cardnumber);
		String query_result=null;
		BigInteger n=new BigInteger("0");
		BigInteger d=new BigInteger("0");
		/*
		Connection tempcon=Connect_bank_db();
		Statement stmt = tempcon.createStatement();    
		ResultSet rsetn =          stmt.executeQuery("select commonkeyofn from person_info where CARDNUM='"+cardnumber+"'");    
		while (rsetn.next()) 
		{        
			query_result=rsetn.getString(1);
			n=new BigInteger(query_result);
			}    
		ResultSet rsetd =          stmt.executeQuery("select commonkeyofd from person_info where CARDNUM='"+cardnumber+"'");    
		while (rsetd.next()) 
		{        
			query_result=rsetd.getString(1);
			d=new BigInteger(query_result);
			}    
			*/
		
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		List<PersonInfo> person_info_list=person_info_dao.findByCardnum(cardnumber);
		PersonInfo person_info=new PersonInfo();
		for(PersonInfo intor:person_info_list)
		{
			d=new BigInteger(intor.getPublickeyd());
			n=new BigInteger(intor.getPublickeyn());
		}
		/*query_result=person_info.getPublickeyd();
		d=new BigInteger(query_result);
		query_result=person_info.getPublickeyn();
		n=new BigInteger(query_result);*/
		
		Rsa rsa=new Rsa(n,d);
//			stmt.close(); 
			return rsa;
	}
	
	public boolean checkAccountCorrect(String userName) throws ClassNotFoundException, SQLException{
		/*
		Connection tempcon = Connect_bank_db();
		Statement stmt = tempcon.createStatement();
		ResultSet rsetn = stmt.executeQuery("select userName from person_info where userName ='" + userName + "'");
		if(rsetn.next())
			return false;
		else
			return true;
			*/
		
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		PersonInfo person_info=person_info_dao.findById(userName);
		if(person_info==null)
			return true;
		else
			return false;
	}
	
	public boolean registerNewAccount(String userName, String password, String customerName, String bluetoothMac) throws ClassNotFoundException, SQLException{
/*
		String dynamicPasswordNum="",commonKeyOFN="",commonKeyOFD="";
		Connection tempcon = Connect_bank_db();
		Statement stmt = tempcon.createStatement();
		System.out.println("1");
		stmt.executeUpdate("insert into person_info "
						+"values ('" + userName + "', '" + customerName +"', '', '" +bluetoothMac + "', '" + dynamicPasswordNum + "', '', '', '', '" + commonKeyOFN + "', '" + commonKeyOFD +"', '" + password + "')");
		tempcon.commit();
		tempcon.close();
		stmt.close();
		
		Connection tempcon2 = Connect_bank_db();
		Statement stmt2 = tempcon2.createStatement();
		ResultSet rsetn2 = stmt2.executeQuery("select userName from person_info where userName ='" + userName + "'");
		if(rsetn2.next())
			return true;
		else
			return false;
		*/
		
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		PersonInfo person_info=new PersonInfo();
		person_info.setUsername(userName);
		person_info.setPassword(password);
		person_info.setCustomername(customerName);
		person_info.setBluetoothmac(bluetoothMac);
		person_info_dao.save(person_info);
		PersonInfo exam_person_info=person_info_dao.findById(userName);
		if(exam_person_info==null)
			return false;
		else
			return true;
	}
	
	public boolean linkBankCard(String userName,String customerName, String cardNum, String phoneNum, String idCardNum, String cardPassword, String imei, Rsa newrsa) throws ClassNotFoundException, SQLException{
/*		Connection tempcon = Connect_bank_db();
		Statement stmt = tempcon.createStatement();
		stmt.executeUpdate("update person_info set cardNum = '" + cardNum +"', phoneNum = '" + phoneNum + "', identificationCardNum = '" + idCardNum + "'"
				+ "where userName ='" + userName + "'");
		tempcon.commit();
		tempcon.close();
		stmt.close();
		
		Connection tempcon2 = Connect_bank_db();
		Statement stmt2 = tempcon2.createStatement();
		ResultSet rsetn2 = stmt2.executeQuery("select cardNum from person_info where userName ='" + userName + "'");
		if(rsetn2.next())
			return true;
		else
			return false;
	*/
		
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();	
		PersonInfo person_info=person_info_dao.findById(userName);
		IBankAccountInfoDAO bank_account_info_dao=new BankAccountInfoDAO();
		BankAccountInfo bank_account_info=bank_account_info_dao.findById(cardNum);
		if((person_info==null)||(bank_account_info==null))
		{
			System.out.println("1");
			return false;
		}
		else
		{
			if((bank_account_info.getIdentificationcardnumber().equals(idCardNum))&&(bank_account_info.getName().equals(customerName))&&(bank_account_info.getPassword().equals(cardPassword)))
			{
			person_info.setUsername(userName);
			person_info.setCustomername(customerName);
			person_info.setCardnum(cardNum);
			person_info.setPhonenum(phoneNum);
			person_info.setIdentificationcardnum(idCardNum);
			person_info.setBalance(bank_account_info.getBalance());
			person_info.setPrivatekey(newrsa.getE());
			person_info.setPublickeyd(newrsa.getD());
			person_info.setPublickeyn(newrsa.getN());
			person_info.setImei(imei);
			person_info_dao.save(person_info);
			PersonInterestInfo person_interest_info=new PersonInterestInfo();
			person_interest_info.setBalance("0");
			person_interest_info.setUsername(userName);
			Store_Interest_Financing(person_interest_info);
			PersonDepositInfo person_deposit_info=new PersonDepositInfo();
			person_deposit_info.setAmount("0");
			person_deposit_info.setDepositway("000");
			person_deposit_info.setInterestrateway("1");
			person_deposit_info.setUsername(userName);
			person_deposit_info.setIntesertrate("0.00029");
			Store_Deposit_Financing(person_deposit_info);
			PersonInfo exam_person_info=person_info_dao.findById(userName);
			if(exam_person_info==null)
			{
				
				System.out.println("2");
				System.out.println(bank_account_info.getIdentificationcardnumber()+idCardNum);
				System.out.println(bank_account_info.getName()+customerName);
				System.out.println(bank_account_info.getPassword()+cardPassword);
				return false;
			}
			else
				return true;
			}
			else
			{
				System.out.println("3");
				System.out.println(bank_account_info.getIdentificationcardnumber()+idCardNum);
				System.out.println(bank_account_info.getName()+customerName);
				System.out.println(bank_account_info.getPassword()+cardPassword);
				return false;
			}
		}
	}
	
	
	public boolean resetPassword(String cardNumber , String idCardNumber) throws SQLException, ClassNotFoundException{
/*
		Connection tempcon = Connect_bank_db();
		Statement stmt = tempcon.createStatement();
		String query = "select * from person_info where cardNum = '" + c + "' and identificationCardNum = '" + i + "'";
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()){
			tempcon.commit();
			tempcon.close();
			stmt.close();
			return true;
		}
		else{
			tempcon.commit();
			tempcon.close();
			stmt.close();
			return false;
		}
		*/
		System.out.println(cardNumber);
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		List<PersonInfo> person_info_list=person_info_dao.findByCardnum(cardNumber);
		for(PersonInfo intor:person_info_list)
			{
				if(idCardNumber.equals(intor.getIdentificationcardnum()))
					return true;
				else
					return false;
			}
		return false;
	}
	
	public boolean compareImei(String cardNumber , String imei) throws SQLException, ClassNotFoundException{
		/*
				Connection tempcon = Connect_bank_db();
				Statement stmt = tempcon.createStatement();
				String query = "select * from person_info where cardNum = '" + c + "' and identificationCardNum = '" + i + "'";
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()){
					tempcon.commit();
					tempcon.close();
					stmt.close();
					return true;
				}
				else{
					tempcon.commit();
					tempcon.close();
					stmt.close();
					return false;
				}
				*/
				
				IPersonInfoDAO person_info_dao=new PersonInfoDAO();
				List<PersonInfo> person_info_list=person_info_dao.findByCardnum(cardNumber);
				for(PersonInfo intor:person_info_list)
					{
						if(imei.equals(intor.getImei()))
							return true;
						else
							return false;
					}
				return false;
			}
	
	public boolean Insert_Elenote(String paynum,String payernum,String recernum,String amount, String transfertime, String payername, String recername, String payerdevice, String recerdevice) throws SQLException, ClassNotFoundException
	{
		/*
		Connection tempcon = Connect_bank_db();
		Statement stmt = tempcon.createStatement();
		stmt.executeUpdate("insert into elenote_info values ('" + paynum +"', '" + payernum + "', '" + recernum +"', '" +amount+"')");
		tempcon.commit();
		tempcon.close();
		stmt.close();
		return true;
		*/
		
		IElenoteInfoDAO elenote_info_dao=new ElenoteInfoDAO();
		ElenoteInfo elenote_info=new ElenoteInfo();
		elenote_info.setPaynum(paynum);
		elenote_info.setPayernum(payernum);
		elenote_info.setRecernum(recernum);
		elenote_info.setAmount(amount);
		elenote_info.setTransfertime(transfertime);
		elenote_info.setPayername(payername);
		elenote_info.setRecername(recername);
		elenote_info.setPayerdevice(payerdevice);
		elenote_info.setRecerdevice(recerdevice);
		elenote_info_dao.save(elenote_info);
		return true;
	}
	
	public boolean Elenote_Exist(String elenote_num) throws ClassNotFoundException, SQLException
	{
	/*	Connection tempcon = Connect_bank_db();
		Statement stmt = tempcon.createStatement();
		String query = "select amount from elenote_info where paynum = '" + elenote_num + "'";
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		if(rs.getString(1)==null)
			return false;
		else
			return true;
			*/
		
		IElenoteInfoDAO elenote_info_dao=new ElenoteInfoDAO();
		ElenoteInfo elenote_info=elenote_info_dao.findById(elenote_num);
		if(elenote_info==null)
			return  false;
		else
			return true;
	}
	
	public boolean Store_Randcode_Search(String randcode)
	{
		IBusinessInfoDAO business_info_dao=new BusinessInfoDAO();
		List<BusinessInfo> business_info_list=business_info_dao.findByRandcode(randcode);
		for(BusinessInfo intor:business_info_list)
		{
			if(intor==null)
				return false;
			else
				return true;
		}
		return false;
	}
	
	public BusinessInfo getBusinessInfo(String cardnumber) throws ClassNotFoundException, SQLException
	{
		IBusinessInfoDAO business_info_dao=new BusinessInfoDAO();
		List<BusinessInfo> business_info_list=business_info_dao.findByCardnum(cardnumber);
		BusinessInfo business_info=new BusinessInfo();
		for(BusinessInfo intor:business_info_list)
			business_info=intor;
		return business_info;
	}
	
	public PersonInfo getPersonInfo(String cardnumber) throws ClassNotFoundException, SQLException
	{
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		List<PersonInfo> person_info_list=person_info_dao.findByCardnum(cardnumber);
		PersonInfo person_info=new PersonInfo();
		for(PersonInfo intor:person_info_list)
			person_info=intor;
		/*
		Connection tempcon = Connect_bank_db();
		Statement stmt = tempcon.createStatement();
		String query = "select * from person_info where cardNum = '" + cardnumber + "'";
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		person_info.setUsername(rs.getString("username"));
		person_info.setPassword(rs.getString("password"));
		person_info.setCardnum(rs.getString("cardNum"));
		person_info.setPrivatekey(rs.getString("dynamicPasswordNum"));
		person_info.setBalance(rs.getString("balance"));
		tempcon.commit();
		tempcon.close();
		stmt.close();
		*/
		return person_info;
	}
	/*
	private String url;
	private String SID;
	private String USERNAME;
	private String PASSWORD;
*/

	public boolean Modify_Phonenumber(String username, String phonenumber) {
		// TODO Auto-generated method stub
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		PersonInfo person_info=person_info_dao.findById(username);
		if(person_info==null)
			return false;
		else
		{
			person_info.setPhonenum(phonenumber);
			person_info_dao.update(person_info);
			return true;
		}
	}
	
	public boolean Modify_Password(String username, String password) {
		// TODO Auto-generated method stub
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		PersonInfo person_info=person_info_dao.findById(username);
		if(person_info==null)
			return false;
		else
		{
			person_info.setPassword(password);
			person_info_dao.update(person_info);
			return true;
		}
	}

	public boolean Store_Deposit_Financing(PersonDepositInfo personDepositInfo) {
		// TODO Auto-generated method stub
		String pointer=null;
		String newpointer=null;
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		PersonInfo person_info=person_info_dao.findById(personDepositInfo.getUsername());
		if(Double.valueOf(person_info.getBalance()).doubleValue()<Double.valueOf(personDepositInfo.getAmount()))
			return false;
		IPersonDepositInfoDAO person_deposit_info_dao=new PersonDepositInfoDAO();
		PersonDepositInfo pointer_deposit=person_deposit_info_dao.findById("pointer");
		pointer=pointer_deposit.getId();
		newpointer=Integer.toString(Integer.parseInt(pointer)+1);	
		System.out.println(newpointer);
		List<PersonDepositInfo> maybe_deposit_list=person_deposit_info_dao.findByUsername(personDepositInfo.getUsername());
//		if(maybe_deposit_list.isEmpty())
//			return false;
		PersonDepositInfo current_note=new PersonDepositInfo();
		for(PersonDepositInfo intor:maybe_deposit_list)
		{
			if(intor.getDepositway().equals("000"))
				current_note=intor;
		}
		if(personDepositInfo.getDepositway().equals("000")&&personDepositInfo.getInterestrateway().equals("0"))
		{
//			personDepositInfo.setId(newpointer);
//			personDepositInfo.setNotes("dep"+newpointer);
//			personDepositInfo.setInterestrateway("0");
			Finance f=new Finance();
			f.Current_Deposit(current_note, System.currentTimeMillis(), true);
			current_note.setAmount(String.valueOf(Double.valueOf(personDepositInfo.getAmount()).doubleValue()+Double.valueOf(current_note.getAmount()).doubleValue()));
//			personDepositInfo.setTime(String.valueOf(System.currentTimeMillis()));
//			person_deposit_info_dao.save(personDepositInfo);
//			pointer_deposit.setId(newpointer);
			person_deposit_info_dao.update(current_note);
		}
		else
		{
			personDepositInfo.setId(newpointer);
			personDepositInfo.setNotes("dep"+newpointer);
			personDepositInfo.setInterestrateway("0");
			personDepositInfo.setTime(String.valueOf(System.currentTimeMillis()));
			person_deposit_info_dao.save(personDepositInfo);
			pointer_deposit.setId(newpointer);
			person_deposit_info_dao.update(pointer_deposit);
		}
		person_info.setBalance(String.valueOf(Double.valueOf(person_info.getBalance()).doubleValue()-Double.valueOf(personDepositInfo.getAmount())));
		person_info_dao.update(person_info);
		return true;
	}

	public boolean Store_Interest_Financing(PersonInterestInfo personInterestInfo) {
		// TODO Auto-generated method stub
		String pointer=null;
		String newpointer=null;
		IPersonInterestInfoDAO person_insterest_info_dao=new PersonInterestInfoDAO();
		PersonInterestInfo pointer_insterest=person_insterest_info_dao.findById("pointer");
		pointer=pointer_insterest.getId();
		newpointer=Integer.toString(Integer.parseInt(pointer)+1);	
		System.out.println(newpointer);
		PersonInterestInfo maybe_insterest=person_insterest_info_dao.findById(personInterestInfo.getUsername());
		if(maybe_insterest==null)
		{
			personInterestInfo.setId(newpointer);
			personInterestInfo.setNotes("int"+newpointer);
			personInterestInfo.setTime(String.valueOf(System.currentTimeMillis()));
			person_insterest_info_dao.save(personInterestInfo);
			pointer_insterest.setId(newpointer);
			person_insterest_info_dao.update(pointer_insterest);
			return true;
		}
		else
		{
//			if(System.currentTimeMillis()-Long.valueOf(maybe_insterest.getTime())>Long.valueOf("2592000000"))
//			{
//				maybe_insterest.setFinancingway(personInterestInfo.getFinancingway());
//				maybe_insterest.setBalance(String.valueOf(Double.valueOf(personInterestInfo.getBalance()).doubleValue()+Double.valueOf(maybe_insterest.getBalance()).doubleValue()));
				maybe_insterest.setTime(String.valueOf(System.currentTimeMillis()));
				person_insterest_info_dao.update(maybe_insterest);
				return true;
//			}
//			else
//				return false;
		}
	}
	
	public String Query_Balance(String name)
	{
		IPersonInfoDAO person_info_dao=new PersonInfoDAO();
		PersonInfo person_info=person_info_dao.findById(name);
		if(person_info!=null)
			return person_info.getBalance();
		else
			return "Balance query error.";
	}

	public void Insert_TradeNote(String hash, String amount,
			String transfertime, String payername, String recername,
			String payerdevice, String recerdevice, String buyerimei) {
		// TODO Auto-generated method stub
		IBankTradeDAO bank_trade_dao=new BankTradeDAO();
		BankTrade bank_trade=new BankTrade();
		bank_trade.setTradenum(hash);
		bank_trade.setAmount(amount);
		bank_trade.setTradetime(transfertime);
		bank_trade.setBuyername(payername);
		bank_trade.setSalername(recername);
		bank_trade.setBuyermac(payerdevice);
		bank_trade.setSalermac(recerdevice);
		bank_trade.setBuyerimei(buyerimei);
		bank_trade_dao.save(bank_trade);
	}

	public boolean Supermarket_Login(String name, String password) {
		// TODO Auto-generated method stub
		IBusinessInfoDAO business_info_dao=new BusinessInfoDAO();
		BusinessInfo business_info=business_info_dao.findById(name);
		if(business_info==null)
			return false;
		else
		{
			if(business_info.getRandcode().equals(password))
				return true;
			else
				return false;
		}
	}
	
	public BusinessInfo Supermarket_Info(String name)
	{
		IBusinessInfoDAO business_info_dao=new BusinessInfoDAO();
		BusinessInfo business_info=business_info_dao.findById(name);
		return business_info;
	}

	public String User_Exist(String name) {
		// TODO Auto-generated method stub
		IPersonInterestInfoDAO person_insterest_info_dao=new PersonInterestInfoDAO();
		PersonInterestInfo pointer_insterest=person_insterest_info_dao.findById(name);
		if(pointer_insterest==null)
			return null;
		else
			return pointer_insterest.getBalance();
	}

	public boolean User_Deposit_Exist(String name) {
		// TODO Auto-generated method stub
		IPersonDepositInfoDAO person_deposit_info_dao=new PersonDepositInfoDAO();
		List<PersonDepositInfo> pointer_deposit=person_deposit_info_dao.findByUsername(name);
		if(pointer_deposit.isEmpty())
			return false;
		else
			return true;
	}

	public List<PersonDepositInfo> Get_User_Deposit(String name) {
		// TODO Auto-generated method stub
		IPersonDepositInfoDAO person_deposit_info_dao=new PersonDepositInfoDAO();
		List<PersonDepositInfo> pointer_deposit=person_deposit_info_dao.findByUsername(name);
		return pointer_deposit;
	}
}

