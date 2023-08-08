package me.abdulhamit.tntrun.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class FillUtil {
    public static void fillIn(Material type, Vector min, Vector max){
        
        for (double x = min.getX(); x<max.getX(); x++){
            for (double z = min.getZ(); z<max.getZ(); z++){
                Block b = Bukkit.getWorlds().get(0).getBlockAt((int)x,(int)min.getY(),(int) z);
                b.setType(type);
            }
        }
    }
}
