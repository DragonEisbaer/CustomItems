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
import java.util.HashMap;
import java.util.List;

public class FeuerArmor extends ArmorTemplate implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (player.hasPermission("customitems.getfirearmor")) {
                if (args.length == 0) {

                    ArmorBuilder armorbuilder = new ArmorBuilder();
                    armorbuilder.setMeta(this);

                    armorbuilder.givePlayer(player);
                }
            }else {
                player.sendMessage(ChatColor.DARK_RED + "You don't have the Permission to get the Emerald Armor!");
            }
        }else {
            sender.sendMessage(ChatColor.DARK_RED + "You have to be a Player!");
        }


        return true;
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }

    @Override
    public String getHelmetOwner() {
        return "PrestonPlayz";
    }

    @Override
    public String getName() {
        return "Fire";
    }

    @Override
    public HashMap<Enchantment, Integer> getCustomEnchants() {
        return null;
    }

    @Override
    public String getLore() {
        return "Laufe in und über Feuer!";
    }
}
