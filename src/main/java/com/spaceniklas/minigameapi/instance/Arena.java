package com.spaceniklas.minigameapi.instance;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.GameState;
import com.spaceniklas.minigameapi.managers.ConfigManager;
import com.spaceniklas.minigameapi.managers.HologramManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private MinigameAPI minigameAPI;

    private int id;
    public Location spawn;

    private GameState state;
    private List<UUID> players;
    public Countdown countdown;

    private com.spacegames.cyborg.instance.Game game;

    public Arena(MinigameAPI minigameAPI, int id, Location spawn){
        this.minigameAPI = minigameAPI;
        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(minigameAPI, this);
        this.game = new Game
    }

    /* GAME */

    public void start(){game.start(); }

    public void reset(boolean kickPlayers){

        if(kickPlayers){
            Location loc = ConfigManager.getLobbySpawn();
            for(UUID uuid : players){
                Bukkit.getPlayer(uuid).teleport(loc);
            }
            players.clear();
        }
        sendTitle("", "");
        if(state == GameState.COUNTDOWN){
            countdown.cancel();
        }
        state = GameState.RECRUITING;
        countdown = new Countdown(minigameAPI, this);
        game = new Game(this);
        HologramManager.updateSoloArmorStands();

    }

    /* TOOLS */

    public void sendMessage(String message){
        for(UUID uuid : players){
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public void playSound(Sound sound){
        for(UUID uuid : players){
            Bukkit.getPlayer(uuid).playSound(Bukkit.getPlayer(uuid).getLocation(), sound, 1, 1);
        }
    }

    public void sendTitle(String title, String subtitle){
        for(UUID uuid : players){
            Bukkit.getPlayer(uuid).sendTitle(title, subtitle);
        }
    }

    /* PLAYERS */

    public void addPlayer(Player player){
        if(players.size() < ConfigManager.getRequiredPlayers()){
            players.add(player.getUniqueId());
            HologramManager.updateSoloArmorStands();
            player.teleport(spawn);
            if(players.size() == 1)
                sendMessage(ChatColor.BLUE + player.getName() + ChatColor.GRAY + " joined the arena!");
            if(players.size() == 2)
                sendMessage(ChatColor.RED + player.getName() + ChatColor.GRAY + " joined the arena!");
            if(players.size() == 3){
                countdown.countdownSeconds = 30;
                sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " joined the arena!");
            }
            if(players.size() == 4){
                sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " joined the arena!");
                countdown.countdownSeconds = 15;
            }
        }else {
            player.sendMessage(ChatColor.RED + "This game is already full! Please try again later.");
        }

        if(state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getRequiredPlayers()){
            countdown.start();
        }
    }

    public void removePlayer(Player player){
        players.remove(player.getUniqueId());
        HologramManager.updateSoloArmorStands();
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("", "");

        if(state == GameState.COUNTDOWN && players.size() < ConfigManager.getRequiredPlayers()){
            sendMessage(ChatColor.RED + "(!) Countdown has stopped as too many players have left!");
            reset(false);
            return;
        }

        if(state == GameState.LIVE && players.size() < ConfigManager.getRequiredPlayers()){
            sendMessage(ChatColor.RED + "(!) Game has ended as too many players have left!");
            sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "Victory", "");
            playSound(Sound.BLOCK_NOTE_BLOCK_PLING);
            Bukkit.getScheduler().runTaskLater(minigameAPI, () -> {
                reset(true);
            }, 60);
        }
    }

    /* INFO */

    public int getId() {return id;}

    public GameState getState() {return state;}

    public List<UUID> getPlayers() {return players;}

    public Game getGame(){return game; }

    public void setState(GameState state) {this.state = state; }
}
