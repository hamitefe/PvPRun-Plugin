package me.abdulhamit.tntrun.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PreventHunger implements Listener {
    @EventHandler
    public void onHunger(FoodLevelChangeEvent evt){
        evt.setCancelled(true);
    }
}
