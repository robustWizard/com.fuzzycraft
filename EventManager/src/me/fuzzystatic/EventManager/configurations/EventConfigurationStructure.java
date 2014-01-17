package me.fuzzystatic.EventManager.configurations;

import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import me.fuzzystatic.EventManager.EventManager;
import me.fuzzystatic.EventManager.utilities.ConfigAccessor;
import me.fuzzystatic.EventManager.utilities.SerializableLocation;
import me.fuzzystatic.EventManager.utilities.YMLLocation;

public class EventConfigurationStructure {
	
	public static final String PASTE_LOCATION = "pasteLocation";
	public static final String ENTRANCE = "entrance";
	public static final String EXIT = "exit";
	public static final String CREATURE_LIMIT = "creatureLimit";
	public static final String CYCLE_TIME = "cycleTime";
	public static final String NO_AIR = "noAir";
	public static final String WORLD_CONDITIONS = "worldConditions";
	public static final String WORLD_CONDITIONS_TIME = WORLD_CONDITIONS + "." + "time";
	public static final String WORLD_CONDITIONS_TIME_CYCLE_TIME = WORLD_CONDITIONS + "." + "timeCycleTime";
	public static final String REMINDER = "reminder";
	public static final String REMINDER_MESSAGE = REMINDER + "." + "message";
	public static final String REMINDER_CYCLE_TIME = REMINDER + '.' + "cycleTime";
	
	private static final int defaultCreatureLimit = 201;
	private static final long defaultCycle = 14400;
	private static final boolean defaultNoAir = false;
	private static final long defaultWorldConditionsTime = 18000;
	private static final long defaultWorldConditionsTimeCycleTime = 60;
	private static final long defaultReminderCycleTime = -1;
	
	private final ConfigAccessor configAccessor;
	private final FileConfiguration config;
	
	public EventConfigurationStructure(EventManager plugin, String eventName) {
		this.configAccessor = new ConfigAccessor(plugin, DirectoryStructure.EVENT_DIR + eventName + ".yml");
		this.config = configAccessor.getConfig();
	}
	
	public void setPasteLocation(Map<String, Object> map) {
		new YMLLocation().setLocation(map, this.config, PASTE_LOCATION);
		this.configAccessor.saveConfig();
	}
	
	public void setEntrance(Map<String, Object> map) {
		new YMLLocation().setLocation(map, this.config, ENTRANCE);
		this.configAccessor.saveConfig();
	}
	
	public void setExit(Map<String, Object> map) {
		new YMLLocation().setLocation(map, this.config, EXIT);
		this.configAccessor.saveConfig();
	}
	
	public void setCreatureLimit(int creatureLimit) {
		this.config.set(CREATURE_LIMIT, creatureLimit);
		this.configAccessor.saveConfig();
	}
	
	public void setCycleTime(long seconds) {
		this.config.set(CYCLE_TIME, seconds);
		this.configAccessor.saveConfig();
	}
	
	public void setNoAir(boolean noAir) {
		this.config.set(NO_AIR, noAir);
		this.configAccessor.saveConfig();
	}
	
	public void setWorldConditionsTime(long seconds) {
		this.config.set(WORLD_CONDITIONS_TIME, seconds);
		this.configAccessor.saveConfig();
	}
	
	public void setWorldConditionsTimeCycleTime(long seconds) {
		this.config.set(WORLD_CONDITIONS_TIME_CYCLE_TIME, seconds);
		this.configAccessor.saveConfig();
	}
	
	public void setReminderMessage(String message) {
		this.config.set(REMINDER_MESSAGE, message);
		this.configAccessor.saveConfig();
	}
	
	public void setReminderCycleTime(long seconds) {
		this.config.set(REMINDER_CYCLE_TIME, seconds);
		this.configAccessor.saveConfig();
	}
	
	public void createFileStructure() {
		if(this.config.get(CREATURE_LIMIT) == null) setCreatureLimit(defaultCreatureLimit);
		if(this.config.get(CYCLE_TIME) == null) setCycleTime(defaultCycle);
		if(this.config.get(NO_AIR) == null) setNoAir(defaultNoAir);
		if(this.config.get(WORLD_CONDITIONS_TIME) == null) setWorldConditionsTime(defaultWorldConditionsTime);
		if(this.config.get(WORLD_CONDITIONS_TIME_CYCLE_TIME) == null) setWorldConditionsTimeCycleTime(defaultWorldConditionsTimeCycleTime);
		if(this.config.get(REMINDER_CYCLE_TIME) == null) setReminderCycleTime(defaultReminderCycleTime);
		if(this.config.get(PASTE_LOCATION) == null) new YMLLocation().setBlankLocation(this.config, PASTE_LOCATION);
		if(this.config.get(ENTRANCE) == null) new YMLLocation().setBlankLocation(this.config, ENTRANCE);
		if(this.config.get(EXIT) == null) new YMLLocation().setBlankLocation(this.config, EXIT);
		this.configAccessor.saveConfig();
	}
	
	public Location getPasteLocation() {
		YMLLocation ymlLocation = new YMLLocation();
		return new SerializableLocation(ymlLocation.getLocation(config, PASTE_LOCATION)).getLocation();
	}
	
	public Location getEntrance() {
		YMLLocation ymlLocation = new YMLLocation();
		return new SerializableLocation(ymlLocation.getLocation(config, ENTRANCE)).getLocation();
	}
	
	public Location getExit() {
		YMLLocation ymlLocation = new YMLLocation();
		return new SerializableLocation(ymlLocation.getLocation(config, EXIT)).getLocation();
	}
	
	public int getCreatureLimit() {
		return config.getInt(CREATURE_LIMIT);
	}
	
	public long getCycleTime() {
		return config.getLong(CYCLE_TIME);
	}
	
	public boolean getNoAir() {
		return config.getBoolean(NO_AIR);
	}
	
	public long getWorldConditionsTime() {
		return config.getLong(WORLD_CONDITIONS_TIME);
	}
	
	public long getWorldConditionsTimeCycleTime() {
		return config.getLong(WORLD_CONDITIONS_TIME_CYCLE_TIME);
	}
	
	public String getReminderMessage() {
		return config.getString(REMINDER_MESSAGE);
	}
	
	public long getReminderCycleTime() {
		return config.getLong(REMINDER_CYCLE_TIME);
	}
	
	public Set<String> getSpawns() {
		return config.getConfigurationSection(SpawnConfigurationStructure.SPAWNS).getKeys(false);
	}
}