package me.abdulhamit.tntrun.stat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.ComponentBuilderApplicable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerStat {
    public static HashMap<UUID, PlayerStat> stats = new HashMap<>();
    
    private int wins = 0;
    private int bobuxs = 0;
    private int looses = 0;
    private final UUID player;
    
    public static PlayerStat of(Player p){
        if (!stats.containsKey(p.getUniqueId())){
            stats.put(p.getUniqueId(), new PlayerStat(p));
        }
        
        return stats.get(p.getUniqueId());
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
    
    public HashMap<String, Object> serialize(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("bobux", this.bobuxs);
        result.put("wins", this.wins);
        result.put("uuid", this.player.toString());
        return result;
    }
    
    public JsonObject toJsonObject(){
        JsonObject obj = new JsonObject();
        obj.addProperty("bobux", bobuxs);
        obj.addProperty("wins", wins);
        obj.addProperty("uuid", this.player.toString());
        return  obj;
    }
    
    public static PlayerStat deserialize(HashMap<String, Object> map){
        int bobux =(int) map.get("bobux");
        int wins = (int) map.get("wins");
        UUID uid = UUID.fromString((String) map.get("uuid"));
        PlayerStat stat = new PlayerStat(uid);
        stat.setWins(wins);
        stat.setBobuxs(bobux);
        return stat;
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
    
    public static void saveAll(File f){
        try {
            if (!f.exists()) {
                f.mkdir();
                f.createNewFile();
            }
            
            JsonObject obj = new JsonObject();
            
            stats.forEach((uuid, stat) -> {
                obj.add(uuid.toString(), stat.toJsonObject());
            });
            
            FileWriter writer = new FileWriter(f);
            writer.write(obj.toString());
        } catch (Exception e){
            Bukkit.getLogger().info("error while trying to save player statistics! : "+e.getMessage());
        }
    }
    
    public static void readAll(File f) {
        if (!f.exists()){
            return;
        }
        try {
            JsonElement el = JsonParser.parseReader(new FileReader(f));
            if (!el.isJsonObject())return;
            JsonObject object = el.getAsJsonObject();
            object.entrySet().forEach(entry -> {
                final UUID uid = UUID.fromString(entry.getKey());
                final Map<String, JsonElement> map = entry.getValue().getAsJsonObject().asMap();
                final PlayerStat stat = PlayerStat.deserializeJson(new HashMap<>(map));
                stats.put(uid, stat);
            });
        }
        catch (FileNotFoundException ignored){
            Bukkit.getLogger().info("Error when reading stats: file not found");
        }
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder()
            .append("player : "+Bukkit.getOfflinePlayer(this.player).getName())
                .append("looses : "+this.looses+" \n")
            .append("bobux : "+this.bobuxs+"\n")
            .append("wins : "+this.wins+"\n");
        return builder.toString();
    }
}
