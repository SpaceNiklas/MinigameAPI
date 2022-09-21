package com.spaceniklas.minigameapi.commands;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.instance.Arena;
import com.spaceniklas.minigameapi.managers.ColorManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player player) {
            Arena arena = MinigameAPI.instance.getArenaManager().getArena(player);
            if (arena != null) {
                arena.removePlayer(player);
                arena.sendMessage(ColorManager.getMainColor() + player.getName() + " left the arena!");
                player.sendMessage(ColorManager.getMainColor() + "You left the arena!");
            } else {
                player.sendMessage(ChatColor.RED + "You are not in a game!");
            }
        }
        return false;
    }
}
