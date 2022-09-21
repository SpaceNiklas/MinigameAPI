package com.spaceniklas.minigameapi.commands;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.instance.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ArenaCreateTab implements TabCompleter {

    private MinigameAPI minigameAPI;

    public ArenaCreateTab(MinigameAPI minigameAPI){
        this.minigameAPI = minigameAPI;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        //	/ac <create> <name>
        // 	/ac <spawn> <id> <red|blue|green|yellow|main>
        //	/ac <holo> <id> <red|blue|green|yellow> <1|2|3|4>

        List<String> results = new ArrayList<>();

        if(args.length == 1 && sender.isOp()) {
            results.add("create");
            results.add("spawn");
            results.add("holo");
        }else if(args.length == 2 && !args[0].equalsIgnoreCase("create")&& sender.isOp()){
            for(Arena arena : minigameAPI.getArenaManager().getArenas()){
                results.add(String.valueOf(arena.getId()));
            }
        }else if(args.length == 3 && !args[0].equalsIgnoreCase("create") && sender.isOp()){
            results.add("blue");
            results.add("red");
            results.add("green");
            results.add("yellow");
            if(args[0].equalsIgnoreCase("spawn")){
                results.add("main");
            }
        }else if(args.length == 4 && args[0].equalsIgnoreCase("holo") && sender.isOp()){
            results.add("1");
            results.add("2");
            results.add("3");
            results.add("4");
        }
        return results;
    }
}
