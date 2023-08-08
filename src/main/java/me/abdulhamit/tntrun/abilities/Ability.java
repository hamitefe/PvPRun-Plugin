package me.abdulhamit.tntrun.abilities;

import me.abdulhamit.tntrun.listeners.AbilityTick;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Rail;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Ability {
    public static List<Ability> abilities = new ArrayList<>();
    public static void registerAbilities(){
        new LeapFeather();
        new ThrowTnt();
        new ThrowCreeper();
    }
    public Ability(){
        abilities.add(this);
    }
    
    public abstract Component getName();
    public abstract ItemStack getItem();
    public abstract void execute(Player p);
    
    public static Ability fromItemStack(ItemStack it){
        Ability[] returnVal = {null};
        abilities.forEach(ability -> {
            if (ability.getItem().isSimilar(it)){
                returnVal[0] = ability;
            }
        });
        
        return returnVal[0];
    }
}
