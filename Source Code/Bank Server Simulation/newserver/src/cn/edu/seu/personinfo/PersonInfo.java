package cn.edu.seu.personinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PersonInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PERSON_INFO", schema = "SYSTEM")
public class PersonInfo implements java.io.Serializable {

	// Fields

	private String username;
	private String customername;
	private String cardnum;
	private String bluetoothmac;
	private String privatekey;
	private String identificationcardnum;
	private String phonenum;
	private String balance;
	private String password;
	private String publickeyn;
	private String publickeyd;
	private String imei;
	private String accountbalance;

	// Constructors

	/** default constructor */
	public PersonInfo() {
	}

	/** minimal constructor */
	public PersonInfo(String username) {
		this.username = username;
	}

	/** full constructor */
	public PersonInfo(String username, String customername, String cardnum,
			String bluetoothmac, String privatekey,
			String identificationcardnum, String phonenum, String balance,
			String password, String publickeyn, String publickeyd, String imei,
			String accountbalance) {
		this.username = username;
		this.customername = customername;
		this.cardnum = cardnum;
		this.bluetoothmac = bluetoothmac;
		this.privatekey = privatekey;
		this.identificationcardnum = identificationcardnum;
		this.phonenum = phonenum;
		this.balance = balance;
		this.password = password;
		this.publickeyn = publickeyn;
		this.publickeyd = publickeyd;
		this.imei = imei;
		this.accountbalance = accountbalance;
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

	@Column(name = "CUSTOMERNAME", length = 32)
	public String getCustomername() {
		return this.customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@Column(name = "CARDNUM", length = 20)
	public String getCardnum() {
		return this.cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	@Column(name = "BLUETOOTHMAC", length = 12)
	public String getBluetoothmac() {
		return this.bluetoothmac;
	}

	public void setBluetoothmac(String bluetoothmac) {
		this.bluetoothmac = bluetoothmac;
	}

	@Column(name = "PRIVATEKEY", length = 20)
	public String getPrivatekey() {
		return this.privatekey;
	}

	public void setPrivatekey(String privatekey) {
		this.privatekey = privatekey;
	}

	@Column(name = "IDENTIFICATIONCARDNUM", length = 20)
	public String getIdentificationcardnum() {
		return this.identificationcardnum;
	}

	public void setIdentificationcardnum(String identificationcardnum) {
		this.identificationcardnum = identificationcardnum;
	}

	@Column(name = "PHONENUM", length = 11)
	public String getPhonenum() {
		return this.phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	@Column(name = "BALANCE", length = 15)
	public String getBalance() {
		return this.balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Column(name = "PASSWORD", length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PUBLICKEYN", length = 30)
	public String getPublickeyn() {
		return this.publickeyn;
	}

	public void setPublickeyn(String publickeyn) {
		this.publickeyn = publickeyn;
	}

	@Column(name = "PUBLICKEYD", length = 30)
	public String getPublickeyd() {
		return this.publickeyd;
	}

	public void setPublickeyd(String publickeyd) {
		this.publickeyd = publickeyd;
	}

	@Column(name = "IMEI", length = 15)
	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	@Column(name = "ACCOUNTBALANCE", length = 15)
	public String getAccountbalance() {
		return this.accountbalance;
	}

	public void setAccountbalance(String accountbalance) {
		this.accountbalance = accountbalance;
	}

}