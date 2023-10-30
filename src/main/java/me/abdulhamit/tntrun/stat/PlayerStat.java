package me.abdulhamit.tntrun.stat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.abdulhamit.tntrun.TNTRun;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.ComponentBuilderApplicable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerStat implements ConfigurationSerializable {
    @Override
    public Map<String, Object> serialize(){
        return Map.of(
                "bobux", bobuxs,
                "wins", wins,
                "looses", looses,
                "uuid",getUUID().toString()
        );
    }
    
    public static PlayerStat deserialize(Map<String, Object> map){
        PlayerStat stat = PlayerStat.of(UUID.fromString((String) map.get("uuid")));
        stat.bobuxs = (Integer) map.get("bobux");
        stat.wins = (Integer) map.get("wins");
        stat.looses = (Integer) map.get("looses");
        return stat;
    }
    
    public static HashMap<UUID, PlayerStat> stats = new HashMap<>();
    
    private int wins = 0;
    private int bobuxs = 0;
    private int looses = 0;
    private final UUID player;
    
    public static PlayerStat of(Player p){
        return of (p.getUniqueId());
    }
    
    public static PlayerStat of(UUID uid){
        if (!stats.containsKey(uid)){
            stats.put(uid, new PlayerStat(uid));
        }
        
        return stats.get(uid);
    }
    
    public PlayerStat(Player p){
        this.player = p.getUniqueId();
    }
    
    public PlayerStat(UUID p){
        this.player = p;
    }
    
    public UUID getUUID() {
        return player;
    }
    
    public void setWins(int wins) {
        this.wins = wins;
    }
    public void setLooses(int loose){this.looses = loose;}
    public void loose(){this.looses += 1;}
    public int getWins() {
        return wins;
    }
    public void win(){
        wins += 1;
        int newbobux = new Random().nextInt(0, 5);
        Objects.requireNonNull(Bukkit.getPlayer(this.player)).sendMessage("+"+newbobux);
        this.bobuxs += newbobux;
    }
    public void setBobuxs(int bobuxs) {
        this.bobuxs = bobuxs;
    }
    public int getBobuxs() {
        return bobuxs;
    }
    
    public JsonObject toJsonObject(){
        JsonObject obj = new JsonObject();
        obj.addProperty("bobux", bobuxs);
        obj.addProperty("wins", wins);
        obj.addProperty("looses", looses);
        obj.addProperty("uuid", this.player.toString());
        return  obj;
    }
    
    public static PlayerStat deserializeJson(HashMap<String, JsonElement> map){
        int bobux =map.get("bobux").getAsInt();
        int wins = map.get("wins").getAsInt();
        UUID uid = UUID.fromString(map.get("uuid").getAsString());
        PlayerStat stat = new PlayerStat(uid);
        stat.setWins(wins);
        stat.setBobuxs(bobux);
        return stat;
    }
    
    public static void saveAll(File f) {

        try {
            Bukkit.getLogger().info("statistics : "+ stats.size());
            YamlConfiguration conf = new YamlConfiguration();
            conf.load(TNTRun.instance.statFile());
            int count = 0;
            for (Map.Entry<UUID, PlayerStat> entry : PlayerStat.stats.entrySet()){
                conf.set("stats."+entry.getKey().toString(), entry.getValue());
                count++;
            }
            Bukkit.getLogger().info("saved "+count+" statistics");
            
            conf.save(TNTRun.instance.statFile());
        } catch (Exception e){
            Bukkit.getLogger().warning("error while trying to save player statistics! : "+e.getMessage());
        }
    }
    
    public static void readAll(File f) {
        if (!f.exists())return;
        try {
            YamlConfiguration conf = new YamlConfiguration();
            conf.load(TNTRun.instance.statFile());
            if (!conf.isConfigurationSection("stats"))return;
            ConfigurationSection section = conf.getConfigurationSection("stats");
            assert section != null : "Section is null :/";
            int count = 0;
            for (String key : section.getKeys(false)){
                count++;
                PlayerStat stat = (PlayerStat) section.get(key);
                Bukkit.getLogger().info("readed: "+stat.toString());
                stats.replace(stat.getUUID(), stat);
            }
            Bukkit.getLogger().info("readed "+count+" statistics");
        }
        catch (Exception ex){
            Bukkit.getLogger().info("Error while trying to read statistics :" +ex.getMessage());
        }
    }
    
    @Override
    public String toString(){
        return new StringBuilder()
            .append("player : "+Bukkit.getOfflinePlayer(this.player).getName()+"\n")
            .append("looses : "+this.looses+" \n")
            .append("bobux : "+this.bobuxs+"\n")
            .append("wins : "+this.wins+"\n").toString();

    }
}
