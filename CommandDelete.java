package com.management.inventory.command;

import com.management.inventory.mobile.dto.Item;

public class CommandDelete implements CommandI { 
	Item item;
	
	public CommandDelete(Item _item) {
		this.item = _item;

	}
	public void execute() {
		item.delete();
	}

	public Item getItem() {
		return item;
	}

	
}
