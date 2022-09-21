package com.spaceniklas.minigameapi.commands;

import com.spaceniklas.minigameapi.MinigameAPI;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ArenaCreateCommand implements CommandExecutor {

    //	/ac <create> <name>
    // 	/ac <spawn> <id> <red|blue|green|yellow|main>
    //	/ac <holo> <id> <red|blue|green|yellow> <1|2|3|4>
    //  /ac <list>

    MinigameAPI minigameAPI;

    public ArenaCreateCommand(MinigameAPI minigameAPI){
        this.minigameAPI = minigameAPI;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player p && sender.isOp()){
            FileConfiguration config = minigameAPI.getConfig();
            ConfigurationSection arenasSection = config.getConfigurationSection("arenas");

            if(args.length == 2 && args[0].equalsIgnoreCase("create")){

                arenasSection.createSection(String.valueOf(minigameAPI.getArenaManager().getArenas().size()));
                arenasSection.getConfigurationSection(String.valueOf(minigameAPI.getArenaManager().getArenas().size())).set("displayname", args[1]);
                p.sendMessage(ChatColor.GREEN + "Successfully created arena " + args[1] + "!");
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            }else if(args.length == 3 && args[0].equalsIgnoreCase("spawn")){

                ConfigurationSection idSection = arenasSection.getConfigurationSection(args[1]);
                if(args[2].equalsIgnoreCase("main")){
                    idSection.set("world", p.getLocation().getWorld().getName());
                    idSection.set("x", p.getLocation().getX());
                    idSection.set("z", p.getLocation().getZ());
                    idSection.set("y", p.getLocation().getY());
                    idSection.set("yaw", p.getLocation().getYaw());
                    idSection.set("pitch", p.getLocation().getPitch());
                    p.sendMessage(ChatColor.GREEN + "Successfully set main spawn!");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                }

                if(args[2].equalsIgnoreCase("blue") || args[2].equalsIgnoreCase("red") || args[2].equalsIgnoreCase("yellow") || args[2].equalsIgnoreCase("green")){
                    idSection.set(args[2] + ".world", p.getLocation().getWorld().getName());
                    idSection.set(args[2] + ".x", p.getLocation().getX());
                    idSection.set(args[2] + ".z", p.getLocation().getZ());
                    idSection.set(args[2] + ".y", p.getLocation().getY());
                    idSection.set(args[2] + ".yaw", p.getLocation().getYaw());
                    idSection.set(args[2] + ".pitch", p.getLocation().getPitch());

                    p.sendMessage(ChatColor.GREEN + "Successfully set spawn!");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }

            }else if(args.length == 4 && args[0].equalsIgnoreCase("holo")){

                ConfigurationSection idSection = arenasSection.getConfigurationSection(args[1]);

                if(args[2].equalsIgnoreCase("blue") || args[2].equalsIgnoreCase("red") || args[2].equalsIgnoreCase("yellow") || args[2].equalsIgnoreCase("green")){
                    idSection.set(args[2] + "." + args[3] + "-world", p.getLocation().getWorld().getName());
                    idSection.set(args[2] + "." + args[3] + "-x", p.getLocation().getX());
                    idSection.set(args[2] + "." + args[3] + "-z", p.getLocation().getZ());
                    idSection.set(args[2] + "." + args[3] + "-y", p.getLocation().getY());
                    idSection.set(args[2] + "." + args[3] + "-yaw", p.getLocation().getYaw());
                    idSection.set(args[2] + "." + args[3] + "-pitch", p.getLocation().getPitch());
                    p.sendMessage(ChatColor.GREEN + "Successfully set hologram!");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);

                }

            }

            minigameAPI.saveConfig();

        }else{sender.sendMessage(ChatColor.RED + "You do not have the permission to execute this command!");}

        return false;
    }
}
