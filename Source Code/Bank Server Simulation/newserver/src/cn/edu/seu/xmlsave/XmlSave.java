package cn.edu.seu.xmlsave;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * XmlSave entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "XML_SAVE", schema = "SYSTEM")
public class XmlSave implements java.io.Serializable {

	// Fields

	private String notes;
	private String num;
	private String xmldoc;

	// Constructors

	/** default constructor */
	public XmlSave() {
	}

	/** minimal constructor */
	public XmlSave(String notes) {
		this.notes = notes;
	}

	/** full constructor */
	public XmlSave(String notes, String num, String xmldoc) {
		this.notes = notes;
		this.num = num;
		this.xmldoc = xmldoc;
	}

	// Property accessors
	@Id
	@Column(name = "notes", unique = true, nullable = false, length = 16)
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "num", length = 10)
	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Column(name = "xmldoc")
	public String getXmldoc() {
		return this.xmldoc;
	}

	public void setXmldoc(String xmldoc) {
		this.xmldoc = xmldoc;
	}

}