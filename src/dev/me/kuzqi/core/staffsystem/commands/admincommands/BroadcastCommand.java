package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.me.kuzqi.core.staffsystem.Main;

public class BroadcastCommand implements CommandExecutor {

    private final Main plugin;

    public BroadcastCommand(Main m) {
        this.plugin = m;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {

        if (command.equalsIgnoreCase("broadcast") || command.equalsIgnoreCase("bc")) {
            if (cs.hasPermission("staff.broadcast")) {
                if(args.length > 0) {
                    StringBuilder msg = new StringBuilder();
                    for (String arg : args) {
                        msg.append(arg).append(" ");
                    }

                    String broadcastFormat = plugin.getMessage("broadcast-format");
                    if (broadcastFormat == null) {
                        cs.sendMessage("§cBroadcast format not found.");
                        return true;
                    }

                    String finalMessage = broadcastFormat.replace("$message", plugin.getTextUtils().format(msg.toString()));
                    plugin.getServer().broadcastMessage(finalMessage);
                    
                } else {
                    cs.sendMessage("§c/bc <message>");
                }
            } else {
                cs.sendMessage(plugin.c(plugin.getConfig().getString("messages.noperm")));
            }
            return true; 
        }

        return false;
    }
}