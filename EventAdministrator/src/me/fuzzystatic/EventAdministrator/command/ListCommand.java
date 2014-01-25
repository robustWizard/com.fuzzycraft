package me.fuzzystatic.EventAdministrator.command;

import me.fuzzystatic.EventAdministrator.EventAdministrator;
import me.fuzzystatic.EventAdministrator.commands.list.EventList;
import me.fuzzystatic.EventAdministrator.commands.list.SpawnList;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class ListCommand extends Command {
	
	@Override
	public boolean runCommand(EventAdministrator plugin, CommandSender sender, String[] args) {
		if(args.length > 1) {
			switch(args[1]) {
			case "e" 			: new EventList().runCommand(plugin, sender, args); break;
			case "event" 		: new EventList().runCommand(plugin, sender, args); break;
			case "events" 		: new EventList().runCommand(plugin, sender, args); break;
			case "s" 			: new SpawnList().runCommand(plugin, sender, args); break;
			case "spawn" 		: new SpawnList().runCommand(plugin, sender, args); break;
			case "spawns" 		: new SpawnList().runCommand(plugin, sender, args); break;
			}
			return true;
		} else {
			sender.sendMessage(new EventList().usage());
			sender.sendMessage(new SpawnList().usage());
			return true;
		}
	}
	
	
	public Permission permission() {
		Permission permission = new Permission("list");
		permission.addParent(super.permission(), true);
		return permission;
	}
	
	public String usage() {
		return super.usage() + " list";
	}
}
