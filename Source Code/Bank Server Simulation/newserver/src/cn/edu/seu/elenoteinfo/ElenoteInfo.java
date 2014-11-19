package cn.edu.seu.elenoteinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ElenoteInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ELENOTE_INFO", schema = "SYSTEM")
public class ElenoteInfo implements java.io.Serializable {

	// Fields

	private String paynum;
	private String payernum;
	private String recernum;
	private String amount;
	private String payerdevice;
	private String recerdevice;
	private String payername;
	private String recername;
	private String transfertime;

	// Constructors

	/** default constructor */
	public ElenoteInfo() {
	}

	/** minimal constructor */
	public ElenoteInfo(String paynum) {
		this.paynum = paynum;
	}

	/** full constructor */
	public ElenoteInfo(String paynum, String payernum, String recernum,
			String amount, String payerdevice, String recerdevice,
			String payername, String recername, String transfertime) {
		this.paynum = paynum;
		this.payernum = payernum;
		this.recernum = recernum;
		this.amount = amount;
		this.payerdevice = payerdevice;
		this.recerdevice = recerdevice;
		this.payername = payername;
		this.recername = recername;
		this.transfertime = transfertime;
	}

	// Property accessors
	@Id
	@Column(name = "PAYNUM", unique = true, nullable = false, length = 31)
	public String getPaynum() {
		return this.paynum;
	}

	public void setPaynum(String paynum) {
		this.paynum = paynum;
	}

	@Column(name = "PAYERNUM", length = 20)
	public String getPayernum() {
		return this.payernum;
	}

	public void setPayernum(String payernum) {
		this.payernum = payernum;
	}

	@Column(name = "RECERNUM", length = 20)
	public String getRecernum() {
		return this.recernum;
	}

	public void setRecernum(String recernum) {
		this.recernum = recernum;
	}

	@Column(name = "AMOUNT", length = 11)
	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "PAYERDEVICE", length = 12)
	public String getPayerdevice() {
		return this.payerdevice;
	}

	public void setPayerdevice(String payerdevice) {
		this.payerdevice = payerdevice;
	}

	@Column(name = "RECERDEVICE", length = 12)
	public String getRecerdevice() {
		return this.recerdevice;
	}

	public void setRecerdevice(String recerdevice) {
		this.recerdevice = recerdevice;
	}

	@Column(name = "PAYERNAME", length = 20)
	public String getPayername() {
		return this.payername;
	}

	public void setPayername(String payername) {
		this.payername = payername;
	}

	@Column(name = "RECERNAME", length = 20)
	public String getRecername() {
		return this.recername;
	}

	public void setRecername(String recername) {
		this.recername = recername;
	}

	@Column(name = "TRANSFERTIME", length = 12)
	public String getTransfertime() {
		return this.transfertime;
	}

	public void setTransfertime(String transfertime) {
		this.transfertime = transfertime;
	}

}