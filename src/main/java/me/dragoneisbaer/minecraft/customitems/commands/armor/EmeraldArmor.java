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

public class EmeraldArmor extends ArmorTemplate implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (player.hasPermission("customitems.getemeraldarmor")) {
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
        return Color.GREEN;
    }

    @Override
    public String getHelmetOwner() {
        return null;
    }

    @Override
    public String getName() {
        return "Emerald";
    }

    @Override
    public HashMap<Enchantment, Integer> getCustomEnchants() {
        return null;
    }

    @Override
    public String getLore() {
        return "Du wirst zum Sparfuchs!";
    }

    @Override
    public HashMap<Character, RecipeChoice.ExactChoice> getMaterials() {
        HashMap<Character, RecipeChoice.ExactChoice> map = new HashMap<>();
        map.put('E', new RecipeChoice.ExactChoice(new ItemStack(Material.EMERALD)));
        return map;
    }

    @Override
    public HashMap<String, String[]> getRecipeShapes() {
        HashMap<String, String[]> map = new HashMap<>();
        map.put("helmet", new String[] {"EEE", "E E", "   "});
        map.put("chestplate", new String[] {"E E", "EEE", "EEE"});
        map.put("leggings", new String[] {"EEE", "E E", "E E"});
        map.put("boots", new String[] {"   ", "E E", "E E"});
        return map;
    }
}