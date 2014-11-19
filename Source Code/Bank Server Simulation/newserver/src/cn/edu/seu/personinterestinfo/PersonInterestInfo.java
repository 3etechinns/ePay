package cn.edu.seu.personinterestinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PersonInterestInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PERSON_INTEREST_INFO", schema = "SYSTEM")
public class PersonInterestInfo implements java.io.Serializable {

	// Fields

	private String username;
	private String id;
	private String notes;
	private String balance;
	private String time;

	// Constructors

	/** default constructor */
	public PersonInterestInfo() {
	}

	/** minimal constructor */
	public PersonInterestInfo(String username, String id, String notes,
			String balance) {
		this.username = username;
		this.id = id;
		this.notes = notes;
		this.balance = balance;
	}

	/** full constructor */
	public PersonInterestInfo(String username, String id, String notes,
			String balance, String time) {
		this.username = username;
		this.id = id;
		this.notes = notes;
		this.balance = balance;
		this.time = time;
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

	@Column(name = "ID", nullable = false, length = 10)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NOTES", nullable = false, length = 16)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "BALANCE", nullable = false, length = 10)
	public String getBalance() {
		return this.balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Column(name = "TIME", length = 15)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}