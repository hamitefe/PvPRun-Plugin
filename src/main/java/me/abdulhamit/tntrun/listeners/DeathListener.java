package me.abdulhamit.tntrun.listeners;

import me.abdulhamit.tntrun.game.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent evt){
        if (Game.current()==null)return;
        if (Game.current().getPlayers().contains(evt.getPlayer())){
            Game.current().killPlayer(evt.getPlayer());
        }
    }
}
