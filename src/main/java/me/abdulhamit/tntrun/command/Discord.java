package me.abdulhamit.tntrun.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Discord implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        commandSender.sendMessage(Component.text("https://discord.gg/jcgNmPq7Gu").color(NamedTextColor.AQUA).clickEvent(
                ClickEvent.openUrl("https://discord.gg/jcgNmPq7Gu")
        ));
        return true;
    }
}
