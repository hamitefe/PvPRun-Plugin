package me.abdulhamit.tntrun;

import me.abdulhamit.tntrun.abilities.Ability;
import me.abdulhamit.tntrun.command.*;
import me.abdulhamit.tntrun.listeners.*;
import me.abdulhamit.tntrun.stat.PlayerStat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class TNTRun extends JavaPlugin {

    @Override
    public void onEnable() {
        new StartTick().runTaskTimer(this, 0, 10);
        new AbilityTick().runTaskTimer(this, 0, 20);
        Bukkit.getPluginManager().registerEvents(new FallListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new TntDamageDecreaser(), this);
        Bukkit.getPluginManager().registerEvents(new BreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new PreventHunger(), this);
        Bukkit.getPluginManager().registerEvents(new AbilityListener(), this);
        Bukkit.getPluginManager().registerEvents(new LeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new BeastDoubleJump(), this);
        Ability.registerAbilities();
        getCommand("startmanually").setExecutor(new StartManually());
        getCommand("width").setExecutor(new SetWidth());
        getCommand("tntrain").setExecutor(new TntRain());
        getCommand("stats").setExecutor(new Stat());
        getCommand("stats").setTabCompleter(new Stat());
        getCommand("toggleparticipant").setExecutor(new ToggleParticipant());
        
        PlayerStat.readAll(new File(getDataFolder(), "stats.json"));
        
        //TODO: item to push nearby players
    }

    @Override
    public void onDisable() {
        PlayerStat.saveAll(new File(getDataFolder(), "stats.json"));
    }
}
