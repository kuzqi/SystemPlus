package dev.me.kuzqi.core.staffsystem.managers;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;

/**
 * @author kuzqi
 */
public class PlayerManager implements Listener {

	private final Main m;    
	public PlayerManager(Main plugin) {
		this.m = plugin;
	}

	public void GmcCommandHandler(CommandSender cs, String[] args) {
	    setGameMode(cs, args, GameMode.CREATIVE);
	}

	
	public void GmsCommandHandler(CommandSender cs, String[] args) {
	    setGameMode(cs, args, GameMode.SURVIVAL);
	}
	
	public void setGameMode(CommandSender cs, String[] args, GameMode gameMode) {
	    if (!cs.hasPermission("staff.gamemode")) {
	        cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
	        return;
	    }

	    if (!(cs instanceof Player)) {
	        cs.sendMessage(Messages.ONLY_PLAYER_CMD.from(m.getMessagesConfig()));
	        return;
	    }

	    Player p = (Player) cs;

	    if (args.length > 1) {
	        cs.sendMessage("§cUsage: /gmc §a<player> or /gms §a<player>");
	        return;
	    }

	    if (args.length == 0) {
	        p.setGameMode(gameMode);
	        cs.sendMessage("§aYour game mode has been set to " + gameMode.toString().toLowerCase() + ".");
	        return;
	    }


	    Player Target = m.getServer().getPlayer(args[0]);
	    if (Target == null) {
	        cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
	        return;
	    }

	    Target.setGameMode(gameMode);
	}


	public void handleHealCommand(CommandSender cs, String[] args) {
		if (!cs.hasPermission("staff.heal")) {
			cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
			return;
		}
	        

		if (args.length > 1) {
            cs.sendMessage("§cUsage: /heal §a<player>");
            return;
		}
		
		if (args.length == 0) {
			
			if (!(cs instanceof Player)) {
	            cs.sendMessage(Messages.ONLY_PLAYER_CMD.from(m.getMessagesConfig()));
	            return;
	        }
	        
			Player p = (Player) cs;
			p.setHealth(20);
			p.setFoodLevel(20);
			p.setFireTicks(0);
		}
		
		if (args.length == 1) {
			
			Player target = m.getServer().getPlayer(args[0]);
			if (target == null) {
				cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
				return;
			}
			
			target.setHealth(20);
			target.setFoodLevel(20);
			target.setFireTicks(0);
		}
	}
	
	public void handleInvseeCommand(CommandSender cs, String[] args) {
        if (!(cs.hasPermission("staff.invsee"))) {
            cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
            return;
        }
        
        if (!(cs instanceof Player)) {
            cs.sendMessage(Messages.ONLY_PLAYER_CMD.from(m.getMessagesConfig()));
            return;
        }
             
        if (args.length != 1) {
        	cs.sendMessage("§cUsage: §a/invsee <player>");
        	return;
        }
        
        Player target = m.getServer().getPlayer(args[0]);
        if (target == null) {
            cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
            return;
        }
        
        if (cs.getName().equalsIgnoreCase(target.getName())) {
            cs.sendMessage(Messages.NOT_ALLOWED_FORSELF.from(m.getMessagesConfig()));
            return;
        }
        
		Player p = (Player) cs;
		p.openInventory(target.getInventory());
		
	}
	
	public void handleRenameCommand(CommandSender cs, String[] args) {
        if (!(cs.hasPermission("staff.rename"))) {
            cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
            return;
        }
        
        if (!(cs instanceof Player)) {
            cs.sendMessage(Messages.ONLY_PLAYER_CMD.from(m.getMessagesConfig()));
            return;
        }
             
        if (args.length < 1) {
        	cs.sendMessage("§cUsage: §a/rename <text>");
        	return;
        }
        
        Player p = (Player) cs;
        
        String name = "";
        for (String arg : args) {
        	name += arg + " ";
        }
                    
        name = name.trim();
        ItemStack i = p.getInventory().getItemInHand();
        
        if (i.getType() != null && i.getType() != Material.AIR) {
        	
        	ItemMeta meta = i.getItemMeta();
        	meta.setDisplayName(m.getTextUtils().format(name));
        	i.setItemMeta(meta);
        	p.updateInventory();
        	
        } else {
            p.sendMessage(Messages.NON_ITEM.from(m.getMessagesConfig()));
            return;
        }
	}
	
	   public void toggleFlight(String[] args, CommandSender cs) {
	        if (!(cs instanceof Player)) {
	            cs.sendMessage(Messages.ONLY_PLAYER_CMD.from(m.getMessagesConfig()));
	            return;
	        }

	        Player p = (Player) cs;

	        if (!p.hasPermission("staff.fly")) {
	            p.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
	            return;
	        }

	        if (args.length != 0) {
	        	p.sendMessage("§cUsage: /fly");
	        	return;
	        }

	        if (p.getGameMode() == GameMode.CREATIVE) {
	            p.sendMessage(Messages.FLY_CREATIVE_MOD.from(m.getMessagesConfig()));
	            return;
	        }

	        if (m.flight.contains(p.getName())) {
	            m.flight.remove(p.getName());
	            p.setAllowFlight(false);
	            p.sendMessage(Messages.FLY_DISABLED.from(m.getMessagesConfig()));
	            
	        } else{
	            m.flight.add(p.getName());
	            p.setAllowFlight(true);
	            p.sendMessage(Messages.FLY_ENABLED.from(m.getMessagesConfig()));
	        }
	    }
	   
		public void handleAtpCommand(CommandSender cs, String[] args) {
			if (cs.hasPermission("staff.teleport")) {
				
				if (args.length == 1) {
					if (args[0] != null) {
						Player target = m.getServer().getPlayer(args[0]);
						Player player = (Player) cs;
						player.teleport(target.getLocation());
					}else {
						cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
					}
					
				}else {
					if (args.length == 2) {
						Player target = m.getServer().getPlayer(args[0]);
						if (target != null) {
						Player player = (Player) cs;
						target.teleport(player.getLocation());
						
					}else {
						cs.sendMessage(Messages.UNKNOWN_PLAYER.from(m.getMessagesConfig()));
					}
					
					}else {
						cs.sendMessage("§cUsage: /atp §a<player1> <player2>");
						cs.sendMessage("§cUsage: /atp §a<player>");
					}
				}	
		}else {
			cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
		}
		}
	   
}
