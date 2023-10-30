package me.abdulhamit.tntrun.command;

import me.abdulhamit.tntrun.stat.PlayerStat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SetStat implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length!=3){
            commandSender.sendMessage(ChatColor.RED+"Usage: /setstat <player> <statistic> <value>");
            return true;
        }
        if (Bukkit.getPlayer(args[0])!=null){
            final UUID uid = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
            PlayerStat stat = PlayerStat.of(uid);
            try {
                switch (args[1].toLowerCase()){
                    case "bobux":
                        stat.setBobuxs(Integer.parseInt(args[2]));
                        break;
                    case "wins":
                        stat.setWins(Integer.parseInt(args[2]));
                        break;
                    case "loose":
                        stat.setLooses(Integer.parseInt(args[2]));
                        break;
                }
                PlayerStat.stats.replace(uid, stat);
                commandSender.sendMessage(stat.toString());
            } catch (NumberFormatException e){
                commandSender.sendMessage(ChatColor.RED+"Error parsing the integer at arg 3");
            }
        }
        
        
        
        
        
        return true;
    }
}
