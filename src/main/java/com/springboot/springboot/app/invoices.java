package com.springboot.springboot.app;

import java.sql.Date;

public class invoices {
	Integer itemId;
	String itemName;
	Integer itemQty;
	Double totalPrice;
	Date itemSolidDate;
	public invoices(Integer itemId, String itemName, Integer itemQty, Double totalPrice, Date itemSolidDate) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemQty = itemQty;
		this.totalPrice = totalPrice;
		this.itemSolidDate = itemSolidDate;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getItemQty() {
		return itemQty;
	}
	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getItemSolidDate() {
		return itemSolidDate;
	}
	public void setItemSolidDate(Date itemSolidDate) {
		this.itemSolidDate = itemSolidDate;
	}

}
