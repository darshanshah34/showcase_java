package com.management.inventory.mobile;

import java.util.ArrayList;

import com.management.inventory.command.CommandI;
import com.management.inventory.command.CommandTypes;
import com.management.inventory.parser.CommandLineParser;


public class MainClass {

	private static Inventory inventory = new Inventory();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> lines = new ArrayList<String>();
		
			
		
		String commandLine = "create Book01 10.50 13.79";
		lines.add(commandLine);
		commandLine = "create Food01 1.47 3.98";
		lines.add(commandLine);
		
		commandLine = "create Med01 30.63 34.29";
		lines.add(commandLine);

		commandLine = "create Tab01 57.00 84.98";
		lines.add(commandLine);

		commandLine = "updateBuy Tab01 100";
		lines.add(commandLine);

		commandLine = "updateSell Tab01 2";
		lines.add(commandLine);

		commandLine = "updateBuy Food01 500";
		lines.add(commandLine);

		commandLine = "updateBuy Book01 100";
		lines.add(commandLine);
		
		commandLine = "updateBuy Med01 100";
		lines.add(commandLine);
		
		commandLine = "updateSell Food01 1";
		lines.add(commandLine);

		commandLine = "updateSell Food01 1";
		lines.add(commandLine);

		commandLine = "updateSell Tab01 2";
		lines.add(commandLine);

		commandLine = "report";
		lines.add(commandLine);

		
		commandLine = "delete Book01";
		lines.add(commandLine);
		
		commandLine = "updateSell Tab01 5";
		lines.add(commandLine);
		commandLine = "create Mobile01 10.51 44.56";
		lines.add(commandLine);
		
		commandLine = "updateBuy Mobile01 250";
		lines.add(commandLine);

		commandLine = "updateSell Food01 5";
		lines.add(commandLine);

		commandLine = "updateSell Mobile01 4";
		lines.add(commandLine);

		commandLine = "updateSell Med01 10";
		lines.add(commandLine);

		commandLine = "report";
		lines.add(commandLine);

		int index = 0;
		try {
			for (String commandL: lines) {
				index ++;
				CommandLineParser clp = new CommandLineParser(commandLine);
				clp.setCommanLine(commandL);
				//System.out.println(commandL + "Index=" + index) ;
				CommandI command = clp.parse();
				if (command != null && command.getItem().getCommandType().equals(CommandTypes.DELETE)) {
					getInventory().remove(command.getItem());
					command.execute();
				} else {
					if (command != null) {
						getInventory().addItem(command.getItem());
						command.execute();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} //end of main method

	public static Inventory getInventory() {
		return inventory;
	}
	
	public void setInventory(Inventory inventory) {
		MainClass.inventory = inventory;
	}

}
