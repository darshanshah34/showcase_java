package com.management.inventory.mobile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.management.inventory.command.CommandTypes;
import com.management.inventory.mobile.dto.Item;

public class Inventory {

	// @Injectable
	// ArrayList <Item> items = new ArrayList<Item>();
	private static Set<Item> items = null;
	public static Inventory inventory = null;
	public static BigDecimal profitSinceLastReport = new BigDecimal("0.00");

	public static BigDecimal getProfileSinceLastReporr() {
		return profitSinceLastReport;
	}

	public static void setProfileSinceLastReporr(BigDecimal profileSinceLastReporr) {
		Inventory.profitSinceLastReport = profileSinceLastReporr;
	}

	public Set<Item> getItems() {
		return items;
	}

	public Item getItem(Item a) {
		for (Item item : items) {
			if (item.compare(item, a) == 0) {
				return item;
			}
		}
		return null;
	}

	public static Inventory getInventory() {
		if (inventory == null) {
			inventory = new Inventory();
			Inventory.items = new HashSet<Item>();
		}
		return inventory;
	}

	public Inventory() {
	}

	public void addItem(Item e) {
		if (e.getCommandType().equals(CommandTypes.REPORT)) {
			Inventory.report();
		}

		/* before we add new we need to make sure that there is only one item */
		if (items.size() == 0) {
			items.add(e);
		}
		synchronized (e) {
			Item item = getItem(e);
			if (item == null) {
				items.add(e);
			}
		}
	}

	public static void report() {
		ArrayList<Item> list = new ArrayList<Item>();
		list.addAll(items);
		System.out.print("\n\n                      	INVENTORY REPORT\n\n");
		System.out.print("Item Name 	        Bought At    	Sold At  	AvailableQty   	Value\n");
		System.out.print("--------- 	        ---------    	-------  	-----------   	-------\n");
		// return p1.name.compareTo(p2.name);
		Collections.sort(list, (p1, p2) -> {
			return p1.getItemName().compareTo(p2.getItemName());
		});
		list.forEach(Item::print);
		list.forEach(Item::setValue);
		System.out.print("\nTotal value                                                   ");
		BigDecimal total = new BigDecimal("0.00");
		for (Item item : items) {
			total = total.add(item.getValue());
		}
		System.out.println(total);
		System.out.print("Profit since previous report                                  ");
		System.out.println(profitSinceLastReport);
	}

	public Item getItem(String itemName) {
		for (Item item : items) {
			if (item.getItemName() == null) {
				return item;
			}
			if (item.getItemName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	public void remove(Item e) {
		Item item = getItem(e);
		if (item != null) {
			items.remove(e);
			//System.out.println("removed item " + e.getItemName());
		}
	
		
	}

}
