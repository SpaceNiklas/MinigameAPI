package com.spaceniklas.minigameapi.commands;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.GameState;
import com.spaceniklas.minigameapi.managers.ColorManager;
import com.spaceniklas.minigameapi.menus.PlayMenu;
import com.spaceniklas.minigameapi.instance.Arena;
import com.spaceniklas.minigameapi.managers.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    private MinigameAPI minigameAPI;

    public ArenaCommand(MinigameAPI minigameAPI){
        this.minigameAPI = minigameAPI;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player player = (Player) sender;
            if(args.length == 1 && args[0].equalsIgnoreCase("list")){
                player.sendMessage(ColorManager.getMainColor() + "These are the available arenas:");
                for(Arena arena : minigameAPI.getArenaManager().getArenas()){
                    if(arena.getState() == GameState.RECRUITING){
                        player.sendMessage(ChatColor.GRAY + "- " + ConfigManager.getDisplayName(arena.getId()) + " (" + arena.getPlayers().size() + "/" + ConfigManager.getRequiredPlayers() + ")");
                    }
                }
            }else if(args.length == 1 && args[0].equalsIgnoreCase("leave")){
                Arena arena = minigameAPI.getArenaManager().getArena(player);
                if(arena != null){
                    arena.removePlayer(player);
                    arena.sendMessage(ColorManager.getMainColor() + player.getName() + " left the arena!");
                    player.sendMessage(ColorManager.getMainColor() + "You left the arena!");
                }else{
                    player.sendMessage(ChatColor.RED + "You are not in a game!");
                }

            }else if(args.length == 2 && args[0].equalsIgnoreCase("join")){

                if(minigameAPI.getArenaManager().getArena(player) != null){
                    player.sendMessage(ChatColor.RED + "You are already in a game, use" + ChatColor.BOLD + " /arena leave " + ChatColor.RESET + ChatColor.RED + "to leave your current game!");
                    return false;
                }

                int id = -1;
                for(Arena arena : minigameAPI.getArenaManager().getArenas()){
                    if(ConfigManager.getDisplayName(arena.getId()).equalsIgnoreCase(args[1])){
                        id = arena.getId();
                    }
                }

                if(id == -1){
                    player.sendMessage(ChatColor.RED + "You did not specify a valid arena!");
                    return false;
                }

                Arena arena = minigameAPI.getArenaManager().getArena(id);
                if(arena.getState() == GameState.RECRUITING){
                    arena.addPlayer(player);
                }else{
                    player.sendMessage(ChatColor.RED + "You cannot join this arena right now!");
                }

            }else if(args.length == 1 && args[0].equalsIgnoreCase("?openmenu?") && sender.isOp()){
                PlayMenu.openMenu(player);
            }else if(args.length == 1 && args[0].equalsIgnoreCase("start") && sender.isOp()){
                minigameAPI.getArenaManager().getArena(player).countdown.countdownSeconds = 5;
            }
            else{
                player.sendMessage(ChatColor.RED + "Invalid Usage! /arena <list|join|leave>");
            }

        }

        return false;
    }

}
