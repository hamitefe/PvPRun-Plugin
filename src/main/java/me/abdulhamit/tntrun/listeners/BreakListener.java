package me.abdulhamit.tntrun.listeners;

import me.abdulhamit.tntrun.TNTRun;
import me.abdulhamit.tntrun.game.Game;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BreakListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent evt){
        if (Game.current()==null){
            return;
        }
        
        if (!Game.current().getPlayers().contains(evt.getPlayer())){
            return;
        }
        Location loc = evt.getPlayer().getLocation();
        
        if (Game.isRunning()){
            new BukkitRunnable(){@Override public void run(){
                loc.subtract(0, 1, 0).getBlock().setType(Material.AIR);
            }}.runTaskLater(TNTRun.getPlugin(TNTRun.class), 10);
        }
    }
}
