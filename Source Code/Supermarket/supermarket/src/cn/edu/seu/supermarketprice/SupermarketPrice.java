package cn.edu.seu.supermarketprice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SupermarketPrice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUPERMARKET_PRICE", schema = "SYSTEM")
public class SupermarketPrice implements java.io.Serializable {

	// Fields

	private String goodsbarcode;
	private String goodsname;
	private String goodsprice;

	// Constructors

	/** default constructor */
	public SupermarketPrice() {
	}

	/** minimal constructor */
	public SupermarketPrice(String goodsbarcode) {
		this.goodsbarcode = goodsbarcode;
	}

	/** full constructor */
	public SupermarketPrice(String goodsbarcode, String goodsname,
			String goodsprice) {
		this.goodsbarcode = goodsbarcode;
		this.goodsname = goodsname;
		this.goodsprice = goodsprice;
	}

	// Property accessors
	@Id
	@Column(name = "GOODSBARCODE", unique = true, nullable = false, length = 32)
	public String getGoodsbarcode() {
		return this.goodsbarcode;
	}

	public void setGoodsbarcode(String goodsbarcode) {
		this.goodsbarcode = goodsbarcode;
	}

	@Column(name = "GOODSNAME", length = 32)
	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	@Column(name = "GOODSPRICE", length = 10)
	public String getGoodsprice() {
		return this.goodsprice;
	}

	public void setGoodsprice(String goodsprice) {
		this.goodsprice = goodsprice;
	}

}