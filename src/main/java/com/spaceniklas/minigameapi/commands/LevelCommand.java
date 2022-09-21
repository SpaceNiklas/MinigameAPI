package com.spaceniklas.minigameapi.commands;

import com.spaceniklas.minigameapi.managers.LevelManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LevelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // /level xp/level set <amount> <player>
        // /level xp/level add <amount> <player>
        // /level xp/level down <amount> <player>
        // /level xp/level get <player>

        if(sender instanceof Player player && sender.isOp()) {

            //SET LEVEL
            if(args.length == 4 && args[1].equalsIgnoreCase("set")){
                if(args[0].equalsIgnoreCase("xp")){
                    try {
                        LevelManager.setXp(Bukkit.getPlayer(args[3]), Integer.parseInt(args[2]));
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "This is not a valid number/player!");
                    }
                    player.sendMessage(ChatColor.GREEN + "Successfully set " + Bukkit.getPlayer(args[3]).getName() + "'s xp to " + args[2] + "!");
                }
                if(args[0].equalsIgnoreCase("level")){
                    try {
                        LevelManager.setLevel(Bukkit.getPlayer(args[3]), Integer.parseInt(args[2]));
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "This is not a valid number/player!");
                    }
                    player.sendMessage(ChatColor.GREEN + "Successfully set " + Bukkit.getPlayer(args[3]).getName() + "'s level to " + args[2] + "!");
                }
            }

            //ADD LEVEL
            if(args.length == 4 && args[1].equalsIgnoreCase("add")){
                if(args[0].equalsIgnoreCase("xp")){
                    try {
                        LevelManager.addXp(Bukkit.getPlayer(args[3]), Integer.parseInt(args[2]));
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "This is not a valid number/player!");
                    }
                    player.sendMessage(ChatColor.GREEN + "Successfully added " + args[2] + " xp to " + Bukkit.getPlayer(args[3]).getName() + "!");
                }
                if(args[0].equalsIgnoreCase("level")){
                    try {
                        LevelManager.addLevel(Bukkit.getPlayer(args[3]), Integer.parseInt(args[2]));
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "This is not a valid number/player!");
                    }
                    player.sendMessage(ChatColor.GREEN + "Successfully added " + args[2] + " levels to " + Bukkit.getPlayer(args[3]).getName() + "!");
                }
            }

            //LEVEL DOWN
            if(args.length == 4 && args[1].equalsIgnoreCase("down")){
                if(args[0].equalsIgnoreCase("xp")){
                    try {
                        LevelManager.setXp(Bukkit.getPlayer(args[3]), LevelManager.getXp(player) - Integer.parseInt(args[2]));
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "This is not a valid number/player!");
                    }
                    player.sendMessage(ChatColor.GREEN + "Successfully removed " + args[2] + " xp from " + Bukkit.getPlayer(args[3]).getName() + "!");
                }
                if(args[0].equalsIgnoreCase("level")){
                    try {
                        LevelManager.setLevel(Bukkit.getPlayer(args[3]), LevelManager.getLevel(player) - Integer.parseInt(args[2]));
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "This is not a valid number/player!");
                    }
                    player.sendMessage(ChatColor.GREEN + "Successfully removed " + args[2] + " levels from " + Bukkit.getPlayer(args[3]).getName() + "!");
                }
            }

            //LEVEL GET
            if(args.length == 3 && args[1].equalsIgnoreCase("get")){
                if(args[0].equalsIgnoreCase("xp")){
                    player.sendMessage(ChatColor.GREEN + Bukkit.getPlayer(args[2]).getName() + "'s xp is " + LevelManager.getXp(Bukkit.getPlayer(args[2])));
                }
                if(args[0].equalsIgnoreCase("level")){
                    player.sendMessage(ChatColor.GREEN + Bukkit.getPlayer(args[2]).getName() + "'s level is " + LevelManager.getLevel(Bukkit.getPlayer(args[2])));                }
            }

        }

        return false;
    }
}
