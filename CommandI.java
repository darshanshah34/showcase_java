package com.management.inventory.command;

import com.management.inventory.mobile.dto.Item;

public interface CommandI {
	
	public void execute();
	
	public Item getItem() ;

}
