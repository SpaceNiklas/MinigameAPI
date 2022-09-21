package com.spaceniklas.minigameapi.commands;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.instance.Arena;
import com.spaceniklas.minigameapi.managers.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ArenaTab implements TabCompleter {

    private MinigameAPI minigameAPI;

    public ArenaTab(MinigameAPI minigameAPI){
        this.minigameAPI = minigameAPI;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> results = new ArrayList<>();

        if(args.length == 1) {
            results.add("join");
            results.add("list");
            results.add("leave");
            if(sender.isOp()){
                results.add("start");
            }
        }else if(args.length == 2){
            for(Arena arena : minigameAPI.getArenaManager().getArenas()){
                results.add(ConfigManager.getDisplayName(arena.getId()));
            }
        }
        return results;
    }
}
