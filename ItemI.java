package com.management.inventory.mobile.dto;
import java.math.BigDecimal;

public interface ItemI {
	
	
	public int create(String itemName, BigDecimal costPrice, BigDecimal sellingPrice);
	public int delete (String itemName);
	public int updateBuy (String itemName, int quantity);
	public int updateSell (String itemName, int quantity);
	
	//public int report ();
	
}
