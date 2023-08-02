package me.abdulhamit.tntrun;

import me.abdulhamit.tntrun.abilities.Ability;
import me.abdulhamit.tntrun.command.Discord;
import me.abdulhamit.tntrun.command.SetWidth;
import me.abdulhamit.tntrun.command.StartManually;
import me.abdulhamit.tntrun.command.TntRain;
import me.abdulhamit.tntrun.game.Game;
import me.abdulhamit.tntrun.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.management.GarbageCollectorMXBean;

public final class TNTRun extends JavaPlugin {

    @Override
    public void onEnable() {
        new TickListener().runTaskTimer(this, 0, 10);
        new AbilityTick().runTaskTimer(this, 0, 20);
        Bukkit.getPluginManager().registerEvents(new FallListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new TntDamageDecreaser(), this);
        Bukkit.getPluginManager().registerEvents(new BreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new AbilityListener(), this);
        Ability.registerAbilities();
        getCommand("startmanually").setExecutor(new StartManually());
        getCommand("discord").setExecutor(new Discord());
        getCommand("width").setExecutor(new SetWidth());
        getCommand("tntrain").setExecutor(new TntRain());
    }

    @Override
    public void onDisable() {
    }
}
