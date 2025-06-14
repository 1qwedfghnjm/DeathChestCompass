package com.aternos.deathchestcompass;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathChestCommand implements CommandExecutor {

    private final DeathChestCompass plugin;

    public DeathChestCommand(DeathChestCompass plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;
        
        if (args.length > 0 && args[0].equalsIgnoreCase("reload") && player.hasPermission("deathchest.reload")) {
            plugin.reloadConfig();
            player.sendMessage(ChatColor.GREEN + "DeathChestCompass config reloaded!");
            return true;
        }
        
        // Handle other commands
        return true;
    }
}
