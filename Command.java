package com.management.inventory.command;

import java.math.BigDecimal;
import java.util.function.Predicate;

import com.management.inventory.mobile.dto.Item;

public class Command {

	String commandName = "test";
	String itemName = "test";
	BigDecimal costPrice = new BigDecimal("1.00");
	int quantity = 0;

	

	BigDecimal sellingPrice;
	String cmdLine = null;
	
	public String getCmdLine() {
		return cmdLine;
	}
	public void setCmdLine(String cmdLine) {
		this.cmdLine = cmdLine;
	}
	public String getCommandName() {
		return commandName;
	}
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Item getItem() {
		Item item = new Item();
		item.setCostPrice(costPrice);
		item.setItemName(itemName);
		item.setQuantity(quantity);
		item.setSellingPrice(sellingPrice);
		item.setCostPrice(costPrice);
		return item;
	}
	
}
