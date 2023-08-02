package me.abdulhamit.tntrun.abilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

public class ThrowTnt extends Ability{
    @Override
    public Component getName() {
        return Component.text("TnT").color(NamedTextColor.DARK_RED);
    }
    
    @Override
    public Material getBlock() {
        return Material.TNT;
    }
    
    @Override
    public ItemStack getItem() {
        ItemStack it = new ItemStack(Material.TNT);
        ItemMeta meta = it.getItemMeta();
        meta.displayName(getName());
        
        it.setItemMeta(meta);
        return it;
    }
    
    @Override
    public void execute(Player p) {
        final Location loc = p.getEyeLocation();
        final Vector dir = loc.getDirection();
        
        final TNTPrimed ent = (TNTPrimed) loc.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
        ent.setFuseTicks(40);
        ent.getYield();
        ent.setVelocity(dir.multiply(1.5));
    }
}
