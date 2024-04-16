package me.dragoneisbaer.minecraft.customitems.commands.armor;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WasserArmor implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (player.hasPermission("customitems.getwaterarmor")) {
                if (args.length == 0) {
                    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                    ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                    ItemStack helmet = new ItemStack(Material.PLAYER_HEAD);

                    LeatherArmorMeta bootmeta = (LeatherArmorMeta) boots.getItemMeta();
                    LeatherArmorMeta chestplatemeta = (LeatherArmorMeta) chestplate.getItemMeta();
                    LeatherArmorMeta leggingsmeta = (LeatherArmorMeta) leggings.getItemMeta();
                    SkullMeta helmetmeta = (SkullMeta) helmet.getItemMeta();

                    bootmeta.setColor(Color.BLUE);
                    chestplatemeta.setColor(Color.BLUE);
                    leggingsmeta.setColor(Color.BLUE);
                    helmetmeta.setOwner("21Stefage");

                    helmetmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
                    chestplatemeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
                    bootmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
                    leggingsmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);

                    leggingsmeta.addEnchant(Enchantment.LUCK, 1, true);
                    helmetmeta.addEnchant(Enchantment.LUCK, 1, true);
                    chestplatemeta.addEnchant(Enchantment.LUCK, 1, true);
                    bootmeta.addEnchant(Enchantment.LUCK, 1, true);

                    bootmeta.addEnchant(Enchantment.DEPTH_STRIDER, 3, true);

                    chestplatemeta.setUnbreakable(true);
                    helmetmeta.setUnbreakable(true);
                    bootmeta.setUnbreakable(true);
                    leggingsmeta.setUnbreakable(true);

                    leggingsmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    helmetmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    chestplatemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    bootmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

                    bootmeta.setDisplayName(ChatColor.BLUE + "Water Boots");
                    chestplatemeta.setDisplayName(ChatColor.BLUE + "Water Chestplate");
                    leggingsmeta.setDisplayName(ChatColor.BLUE + "Water Leggings");
                    helmetmeta.setDisplayName(ChatColor.BLUE + "Water Helmet");

                    ArrayList<String> armorlore = new ArrayList<>();
                    armorlore.add("Level: 1");
                    armorlore.add("Exp: 0");
                    armorlore.add("Aquaman!");

                    bootmeta.setLore(armorlore);
                    chestplatemeta.setLore(armorlore);
                    leggingsmeta.setLore(armorlore);
                    helmetmeta.setLore(armorlore);

                    boots.setItemMeta(bootmeta);
                    chestplate.setItemMeta(chestplatemeta);
                    leggings.setItemMeta(leggingsmeta);
                    helmet.setItemMeta(helmetmeta);

                    player.getInventory().addItem(helmet);
                    player.getInventory().addItem(chestplate);
                    player.getInventory().addItem(leggings);
                    player.getInventory().addItem(boots);
                }
            }else {
                player.sendMessage(ChatColor.DARK_RED + "You don't have the Permission to get the Emerald Armor!");
            }
        }else {
            sender.sendMessage(ChatColor.DARK_RED + "You have to be a Player!");
        }


        return true;
    }
}
