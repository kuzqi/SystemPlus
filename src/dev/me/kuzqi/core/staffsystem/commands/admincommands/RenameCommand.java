package dev.me.kuzqi.core.staffsystem.commands.admincommands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.me.kuzqi.core.staffsystem.Main;

public class RenameCommand implements CommandExecutor {

	private final Main m;
	public RenameCommand(Main m) {
		this.m = m;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
	    if (command.equalsIgnoreCase("rename")) {
	        if (cs.hasPermission("staff.rename")) {
	            if (cs instanceof Player) {
	                Player player = (Player) cs;
	                if (args.length >= 1) {
	                    String name = "";
	                    for (String arg : args) {
	                        name += arg + " ";
	                    }
	                    name = name.trim();
	                    ItemStack item = player.getInventory().getItemInHand();
	                    if (item.getType() != null && item.getType() != Material.AIR) {
	                        ItemMeta meta = item.getItemMeta();
	                        meta.setDisplayName(m.c(name));
	                        item.setItemMeta(meta);
	                        ((Player) cs).updateInventory();
	                    } else {
	                        player.sendMessage("§cYou are not holding an item!");
	                    }
	                } else {
	                    player.sendMessage("§c/rename <name>");
	                }
	            }
	            return true;
	        } else {
	            cs.sendMessage(m.c(m.getConfig().getString("messages.noperm")));
	        }
	    }
	    return false;
	}
}