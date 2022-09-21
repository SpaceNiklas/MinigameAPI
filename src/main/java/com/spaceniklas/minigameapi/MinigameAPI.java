package com.spaceniklas.minigameapi;

import com.spaceniklas.minigameapi.commands.*;
import com.spaceniklas.minigameapi.listeners.ChatListener;
import com.spaceniklas.minigameapi.listeners.ConnectListener;
import com.spaceniklas.minigameapi.listeners.GameListener;
import com.spaceniklas.minigameapi.managers.ArenaManager;
import com.spaceniklas.minigameapi.managers.ConfigManager;
import com.spaceniklas.minigameapi.managers.HologramManager;
import com.spaceniklas.minigameapi.menus.PlayMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class MinigameAPI extends JavaPlugin {

    private ArenaManager arenaManager;
    public static MinigameAPI instance;
    public static YamlConfiguration level;
    public static File levelFile;

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);
        arenaManager = new ArenaManager(this);
        instance = this;

        Bukkit.getLogger().info("[KnockOff] Plugin is up!");
        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        getCommand("arena").setExecutor(new ArenaCommand(this));
        getCommand("ac").setExecutor(new ArenaCreateCommand(this));
        getCommand("ac").setTabCompleter(new ArenaCreateTab(this));
        getCommand("arena").setTabCompleter(new ArenaTab(this));
        getCommand("leave").setExecutor(new LeaveCommand());
        getCommand("level").setExecutor(new LevelCommand());
        getCommand("level").setTabCompleter(new LevelTab());

        HologramManager.addSoloArmorStands();
        HologramManager.addDoublesArmorStands();

        createLevelFile();

        if(level.get("level") == null) {
            level.set("level." + UUID.randomUUID() , 10);
            try {
                level.save(levelFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void onDisable(){

        HologramManager.removeSoloArmorStands();
        HologramManager.removeDoublesArmorStands();
    }

    public ArenaManager getArenaManager(){ return arenaManager;}

    public void createLevelFile(){

        levelFile = new File(this.getDataFolder(), "level.yml");
        if(!levelFile.exists()){
            try {
                levelFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().warning("[MinigameAPI] Error while creating level.yml!");
                e.printStackTrace();
            }
        }

        level = YamlConfiguration.loadConfiguration(levelFile);

    }

}
