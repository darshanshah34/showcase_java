package com.management.inventory.command;

import com.management.inventory.mobile.dto.Item;

public class CommandCreate implements CommandI { 
	Item item;
	
	public CommandCreate(Item _item) {
		this.item = _item;

	}
	public void execute() {
		item.create();
	}
	public Item getItem() {
		return item;
	}

}
