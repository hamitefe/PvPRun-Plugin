package me.abdulhamit.tntrun.listeners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import me.abdulhamit.tntrun.game.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class BeastDoubleJump implements Listener {
    @EventHandler
    public void onFlightToggle(PlayerToggleFlightEvent evt){
        if (Game.current()==null)return;
        if (evt.getPlayer()!=Game.current().getBeast())return;
        
        
        evt.getPlayer().setFlying(false);
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
