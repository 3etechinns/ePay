package cn.edu.seu.rsadb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RsaDb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RSA_DB", schema = "SYSTEM")
public class RsaDb implements java.io.Serializable {

	// Fields

	private String idnum;
	private String keye;
	private String keyd;
	private String theidu;

	// Constructors

	/** default constructor */
	public RsaDb() {
	}

	/** minimal constructor */
	public RsaDb(String idnum) {
		this.idnum = idnum;
	}

	/** full constructor */
	public RsaDb(String idnum, String keye, String keyd, String theidu) {
		this.idnum = idnum;
		this.keye = keye;
		this.keyd = keyd;
		this.theidu = theidu;
	}

	// Property accessors
	@Id
	@Column(name = "IDNUM", unique = true, nullable = false, length = 5)
	public String getIdnum() {
		return this.idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	@Column(name = "KEYE", length = 20)
	public String getKeye() {
		return this.keye;
	}

	public void setKeye(String keye) {
		this.keye = keye;
	}

	@Column(name = "KEYD", length = 30)
	public String getKeyd() {
		return this.keyd;
	}

	public void setKeyd(String keyd) {
		this.keyd = keyd;
	}

	@Column(name = "THEIDU", length = 5)
	public String getTheidu() {
		return this.theidu;
	}

	public void setTheidu(String theidu) {
		this.theidu = theidu;
	}

}