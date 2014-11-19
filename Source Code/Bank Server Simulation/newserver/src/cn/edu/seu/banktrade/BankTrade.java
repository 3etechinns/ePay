package cn.edu.seu.banktrade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BankTrade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANK_TRADE", schema = "SYSTEM")
public class BankTrade implements java.io.Serializable {

	// Fields

	private String tradenum;
	private String salername;
	private String salermac;
	private String buyername;
	private String buyermac;
	private String buyerimei;
	private String amount;
	private String tradetime;

	// Constructors

	/** default constructor */
	public BankTrade() {
	}

	/** minimal constructor */
	public BankTrade(String tradenum) {
		this.tradenum = tradenum;
	}

	/** full constructor */
	public BankTrade(String tradenum, String salername, String salermac,
			String buyername, String buyermac, String buyerimei, String amount,
			String tradetime) {
		this.tradenum = tradenum;
		this.salername = salername;
		this.salermac = salermac;
		this.buyername = buyername;
		this.buyermac = buyermac;
		this.buyerimei = buyerimei;
		this.amount = amount;
		this.tradetime = tradetime;
	}

	// Property accessors
	@Id
	@Column(name = "TRADENUM", unique = true, nullable = false, length = 31)
	public String getTradenum() {
		return this.tradenum;
	}

	public void setTradenum(String tradenum) {
		this.tradenum = tradenum;
	}

	@Column(name = "SALERNAME", length = 32)
	public String getSalername() {
		return this.salername;
	}

	public void setSalername(String salername) {
		this.salername = salername;
	}

	@Column(name = "SALERMAC", length = 12)
	public String getSalermac() {
		return this.salermac;
	}

	public void setSalermac(String salermac) {
		this.salermac = salermac;
	}

	@Column(name = "BUYERNAME", length = 32)
	public String getBuyername() {
		return this.buyername;
	}

	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}

	@Column(name = "BUYERMAC", length = 12)
	public String getBuyermac() {
		return this.buyermac;
	}

	public void setBuyermac(String buyermac) {
		this.buyermac = buyermac;
	}

	@Column(name = "BUYERIMEI", length = 15)
	public String getBuyerimei() {
		return this.buyerimei;
	}

	public void setBuyerimei(String buyerimei) {
		this.buyerimei = buyerimei;
	}

	@Column(name = "AMOUNT", length = 12)
	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "TRADETIME", length = 15)
	public String getTradetime() {
		return this.tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

}