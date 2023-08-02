package me.abdulhamit.tntrun.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent evt){
        evt.getPlayer().setGameMode(GameMode.SPECTATOR);
    }
}
