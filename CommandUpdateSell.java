package com.management.inventory.command;

import com.management.inventory.mobile.dto.Item;

public class CommandUpdateSell implements CommandI { 
	Item item;
	
	public CommandUpdateSell(Item _item) {
		this.item = _item;

	}
	public void execute() {
		item.updateSell();
	}
	public Item getItem() {
		return item;
	}

}
