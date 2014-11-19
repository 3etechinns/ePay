package cn.edu.seu.bankserver;

public class Rate {
	Rate()
	{
		cpi_rate=new String("0.005");
		fund_rate=new String("0.0025");
		current_rate=new String("0.00029");
	}
	
	Rate(String cpi,String fund,String current)
	{
		Set_CPI_Rate(cpi);
		Set_Fund_Rate(fund);
		Set_Current_Rate(current);
	}
	
	public String Set_CPI_Rate(String rate)
	{
		cpi_rate=rate;
		return cpi_rate;
	}
	
	public String Set_Fund_Rate(String rate)
	{
		fund_rate=rate;
		return fund_rate;
	}
	
	public String Set_Current_Rate(String rate)
	{
		current_rate=rate;
		return current_rate;
	}
	
	public String Get_CPI_Rate()
	{
		return cpi_rate;
	}
	
	public String Get_Fund_Rate()
	{
		return fund_rate;
	}
	
	public String Get_Current_Rate()
	{
		return current_rate;
	}
	
	private String cpi_rate;
	private String fund_rate;
	private String current_rate;
}
