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
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class WasserArmor extends ArmorTemplate implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            if (player.hasPermission("customitems.getwaterarmor")) {
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
        return Color.BLUE;
    }

    @Override
    public String getHelmetOwner() {
        return "33581684ab415141959daa10539ae6ff0526246dc03d5e1d74479b0325516e5c";
    }

    @Override
    public String getName() {
        return "Water";
    }

    @Override
    public HashMap<Enchantment, Integer> getCustomEnchants() {
        return new HashMap<Enchantment, Integer>(){{put(Enchantment.DEPTH_STRIDER, 3);}};
    }

    @Override
    public String getLore() {
        return "Aquaman!";
    }

    @Override
    public HashMap<Character, RecipeChoice.ExactChoice> getMaterials() {
        HashMap<Character, RecipeChoice.ExactChoice> map = new HashMap<>();

        ItemStack bottle = new ItemStack(Material.POTION, 1);
        PotionMeta pmeta = (PotionMeta) bottle.getItemMeta();
        pmeta.setBasePotionType(PotionType.WATER);
        bottle.setItemMeta(pmeta);

        map.put('W', new RecipeChoice.ExactChoice(bottle));
        map.put('H', new RecipeChoice.ExactChoice(new ItemStack(Material.HEART_OF_THE_SEA)));

        return map;
    }

    @Override
    public HashMap<String, String[]> getRecipeShapes() {
        HashMap<String, String[]> map = new HashMap<>();

        map.put("helmet", new String[] {"WWW", "W W", "   "});
        map.put("chestplate", new String[] {"W W", "WHW", "WWW"});
        map.put("leggings", new String[] {"WWW", "W W", "W W"});
        map.put("boots", new String[] {"   ", "W W", "H H"});

        return map;
    }
}
