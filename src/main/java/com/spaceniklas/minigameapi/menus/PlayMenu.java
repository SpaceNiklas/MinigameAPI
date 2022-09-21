package com.spaceniklas.minigameapi.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PlayMenu {

    public static void openMenu(Player p){
        Inventory mlginv = Bukkit.createInventory(p, 45, ChatColor.DARK_GRAY.toString() + "Play MinigameAPI");


        //Knock Off
        ItemStack slime = new ItemStack(Material.ARMOR_STAND);
        ItemMeta slimemeta = slime.getItemMeta();
        slimemeta.setDisplayName(ChatColor.AQUA + "MinigameAPI");
        slimemeta.setLore(Arrays.asList(ChatColor.GRAY + "Upgrade yourself and fight to the death!"));
        slime.setItemMeta(slimemeta);

        mlginv.setItem(4, slime);


        //CLOSE

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(closemeta);

        mlginv.setItem(0, close);

        //SWORD

        ItemStack sword = new ItemStack(Material.ARMOR_STAND);
        ItemMeta swordItemMeta = sword.getItemMeta();
        swordItemMeta.setDisplayName(ChatColor.AQUA + "Solo");
        swordItemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Click to play!"));
        swordItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        sword.setItemMeta(swordItemMeta);

        mlginv.setItem(21, sword);

        //MAP

        ItemStack map = new ItemStack(Material.MAP);
        ItemMeta mapmeta = map.getItemMeta();
        mapmeta.setDisplayName(ChatColor.AQUA + "Map Selector " + ChatColor.DARK_GRAY + "(Solo)");
        mapmeta.setLore(Arrays.asList(ChatColor.GRAY + "Click to browse!"));
        map.setItemMeta(mapmeta);

        mlginv.setItem(23, map);

        //FRAME

        ItemStack frame = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

        for(int i : new int[]{1,2,3,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44}){
            mlginv.setItem(i , frame);
        }

        p.openInventory(mlginv);
    }

}
