package me.dragoneisbaer.minecraft.customitems.listener;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.awt.*;
import java.util.List;

public class FeuerArmorEffekt implements Listener {

    @EventHandler
    public void ParticleDamage(PlayerMoveEvent e)  {
        Player player = (Player) e.getPlayer();
        if (FullArmorSet(player, "Laufe in und über Feuer!")) {
            int level = GetLevel(player);
            int amplifier;
            if (level<10) {
                amplifier = 1;
            } else if (level<20) {
                amplifier = 2;
            }else {
                amplifier = 3;
            }
            player.spawnParticle(Particle.LAVA, player.getLocation(), amplifier);
            double multiplier = (double) 2 /(31-level);
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue() * (multiplier+1));
        }else {
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getDefaultValue());
        }
    }

    @EventHandler
    public void IgnoreFireDamage(PlayerArmorChangeEvent e) {
        Player player = e.getPlayer();
        if (FullArmorSet(player, "Laufe in und über Feuer!")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 0));
        }else {
            if (player.getActivePotionEffects().contains(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1 ,0))) {
                player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            }
        }
    }

    @EventHandler
    public void IgniteOthers(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (FullArmorSet(player, "Laufe in und über Feuer!")) {
                Entity angreifer = e.getDamager();
                int level = GetLevel(player);
                angreifer.setFireTicks(level * 33);
            }
        }
    }

    @EventHandler
    public void AddXP(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            AddXP(player, "Laufe in und über Feuer!");
        }
    }

    @EventHandler
    public void AddXPAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            int level = GetLevel(player);
            e.getEntity().setFireTicks(level * 33);
            AddXP(player, "Laufe in und über Feuer!");
        }
    }

    public boolean FullArmorSet(Player player, String lore) {
        if (!(player.getInventory().getHelmet() == null || player.getInventory().getChestplate() == null || player.getInventory().getLeggings() == null || player.getInventory().getBoots() == null)) {
            if (player.getInventory().getHelmet().getLore() != null && player.getInventory().getChestplate().getLore() != null && player.getInventory().getLeggings().getLore() != null && player.getInventory().getBoots().getLore() != null) {
                if (player.getInventory().getHelmet().getItemMeta().getLore().contains(lore) && player.getInventory().getChestplate().getItemMeta().getLore().contains(lore) && player.getInventory().getLeggings().getItemMeta().getLore().contains(lore) && player.getInventory().getBoots().getItemMeta().getLore().contains(lore)) {
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    public int GetLevel(Player player) {
        int dlevel = 0;
        for (int i = 0;i<4;i++) {
            String level = player.getInventory().getArmorContents()[i].getLore().get(0);
            int newlevel = Character.getNumericValue(level.charAt(level.length() - 1));
            int newlevel1 = Character.getNumericValue(level.charAt(level.length() - 2));
            if (newlevel1 != -1) {
                String newlevels = String.valueOf(newlevel1) + String.valueOf(newlevel);
                newlevel = Integer.parseInt(newlevels);
                dlevel = dlevel + newlevel;
            }else {
                dlevel = dlevel + newlevel;
            }
        }
        return (int) Math.floor(dlevel/4);
    }
    public void AddXP(Player player, String armorlore) {
        if (FullArmorSet(player, armorlore)){
            for (int i = 0;i<4;i++) {
                int level = GetLevel(player);
                if (level >= 30) {
                    return;
                }
                String xplore = player.getInventory().getArmorContents()[i].getLore().get(1);
                List<String> lore = player.getInventory().getArmorContents()[i].getLore();
                int newxp = Character.getNumericValue(xplore.charAt(xplore.length()-1));
                int newxp2 = Character.getNumericValue(xplore.charAt(xplore.length() - 2));
                if (newxp2 != -1) {
                    String newxps = String.valueOf(newxp2) + String.valueOf(newxp);
                    newxp = Integer.parseInt(newxps);
                }
                if (newxp >= 99) {
                    level = level + 1;
                    newxp = 0;
                    lore.set(1, "Exp: " + newxp);
                    player.getInventory().getArmorContents()[i].setLore(lore);
                    player.getInventory().getArmorContents()[i].getLore().add("Exp: " + newxp);
                    player.sendMessage(ChatColor.RED + "Ein Rüstungsteil ist ein Level aufgestiegen! " + (level-1) + " --> " + level);
                    if (i==0) {
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 0);
                    }
                    if (level >= 30) {
                        player.sendMessage("");
                        player.sendMessage(ChatColor.GREEN + "Du hast das maximale Level eines Rüstungsteil erreicht!");
                        player.sendMessage("");
                        lore.remove(1);
                    }
                    lore.set(0, "Level: " + level);
                    player.getInventory().getArmorContents()[i].setLore(lore);
                }else {
                    if (!(xplore.charAt(xplore.length() - 2) == ' ')) {
                        newxp = newxp + 1;
                        lore.set(1, "Exp: " + newxp);
                        player.getInventory().getArmorContents()[i].setLore(lore);
                    }else {
                        newxp = newxp + 1;
                        lore.set(1, "Exp: " + newxp);
                        player.getInventory().getArmorContents()[i].setLore(lore);
                        player.getInventory().getArmorContents()[i].getLore().add("Exp: " + newxp);
                    }
                }
            }
        }
    }
}