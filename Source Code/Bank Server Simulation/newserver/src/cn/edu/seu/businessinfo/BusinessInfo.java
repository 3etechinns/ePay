package cn.edu.seu.businessinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BusinessInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUSINESS_INFO", schema = "SYSTEM")
public class BusinessInfo implements java.io.Serializable {

	// Fields

	private String username;
	private String storename;
	private String cardnum;
	private String bluetoothpwd;
	private String balance;
	private String randcode;

	// Constructors

	/** default constructor */
	public BusinessInfo() {
	}

	/** minimal constructor */
	public BusinessInfo(String username) {
		this.username = username;
	}

	/** full constructor */
	public BusinessInfo(String username, String storename, String cardnum,
			String bluetoothpwd, String balance, String randcode) {
		this.username = username;
		this.storename = storename;
		this.cardnum = cardnum;
		this.bluetoothpwd = bluetoothpwd;
		this.balance = balance;
		this.randcode = randcode;
	}

	// Property accessors
	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, length = 32)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "STORENAME", length = 32)
	public String getStorename() {
		return this.storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	@Column(name = "CARDNUM", length = 20)
	public String getCardnum() {
		return this.cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	@Column(name = "BLUETOOTHPWD", length = 10)
	public String getBluetoothpwd() {
		return this.bluetoothpwd;
	}

	public void setBluetoothpwd(String bluetoothpwd) {
		this.bluetoothpwd = bluetoothpwd;
	}

	@Column(name = "BALANCE", length = 15)
	public String getBalance() {
		return this.balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Column(name = "RANDCODE", length = 11)
	public String getRandcode() {
		return this.randcode;
	}

	public void setRandcode(String randcode) {
		this.randcode = randcode;
	}

}