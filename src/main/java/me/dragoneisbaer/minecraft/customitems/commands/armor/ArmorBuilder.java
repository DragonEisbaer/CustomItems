package me.dragoneisbaer.minecraft.customitems.commands.armor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmorBuilder {

    private HashMap<String, ItemStack> armor;

    public ArmorBuilder() {
        armor = new HashMap<>();
    }

    public void setMeta(ArmorTemplate type) {

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack helmet = new ItemStack(Material.PLAYER_HEAD);

        armor.put("boots", boots);
        armor.put("chestplate", chestplate);
        armor.put("leggings", leggings);
        armor.put("helmet", helmet);

        LeatherArmorMeta bootmeta = (LeatherArmorMeta) boots.getItemMeta();
        LeatherArmorMeta chestplatemeta = (LeatherArmorMeta) chestplate.getItemMeta();
        LeatherArmorMeta leggingsmeta = (LeatherArmorMeta) leggings.getItemMeta();
        SkullMeta helmetmeta = (SkullMeta) helmet.getItemMeta();

        Map<String, LeatherArmorMeta> map = new HashMap<>();
        map.put("chestplate", chestplatemeta);
        map.put("leggings", leggingsmeta);
        map.put("boots", bootmeta);

        helmetmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        map.forEach((name, meta) -> {meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);});
        if (type.getCustomEnchants() != null) {
            map.forEach((name, meta) -> {
                type.getCustomEnchants().forEach((enchant, level )-> {
                    meta.addEnchant(enchant, level, true);
                });
            });
        }

        map.forEach((name, meta) -> {meta.setUnbreakable(true);});
        helmetmeta.setUnbreakable(true);

        map.forEach((name, meta) -> {meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);});
        helmetmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        map.forEach((name, meta) -> {meta.setColor(type.getColor());});

        if (type.getHelmetOwner() != null) {
            helmetmeta.setOwningPlayer(Bukkit.getOfflinePlayer(type.getHelmetOwner()));
        }

        map.forEach((name, meta) -> meta.setDisplayName(type.getName() + " " + name.substring(0, 1).toUpperCase() + name.substring(1)));

        ArrayList<String> armorlore = new ArrayList<>();

        armorlore.add(type.getLore());

        armorlore.add("Level: 1");
        armorlore.add("Exp: 0");

        map.forEach((name, meta) -> meta.setLore(armorlore));

        armor.forEach((name, item) -> {item.setItemMeta(map.get(name));});
    }

    public HashMap<String, ItemStack> getArmor() {
        return armor;
    }

    public void givePlayer(Player player) {
        armor.forEach((name, item) -> player.getInventory().addItem(item));
    }
}
