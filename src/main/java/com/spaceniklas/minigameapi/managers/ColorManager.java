package com.spaceniklas.minigameapi.managers;

import com.spaceniklas.minigameapi.instance.Arena;
import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.instance.Game;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ColorManager {

    public static ChatColor getChatColor(Player p){
        Arena arena = MinigameAPI.instance.getArenaManager().getArena(p);
        Game game = arena.getGame();
        if(arena != null){
            if(p.equals(game.playerList.get(0))){
                return ChatColor.BLUE;
            }
            if(p.equals(game.playerList.get(1))){
                return ChatColor.RED;
            }
            if(p.equals(game.playerList.get(2))){
                return ChatColor.GREEN;
            }
            if(p.equals(game.playerList.get(3))){
                return ChatColor.YELLOW;
            }
        }
        return ChatColor.GRAY;
    }

    public static void setMainColor(String color){
        MinigameAPI.instance.getConfig().set("main-color", color.toUpperCase());
        MinigameAPI.instance.saveConfig();
    }

    public static ChatColor getMainColor(){
        return ChatColor.valueOf((String) MinigameAPI.instance.getConfig().get("main-color"));
    }

    public static void setAccentColor(String color){
        MinigameAPI.instance.getConfig().set("accent-color", color.toUpperCase());
        MinigameAPI.instance.saveConfig();
    }

    public static ChatColor getAccentColor(){
        return ChatColor.valueOf((String) MinigameAPI.instance.getConfig().get("accent-color"));
    }

}
