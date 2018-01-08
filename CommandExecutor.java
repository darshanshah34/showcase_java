package com.management.inventory.mobile;

import java.util.ArrayList;
import java.util.List;

import com.management.inventory.mobile.dto.Item;
import com.management.inventory.parser.CommandLineParser;

public class CommandExecutor {
	List<String> commandLines = new ArrayList<String>();
	
	public List<String> getCommandLines() {
		return commandLines;
	}

	public void setCommandLines(List<String> commandLines) {
		this.commandLines = commandLines;
	}


}
