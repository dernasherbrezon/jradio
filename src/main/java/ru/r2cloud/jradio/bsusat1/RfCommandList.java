package ru.r2cloud.jradio.bsusat1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.r2cloud.jradio.util.LittleEndianDataInputStream;

public class RfCommandList {

	private List<RfCommand> commands;

	public RfCommandList() {
		// do nothing
	}

	public RfCommandList(LittleEndianDataInputStream dis) throws IOException {
		commands = new ArrayList<>(10);
		for (int i = 0; i < commands.size(); i++) {
			commands.add(new RfCommand(dis));
		}
	}
	
	public List<RfCommand> getCommands() {
		return commands;
	}
	
	public void setCommands(List<RfCommand> commands) {
		this.commands = commands;
	}
}
