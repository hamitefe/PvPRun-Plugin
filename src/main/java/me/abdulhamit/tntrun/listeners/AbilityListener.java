package me.abdulhamit.tntrun.listeners;

import me.abdulhamit.tntrun.abilities.Ability;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class AbilityListener implements Listener {
    @EventHandler
    public void onAbility(PlayerInteractEvent evt){
        final Ability ability = Ability.fromItemStack(evt.getPlayer().getEquipment().getItemInMainHand());
        
        if (ability==null){return;}
        if (!evt.getAction().isRightClick()){return;}
        evt.setCancelled(true);
        
        ability.execute(evt.getPlayer());
        final Inventory inv = evt.getPlayer().getInventory();
        
        for (int index = 0; index <inv.getSize(); index++){
            if (inv.getItem(index)!=null) {
                if (inv.getItem(index).isSimilar(ability.getItem())) {
                    final ItemStack replaceItem = inv.getItem(index);
                    if (replaceItem != null) {
                        replaceItem.setAmount(replaceItem.getAmount() - 1);
                        inv.setItem(index, replaceItem);
                        break;
                    }
                }
            }
        }
    }
}
