package me.abdulhamit.tntrun.command;

import me.abdulhamit.tntrun.game.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class StartManually implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!Game.isStarting()){
            if (Game.current()!=null) {
                Game.current().stop();
            }
            Game.startNew();
            commandSender.sendMessage(Component.text("Game is starting!").color(NamedTextColor.GREEN));
        } else {
            commandSender.sendMessage(Component.text("Game is already starting!").color(NamedTextColor.GREEN));
        }
        return true;
    }
}
