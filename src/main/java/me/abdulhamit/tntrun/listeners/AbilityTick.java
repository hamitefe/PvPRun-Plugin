package me.abdulhamit.tntrun.listeners;

import me.abdulhamit.tntrun.abilities.Ability;
import me.abdulhamit.tntrun.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AbilityTick extends BukkitRunnable {
    @Override
    public void run() {
        if (!Game.isRunning()){
            return;
        }
        
        double randomX = ThreadLocalRandom.current().nextInt(-Game.width, Game.width+1);
        double randomY = ThreadLocalRandom.current().nextInt(-Game.width, Game.width+1);
        final Location randomLocation = new Location(Bukkit.getWorlds().get(0), randomX, 70,randomY);
        
        final Ability randomAbility =Ability.abilities.get(new Random().nextInt(Ability.abilities.size()));
        
        Item it = randomLocation.getWorld().dropItem(randomLocation, randomAbility.getItem());
        it.setGlowing(true);
        it.setVelocity(new Vector(0, 1, 0));
    }
}
