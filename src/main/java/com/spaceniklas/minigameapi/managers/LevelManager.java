package com.spaceniklas.minigameapi.managers;

import com.spaceniklas.minigameapi.MinigameAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;

public class LevelManager {

    public static int getLevel(Player p){
        if(MinigameAPI.level.get("level." + p.getUniqueId().toString()) == null){
            return -1;
        }
        return (int) MinigameAPI.level.get("level." + p.getUniqueId().toString()) / 100;
    }
    public static int getXp(Player p){
        if(MinigameAPI.level.get("level." + p.getUniqueId().toString()) == null){
            return -1;
        }
        return (int) MinigameAPI.level.get("level." + p.getUniqueId().toString());
    }
    public static void setLevel(Player p, int level) throws IOException {
        MinigameAPI.level.set("level." + p.getUniqueId().toString(), level * 100);
        MinigameAPI.level.save(MinigameAPI.levelFile);
        p.setLevel(level);
        p.setLevel(level);
        p.sendMessage(ChatColor.DARK_GRAY + "--------------------------");
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.MAGIC + "       s " + ChatColor.RESET + ColorManager.getMainColor() + "Level Up! " + ChatColor.GRAY + "" + ChatColor.MAGIC +"s");
        p.sendMessage(ChatColor.GRAY + "    You are now Level " + ColorManager.getAccentColor() + getLevel(p) + ChatColor.GRAY + "!");
        p.sendMessage(ChatColor.DARK_GRAY + "--------------------------");
    }
    public static void setXp(Player p, int xp) throws IOException {
        MinigameAPI.level.set("level." + p.getUniqueId().toString(), xp);
        MinigameAPI.level.save(MinigameAPI.levelFile);
        int amount = getXp(p) / 100;
        int rest = getXp(p) - amount * 100;
        p.setLevel(amount);
        p.setExp((float) (rest * 0.01));
        if(rest == 0 && amount != 0){
            p.sendMessage(ChatColor.DARK_GRAY + "--------------------------");
            p.sendMessage(ChatColor.GRAY + "" + ChatColor.MAGIC + "       s " + ChatColor.RESET + ColorManager.getMainColor() + "Level Up! " + ChatColor.GRAY + "" + ChatColor.MAGIC +"s");
            p.sendMessage(ChatColor.GRAY + "    You are now Level " + ColorManager.getAccentColor() + amount + ChatColor.GRAY + "!");
            p.sendMessage(ChatColor.DARK_GRAY + "--------------------------");
        }

    }

    public static void addLevel(Player p, int level) throws IOException {
        MinigameAPI.level.set("level." + p.getUniqueId().toString(), level * 100 + getLevel(p));
        MinigameAPI.level.save(MinigameAPI.levelFile);
        p.setLevel(getLevel(p) / 100 + level);
        p.sendMessage(ChatColor.DARK_GRAY + "--------------------------");
        p.sendMessage(ChatColor.GRAY + "" + ChatColor.MAGIC + "       s " + ChatColor.RESET + ColorManager.getMainColor() + "Level Up! " + ChatColor.GRAY + "" + ChatColor.MAGIC +"s");
        p.sendMessage(ChatColor.GRAY + "    You are now Level " + ColorManager.getAccentColor() + getLevel(p) + ChatColor.GRAY + "!");
        p.sendMessage(ChatColor.DARK_GRAY + "--------------------------");
        p.setLevel(getLevel(p) + level);

    }
    public static void addXp(Player p, int xp) throws IOException {
        MinigameAPI.level.set("level." + p.getUniqueId().toString(), xp + getXp(p));
        MinigameAPI.level.save(MinigameAPI.levelFile);

        int amount = getXp(p) / 100;
        int rest = getXp(p) - amount * 100;
        p.setLevel(amount);
        p.setExp((float) (rest * 0.01));
        if(rest == 0){
            p.sendMessage(ChatColor.DARK_GRAY + "--------------------------");
            p.sendMessage(ChatColor.GRAY + "" + ChatColor.MAGIC + "       s " + ChatColor.RESET + ColorManager.getMainColor() + "Level Up! " + ChatColor.GRAY + "" + ChatColor.MAGIC +"s");
            p.sendMessage(ChatColor.GRAY + "    You are now Level " + ColorManager.getAccentColor() + getLevel(p) + ChatColor.GRAY + "!");
            p.sendMessage(ChatColor.DARK_GRAY + "--------------------------");
        }

    }

}
