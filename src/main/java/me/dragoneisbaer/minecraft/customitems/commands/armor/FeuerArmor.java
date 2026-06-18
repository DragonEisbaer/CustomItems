package me.dragoneisbaer.minecraft.customitems.commands.armor;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class FeuerArmor extends ArmorTemplate implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (player.hasPermission("customitems.getfirearmor")) {
                if (args.length == 0) {

                    ArmorBuilder armorbuilder = new ArmorBuilder();
                    armorbuilder.createArmor(this);

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
        return "a2d4521f2c449ea8751474647c2f22139f53a05a786634d2b5612d6a78e2b3fb";
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

    @Override
    public HashMap<Character, RecipeChoice.ExactChoice> getMaterials() {
        HashMap<Character, RecipeChoice.ExactChoice> map = new HashMap<>();
        map.put('B', new RecipeChoice.ExactChoice(new ItemStack(Material.BLAZE_POWDER)));
        map.put('F', new RecipeChoice.ExactChoice(new ItemStack(Material.FIRE_CHARGE)));
        return map;
    }

    @Override
    public HashMap<String, String[]> getRecipeShapes() {
        HashMap<String, String[]> map = new HashMap<>();
        map.put("helmet", new String[] {"BBB", "B B", "   "});
        map.put("chestplate", new String[] {"B B", "BFB", "BBB"});
        map.put("leggings", new String[] {"BBB", "B B", "B B"});
        map.put("boots", new String[] {"   ", "B B", "F F"});
        return map;
    }
}
