package cn.edu.seu.bankaccountinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BankAccountInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BANK_ACCOUNT_INFO", schema = "SYSTEM")
public class BankAccountInfo implements java.io.Serializable {

	// Fields

	private String cardnumber;
	private String name;
	private String identificationcardnumber;
	private String password;
	private String balance;

	// Constructors

	/** default constructor */
	public BankAccountInfo() {
	}

	/** minimal constructor */
	public BankAccountInfo(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	/** full constructor */
	public BankAccountInfo(String cardnumber, String name,
			String identificationcardnumber, String password, String balance) {
		this.cardnumber = cardnumber;
		this.name = name;
		this.identificationcardnumber = identificationcardnumber;
		this.password = password;
		this.balance = balance;
	}

	// Property accessors
	@Id
	@Column(name = "CARDNUMBER", unique = true, nullable = false, length = 19)
	public String getCardnumber() {
		return this.cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	@Column(name = "NAME", length = 31)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IDENTIFICATIONCARDNUMBER", length = 18)
	public String getIdentificationcardnumber() {
		return this.identificationcardnumber;
	}

	public void setIdentificationcardnumber(String identificationcardnumber) {
		this.identificationcardnumber = identificationcardnumber;
	}

	@Column(name = "PASSWORD", length = 6)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "BALANCE", length = 15)
	public String getBalance() {
		return this.balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

}