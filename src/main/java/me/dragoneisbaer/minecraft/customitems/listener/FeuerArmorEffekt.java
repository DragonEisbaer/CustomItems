package me.dragoneisbaer.minecraft.customitems.listener;

import me.dragoneisbaer.minecraft.customitems.commands.armor.ArmorTemplate;
import me.dragoneisbaer.minecraft.customitems.commands.armor.FeuerArmor;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class FeuerArmorEffekt extends ArmorEffectTemplate implements Listener {

    @EventHandler
    public void ParticleDamage(PlayerMoveEvent e)  {
        Player player = e.getPlayer();
        if (isFullArmorSet(player)) {
            int lvl = 0;
            for (String armor : getArmorFromPlayer(player).keySet()) {
                lvl += getLevel(player, armor);
            }
            int avglevel = lvl/4;
            int amplifier;
            if (avglevel <10) {
                amplifier = 1;
            } else if (avglevel <20) {
                amplifier = 2;
            }else {
                amplifier = 3;
            }
            player.spawnParticle(Particle.LAVA, player.getLocation(), amplifier);
            double multiplier = (double) 2 /(31- avglevel);
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue() * (multiplier+1));
        }else {
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
        }
    }

    @EventHandler
    public void IgniteOthers(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (isFullArmorSet(player)) {
                Entity angreifer = e.getDamager();
                int lvl = 0;
                for (String armor : getArmorFromPlayer(player).keySet()) {
                    lvl += getLevel(player, armor);
                }
                int avglevel = lvl/4;
                angreifer.setFireTicks(avglevel * 33);
            }
        }
    }


    @Override
    public Map<PotionEffectType, Integer> getEffects() {
        return new HashMap<PotionEffectType, Integer>(){{put(PotionEffectType.FIRE_RESISTANCE, 0);}};
    }

    @Override
    public ArmorTemplate getArmorTemplate() {
        return new FeuerArmor();
    }
}