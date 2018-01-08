package com.management.inventory.command;

import com.management.inventory.mobile.dto.Item;

public class CommandReport implements CommandI { 
	Item item;
	
	public CommandReport(Item _item) {
		this.item = _item;

	}
	public void execute() {
		item.report();
	}
	
	public Item getItem() {
		return item;
	}


}
