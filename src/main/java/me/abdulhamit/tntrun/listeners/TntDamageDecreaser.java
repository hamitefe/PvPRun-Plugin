package me.abdulhamit.tntrun.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class TntDamageDecreaser implements Listener {
    @EventHandler
    public void onExplode(ExplosionPrimeEvent evt){
        evt.setFire(false);
        evt.setRadius(3);
    }
    
    @EventHandler
    public void onExplode(EntityDamageEvent evt){
        if (evt.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)||evt.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)){
            evt.setDamage(evt.getDamage()/2);
        }
    }
}
