package me.abdulhamit.tntrun.command;

import me.abdulhamit.tntrun.game.PlayerSettings;
import me.abdulhamit.tntrun.stat.PlayerStat;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToggleParticipant implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player))return true;
        
        final Player p = (Player) commandSender;
        PlayerSettings settings = PlayerSettings.of(p);
        
        settings.setParticipant(!settings.isParticipant());
        p.sendMessage(Component.text("Participant mode is now "+settings.isParticipant()));
        return true;
    }
}
