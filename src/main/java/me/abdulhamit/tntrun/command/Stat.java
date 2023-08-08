package me.abdulhamit.tntrun.command;

import me.abdulhamit.tntrun.stat.PlayerStat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Stat implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        try {
            if (args.length == 0 && commandSender instanceof Player) {
                commandSender.sendMessage(PlayerStat.stats.get(((Player) commandSender).getUniqueId()).toString());
            } else {
                final UUID uid = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
                commandSender.sendMessage(PlayerStat.stats.get(uid).toString());
            }
        } catch (Exception e){
            commandSender.sendMessage(Component.text("Error while getting stats of player: "+e.getMessage()).color(NamedTextColor.RED));
        }
        
        
        return true;
    }
    
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        ArrayList<String> result = new ArrayList<>();
        if (args.length>1){
            result.add("<TOO MANY ARGUMENTS!>");
        } else if (args.length==1) {
            PlayerStat.stats.keySet().forEach(uid ->{
                String name = Bukkit.getOfflinePlayer(uid).getName();
                if (name.startsWith(args[0])){
                    result.add(name);
                }
            });
        }
        
        
        
        
        return result;
    }
}
