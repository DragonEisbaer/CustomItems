package me.dragoneisbaer.minecraft.customitems.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class DisableHeadPlacement implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        Player player = e.getPlayer();
        ItemStack item = player.getItemInHand();

        if (item.getType() == Material.PLAYER_HEAD) {
            e.setCancelled(true);
        }
    }
}
