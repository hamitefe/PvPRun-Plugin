package me.abdulhamit.tntrun.abilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LeapFeather extends Ability{
    
    @Override
    public Component getName(){
        return Component.text("Leap Feather").color(NamedTextColor.GRAY);
    }
    
    @Override
    public Material getBlock() {
        return Material.FEATHER;
    }
    
    @Override
    public ItemStack getItem() {
        ItemStack it = new ItemStack(Material.FEATHER);
        ItemMeta meta = it.getItemMeta();
        
        meta.displayName(Component.text("Leap Feather").color(NamedTextColor.GRAY));


        it.setItemMeta(meta);
        return it;
    }
    
    @Override
    public void execute(Player p) {
        p.setVelocity(p.getEyeLocation().getDirection().multiply(1.5));
    }
}
