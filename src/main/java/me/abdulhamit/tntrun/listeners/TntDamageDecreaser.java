package me.abdulhamit.tntrun.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class TntDamageDecreaser implements Listener {
    @EventHandler
    public void onExplode(ExplosionPrimeEvent evt){
        evt.setFire(false);
        evt.setRadius(5);
    }
    
    @EventHandler
    public void onExplodeDamage(EntityDamageEvent evt){
        if (evt.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)){
            evt.setDamage(0);
        }
    }
}
