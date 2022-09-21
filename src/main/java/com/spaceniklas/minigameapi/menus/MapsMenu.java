package com.spaceniklas.minigameapi.menus;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.instance.Arena;
import com.spaceniklas.minigameapi.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class MapsMenu {

    public static ItemStack paper;
    public static ItemMeta papermeta;

    public static void openMenu(Player p){
        Inventory mlginv = Bukkit.createInventory(p, 45, ChatColor.DARK_GRAY.toString() + "Select Map");

        //PAPER
        paper = new ItemStack(Material.PAPER);
        papermeta = paper.getItemMeta();

        List<Integer> slots = Arrays.asList(10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34);
        int run = -1;
        for(Arena arena : MinigameAPI.instance.getArenaManager().getArenas()){
            papermeta.setDisplayName(ChatColor.AQUA + ConfigManager.getDisplayName(arena.getId()));
            papermeta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Mode: Solo" , ChatColor.GRAY + "Click to play!"));
            paper.setItemMeta(papermeta);
            run++;
            mlginv.setItem(slots.get(run), paper);
        }

        //MAP

        ItemStack map = new ItemStack(Material.MAP);
        ItemMeta mapmeta = map.getItemMeta();
        mapmeta.setDisplayName(ChatColor.AQUA + "Map Selector " + ChatColor.DARK_GRAY + "(Solo)");
        mapmeta.setLore(Arrays.asList(ChatColor.GRAY + "Select your favourite map!"));
        map.setItemMeta(mapmeta);

        mlginv.setItem(4, map);

        ItemStack firework = new ItemStack(Material.FIREWORK_ROCKET);
        ItemMeta fireworkmeta = firework.getItemMeta();
        fireworkmeta.setDisplayName(ChatColor.WHITE + "Random Map");
        fireworkmeta.setLore(Arrays.asList(ChatColor.GRAY + "Click to play!"));
        firework.setItemMeta(fireworkmeta);

        mlginv.setItem(40, firework);


        //CLOSE

        ItemStack close = new ItemStack(Material.ARROW);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.GRAY + "Back");
        close.setItemMeta(closemeta);

        mlginv.setItem(0, close);


        //FRAME

        ItemStack frame = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        for(int i : new int[]{1,2,3,5,6,7,8,9,17,18,26,27,35,36,37,38,39,41,42,43,44}){
            mlginv.setItem(i , frame);
        }

        p.openInventory(mlginv);
    }

}
