package me.junglesociety.everyoneutilities;

import java.util.ArrayList;
import java.util.UUID;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	Methods methods = new Methods();
	public static ArrayList<UUID> frozen = new ArrayList<UUID>();
    public static Permission permission = null;
    public static Economy economy = null;
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		//Setup Vault stuff
		setupPermissions();
        if (!setupEconomy() ) {
            Bukkit.getServer().getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
	}
	@Override
	public void onDisable() {
		
	}
	//Setup permissions
    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
    //setup economy
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
    
    
    /*
     * COMMANDS HERE
     * COMMANDS HERE
     */
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("You must be a player to do this!");
		}else{
		//Variables
		Player player = (Player) sender;
		
		//Main command
		if(label.equalsIgnoreCase("everyoneutilities") || label.equalsIgnoreCase("eu")){
			
			//If there is no arguments
			if(args.length == 0){
				player.sendMessage(ChatColor.RED + "Please use /EveryoneUtilities help for help!");
			
			}else{
				
				//If the commands aren't available
				if(!(args[0].equalsIgnoreCase("giveitems") || args[0].equalsIgnoreCase("giveexp") || args[0].equalsIgnoreCase("givexp") || args[0].equalsIgnoreCase("heal") || args[0].equalsIgnoreCase("givemoney") || args[0].equalsIgnoreCase("pay") || args[0].equalsIgnoreCase("teleport") || args[0].equalsIgnoreCase("tp") || args[0].equalsIgnoreCase("tphere") || args[0].equalsIgnoreCase("teleporthere") || args[0].equalsIgnoreCase("kill") || args[0].equalsIgnoreCase("killall") || args[0].equalsIgnoreCase("command") || args[0].equalsIgnoreCase("cmd") || args[0].equalsIgnoreCase("runcommand") || args[0].equalsIgnoreCase("runcmd") || args[0].equalsIgnoreCase("freeze") || args[0].equalsIgnoreCase("freezeall") || args[0].equalsIgnoreCase("unfreeze") || args[0].equalsIgnoreCase("unfreezeall") || args[0].equalsIgnoreCase("takemoney") || args[0].equalsIgnoreCase("removemoney") || args[0].equalsIgnoreCase("takexp") || args[0].equalsIgnoreCase("removexp") || args[0].equalsIgnoreCase("removeexp") || args[0].equalsIgnoreCase("takeexp") || args[0].equalsIgnoreCase("takeitems") || args[0].equalsIgnoreCase("removeitems") || args[0].equalsIgnoreCase("takeitem") || args[0].equalsIgnoreCase("removeitem") || args[0].equalsIgnoreCase("takehealth") || args[0].equalsIgnoreCase("removehealth") || args[0].equalsIgnoreCase("sethealth") || args[0].equalsIgnoreCase("sethearts") || args[0].equalsIgnoreCase("setxp") || args[0].equalsIgnoreCase("setexp") || args[0].equalsIgnoreCase("setmoney") || args[0].equalsIgnoreCase("setcash") || args[0].equalsIgnoreCase("setbalance") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase(""))){
					player.sendMessage(ChatColor.RED + "Please use /EveryoneUtilities help for help!");
				}
				
				//help command
				if(args[0].equalsIgnoreCase("help")){
					if(player.hasPermission("eu.help")){
						if(player.hasPermission("eu.help")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities help: " + ChatColor.WHITE + "Displays help for Everyone Utilities.");
						}
						if(player.hasPermission("eu.setbalance")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities setbalance: " + ChatColor.WHITE + "Set the balance of everyone online.");
						}
						if(player.hasPermission("eu.setxp")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities setxp: " + ChatColor.WHITE + "Set the xp level of everyone online.");
						}
						if(player.hasPermission("eu.sethealth")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities sethealth: " + ChatColor.WHITE + "Set the health of everyone online.");
						}
						if(player.hasPermission("eu.takehealth")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities takehealth: " + ChatColor.WHITE + "Take health from everyone online.");
						}
						if(player.hasPermission("eu.takeitems")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities takeitems: " + ChatColor.WHITE + "Take items from everyone online.");
						}
						if(player.hasPermission("eu.takexp")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities takexp: " + ChatColor.WHITE + "Take xp form everyone online.");
						}
						if(player.hasPermission("eu.freeze")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities freeze: " + ChatColor.WHITE + "Freeze everyone online.");
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities unfreeze: " + ChatColor.WHITE + "UnFreeze everyone online.");
						}
						if(player.hasPermission("eu.runcommand")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities command: " + ChatColor.WHITE + "Force everyone online to run a command.");
						}
						if(player.hasPermission("eu.kill")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities kill: " + ChatColor.WHITE + "Kill everyone online.");
						}
						if(player.hasPermission("eu.teleport")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities teleport: " + ChatColor.WHITE + "Teleport everyone online to you.");
						}
						if(player.hasPermission("eu.givemoney")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities givemoney: " + ChatColor.WHITE + "Give money to everyone online.");
						}
						if(player.hasPermission("eu.givexp")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities givexp: " + ChatColor.WHITE + "Give xp to everyone online.");
						}
						if(player.hasPermission("eu.heal")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities heal: " + ChatColor.WHITE + "Heal everyone online.");
						}
						if(player.hasPermission("eu.giveitems")){
							player.sendMessage(ChatColor.YELLOW + "/EveryoneUtilities giveitems: " + ChatColor.WHITE + "Give items to everyone online.");
						}
						player.sendMessage(ChatColor.GREEN + "/eu: " + ChatColor.AQUA + "Replacement for /EveryoneUtilities.");
					}else{
						player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}
				}
				
				//set Health
				if(args[0].equalsIgnoreCase("setmoney") || args[0].equalsIgnoreCase("setcash") || args[0].equalsIgnoreCase("setbalance")){
					if(player.hasPermission("eu.setbalance")){
					if(args.length >= 2){
						double args1 = Double.parseDouble(args[1]);
						for(Player p : Bukkit.getServer().getOnlinePlayers()){
							double balance = economy.getBalance(p.getName());
								EconomyResponse r = economy.withdrawPlayer(p.getName(), balance);
								EconomyResponse re = economy.depositPlayer(p.getName(), args1);
					            if(r.transactionSuccess() && re.transactionSuccess()) {
					                p.sendMessage(String.format(ChatColor.BLUE + "Your balance was set to %s", economy.format(re.amount)));
					            } else {
					                player.sendMessage(String.format("An error occured: %s", r.errorMessage, re.errorMessage));
					            }

							
						}	
					}else{
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities setmoney (money)");
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//set XP
				if(args[0].equalsIgnoreCase("setxp") || args[0].equalsIgnoreCase("setexp")){
					if(player.hasPermission("eu.setxp")){
					if(args.length >= 2){
						int args1 = Integer.parseInt(args[1]);
						methods.setXP(args1);
					}else{
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities setxp (amount of XP)");
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//set Health
				if(args[0].equalsIgnoreCase("sethealth") || args[0].equalsIgnoreCase("sethearts")){
					if(player.hasPermission("eu.sethealth")){
					if(args.length >= 2){
						int args1 = Integer.parseInt(args[1]);
						methods.setHealth(args1);
					}else{
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities sethealth (amount of health)");
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//take health
				if(args[0].equalsIgnoreCase("takehealth") || args[0].equalsIgnoreCase("removehealth")){
					if(player.hasPermission("eu.takehealth")){
					if(args.length >= 2){
					int args1 = Integer.parseInt(args[1]);
					methods.takeHealth(args1);
					player.sendMessage(ChatColor.YELLOW + "Health removed!");
					}else{
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities takehealth (amount of health)");
					}
					}else{
						player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}
				}
				
				//take items 
				if(args[0].equalsIgnoreCase("takeitems") || args[0].equalsIgnoreCase("removeitems") || args[0].equalsIgnoreCase("removeitem") || args[0].equalsIgnoreCase("takeitem")){
					if(player.hasPermission("eu.takeitems")){
					if(args.length == 2 || args.length == 1){
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities takeitems (item id) (item amount)");
					}else{
						int args1 = Integer.parseInt(args[1]);
						int args2 = Integer.parseInt(args[2]);
						methods.takeItems(args1, args2);
						player.sendMessage(ChatColor.YELLOW + "Items removed!");
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//Take XP
				if(args[0].equalsIgnoreCase("takexp") || args[0].equalsIgnoreCase("removexp") || args[0].equalsIgnoreCase("removeexp") || args[0].equalsIgnoreCase("takeexp")){
					if(player.hasPermission("eu.takexp")){
					if(args.length >= 2){
					int args1 = Integer.parseInt(args[1]);
					methods.takeXP(args1);
					player.sendMessage(ChatColor.YELLOW + "XP removed!");
					}else{
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities takexp (amount)");
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//take money command
				if(args[0].equalsIgnoreCase("takemoney") || args[0].equalsIgnoreCase("removemoney")){
				if(player.hasPermission("takemoney")){
					if(args.length >= 2){
						double args1 = Double.parseDouble(args[1]);
						for(Player p : Bukkit.getServer().getOnlinePlayers()){
							EconomyResponse r = economy.withdrawPlayer(p.getName(), args1);
				            if(r.transactionSuccess()) {
				                p.sendMessage(String.format(ChatColor.BLUE + "%s was taken from you, you now have %s", economy.format(r.amount), economy.format(r.balance)));
				            } else {
				                player.sendMessage(String.format("An error occured: %s", r.errorMessage));
				            }

						}
					}else{
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities givemoney (money)");
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//unFreeze
				if(args[0].equalsIgnoreCase("unfreeze") || args[0].equalsIgnoreCase("unfreezeall")){
					if(player.hasPermission("eu.freeze")){
					if(args.length >= 1){
					unfreeze();
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//Freeze
				if(args[0].equalsIgnoreCase("freeze") || args[0].equalsIgnoreCase("freezeall")){
					if(player.hasPermission("eu.freeze")){
					if(args.length >= 1){
					freeze();
					}
					}else{
						player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
					}
				}
				
				//Run command as player
				if(args[0].equalsIgnoreCase("command") || args[0].equalsIgnoreCase("cmd") || args[0].equalsIgnoreCase("runcommand") || args[0].equalsIgnoreCase("runcmd")){
					if(player.hasPermission("eu.runcommand")){
					if(args.length > 1){
					StringBuilder sb = new StringBuilder();
					for(int i = 1; i < args.length; i++){
						sb.append(args[i]).append(" ");
					}
					String cmd = sb.toString().trim();
					
					methods.runCommand(cmd);
					}else{
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities command (command here)");
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//Kill everyone command
				if(args[0].equalsIgnoreCase("kill") || args[0].equalsIgnoreCase("killall")){
					if(player.hasPermission("eu.kill")){
					if(args.length >= 1){
						methods.killAll();
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//Teleport command
				if(args[0].equalsIgnoreCase("teleport") || args[0].equalsIgnoreCase("tp") || args[0].equalsIgnoreCase("tphere") || args[0].equalsIgnoreCase("teleporthere")){
				if(player.hasPermission("eu.teleport")){
					if(args.length >= 1){
					methods.teleportAll(player.getLocation());
					player.sendMessage(ChatColor.BLUE + "All players teleported!");
				}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//PERMISSIONS ARE DISABLED UNTIL FIX IS AVAILABLE
				/*
				if(args[0].equalsIgnoreCase("givepermission") || args[0].equalsIgnoreCase("giveperm") || args[0].equalsIgnoreCase("giveperms") || args[0].equalsIgnoreCase("givepermissions")){
					if(args.length >= 2){
						for(Player p : Bukkit.getServer().getOnlinePlayers()){
							permission.playerAdd(player, args[1]);
							p.sendMessage(ChatColor.GREEN + "You have been given a new permission!");
						
						}
					}else{
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities givepermission (permission)");
					}
				}
				*/
				
				
				//Give money command
				if(args[0].equalsIgnoreCase("givemoney") || args[0].equalsIgnoreCase("pay")){
					if(player.hasPermission("eu.pay")){
					if(args.length >= 2){
						double args1 = Double.parseDouble(args[1]);
						for(Player p : Bukkit.getServer().getOnlinePlayers()){
							EconomyResponse r = economy.depositPlayer(p.getName(), args1);
				            if(r.transactionSuccess()) {
				                p.sendMessage(String.format(ChatColor.BLUE + "You were given %s and now have %s", economy.format(r.amount), economy.format(r.balance)));
				            } else {
				                player.sendMessage(String.format("An error occured: %s", r.errorMessage));
				            }

						}
					}else{
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities givemoney (money)");
					}
				}
				}
				
				//Heal command
				if(args[0].equalsIgnoreCase("heal")){
					if(player.hasPermission("eu.heal")){
					methods.Heal();
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//Give XP command
				if(args[0].equalsIgnoreCase("givexp") || args[0].equalsIgnoreCase("giveexp")){
					if(player.hasPermission("eu.givexp")){
					if(args.length >= 2){
						int args1 = Integer.parseInt(args[1]);
						methods.giveXP(args1);
					}else{
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities givexp (xp levels)");
					}
				}else{
					player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
				}
				}
				
				//Give items command
				if(args[0].equalsIgnoreCase("giveitems")){
					if(player.hasPermission("eu.giveitems")){
					//Check if its more than 2 arguments
					if(args.length == 2 || args.length == 1){
						player.sendMessage(ChatColor.RED + "Please use this format: /EveryoneUtilities giveitems (item id) (item amount)");
					}else{
						int args1 = Integer.parseInt(args[1]);
						int args2 = Integer.parseInt(args[2]);
					//give items
					methods.giveItems(args1, args2);
					}
				}
				}
			}
		}else{
			player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command.");
		}
		}
		return false;
	}
	
	
	
	
	
	public void freeze(){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			UUID uuid = p.getUniqueId();
			frozen.add(uuid);
		}
		
	}
	public void unfreeze(){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			UUID uuid = p.getUniqueId();
			frozen.remove(uuid);
		}
		
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		if(frozen.contains(e.getPlayer().getUniqueId())){
			e.setTo(e.getFrom());
			e.getPlayer().sendMessage(ChatColor.RED + "You are frozen!");
		}
	}
}
