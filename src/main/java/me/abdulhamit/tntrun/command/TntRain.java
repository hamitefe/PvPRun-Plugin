package me.abdulhamit.tntrun.command;

import me.abdulhamit.tntrun.game.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class TntRain implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (Game.current() == null){
            commandSender.sendMessage(Component.text("No online games right now").color(NamedTextColor.RED));
        } else {
            Game.current().tntRain();
        }
        
        
        
        return true;
    }
}
