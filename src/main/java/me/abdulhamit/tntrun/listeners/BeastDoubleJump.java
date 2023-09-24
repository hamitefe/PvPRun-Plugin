package me.abdulhamit.tntrun.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.abdulhamit.tntrun.TNTRun;
import me.abdulhamit.tntrun.game.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BeastDoubleJump implements Listener {
    @EventHandler
    public void onFlightToggle(PlayerToggleFlightEvent evt){
        if (Game.current()==null)return;
        if (evt.getPlayer()!=Game.current().getBeast())return;
        
        if (evt.isFlying()) {
            new BukkitRunnable(){@Override
            public void run(){
                evt.getPlayer().setFlying(false);
            }}.runTaskLater(TNTRun.getPlugin(TNTRun.class), 10);
            
        }
        evt.getPlayer().setVelocity(evt.getPlayer().getEyeLocation().getDirection().multiply(1));
        evt.getPlayer().setAllowFlight(false);
    }
    
    @EventHandler
    public void onJump(PlayerJumpEvent evt){
        if (Game.current()==null)return;
        if (evt.getPlayer()!=Game.current().getBeast())return;
        
        evt.getPlayer().setAllowFlight(true);
    }
}
