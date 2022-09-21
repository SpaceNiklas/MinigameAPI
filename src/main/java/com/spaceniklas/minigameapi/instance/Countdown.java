package com.spaceniklas.minigameapi.instance;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.GameState;
import com.spaceniklas.minigameapi.managers.ColorManager;
import com.spaceniklas.minigameapi.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Countdown extends BukkitRunnable {

    private MinigameAPI minigameAPI;
    private Arena arena;
    public int countdownSeconds = 0;

    public Countdown(MinigameAPI minigameAPI, Arena arena){
        this.minigameAPI = minigameAPI;
        this.arena = arena;
        this.countdownSeconds = ConfigManager.getCountdownSeconds();
    }

    public void start(){
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(minigameAPI, 0, 20);
    }

    @Override
    public void run() {
        for(UUID uuid: arena.getPlayers()){
            Bukkit.getPlayer(uuid).setLevel(countdownSeconds);
        }
        if(countdownSeconds == 60){
            arena.sendMessage(ChatColor.GRAY + "Game starting in " + ColorManager.getMainColor() + "" + countdownSeconds + ChatColor.GRAY + " second" + (countdownSeconds == 1 ? "" : "s") + "!");
            arena.playSound(Sound.UI_BUTTON_CLICK);
        }
        if(countdownSeconds == 50){
            arena.sendMessage(ChatColor.GRAY + "Game starting in " + ColorManager.getMainColor() + "" + countdownSeconds + ChatColor.GRAY + " second" + (countdownSeconds == 1 ? "" : "s") + "!");
            arena.playSound(Sound.UI_BUTTON_CLICK);
        }
        if(countdownSeconds == 40){
            arena.sendMessage(ChatColor.GRAY + "Game starting in " + ColorManager.getMainColor() + "" + countdownSeconds + ChatColor.GRAY + " second" + (countdownSeconds == 1 ? "" : "s") + "!");
            arena.playSound(Sound.UI_BUTTON_CLICK);
        }
        if(countdownSeconds == 30){
            arena.sendMessage(ChatColor.GRAY + "Game starting in " + ColorManager.getMainColor() + "" + countdownSeconds + ChatColor.GRAY + " second" + (countdownSeconds == 1 ? "" : "s") + "!");
            arena.playSound(Sound.UI_BUTTON_CLICK);
        }
        if(countdownSeconds == 20){
            arena.sendMessage(ChatColor.GRAY + "Game starting in " + ColorManager.getMainColor() + "" + countdownSeconds + ChatColor.GRAY + " second" + (countdownSeconds == 1 ? "" : "s") + "!");
            arena.playSound(Sound.UI_BUTTON_CLICK);
        }
        if(countdownSeconds == 10){
            arena.sendMessage(ChatColor.GRAY + "Game starting in " + ColorManager.getMainColor() + "" + countdownSeconds + ChatColor.GRAY + " second" + (countdownSeconds == 1 ? "" : "s") + "!");
            arena.playSound(Sound.UI_BUTTON_CLICK);
        }
        if(countdownSeconds == 0){
            arena.sendMessage(ChatColor.GRAY + "Game has started!");
            arena.sendMessage(ChatColor.RED + "(!) If you believe a player is cheating, use" + ChatColor.BOLD + " /report " + ChatColor.RESET + ChatColor.RED + "to report them!");
            cancel();
            arena.start();
            return;
        }

        if(countdownSeconds <= 5){
            arena.sendTitle(ColorManager.getMainColor() + "" + countdownSeconds, "");
            arena.playSound(Sound.UI_BUTTON_CLICK);
            arena.sendMessage(ChatColor.GRAY + "Game starting in " + ChatColor.BLUE + "" + countdownSeconds + ChatColor.GRAY + " second" + (countdownSeconds == 1 ? "" : "s") + "!");
        }
        countdownSeconds--;
    }
}
