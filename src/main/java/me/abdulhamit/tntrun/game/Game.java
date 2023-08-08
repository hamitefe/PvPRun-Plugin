package me.abdulhamit.tntrun.game;

import me.abdulhamit.tntrun.TNTRun;
import me.abdulhamit.tntrun.abilities.Ability;
import me.abdulhamit.tntrun.stat.PlayerStat;
import me.abdulhamit.tntrun.util.FillUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    
    private static List<EntityType> protectedEntities = List.of(
        EntityType.PLAYER,
        EntityType.TEXT_DISPLAY,
        EntityType.BLOCK_DISPLAY
    );
    private static boolean isStarting = false;
    private int currWidth = width;
    private final GameEvent event;
    
    public static int tick = 0;
    public static int width = 20;
    private static boolean isRunning = false;
    private static Game current = null;
    private Player beast;
    public static Game current(){
        return current;
    }
    
    private Game(){
        current=this;
        this.event = GameEvent.values()[new Random().nextInt(GameEvent.values().length)];
        Bukkit.broadcastMessage("Selected event: "+event.getName());
        Bukkit.broadcastMessage(event.getDescription());
    }
    private List<Player> players = new ArrayList<>();
    
    public void start(){
        Vector locmin = new Vector(-width, 64, -width);
        Vector locmax = new Vector(width, 64, width);
        FillUtil.fillIn(Material.MOSS_BLOCK, locmin, locmax);
        
        Bukkit.getWorlds().get(0).getEntities().forEach(entity ->{
            if (!protectedEntities.contains(entity.getType())){
                entity.remove();
            }
        });
        
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (PlayerSettings.of(player).isParticipant())players.add(player);
        });
        
        
        
        players.forEach(player -> {
            player.teleport(
                new Location(Bukkit.getWorlds().get(0),0, 65, 0)
            );
            player.setHealth(20);
            player.getInventory().clear();  
        }
        );
        players.forEach(player -> {player.setGameMode(GameMode.ADVENTURE);});
        new BukkitRunnable(){@Override public void run(){
            isRunning = true;
            Bukkit.broadcast(Component.text("Game started").color(NamedTextColor.GREEN));
        }}.runTaskLater(TNTRun.getPlugin(TNTRun.class), 120);
        Game curr = this;
        new BukkitRunnable(){@Override public void run(){
            if (Game.current()==curr){
                tntRain();
            } else {
                cancel();
            }
        }}.runTaskTimer(TNTRun.getPlugin(TNTRun.class), 20*60L, 20*60L);
        this.event.getStartEvent().accept(this);
    }
    public List<Player> getPlayers(){return players;}
    
    public void stop(boolean bool){
        if (bool) {
            if (!this.players.isEmpty()) {
                this.players.forEach(this::killPlayer);
            }
        }
        isRunning = false;
        current = null;
        Vector locmin = new Vector(-currWidth, 64, -currWidth);
        Vector locmax = new Vector(currWidth, 64, currWidth);
        
        FillUtil.fillIn(Material.AIR, locmin, locmax);
    }
    
    public void tntRain()   {
        final int randomCount = new Random().nextInt(10, 40);
        
        for (int i = 0; i<randomCount; i++) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!Game.isRunning()) {
                        return;
                    }
                    
                    double randomX = ThreadLocalRandom.current().nextInt(-width, width + 1);
                    double randomY = ThreadLocalRandom.current().nextInt(-width, width + 1);
                    final Location randomLocation = new Location(Bukkit.getWorlds().get(0), randomX, 65, randomY);
                    
                    TNTPrimed entity = randomLocation.getWorld().spawn(randomLocation, TNTPrimed.class);
                    entity.setGlowing(true);
                    entity.setVelocity(new Vector(0, 1, 0));
                }
            }.runTaskLater(TNTRun.getPlugin(TNTRun.class), i* 10L);
        }
    }
    public void killPlayer(Player p){
        players.remove(p);
        if (p.isOnline()) {
            p.setGameMode(GameMode.SPECTATOR);
            p.getInventory().clear();
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_HIT, 1, 1);
        }
        if (players.size()==1){
            Bukkit.broadcast(Component.text("Game ended "+getPlayers().get(0).getDisplayName()+" won the game!").color(NamedTextColor.GREEN));
            getPlayers().get(0).setGameMode(GameMode.SPECTATOR);
            PlayerStat.of(getPlayers().get(0)).win();
            stop(false);
        } else if(players.isEmpty()) {
            Bukkit.broadcast(Component.text("Game ended no winners!").color(NamedTextColor.GREEN));
            stop(false);
        } else {
            Bukkit.broadcast(Component.text(p.getDisplayName() + " is Eliminated! " + players.size() + " players left").color(NamedTextColor.RED));
        }
    }
    
    public void setBeast(Player beast) {
        this.beast = beast;
    }
    
    public Player getBeast() {
        return beast;
    }
    
    public static boolean isStarting(){return isStarting;}
    
    public static boolean isRunning() {return isRunning;}
    
    public static void startNew(){
        
        int[] second = {5};
        isStarting = true;
        new BukkitRunnable(){@Override
        public void run(){
            if (second[0]==0){
                if (Game.current()!=null){
                    current.stop(true);
                }
                new Game().start();
                isStarting= false;
                cancel();
            }
            
            second[0]--;
            Bukkit.broadcast(Component.text("new game starting in "+(second[0]+1)+" seconds!").color(NamedTextColor.AQUA));
        }}.runTaskTimer(TNTRun.getPlugin(TNTRun.class), 20, 20);
    }
}
