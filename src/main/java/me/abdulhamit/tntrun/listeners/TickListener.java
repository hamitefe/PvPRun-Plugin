package me.abdulhamit.tntrun.listeners;

import me.abdulhamit.tntrun.TNTRun;
import me.abdulhamit.tntrun.game.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class TickListener extends BukkitRunnable {
    @Override
    public void run() {
        if (Game.current()==null){
            if (Bukkit.getOnlinePlayers().size()>=2) {
                if (!Game.isStarting()) {
                    Game.startNew();
                }
            } else if(Bukkit.getCurrentTick()%(20*60)==0){
                Bukkit.broadcast(Component.text("At least 2 player is required to start the game!").color(NamedTextColor.RED));
            }
        }
    }
}
