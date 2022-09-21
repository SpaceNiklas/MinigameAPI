package com.spaceniklas.minigameapi.listeners;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.instance.Arena;
import com.spaceniklas.minigameapi.managers.LevelManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent e){
        e.setCancelled(true);
        Player p = e.getPlayer();
        if(MinigameAPI.instance.getArenaManager().getArena(p) == null){

            for(Player player : Bukkit.getOnlinePlayers()){
                if(MinigameAPI.instance.getArenaManager().getArena(player) == null){

                    if(LevelManager.getLevel(p) < 50){
                        player.sendMessage(ChatColor.GRAY + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
                    }else
                    if(LevelManager.getLevel(p) < 100){
                        player.sendMessage(ChatColor.WHITE + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
                    }else
                    if(LevelManager.getLevel(p) < 150){
                        player.sendMessage(ChatColor.BLUE + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
                    }else
                    if(LevelManager.getLevel(p) < 200){
                        player.sendMessage(ChatColor.DARK_AQUA + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
                    }else
                    if(LevelManager.getLevel(p) >= 250){
                        player.sendMessage(ChatColor.DARK_RED + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
                    }

                }
            }

        }
        if(MinigameAPI.instance.getArenaManager().getArena(e.getPlayer()) != null){
            Arena arena = MinigameAPI.instance.getArenaManager().getArena(p);
            if(LevelManager.getLevel(p) < 50){
                arena.sendMessage(ChatColor.GRAY + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
            }else
            if(LevelManager.getLevel(p) < 100){
                arena.sendMessage(ChatColor.WHITE + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
            }else
            if(LevelManager.getLevel(p) < 150){
                arena.sendMessage(ChatColor.BLUE + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
            }else
            if(LevelManager.getLevel(p) < 200){
                arena.sendMessage(ChatColor.DARK_AQUA + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
            }else
            if(LevelManager.getLevel(p) < 250){
                arena.sendMessage(ChatColor.DARK_RED + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
            }else
            if(LevelManager.getLevel(p) < 300){
                arena.sendMessage(ChatColor.LIGHT_PURPLE + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
            }else
             if(LevelManager.getLevel(p) > 300){
                arena.sendMessage(ChatColor.DARK_PURPLE + "[" + LevelManager.getLevel(p) + "☆] " + ChatColor.WHITE + p.getName() + ": " + e.getMessage());
            }
        }
    }

}
