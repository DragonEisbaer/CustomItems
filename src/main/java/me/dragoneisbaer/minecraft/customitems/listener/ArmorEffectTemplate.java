package me.dragoneisbaer.minecraft.customitems.listener;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.dragoneisbaer.minecraft.customitems.commands.armor.ArmorTemplate;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public abstract class ArmorEffectTemplate implements Listener {

    private final Map<PotionEffectType, Integer> effects;
    private final ArmorTemplate armorTemplate;
    public final int MAX_LEVEL = 30;

    public ArmorEffectTemplate() {
        this.armorTemplate = getArmorTemplate();
        this.effects = getEffects();
    }

    @EventHandler
    public void onArmorWear(PlayerArmorChangeEvent e) {
        Player player = e.getPlayer();
        if (isFullArmorSet(player) && effects != null) {
            effects.forEach((effect, amplifier) -> player.addPotionEffect(new PotionEffect(effect, PotionEffect.INFINITE_DURATION, amplifier)));
        }else {
            if (effects == null) {
                for (PotionEffectType effect : PotionEffectType.values()) {
                    if (player.getActivePotionEffects().contains(new PotionEffect(effect, -1, 0))) {
                        player.removePotionEffect(effect);
                    }
                }
            }else {
                effects.forEach((effect, amplifier) -> player.removePotionEffect(effect));
            }
        }
    }

    @EventHandler
    public void addXPFromAttack(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            addXP(player);
        }
    }

    @EventHandler
    public void addXPOnAttack(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player player = (Player) e.getDamager();
            addXP(player);
        }
    }

    private void addXP(Player player) {
        if (!isFullArmorSet(player)) {
            return;
        }

        Map<String, ItemStack> armor = getArmorFromPlayer(player);

        for (String armorName : armor.keySet()) {
            int level = getLevel(player, armorName);

            if (level >= MAX_LEVEL) {
                return;
            }

            String xpLore = armor.get(armorName).getLore().get(2);
            //xpLore is null when max_level (deletion of Lore)
            if (xpLore == null) {
                return;
            }

            StringBuilder expString = new StringBuilder();
            for (char c : xpLore.toCharArray()) {
                if (Character.isDigit(c)) {
                    expString.append(c);
                }
            }

            int xp = Integer.parseInt(expString.toString());
            List<String> lore = armor.get(armorName).getLore();

            if (xp >= 99) {
                level = level + 1;
                lore.set(1, "Level: " + level);
                lore.set(2, "Exp: 0");
                player.sendMessage(ChatColor.RED + "Ein Rüstungsteil ist ein Level aufgestiegen! " + (level - 1) + " --> " + level);
                if (level >= MAX_LEVEL) {
                    player.sendMessage("");
                    player.sendMessage(ChatColor.GREEN + "Du hast das maximale Level eines Rüstungsteil erreicht!");
                    player.sendMessage("");
                    lore.remove(2);
                }
                if (armorName.equals("helmet")) {
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 0);
                }
            }else {
                lore.set(2, "Exp: " + (xp + 1));
            }
            armor.get(armorName).setLore(lore);
            player.getInventory().setBoots(armor.get("boots"));
            player.getInventory().setLeggings(armor.get("leggings"));
            player.getInventory().setChestplate(armor.get("chestplate"));
            player.getInventory().setHelmet(armor.get("helmet"));
        }
    }

    protected Map<String, ItemStack> getArmorFromPlayer(Player player) {
        Map<String, ItemStack> armor = new HashMap<>();
        armor.put("helmet", player.getInventory().getHelmet());
        armor.put("chestplate", player.getInventory().getChestplate());
        armor.put("leggings", player.getInventory().getLeggings());
        armor.put("boots", player.getInventory().getBoots());
        return armor;
    }

    public int getLevel(Player player, String armorName) {
        Map<String, ItemStack> armor = getArmorFromPlayer(player);

        String lvlLore = armor.get(armorName).getLore().get(1);
        StringBuilder lvlString = new StringBuilder();
        for (char c : lvlLore.toCharArray()) {
            if (Character.isDigit(c)) {
                lvlString.append(c);
            }
        }
        return Integer.parseInt(lvlString.toString());
    }

    public boolean isFullArmorSet(Player player) {

        if (isArmorNull(player)) {
            return false;
        }

        if (isLoreNull(player)) {
            return false;
        }

        return loreContains(player, armorTemplate.getLore());
    }

    private boolean isArmorNull(Player player) {
        PlayerInventory inv = player.getInventory();
        if (inv.getHelmet() == null) {
            return true;
        }
        if (inv.getChestplate() == null) {
            return true;
        }
        if (inv.getLeggings() == null) {
            return true;
        }
        return inv.getBoots() == null;
    }

    private boolean isLoreNull(Player player) {
        PlayerInventory inv = player.getInventory();
        if (inv.getHelmet().getLore() == null) {
            return true;
        }
        if (inv.getChestplate().getLore() == null) {
            return true;
        }
        if (inv.getLeggings().getLore() == null) {
            return true;
        }
        return inv.getBoots().getLore() == null;
    }

    private boolean loreContains(Player player, String lore) {
        PlayerInventory inv = player.getInventory();
        if (!inv.getHelmet().getItemMeta().getLore().contains(lore)) {
            return false;
        }
        if (!inv.getChestplate().getItemMeta().getLore().contains(lore)) {
            return false;
        }
        if (!inv.getLeggings().getItemMeta().getLore().contains(lore)) {
            return false;
        }
        return inv.getBoots().getItemMeta().getLore().contains(lore);
    }

    public abstract Map<PotionEffectType, Integer> getEffects();
    public abstract ArmorTemplate getArmorTemplate();
}
