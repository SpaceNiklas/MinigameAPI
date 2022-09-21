package com.spaceniklas.minigameapi.instance;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.GameState;
import com.spaceniklas.minigameapi.managers.ColorManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> points;
    public List<Player> playerList = new ArrayList<>();
    public boolean won = false;
    private Player playerOne;
    private Player playerTwo;
    private Player playerThree;
    private Player playerFour;

    public Game(Arena arena){
        this.arena = arena;
        points = new HashMap<>();
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendMessage(ChatColor.DARK_GRAY + "_________________________________________");
        arena.sendMessage(ChatColor.DARK_GRAY + "");
        arena.sendMessage(ColorManager.getMainColor() + "" + ChatColor.BOLD + "               MinigameAPI:               ");
        arena.sendMessage(ChatColor.GRAY + "Your objective is to API yourself");
        arena.sendMessage(ChatColor.GRAY + " using the APIs APIs and");
        arena.sendMessage(ChatColor.GRAY + "      APIs your API!      ");
        arena.sendMessage(ChatColor.DARK_GRAY + "_________________________________________");
        arena.sendMessage(ChatColor.DARK_GRAY + "");

        for (UUID uuid : arena.getPlayers()) {
            points.put(uuid, 0);
        }
        if(arena.getPlayers().size() >= 1)
            playerOne = Bukkit.getPlayer(arena.getPlayers().get(0)); //BLUE
        if(arena.getPlayers().size() >= 2)
            playerTwo = Bukkit.getPlayer(arena.getPlayers().get(1)); //RED
        if(arena.getPlayers().size() >= 3)
            playerThree = Bukkit.getPlayer(arena.getPlayers().get(2)); //GREEN
        if(arena.getPlayers().size() >= 4)
            playerFour = Bukkit.getPlayer(arena.getPlayers().get(3)); //YELLOW

        playerList.add(playerOne);
        playerList.add(playerTwo);
        playerList.add(playerThree);
        playerList.add(playerFour);
    }



    public void addPoint(Player player){

        int playerpoints = points.get(player.getUniqueId()) + 1;
        if(playerpoints == 5 && !won){
            won = true;
            player.sendMessage(ChatColor.GRAY + "You APIed your opponent! Well done!" + ColorManager.getMainColor() + " (5" + " APIs)");
            String vivtoryMessage = player.getName() + " has won the game!";
            int count = vivtoryMessage.length();
            char mySymbol = '_';
            String filler = String.format("%" + count + "c", ' ').replaceAll("\\ ", "\\" + mySymbol);

            arena.sendMessage(ChatColor.DARK_GRAY + filler);
            arena.sendMessage(ChatColor.DARK_GRAY +"");
            if(player == playerOne){
                arena.sendMessage(ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has won the game!");
            }
            else if(player == playerTwo) {
                arena.sendMessage(ChatColor.RED + player.getName() + ChatColor.GRAY + " has won the game!");
            }
            arena.sendMessage(ChatColor.DARK_GRAY + filler);
            arena.sendMessage(ChatColor.DARK_GRAY +"");

            for(UUID uuid : arena.getPlayers()){
                Player p = Bukkit.getPlayer(uuid);

                if(!p.equals(player)){
                    p.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "Game Over", "");
                    p.playSound(p.getLocation(), Sound.ENTITY_CAT_PURR, 1, 1);
                }
            }
            player.sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "Victory", "");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            Bukkit.getScheduler().runTaskLater(MinigameAPI.instance, () -> {
                arena.reset(true);
            }, 60);
            return;
        }
        if(!won){
            player.sendMessage(ChatColor.GRAY + "You killed your opponent! Well done!" + ColorManager.getMainColor() + " (" + playerpoints + " Kills)");
            points.replace(player.getUniqueId(), playerpoints);
        }

    }

}
