package com.management.inventory.mobile.dto;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

import com.management.inventory.command.CommandTypes;
import com.management.inventory.mobile.Inventory;

public class Item implements ItemI, Comparator<Item>  {
	String itemName = null;
	int quantity= 0;

	CommandTypes commandType = CommandTypes.CREATE;

	public CommandTypes getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandTypes commandType) {
		this.commandType = commandType;
	}

	private static Inventory inventory = Inventory.getInventory();
	
	

	public String getItemName() {
		return itemName;
	}
	
	public void print() {
		System.out.print( (this.itemName + "                      ").subSequence(0, 20));
		System.out.print( "\t");
		System.out.print(this.costPrice );
		System.out.print( "\t\t");
		System.out.print(this.sellingPrice );
		System.out.print( "\t\t");
		System.out.print(this.quantity );
		System.out.print( "\t\t");
		BigDecimal value = this.costPrice.multiply(new BigDecimal(quantity));
		System.out.println(value );
	}

	BigDecimal value;
	BigDecimal profit;
	int availableQty = 0;    
	
	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public void setValue() {
		
		this.value = this.getCostPrice().multiply(new BigDecimal(quantity));
		BigDecimal totalSellingValue = this.getSellingPrice().multiply(new BigDecimal(quantity));

		this.profit = this.value.subtract(totalSellingValue);
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

	BigDecimal costPrice = new BigDecimal("0.00");
	BigDecimal sellingPrice =  new BigDecimal("0.00");
	
	public int create(String itemName, BigDecimal costPrice, BigDecimal sellingPrice) {
		Item item = new Item();
		item.setItemName(itemName);
		item.setCostPrice(costPrice);
		item.setSellingPrice(sellingPrice);
		item.setQuantity(0);
		item.setCommandType(CommandTypes.CREATE);
		getInventory().addItem(item);
		return quantity;
	}
	
	public Item findItem (String itemName, Inventory inventory) {
		 Item item = inventory.getItem(itemName);
		 return item;
	}

	
	public int delete (String itemName) {
		Item item = getInventory().getItem(itemName);
		//int quantity = tem.getQuantity() - 1;
		//item.setQuantity(item.getQuantity() - 1);
		getInventory().remove(item);
		return 0;
		
	}


	public int updateBuy (String itemName, int a_quantity) {
		Item item = getInventory().getItem(itemName);
		//int quantity = tem.getQuantity() - 1;
		item.setQuantity(item.getQuantity() + a_quantity);
		//System.out.println("updateBuy " + itemName + "  " + item.getQuantity());
		return 0;
	}
	
	public int updateSell (String itemName, int a_quantity) {
		Item item = getInventory().getItem(itemName);
		//int quantity = tem.getQuantity() - 1;
		item.setQuantity(item.getQuantity() - a_quantity);
		BigDecimal profitNow = new BigDecimal("0.00");
		profitNow = profitNow.add(item.getSellingPrice().subtract(item.costPrice));
		profitNow = profitNow.multiply(new BigDecimal(a_quantity));
		Inventory.getInventory();
		BigDecimal temp = Inventory.getProfileSinceLastReporr().add(profitNow);
		Inventory.getInventory();
		Inventory.setProfileSinceLastReporr(temp);
		//System.out.println("updateSell " + itemName + "  " + item.getQuantity());
		//System.out.println("setProfileSinceLastReporr " + Inventory.getProfileSinceLastReporr());
		return 0;
	}
	

	
	
	public Inventory getInventory() {
		return Inventory.getInventory();
	}

	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item create() {
		// TODO Auto-generated method stub
		this.create(itemName, costPrice, sellingPrice);
		return this;
		
	}
	
	public Item updateBuy() {
		// TODO Auto-generated method stub
		this.updateBuy(itemName, quantity);
		return this;
		
	}
	
	public Item delete() {
		// TODO Auto-generated method stub
		this.delete(itemName);
		return this;
		
	}
	
	public Item updateSell () {
		// TODO Auto-generated method stub
		this.updateSell(itemName, quantity);
		return this;
		
	}

	public void report() {
		getInventory().report();
		
	}

	@Override
	public int compare(Item a, Item b) {
		if ((a.getItemName() == null) || (b.getItemName() == null) ) {
			return -1;
		}
		if (a.getItemName().compareToIgnoreCase(b.getItemName())>0)
            return 1;
        else if (a.getItemName().compareToIgnoreCase(b.getItemName())<0)
            return -1;
        else
            return 0;
		}
	
}
