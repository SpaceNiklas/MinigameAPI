package com.spaceniklas.minigameapi.managers;

import com.spaceniklas.minigameapi.instance.Arena;
import com.spaceniklas.minigameapi.MinigameAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class HologramManager {

    static ArmorStand duelPlayers;
    static ArmorStand duelGame;
    static ArmorStand duelClick;

    static ArmorStand trainingPlayers;
    static ArmorStand trainingGame;
    static ArmorStand trainingClick;


    public static void addSoloArmorStands(){
            duelPlayers = (ArmorStand) Bukkit.getWorld("spawn_spacecraft").spawnEntity(ConfigManager.getHologramLocation("solo"), EntityType.ARMOR_STAND);
        duelPlayers.setInvisible(true);
        duelPlayers.setInvulnerable(true);
        duelPlayers.setGravity(false);
        duelPlayers.setCustomNameVisible(true);
        duelPlayers.setCustomName(ChatColor.GRAY + "0 Players");

        duelGame = (ArmorStand) Bukkit.getWorld("spawn_spacecraft").spawnEntity(ConfigManager.getHologramLocation("solo").add(0, 0.3, 0), EntityType.ARMOR_STAND);
        duelGame.setInvisible(true);
        duelGame.setInvulnerable(true);
        duelGame.setGravity(false);
        duelGame.setCustomNameVisible(true);
        duelGame.setCustomName(ColorManager.getMainColor() + "Solo" + ChatColor.GRAY + " [v.1.1]");

        duelClick = (ArmorStand) Bukkit.getWorld("spawn_spacecraft").spawnEntity(ConfigManager.getHologramLocation("solo").add(0, 0.6, 0), EntityType.ARMOR_STAND);

        duelClick.setInvisible(true);
        duelClick.setInvulnerable(true);
        duelClick.setGravity(false);
        duelClick.setCustomNameVisible(true);
        duelClick.setCustomName(ColorManager.getAccentColor() + "" + ChatColor.BOLD + "CLICK TO JOIN");

    }
    public static void removeSoloArmorStands(){
        if(duelClick != null && duelGame != null && duelPlayers != null) {
            duelClick.remove();
            duelGame.remove();
            duelPlayers.remove();
        }
    }
    public static void updateSoloArmorStands(){

        int players = 0;
        for(Arena arena : MinigameAPI.instance.getArenaManager().getArenas()){
            players = players + arena.getPlayers().size();
        }

        duelPlayers.setCustomName(ChatColor.GRAY + "" + players + " Players");

    }

    public static void addDoublesArmorStands(){
        trainingPlayers = (ArmorStand) Bukkit.getWorld("spawn_spacecraft").spawnEntity(ConfigManager.getHologramLocation("doubles"), EntityType.ARMOR_STAND);
        trainingPlayers.setInvisible(true);
        trainingPlayers.setInvulnerable(true);
        trainingPlayers.setGravity(false);
        trainingPlayers.setCustomNameVisible(true);
        trainingPlayers.setCustomName(ChatColor.RED + "Coming soon!");

        trainingGame = (ArmorStand) Bukkit.getWorld("spawn_spacecraft").spawnEntity(ConfigManager.getHologramLocation("doubles").add(0, 0.3, 0), EntityType.ARMOR_STAND);
        trainingGame.setInvisible(true);
        trainingGame.setInvulnerable(true);
        trainingGame.setGravity(false);
        trainingGame.setCustomNameVisible(true);
        trainingGame.setCustomName(ColorManager.getMainColor() + "Doubles" + ChatColor.GRAY + " [v.1.0]");

        trainingClick = (ArmorStand) Bukkit.getWorld("spawn_spacecraft").spawnEntity(ConfigManager.getHologramLocation("doubles").add(0, 0.6, 0), EntityType.ARMOR_STAND);
        trainingClick.setInvisible(true);
        trainingClick.setInvulnerable(true);
        trainingClick.setGravity(false);
        trainingClick.setCustomNameVisible(true);
        trainingClick.setCustomName(ColorManager.getAccentColor() + "" + ChatColor.BOLD + "CLICK TO JOIN");

    }
    public static void removeDoublesArmorStands(){
        if(trainingClick != null && trainingGame != null && trainingPlayers != null) {
            trainingGame.remove();
            trainingPlayers.remove();
            trainingClick.remove();
        }
    }

}
