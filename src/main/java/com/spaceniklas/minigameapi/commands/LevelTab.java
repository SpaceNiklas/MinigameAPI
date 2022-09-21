package com.spaceniklas.minigameapi.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LevelTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> results = new ArrayList<>();

        if(args.length == 1) {
            results.add("xp");
            results.add("level");
        }else if(args.length == 2){
            results.add("set");
            results.add("add");
            results.add("down");
            results.add("get");
        }else if(args.length == 3 && !args[1].equalsIgnoreCase("get")){
            results.add("<amount>");
        }else if(args.length == 3 && args[1].equalsIgnoreCase("get")){
            for(Player p : Bukkit.getOnlinePlayers()){
                results.add(p.getName());
            }
            results.add("<player>");
        }else if(args.length == 4 && !args[1].equalsIgnoreCase("get")){
            for(Player p : Bukkit.getOnlinePlayers()){
                results.add(p.getName());
            }
            results.add("<player>");
        }
        return results;
    }
}
