package com.management.inventory.command;

import com.management.inventory.mobile.dto.Item;

public class CommandUpdateBuy implements CommandI {
	Item item;
	public CommandUpdateBuy(Item _item) {
		this.item = _item;

	}
	public void execute() {
		item.updateBuy();
	}
	public Item getItem() {
		return item;
	}


}
