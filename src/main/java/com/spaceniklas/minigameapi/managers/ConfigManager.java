package com.spaceniklas.minigameapi.managers;

import com.spaceniklas.minigameapi.MinigameAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setupConfig(MinigameAPI minigameAPI){
        ConfigManager.config = minigameAPI.getConfig();
        minigameAPI.saveDefaultConfig();
    }

    public static int getRequiredPlayers(){return config.getInt("required-players");}

    public static int getCountdownSeconds(){return config.getInt("countdown-seconds");}

    public static String getDisplayName(int id){return config.getString("arenas." + id + ".displayname"); }

    public static Location getLobbySpawn(){
        return new Location(
                Bukkit.getWorld(config.getString("lobby-spawn.world")),
                config.getDouble("lobby-spawn.x"),
                config.getDouble("lobby-spawn.y"),
                config.getDouble("lobby-spawn.z"),
                (float) config.getDouble("lobby-spawn.yaw"),
                (float) config.getDouble("lobby-spawn.pitch"));
    }

    public static Location getBlueSpawn(int id){
        return new Location(
                Bukkit.getWorld(config.getString("arenas." + id + ".blue.world")),
                config.getDouble("arenas." + id + ".blue.x"),
                config.getDouble("arenas." + id + ".blue.y"),
                config.getDouble("arenas." + id + ".blue.z"));
    }

    public static Location getRedSpawn(int id){
        return new Location(
                Bukkit.getWorld(config.getString("arenas." + id + ".red.world")),
                config.getDouble("arenas." + id + ".red.x"),
                config.getDouble("arenas." + id + ".red.y"),
                config.getDouble("arenas." + id + ".red.z"));
    }

    public static Location getGreenSpawn(int id){
        return new Location(
                Bukkit.getWorld(config.getString("arenas." + id + ".green.world")),
                config.getDouble("arenas." + id + ".green.x"),
                config.getDouble("arenas." + id + ".green.y"),
                config.getDouble("arenas." + id + ".green.z"));
    }

    public static Location getYellowSpawn(int id){
        return new Location(
                Bukkit.getWorld(config.getString("arenas." + id + ".yellow.world")),
                config.getDouble("arenas." + id + ".yellow.x"),
                config.getDouble("arenas." + id + ".yellow.y"),
                config.getDouble("arenas." + id + ".yellow.z"));
    }

    public static Location getBlueHoloSpawn(int id, int number){
        return new Location(
                Bukkit.getWorld(config.getString("arenas." + id + ".blue." + number + "-world")),
                config.getDouble("arenas." + id + ".blue." + number + "-x"),
                config.getDouble("arenas." + id + ".blue." + number + "-y"),
                config.getDouble("arenas." + id + ".blue." + number + "-z"));
    }

    public static Location getRedHoloSpawn(int id, int number){
        return new Location(
                Bukkit.getWorld(config.getString("arenas." + id + ".red." + number + "-world")),
                config.getDouble("arenas." + id + ".red." + number + "-x"),
                config.getDouble("arenas." + id + ".red." + number + "-y"),
                config.getDouble("arenas." + id + ".red." + number + "-z"));
    }

    public static Location getGreenHoloSpawn(int id, int number){
        return new Location(
                Bukkit.getWorld(config.getString("arenas." + id + ".green." + number + "-world")),
                config.getDouble("arenas." + id + ".green." + number + "-x"),
                config.getDouble("arenas." + id + ".green." + number + "-y"),
                config.getDouble("arenas." + id + ".green." + number + "-z"));
    }

    public static Location getYellowHoloSpawn(int id, int number){
        return new Location(
                Bukkit.getWorld(config.getString("arenas." + id + ".yellow." + number + "-world")),
                config.getDouble("arenas." + id + ".yellow." + number + "-x"),
                config.getDouble("arenas." + id + ".yellow." + number + "-y"),
                config.getDouble("arenas." + id + ".yellow." + number + "-z"));
    }

    public static Location getHologramLocation(String name){
        return new Location(
                Bukkit.getWorld(config.getString("holograms." + name + ".world")),
                config.getDouble("holograms." + name + ".x"),
                config.getDouble("holograms." + name + ".y"),
                config.getDouble("holograms." + name + ".z")
        );
    }
    public static Location getArenaSpawn(int id){
        return new Location(
                Bukkit.getWorld(config.getString("arenas." + id + ".world")),
                config.getDouble("arenas." + id + ".x"),
                config.getDouble("arenas." + id + ".y"),
                config.getDouble("arenas." + id + ".z"));
    }
}
