package me.fuzzystatic.EventManager.schedules;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.configurations.EventConfigurationStructure;
import me.fuzzystatic.EventManager.entities.Entities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerItems {
	
	private EventManager plugin;
	private final String eventName;
	private final EventConfigurationStructure ecs;
	
	public PlayerItems(EventManager plugin, String eventName) {
		this.plugin = plugin;
		this.eventName = eventName;
		this.ecs = new EventConfigurationStructure(plugin, eventName);
	}
	
	private final ItemStack bow = new ItemStack(Material.BOW, 1);
	private final ItemStack arrows = new ItemStack(Material.ARROW, 64);
	private final ItemStack food = new ItemStack(Material.BREAD, 6);
	
	public void start() {
		int id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				Entities eventEntities = new Entities(ecs.getPasteLocation().getWorld());
				for (Player player : eventEntities.getPlayers()) {
					PlayerInventory inventory = player.getInventory();
				    inventory.addItem(bow);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(arrows);
				    inventory.addItem(food);
				    inventory.addItem(bow);
				}	
			}
		}, 60* 20, 90 * 20);
		EventSchedulerMultimap esm = new EventSchedulerMultimap();
		esm.set(eventName, id);
	}
}
