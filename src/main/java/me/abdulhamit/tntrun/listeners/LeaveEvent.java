package me.abdulhamit.tntrun.listeners;

import me.abdulhamit.tntrun.game.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent evt){
        if (Game.current().getPlayers().contains(evt.getPlayer())){
            Game.current().killPlayer(evt.getPlayer());
        }
    }
}
