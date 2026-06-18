package me.dragoneisbaer.minecraft.customitems.commands.armor;

import me.dragoneisbaer.minecraft.customitems.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ArmorBuilder {

    private HashMap<String, ItemStack> armor;

    public ArmorBuilder() {
        armor = new HashMap<>();
    }

    /**
     * Creates a new armor with the given template
     * adds it to Bukkit and creates a recipe for it
     * ONLY USE FOR REGISTERING IN CUSTOMITEMS.JAVA
     * @param template template to create the armor from
     * @param addRecipe if true the armor will be added to Bukkit and a recipe will be created
     */
    public ArmorBuilder(ArmorTemplate template, boolean addRecipe) {
        this();
        if (addRecipe) {
            createArmor(template);
            addToBukkitRecipe(template);
        }
    }

    public void createArmor(ArmorTemplate type) {
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

    private void addToBukkitRecipe(ArmorTemplate type) {
        CustomItems plugin = CustomItems.getInstance();
        String name = type.getName().toLowerCase();

        NamespacedKey helmetkey = new NamespacedKey(plugin, name + "_helmet");
        NamespacedKey chestplatekey = new NamespacedKey(plugin, name + "_chestplate");
        NamespacedKey leggingskey = new NamespacedKey(plugin, name + "_leggings");
        NamespacedKey bootskey = new NamespacedKey(plugin, name + "_boots");

        ShapedRecipe helmet = new ShapedRecipe(helmetkey, armor.get("helmet"));
        ShapedRecipe chestplate = new ShapedRecipe(chestplatekey, armor.get("chestplate"));
        ShapedRecipe leggings = new ShapedRecipe(leggingskey, armor.get("leggings"));
        ShapedRecipe boots = new ShapedRecipe(bootskey, armor.get("boots"));

        String[] shapeHelmet = type.getRecipeShapes().get("helmet");
        String[] shapeChestplate = type.getRecipeShapes().get("chestplate");
        String[] shapeLeggings = type.getRecipeShapes().get("leggings");
        String[] shapeBoots = type.getRecipeShapes().get("boots");

        helmet.shape(shapeHelmet[0], shapeHelmet[1], shapeHelmet[2]);
        chestplate.shape(shapeChestplate[0], shapeChestplate[1], shapeChestplate[2]);
        leggings.shape(shapeLeggings[0], shapeLeggings[1], shapeLeggings[2]);
        boots.shape(shapeBoots[0], shapeBoots[1], shapeBoots[2]);

        for (Map.Entry<Character, RecipeChoice.ExactChoice> entry : type.getMaterials().entrySet()) {
            char key = entry.getKey();
            RecipeChoice.ExactChoice choice = entry.getValue();

            if (shapeContains(shapeHelmet, key)) helmet.setIngredient(key, choice);
            if (shapeContains(shapeChestplate, key)) chestplate.setIngredient(key, choice);
            if (shapeContains(shapeLeggings, key)) leggings.setIngredient(key, choice);
            if (shapeContains(shapeBoots, key)) boots.setIngredient(key, choice);
        }

        Bukkit.addRecipe(helmet);
        Bukkit.addRecipe(chestplate);
        Bukkit.addRecipe(leggings);
        Bukkit.addRecipe(boots);
    }

    /**
     * Checks if a given shape contains a given symbol
     * Prevents the ingredient not in shape error
     * @param shape the shape to check
     * @param symbol Key to check
     * @return true if the symbol is in the shape, false if not
     */
    private boolean shapeContains(String[] shape, char symbol) {
    for (String row : shape) {
        if (row.indexOf(symbol) >= 0) return true;
    }
    return false;
}
}
