package me.abdulhamit.tntrun;

import me.abdulhamit.tntrun.abilities.Ability;
import me.abdulhamit.tntrun.command.*;
import me.abdulhamit.tntrun.listeners.*;
import me.abdulhamit.tntrun.stat.PlayerStat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class TNTRun extends JavaPlugin {
    public static TNTRun instance;
    public File statFile(){
        return new File(getDataFolder(), "stats.yml");
    }

    @Override
    public void onEnable() {
        instance = this;
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
        getCommand("setstat").setExecutor(new SetStat());
        Bukkit.getLogger().info("enabling TNT RUN");
        PlayerStat.readAll(statFile());
        
        //TODO: item to push nearby players
    }

    @Override
    public void onDisable() {
        try {
            PlayerStat.saveAll(statFile());
        }catch (Exception e) {
            Bukkit.getLogger().info("error while saving statistics:" +e.getMessage());
        }
    }
}
