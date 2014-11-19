package cn.edu.seu.persondepositinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PersonDepositInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PERSON_DEPOSIT_INFO", schema = "SYSTEM")
public class PersonDepositInfo implements java.io.Serializable {

	// Fields

	private String notes;
	private String id;
	private String username;
	private String depositway;
	private String interestrateway;
	private String amount;
	private String time;
	private String intesertrate;

	// Constructors

	/** default constructor */
	public PersonDepositInfo() {
	}

	/** minimal constructor */
	public PersonDepositInfo(String notes, String id, String username,
			String depositway, String interestrateway, String amount) {
		this.notes = notes;
		this.id = id;
		this.username = username;
		this.depositway = depositway;
		this.interestrateway = interestrateway;
		this.amount = amount;
	}

	/** full constructor */
	public PersonDepositInfo(String notes, String id, String username,
			String depositway, String interestrateway, String amount,
			String time, String intesertrate) {
		this.notes = notes;
		this.id = id;
		this.username = username;
		this.depositway = depositway;
		this.interestrateway = interestrateway;
		this.amount = amount;
		this.time = time;
		this.intesertrate = intesertrate;
	}

	// Property accessors
	@Id
	@Column(name = "NOTES", unique = true, nullable = false, length = 16)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "ID", nullable = false, length = 10)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USERNAME", nullable = false, length = 32)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "DEPOSITWAY", nullable = false, length = 10)
	public String getDepositway() {
		return this.depositway;
	}

	public void setDepositway(String depositway) {
		this.depositway = depositway;
	}

	@Column(name = "INTERESTRATEWAY", nullable = false, length = 10)
	public String getInterestrateway() {
		return this.interestrateway;
	}

	public void setInterestrateway(String interestrateway) {
		this.interestrateway = interestrateway;
	}

	@Column(name = "AMOUNT", nullable = false, length = 15)
	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "TIME", length = 15)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "INTESERTRATE", length = 8)
	public String getIntesertrate() {
		return this.intesertrate;
	}

	public void setIntesertrate(String intesertrate) {
		this.intesertrate = intesertrate;
	}

}