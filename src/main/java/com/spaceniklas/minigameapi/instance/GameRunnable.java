package com.spaceniklas.minigameapi.instance;

import com.spaceniklas.minigameapi.MinigameAPI;
import org.bukkit.scheduler.BukkitRunnable;

public class GameRunnable extends BukkitRunnable {

    private MinigameAPI minigameAPI;

    public GameRunnable(MinigameAPI minigameAPI){
        this.minigameAPI = minigameAPI;
    }

    public void start(){
        runTaskTimer(minigameAPI, 0, 1);
    }

    @Override
    public void run() {

    }
}
