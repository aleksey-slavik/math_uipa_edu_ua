package ua.uipa.math.web.command;

import java.util.Map;
import java.util.TreeMap;
import ua.uipa.math.web.command.ua.NewsCommand_UA;

public class CommandContainer {

	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		commands.put("ua_news", new NewsCommand_UA());
		commands.put("ua_conferences", new NewsCommand_UA());
		commands.put("noCommand", new NoCommand());
	}

	public static Command get(String commandName){
		if(commandName ==null||!commands.containsKey(commandName)){
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}
}
