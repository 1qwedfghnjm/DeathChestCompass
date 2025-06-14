package com.aternos.deathchest.listeners;

import com.aternos.deathchest.DeathChestCompass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class DeathListener implements Listener {

    private final DeathChestCompass plugin;

    public DeathListener(DeathChestCompass plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        List<ItemStack> items = new ArrayList<>(event.getDrops());
        event.getDrops().clear();
        
        plugin.getChestManager().createDeathChest(player, items);
    }
}
