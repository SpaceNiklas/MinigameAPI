package com.spaceniklas.minigameapi.menus;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.GameState;
import com.spaceniklas.minigameapi.instance.Arena;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayMenuListener implements Listener {

    @EventHandler
    public void onSettingInvCLick(InventoryClickEvent e){
        if(ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.DARK_GRAY.toString() + "Play MinigameAPI") && e.getCurrentItem() != null){

            Player p = (Player) e.getWhoClicked();
            if(e.getRawSlot() < 46){
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1 ,1);
            }

            if(e.getRawSlot() == 0){
                p.closeInventory();
            } else if(e.getRawSlot() == 21){
                int lowest = -1;
                int id = -1;

                for(Arena arena : MinigameAPI.instance.getArenaManager().getArenas()){
                    if(lowest == -1 && id == -1 && arena.getState() == GameState.RECRUITING){
                        lowest = arena.getPlayers().size();
                        id = arena.getId();
                    }else if(arena.getPlayers().size() > lowest && arena.getState() == GameState.RECRUITING){
                        lowest = arena.getPlayers().size();
                        id = arena.getId();
                    }
                }
                if(id == -1 && lowest == -1){
                    p.sendMessage(ChatColor.RED + "There are currently no available arenas! Please try again later!");
                    return;
                }
                if(MinigameAPI.instance.getArenaManager().getArena(id).getState() == GameState.RECRUITING && MinigameAPI.instance.getArenaManager().getArena(p) == null){
                    MinigameAPI.instance.getArenaManager().getArena(id).addPlayer(p);
                }
            }else if(e.getRawSlot() == 23){
                MapsMenu.openMenu(p);
            }

        }

    }

    @EventHandler
    public void onMapInvClick(InventoryClickEvent e){
        List<Integer> slots = Arrays.asList(10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34);
        if(ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.DARK_GRAY.toString() + "Select Map") && e.getCurrentItem() != null){

            Player p = (Player) e.getWhoClicked();
            if(e.getRawSlot() < 46){
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1 ,1);
            }

            if(e.getRawSlot() == 0){
                PlayMenu.openMenu(p);

            } else if(slots.contains(e.getRawSlot())){

                if(MinigameAPI.instance.getArenaManager().getArena(slots.indexOf(e.getRawSlot())).getState() == GameState.RECRUITING && MinigameAPI.instance.getArenaManager().getArena(p) == null){
                    MinigameAPI.instance.getArenaManager().getArena(slots.indexOf(e.getRawSlot())).addPlayer(p);
                }else {
                    p.sendMessage(ChatColor.RED + "You can't join this game right now! Please try again later!");
                }
            }else if(e.getRawSlot() == 40){
                List<Arena> arenaList = new ArrayList<>();
                for(Arena arena : MinigameAPI.instance.getArenaManager().getArenas()){
                    if(arena.getState() == GameState.RECRUITING){
                        arenaList.add(arena);
                    }
                }

                if(arenaList.size() != 0){
                    int max = arenaList.size() - 1;
                    int min = 0;
                    int diff = max - min;
                    Random rn = new Random();
                    int i = rn.nextInt(diff + 1);
                    i += min;
                    arenaList.get(i).addPlayer(p);
                }else{
                    p.sendMessage(ChatColor.RED + "There is currently no arena available! Please try again later!");
                }

            }

        }

    }
}
