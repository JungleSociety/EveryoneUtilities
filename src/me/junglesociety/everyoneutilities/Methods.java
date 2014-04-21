package me.junglesociety.everyoneutilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Methods {
	
	
	@SuppressWarnings("deprecation")
	public void Heal(){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.setHealth(20);
			p.sendMessage(ChatColor.YELLOW + "You have been healed!");
		}
	}
	public void giveItems(int item, int amount){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			@SuppressWarnings("deprecation")
			ItemStack items = new ItemStack(item, amount);
			p.getInventory().addItem(items);
			p.sendMessage(ChatColor.BLUE + "You have been given items!");
		}
		
	}
	public void giveXP(int amount){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.giveExpLevels(amount);
			p.sendMessage(ChatColor.GOLD + "You have been given " + amount + " XP levels!");
		}
		
	}
	
	public void teleportAll(Location loc){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.teleport(loc);
			p.sendMessage(ChatColor.BLUE + "You have been teleported!");
		}
	}
	
	@SuppressWarnings("deprecation")
	public void killAll(){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.setHealth(0);
			p.sendMessage(ChatColor.DARK_RED + "You have been killed!");
		}
	}
	
	public void runCommand(String command){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.performCommand(command);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void takeItems(int item, int amount){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			ItemStack finalitem = new ItemStack(item, amount);
			if(p.getInventory().contains(item)){
				p.getInventory().removeItem(finalitem);
				p.sendMessage(ChatColor.RED + "Some items have been taken from you!");
				p.updateInventory();
			}
		}
	}
	
	public void takeXP(int amount){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			if(p.getExpToLevel() - amount < 0){
				p.setLevel(0);
				p.sendMessage(ChatColor.RED + "" + amount + " XP has been taken from you!");
			}else{
				p.setLevel(p.getLevel() - amount);
				p.sendMessage(ChatColor.RED + "" + amount + " XP has been taken from you!");
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void takeHealth(int amount){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			Damageable player = p;
			if(player.getHealth() - amount < 0){
				p.setHealth(0);
				p.sendMessage(ChatColor.RED + "" + amount + " health has been taken from you!");
			}else{
				p.setHealth(player.getHealth() - amount);
				p.sendMessage(ChatColor.RED + "" + amount + " health has been taken from you!");
			}
		}
	}
	
	public void setXP(int amount){
		for(Player p : Bukkit.getOnlinePlayers()){
			p.setLevel(amount);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void setHealth(int amount){
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.setHealth(amount);
		}
	}
	
}
