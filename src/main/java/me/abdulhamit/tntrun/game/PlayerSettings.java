package me.abdulhamit.tntrun.game;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerSettings {
    private static HashMap<Player, PlayerSettings> map = new HashMap<>();
    
    private PlayerSettings(Player p){
        this.p = p;
    }
    
    private final Player p;
    
    private boolean participant = true;
    
    public static PlayerSettings of(Player p){
        if (!map.containsKey(p)){
            map.put(p,new PlayerSettings(p));
        }
        return map.get(p);
    }
    
    public Player getPlayer() {
        return p;
    }
    
    public boolean isParticipant() {
        return participant;
    }
    
    public void setParticipant(boolean participant) {
        this.participant = participant;
    }
}
