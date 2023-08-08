package me.abdulhamit.tntrun.abilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class ThrowCreeper extends Ability{
    @Override
    public Component getName() {
        return Component.text("Creeper").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD, TextDecoration.ITALIC);
    }
    

    
    @Override
    public ItemStack getItem() {
        ItemStack it = new ItemStack(Material.CREEPER_SPAWN_EGG);
        ItemMeta meta = it.getItemMeta();
        
        meta.displayName(Component.text("Shoot creeper").color(NamedTextColor.GREEN));
        
        
        
        it.setItemMeta(meta);
        return it;
    }
    
    @Override
    public void execute(Player p) {
        final Location loc = p.getEyeLocation();
        final Vector dir = loc.getDirection();
        
        final Creeper ent = loc.getWorld().spawn(loc, Creeper.class);
        ent.ignite();
        ent.setVelocity(dir.multiply(1.5));
    }
}
