package me.abdulhamit.tntrun.listeners;

import me.abdulhamit.tntrun.game.Game;
import me.abdulhamit.tntrun.game.PlayerSettings;
import me.abdulhamit.tntrun.stat.PlayerStat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTick extends BukkitRunnable {
    @Override
    public void run() {
        final int[] playerCount = {0};
        Bukkit.getOnlinePlayers().forEach(player ->{
            if (PlayerSettings.of(player).isParticipant()) playerCount[0]++;
        });
        
        
        
        
        if (Game.current()==null){
            if (playerCount[0]>=2) {
                if (!Game.isStarting()) {
                    Game.startNew();
                }
            } else if(Bukkit.getCurrentTick()%(20*60)==0){
                Bukkit.broadcast(Component.text("At least 2 player is required to start the game!").color(NamedTextColor.RED));
            }
        }
    }
}
