package com.spaceniklas.minigameapi.listeners;

import com.spaceniklas.minigameapi.MinigameAPI;
import com.spaceniklas.minigameapi.GameState;
import com.spaceniklas.minigameapi.instance.Arena;
import com.spaceniklas.minigameapi.managers.ColorManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class GameListener implements Listener {

    private MinigameAPI minigameAPI;

    public GameListener(MinigameAPI minigameAPI){
        this.minigameAPI = minigameAPI;
    }

    @EventHandler
    public void onPlayerHitEvent(PlayerDeathEvent e) {
        if (e.getEntity().getType().equals(EntityType.PLAYER) && e.getEntity().getKiller().getType().equals(EntityType.PLAYER)) {
            Player p = (Player) e.getEntity().getKiller();
            Player v = (Player) e.getEntity();
            Arena arena = minigameAPI.getArenaManager().getArena(p);
            if (arena != null && arena.getState() == GameState.LIVE) {
                if (e.getEntityType().equals(EntityType.PLAYER)) {
                    arena.getGame().addPoint(p);
                    arena.sendMessage(ColorManager.getChatColor(v) + v.getName() + ChatColor.GRAY + " was killed by " + ColorManager.getChatColor(p) + p.getName());
                    e.setDeathMessage("");
                }
            }

        } else {
            return;
        }
    }
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        Arena arena = minigameAPI.getArenaManager().getArena(p);
        if (arena != null && arena.getState() == GameState.LIVE) {
            p.teleport(arena.spawn);
        }
    }
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        Arena arena = minigameAPI.getArenaManager().getArena((Player) e.getDamager());
        if (arena != null && arena.getGame().won) {
            e.setCancelled(true);
        }
        if (arena != null && arena.getState() == GameState.COUNTDOWN) {
            e.setCancelled(true);
        }
        if (arena != null && arena.getState() == GameState.LIVE && e.getEntityType() != EntityType.PLAYER) {
            e.setCancelled(true);
        }
        if (arena == null) {
            if (!e.getDamager().isOp()) {
                e.setCancelled(true);
            }
        }
    }
}


