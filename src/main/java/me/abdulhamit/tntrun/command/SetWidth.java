package me.abdulhamit.tntrun.command;

import me.abdulhamit.tntrun.game.Game;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SetWidth implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
            Game.width = Integer.parseInt(strings[0]);
        } catch (Exception e){
            commandSender.sendMessage(Component.text(e.getMessage()).color(NamedTextColor.RED));
        }
        return true;
    }
}
