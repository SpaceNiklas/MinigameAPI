package com.spaceniklas.minigameapi.listeners;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.instance.Arena;
import com.spaceniklas.minigameapi.managers.ConfigManager;
import com.spaceniklas.minigameapi.managers.LevelManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.IOException;

public class ConnectListener implements Listener {

    private MinigameAPI minigameAPI;

    public ConnectListener(MinigameAPI minigameAPI){
        this.minigameAPI = minigameAPI;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.getPlayer().teleport(ConfigManager.getLobbySpawn());
        if(LevelManager.getLevel(p) == -1 && LevelManager.getXp(p) == -1){
            try {
                LevelManager.setXp(p, 25);
                minigameAPI.saveConfig();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            p.sendMessage(ChatColor.GOLD + "+25 Xp (First Join)");
        }
        try {
            LevelManager.setXp(p, LevelManager.getXp(p));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        Arena arena = minigameAPI.getArenaManager().getArena(e.getPlayer());
        if(arena != null){
            arena.removePlayer(e.getPlayer());
        }

    }

    @EventHandler
    public void onRespawnEvent(PlayerRespawnEvent e){
        e.setRespawnLocation(ConfigManager.getLobbySpawn());
    }

}
