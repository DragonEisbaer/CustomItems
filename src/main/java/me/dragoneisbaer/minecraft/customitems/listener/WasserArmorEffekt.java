package me.dragoneisbaer.minecraft.customitems.listener;

import me.dragoneisbaer.minecraft.customitems.commands.armor.ArmorTemplate;
import me.dragoneisbaer.minecraft.customitems.commands.armor.WasserArmor;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Map;

public class WasserArmorEffekt extends ArmorEffectTemplate implements Listener {

    @EventHandler
    public void DisableDrown(EntityDamageEvent e) {

        if (e.getCause() != EntityDamageEvent.DamageCause.DROWNING) return;
        if (!(e.getEntity() instanceof Player)) return;

        Player player = (Player) e.getEntity();
        if (isFullArmorSet(player)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void UnderwaterHaste(PlayerMoveEvent e)  {
        Player player = e.getPlayer();
        if (isFullArmorSet(player)) {
            player.spawnParticle(Particle.WATER_DROP, player.getLocation(), 10);
            if (player.isInWater()) {
                player.setMaxHealth(40);
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, -1, 9));
            }else {
                reset(player);
            }
        }else {
            reset(player);
        }
    }

    @Override
    public Map<PotionEffectType, Integer> getEffects() {
        return null;
    }

    @Override
    public ArmorTemplate getArmorTemplate() {
        return new WasserArmor();
    }

    private void reset(Player player) {
         player.setMaxHealth(20);
         if (player.getActivePotionEffects().contains(new PotionEffect(PotionEffectType.FAST_DIGGING, PotionEffect.INFINITE_DURATION, 9))) {
             player.removePotionEffect(PotionEffectType.FAST_DIGGING);
         }
    }
}
