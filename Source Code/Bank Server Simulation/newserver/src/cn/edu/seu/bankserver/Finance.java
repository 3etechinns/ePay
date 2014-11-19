package cn.edu.seu.bankserver;

import java.util.List;

import cn.edu.seu.interfaces.IPersonDepositInfoDAO;
import cn.edu.seu.interfaces.IPersonInterestInfoDAO;
import cn.edu.seu.persondepositinfo.PersonDepositInfo;
import cn.edu.seu.persondepositinfo.PersonDepositInfoDAO;
import cn.edu.seu.personinterestinfo.PersonInterestInfo;
import cn.edu.seu.personinterestinfo.PersonInterestInfoDAO;

public class Finance {
	Finance()
	{
		onemonth=Long.parseLong("2592000000");
		rate=new Rate();
		person_deposit_info_dao=new PersonDepositInfoDAO();
		person_interest_info_dao=new PersonInterestInfoDAO();
	}
	
	Finance(Rate newrate)
	{
		onemonth=Long.parseLong("2592000000");
		rate=newrate;
		person_deposit_info_dao=new PersonDepositInfoDAO();
		person_interest_info_dao=new PersonInterestInfoDAO();
	}
	
	public void Update_All_Deposit()
	{
		long nowtime=System.currentTimeMillis();
		List<PersonDepositInfo> person_deposit_info_list=person_deposit_info_dao.findByInterestrateway(0);
		for(PersonDepositInfo intor:person_deposit_info_list)
		{
			PersonDepositInfo deal=intor;
			if(deal.getDepositway().startsWith("0")&&!deal.getDepositway().equals("000"))
			{
				Standard_Deposit(deal,nowtime);
			}
			else if(deal.getDepositway().startsWith("1"))
			{
				Fund_Deposit(deal,nowtime);
			}
			else if(deal.getDepositway().startsWith("2"))
			{
				CPI_Deposit(deal,nowtime);
			}
			else if(deal.getDepositway().equals("000"))
			{
				Current_Deposit(deal,nowtime,false);
			}
			else if(deal.getDepositway().startsWith("4"))
			{
				Ex_Standard_Deposit(deal,nowtime);
			}
			else if(deal.getDepositway().startsWith("5"))
			{
				Ex_Fund_Deposit(deal,nowtime);
			}
			else if(deal.getDepositway().startsWith("6"))
			{
				Ex_CPI_Deposit(deal,nowtime);
			}
			else
			{
				person_deposit_info_dao.delete(deal);
			}
		}
	}
	
	private void Ex_CPI_Deposit(PersonDepositInfo deal, long nowtime) {
		// TODO Auto-generated method stub
		int length=Integer.valueOf(deal.getDepositway()).intValue()%100;
		if((nowtime-Long.valueOf(deal.getTime()).longValue())>=onemonth*length)
		{
			PersonInterestInfo person_interest_info=person_interest_info_dao.findById(deal.getUsername());
			double balance=Double.valueOf(person_interest_info.getBalance()).doubleValue()+0.6*Double.valueOf(deal.getAmount()).doubleValue()*Double.valueOf(deal.getIntesertrate()).doubleValue()*length*(1+1*Double.valueOf(rate.Get_CPI_Rate()).doubleValue());
			person_interest_info.setBalance(String.valueOf(balance));
			List<PersonDepositInfo> current=person_deposit_info_dao.findByUsername(deal.getUsername());
			PersonDepositInfo current_deposit=current.get(0);
			Current_Deposit(current_deposit,nowtime,true);
			current_deposit.setAmount(String.valueOf(Double.valueOf(current_deposit.getAmount()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()));
			person_deposit_info_dao.delete(deal);
			person_deposit_info_dao.update(current_deposit);
		}
	}

	private void Ex_Fund_Deposit(PersonDepositInfo deal, long nowtime) {
		// TODO Auto-generated method stub
		int length=Integer.valueOf(deal.getDepositway()).intValue()%100;
		if((nowtime-Long.valueOf(deal.getTime()).longValue())>=onemonth*length)
		{
			PersonInterestInfo person_interest_info=person_interest_info_dao.findById(deal.getUsername());
			double balance=Double.valueOf(person_interest_info.getBalance()).doubleValue()+0.6*Double.valueOf(deal.getAmount()).doubleValue()*Double.valueOf(deal.getIntesertrate()).doubleValue()*length*(1+2*Double.valueOf(rate.Get_Fund_Rate()).doubleValue());
			person_interest_info.setBalance(String.valueOf(balance));
			List<PersonDepositInfo> current=person_deposit_info_dao.findByUsername(deal.getUsername());
			PersonDepositInfo current_deposit=current.get(0);
			Current_Deposit(current_deposit,nowtime,true);
			current_deposit.setAmount(String.valueOf(Double.valueOf(current_deposit.getAmount()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()));
			person_deposit_info_dao.delete(deal);
			person_deposit_info_dao.update(current_deposit);
		}
	}

	private void Ex_Standard_Deposit(PersonDepositInfo deal, long nowtime) {
		// TODO Auto-generated method stub
		int length=Integer.valueOf(deal.getDepositway()).intValue();
		if((nowtime-Long.valueOf(deal.getTime()).longValue())>=onemonth*length)
		{
			PersonInterestInfo person_interest_info=person_interest_info_dao.findById(deal.getUsername());
			person_interest_info.setBalance(String.valueOf(Double.valueOf(person_interest_info.getBalance()).doubleValue()+0.6*Double.valueOf(deal.getAmount()).doubleValue()*Double.valueOf(deal.getIntesertrate()).doubleValue()*length));
			List<PersonDepositInfo> current=person_deposit_info_dao.findByUsername(deal.getUsername());
			PersonDepositInfo current_deposit=current.get(0);
			Current_Deposit(current_deposit,nowtime,true);
			current_deposit.setAmount(String.valueOf(Double.valueOf(current_deposit.getAmount()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()));
			person_deposit_info_dao.delete(deal);
			person_deposit_info_dao.update(current_deposit);
		}
	}

	public void Current_Deposit(PersonDepositInfo deal, long nowtime,Boolean force) {
		// TODO Auto-generated method stub
		if(!force)
		{
			if((nowtime-Long.getLong(deal.getTime())>=onemonth))
			{
			PersonInterestInfo person_interest_info=person_interest_info_dao.findById(deal.getUsername());
			person_interest_info.setBalance(String.valueOf(Double.valueOf(person_interest_info.getBalance()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()*Double.valueOf(rate.Get_Current_Rate()).doubleValue()));
			person_interest_info_dao.update(person_interest_info);
			deal.setTime(String.valueOf(nowtime));
			person_deposit_info_dao.update(deal);
			}
		}
		else if(force)
		{
			PersonInterestInfo person_interest_info=person_interest_info_dao.findById(deal.getUsername());
			double interest=Double.valueOf(person_interest_info.getBalance()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()*Double.valueOf(rate.Get_Current_Rate()).doubleValue()*((nowtime-Long.valueOf(deal.getTime()).longValue())/onemonth);
			if(deal.getDepositway().equals("000"))
			{
				person_interest_info.setBalance(String.valueOf(interest));
				person_interest_info_dao.update(person_interest_info);
				deal.setTime(String.valueOf(nowtime));
				person_deposit_info_dao.update(deal);
			}
			else
			{
				person_interest_info.setBalance(String.valueOf(interest));
				person_interest_info_dao.update(person_interest_info);
				List<PersonDepositInfo> current=person_deposit_info_dao.findByUsername(deal.getUsername());
				PersonDepositInfo current_deposit=current.get(0);
				Current_Deposit(current_deposit,nowtime,true);
				current_deposit.setAmount(String.valueOf(Double.valueOf(current_deposit.getAmount()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()));
				person_deposit_info_dao.delete(deal);
				person_deposit_info_dao.update(current_deposit);
			}
		}
	}

	private void CPI_Deposit(PersonDepositInfo deal, long nowtime) {
		// TODO Auto-generated method stub
		int length=Integer.valueOf(deal.getDepositway()).intValue()%100;
		if((nowtime-Long.valueOf(deal.getTime()).longValue())>=onemonth*length)
		{
			PersonInterestInfo person_interest_info=person_interest_info_dao.findById(deal.getUsername());
			double balance=Double.valueOf(person_interest_info.getBalance()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()*Double.valueOf(deal.getIntesertrate()).doubleValue()*length*(1+1*Double.valueOf(rate.Get_CPI_Rate()).doubleValue());
			person_interest_info.setBalance(String.valueOf(balance));
			List<PersonDepositInfo> current=person_deposit_info_dao.findByUsername(deal.getUsername());
			PersonDepositInfo current_deposit=current.get(0);
			Current_Deposit(current_deposit,nowtime,true);
			current_deposit.setAmount(String.valueOf(Double.valueOf(current_deposit.getAmount()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()));
			person_deposit_info_dao.delete(deal);
			person_deposit_info_dao.update(current_deposit);
		}
	}

	private void Fund_Deposit(PersonDepositInfo deal, long nowtime) {
		// TODO Auto-generated method stub
		int length=Integer.valueOf(deal.getDepositway()).intValue()%100;
		if((nowtime-Long.valueOf(deal.getTime()).longValue())>=onemonth*length)
		{
			PersonInterestInfo person_interest_info=person_interest_info_dao.findById(deal.getUsername());
			double balance=Double.valueOf(person_interest_info.getBalance()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()*Double.valueOf(deal.getIntesertrate()).doubleValue()*length*(1+2*Double.valueOf(rate.Get_Fund_Rate()).doubleValue());
			person_interest_info.setBalance(String.valueOf(balance));
			List<PersonDepositInfo> current=person_deposit_info_dao.findByUsername(deal.getUsername());
			PersonDepositInfo current_deposit=current.get(0);
			Current_Deposit(current_deposit,nowtime,true);
			current_deposit.setAmount(String.valueOf(Double.valueOf(current_deposit.getAmount()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()));
			person_deposit_info_dao.delete(deal);
			person_deposit_info_dao.update(current_deposit);
		}
	}

	private void Standard_Deposit(PersonDepositInfo deal, long nowtime) {
		// TODO Auto-generated method stub
		int length=Integer.valueOf(deal.getDepositway()).intValue();
		if((nowtime-Long.valueOf(deal.getTime()).longValue())>=onemonth*length)
		{
			PersonInterestInfo person_interest_info=person_interest_info_dao.findById(deal.getUsername());
			person_interest_info.setBalance(String.valueOf(Double.valueOf(person_interest_info.getBalance()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()*Double.valueOf(deal.getIntesertrate()).doubleValue()*length));
			List<PersonDepositInfo> current=person_deposit_info_dao.findByUsername(deal.getUsername());
			PersonDepositInfo current_deposit=current.get(0);
			Current_Deposit(current_deposit,nowtime,true);
			current_deposit.setAmount(String.valueOf(Double.valueOf(current_deposit.getAmount()).doubleValue()+Double.valueOf(deal.getAmount()).doubleValue()));
			person_deposit_info_dao.delete(deal);
			person_deposit_info_dao.update(current_deposit);
		}
	}
	
	private long onemonth;
	private Rate rate;
	private IPersonDepositInfoDAO person_deposit_info_dao;
	private IPersonInterestInfoDAO person_interest_info_dao;
}
