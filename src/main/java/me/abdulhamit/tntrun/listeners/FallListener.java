package me.abdulhamit.tntrun.listeners;

import me.abdulhamit.tntrun.game.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FallListener implements Listener {
    @EventHandler
    public void onPosition(PlayerMoveEvent evt){
        if (evt.getTo().getY()<=50&&Game.current()!=null){
            if (Game.current().getPlayers().contains(evt.getPlayer())){
                Game.current().killPlayer(evt.getPlayer());
            }
        }
    }
}
