package com.management.inventory.parser;

import java.math.BigDecimal;
import java.util.StringTokenizer;

import com.management.inventory.command.Command;
import com.management.inventory.command.CommandCreate;
import com.management.inventory.command.CommandDelete;
import com.management.inventory.command.CommandI;
import com.management.inventory.command.CommandReport;
import com.management.inventory.command.CommandTypes;
import com.management.inventory.command.CommandUpdateBuy;
import com.management.inventory.command.CommandUpdateSell;
import com.management.inventory.mobile.Inventory;
import com.management.inventory.mobile.dto.Item;

public class CommandLineParser {
	String commanLine;
	String commadName;
	Command command = new Command();
	


	Item item;
	
	public CommandLineParser(String commanLineA) {
		commanLine =  commanLineA;
	}
	
	public String getCommadName() {
		return commadName;
	}

	public void setCommadName(String commadName) {
		this.commadName = commadName;
	}

	

	public String getCommanLine() {
		return commanLine;
	}

	public void setCommanLine(String commanLine) {
		this.commanLine = commanLine;
	}
	
	public CommandI parse() {
		CommandI command = null;
		StringTokenizer st = new StringTokenizer(commanLine);
		Item item = new Item();
		String commandName = null;
		while (st.hasMoreTokens()) {
			String temp = null;
			if ((temp = st.nextToken()) != null) {
				commandName = temp;
				if ("report".equals(commandName)) {
					procesReport(item);
					return command;
				}
				if ("delete".equals(commandName)) {
					item.setCommandType(CommandTypes.DELETE);
					if ((temp = st.nextToken()) != null) {
						item.setItemName(temp);
						break;
					}					
				}				
			}
			if ((temp = st.nextToken()) != null) {
				item.setItemName(temp);
			}
			if (!st.hasMoreTokens())
				return command;
			if ((temp = st.nextToken()) != null) {
				processUpdate(st, item, commandName, temp);
			}
			break;
		}
		command = createCommandItem(item, commandName);
		return command;
	}

	private void procesReport(Item item) {
		item.setCommandType(CommandTypes.REPORT);
		item.getInventory();
		Inventory.report();
	}

	private void processUpdate(StringTokenizer st, Item item, String commandName, String temp) {
		if ( ("updateBuy".equals(commandName)) || ("updateSell".equals(commandName)))  {
			item.setQuantity(new Integer(temp).intValue());
		} else {
			item.setCostPrice(new BigDecimal(temp));
			if ((temp = st.nextToken()) != null) {
				item.setSellingPrice(new BigDecimal(temp));
			}
		}
	}

	private CommandI createCommandItem(Item item, String commandName) {
		if ("create".equals(commandName)) {
			return new CommandCreate(item);
		} else if ("updateBuy".equals(commandName)) {
			return new CommandUpdateBuy(item);
		} else if ("updateSell".equals(commandName)) {
			return new CommandUpdateSell(item);

		} else if ("report".equals(commandName)) {
			return new CommandReport(item);

		} else if ("delete".equals(commandName)) {
			return new CommandDelete(item);

		}
		return null;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

}
