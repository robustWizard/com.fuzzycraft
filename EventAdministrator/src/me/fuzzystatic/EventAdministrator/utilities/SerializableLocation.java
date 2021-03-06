package me.fuzzystatic.EventAdministrator.utilities;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
 
public class SerializableLocation implements ConfigurationSerializable {
    
	private final World world;
	private final double x, y, z;
	private final float yaw, pitch;
	private final Location location;
 
    public SerializableLocation(Location location) {
    	this.world = location.getWorld();
    	this.x = location.getBlockX();
    	this.y = location.getBlockY();
    	this.z = location.getBlockZ();
    	this.pitch = location.getPitch();
    	this.yaw = location.getYaw();
        this.location = location;
    }
 
    public SerializableLocation(Map<String, Object> map) {
    	this.world = Bukkit.getWorld(map.get("world").toString());
    	this.x = (Double) map.get("x");
    	this.y = (Double) map.get("y");
    	this.z = (Double) map.get("z");
    	this.pitch = Float.intBitsToFloat((Integer) map.get("pitch"));    
    	this.yaw = Float.intBitsToFloat((Integer) map.get("yaw"));
    	this.location = new Location(world, x, y, z, yaw, pitch);
    }
 
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("world", location.getWorld().getName());
        map.put("x", location.getX());
        map.put("y", location.getY());
        map.put("z", location.getZ());
        map.put("pitch", Float.floatToIntBits(location.getPitch()));
        map.put("yaw", Float.floatToIntBits(location.getYaw()));
        return map;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public World getWorld() {
        return this.world;
    }
}