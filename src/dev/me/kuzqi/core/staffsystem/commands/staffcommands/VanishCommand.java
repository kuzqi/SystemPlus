package dev.me.kuzqi.core.staffsystem.commands.staffcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.me.kuzqi.core.staffsystem.Main;
import dev.me.kuzqi.core.staffsystem.utils.config.Messages;

/**
 * @author kuzqi
 */
public class VanishCommand implements CommandExecutor {

	private final Main m;
	public VanishCommand(Main m) {
		this.m = m;
	}
  
  @Override
  public boolean onCommand(CommandSender cs, Command cmd, String command, String[] args) {
	  if (!(cs instanceof Player)) {
		  cs.sendMessage(Messages.ONLY_PLAYER_CMD.from(m.getMessagesConfig()));
		  return true;
	  } 
    
	  if (!cs.hasPermission("staff.vanish")) {
		  cs.sendMessage(Messages.NO_PERMISSION.from(m.getMessagesConfig()));
		  return true;
	  } 
	  
	  Player p = (Player)cs;
	  
	  switch (command.toLowerCase()) {
	  
	  case "v":
		  m.getVanishManager().handleVanish(p);
		  break;
		  
	  case "vtp":
		  m.getVanishManager().handleTeleportToVanish(p, args);
		  break; 
		  
	  case "vlist":
		  m.getVanishManager().handleVList(p);
		  break;
		  
	  }
	  return false;
  }
}
