package cn.edu.seu.supermarketprice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SupermartketPrice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUPERMARTKET_PRICE", schema = "SYSTEM")
public class SupermartketPrice implements java.io.Serializable {

	// Fields

	private String goodsbarcode;
	private String goodsname;
	private String goodsprice;

	// Constructors

	/** default constructor */
	public SupermartketPrice() {
	}

	/** minimal constructor */
	public SupermartketPrice(String goodsbarcode) {
		this.goodsbarcode = goodsbarcode;
	}

	/** full constructor */
	public SupermartketPrice(String goodsbarcode, String goodsname,
			String goodsprice) {
		this.goodsbarcode = goodsbarcode;
		this.goodsname = goodsname;
		this.goodsprice = goodsprice;
	}

	// Property accessors
	@Id
	@Column(name = "goodsbarcode", unique = true, nullable = false, length = 32)
	public String getGoodsbarcode() {
		return this.goodsbarcode;
	}

	public void setGoodsbarcode(String goodsbarcode) {
		this.goodsbarcode = goodsbarcode;
	}

	@Column(name = "goodsname", length = 32)
	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	@Column(name = "goodsprice", length = 10)
	public String getGoodsprice() {
		return this.goodsprice;
	}

	public void setGoodsprice(String goodsprice) {
		this.goodsprice = goodsprice;
	}

}