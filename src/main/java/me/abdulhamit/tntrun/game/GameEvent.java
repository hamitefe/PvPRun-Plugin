package me.abdulhamit.tntrun.game;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public enum GameEvent {
    Knockback("§3knockback", "push others", game ->{
        ItemStack i = new ItemStack(Material.STICK);
        ItemMeta met = i.getItemMeta();
        met.addEnchant(Enchantment.KNOCKBACK, 3, true);
        met.displayName(Component.text("Knockback stick").color(NamedTextColor.BLUE));
        i.setItemMeta(met);
        game.getPlayers().forEach(player ->{
            player.getInventory().addItem(i);
        });
    }),
    
    Beast("§6Beast", "1 player will be choosen as beast and kill other players try to survive!", game -> {
        
        
        game.getPlayers().forEach(player ->{
            player.getInventory().addItem(new ItemStack(Material.WOODEN_SWORD));
        });
        ItemStack hoe = new ItemStack(Material.IRON_HOE);
        ItemMeta meta = hoe.getItemMeta();
        meta.setDestroyableKeys(List.of(Material.MOSS_BLOCK.getKey()));
        hoe.setItemMeta(meta);
        Player beast = game.getPlayers().get(new Random().nextInt(game.getPlayers().size()));
        beast.getInventory().addItem(hoe);
        beast.setAllowFlight(true);
        game.setBeast(beast);
        beast.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
        beast.getInventory().addItem(new ItemStack(Material.STONE_AXE));
        Bukkit.broadcast(Component.text(beast.getDisplayName()+" is choosen BEAST").color(NamedTextColor.RED));
    }),
    Spleef("§5Spleef", "§3Mine blocks under enemy players to make them fall!", game -> {
        ItemStack hoe = new ItemStack(Material.IRON_HOE);
        ItemMeta meta = hoe.getItemMeta();
        meta.setDestroyableKeys(List.of(Material.MOSS_BLOCK.getKey()));
        hoe.setItemMeta(meta);
        
        game.getPlayers().forEach(player -> {
            player.getInventory().addItem(hoe);
        });
    }),
    None("§2None", "no events for this game good luck next time :)", game -> {}),
    Pvp("§4pvp", "kill other players", (game) ->{
        game.getPlayers().forEach(player -> {
            player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
            player.getInventory().addItem(new ItemStack(Material.STONE_AXE));
            player.getInventory().addItem(new ItemStack(Material.SHIELD));
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
        });
    });
    private final String name;
    private final String description;
    private final Consumer<Game> consumer;
    GameEvent(String name, String description, Consumer<Game> consumer){
        this.name = name;
        this.description = description;
        this.consumer = consumer;
    }
    
    public Consumer<Game> getStartEvent() {
        return consumer;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
}
