package me.dragoneisbaer.minecraft.customitems;

import me.dragoneisbaer.minecraft.customitems.commands.armor.EmeraldArmor;
import me.dragoneisbaer.minecraft.customitems.commands.armor.FeuerArmor;
import me.dragoneisbaer.minecraft.customitems.commands.armor.WasserArmor;
import me.dragoneisbaer.minecraft.customitems.listener.DisableHeadPlacement;
import me.dragoneisbaer.minecraft.customitems.listener.EmeraldArmorEffekt;
import me.dragoneisbaer.minecraft.customitems.listener.FeuerArmorEffekt;
import me.dragoneisbaer.minecraft.customitems.listener.WasserArmorEffekt;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.logging.Level;

public final class CustomItems extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().log(Level.INFO, "[CustomItems] started.");
        getCommand("emeraldarmor").setExecutor(new EmeraldArmor());
        getCommand("firearmor").setExecutor(new FeuerArmor());
        getCommand("waterarmor").setExecutor(new WasserArmor());
        loadEmeraldArmor();
        loadFireArmor();
        loadWaterArmor();
        loadLightArmor();
        getServer().getPluginManager().registerEvents(new EmeraldArmorEffekt(), this);
        getServer().getPluginManager().registerEvents(new FeuerArmorEffekt(), this);
        getServer().getPluginManager().registerEvents(new WasserArmorEffekt(), this);
        getServer().getPluginManager().registerEvents(new DisableHeadPlacement(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().log(Level.INFO, "[CustomItems] stopped.");
    }

    public void loadEmeraldArmor() {

        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);

        LeatherArmorMeta bootmeta = (LeatherArmorMeta) boots.getItemMeta();
        LeatherArmorMeta chestplatemeta = (LeatherArmorMeta) chestplate.getItemMeta();
        LeatherArmorMeta leggingsmeta = (LeatherArmorMeta) leggings.getItemMeta();
        LeatherArmorMeta helmetmeta = (LeatherArmorMeta) helmet.getItemMeta();

        bootmeta.setColor(Color.GREEN);
        chestplatemeta.setColor(Color.GREEN);
        leggingsmeta.setColor(Color.GREEN);
        helmetmeta.setColor(Color.GREEN);

        helmetmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        chestplatemeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        bootmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        leggingsmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);

        chestplatemeta.setUnbreakable(true);
        helmetmeta.setUnbreakable(true);
        bootmeta.setUnbreakable(true);
        leggingsmeta.setUnbreakable(true);

        leggingsmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        helmetmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        chestplatemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bootmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        bootmeta.setDisplayName(ChatColor.GREEN + "Emerald Boots");
        chestplatemeta.setDisplayName(ChatColor.GREEN + "Emerald Chestplate");
        leggingsmeta.setDisplayName(ChatColor.GREEN + "Emerald Leggings");
        helmetmeta.setDisplayName(ChatColor.GREEN + "Emerald Helmet");

        ArrayList<String> armorlore = new ArrayList<>();
        armorlore.add("Du wirst zum Sparfuchs!");
        armorlore.add("Level: 1");
        armorlore.add("Exp: 0");

        bootmeta.setLore(armorlore);
        chestplatemeta.setLore(armorlore);
        leggingsmeta.setLore(armorlore);
        helmetmeta.setLore(armorlore);

        boots.setItemMeta(bootmeta);
        chestplate.setItemMeta(chestplatemeta);
        leggings.setItemMeta(leggingsmeta);
        helmet.setItemMeta(helmetmeta);

        NamespacedKey helmetkey = new NamespacedKey(this, "emerald_helmet");
        NamespacedKey chestplatekey = new NamespacedKey(this, "emerald_chestplate");
        NamespacedKey leggingskey = new NamespacedKey(this, "emerald_leggings");
        NamespacedKey bootskey = new NamespacedKey(this, "emerald_boots");

        ShapedRecipe emeraldHelmet = new ShapedRecipe(helmetkey, helmet);
        ShapedRecipe emeraldchestplate = new ShapedRecipe(chestplatekey, chestplate);
        ShapedRecipe emeraldleggings = new ShapedRecipe(leggingskey, leggings);
        ShapedRecipe emeraldboots = new ShapedRecipe(bootskey, boots);

        emeraldHelmet.shape("EEE", "E E", "   ");
        emeraldchestplate.shape("E E", "EEE", "EEE");
        emeraldleggings.shape("EEE", "E E", "E E");
        emeraldboots.shape("   ", "E E", "E E");

        emeraldHelmet.setIngredient('E', Material.EMERALD);

        emeraldchestplate.setIngredient('E', Material.EMERALD);

        emeraldleggings.setIngredient('E', Material.EMERALD);

        emeraldboots.setIngredient('E', Material.EMERALD);

        Bukkit.addRecipe(emeraldHelmet);
        Bukkit.addRecipe(emeraldchestplate);
        Bukkit.addRecipe(emeraldleggings);
        Bukkit.addRecipe(emeraldboots);
    }
    public void loadFireArmor(){
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack helmet = new ItemStack(Material.PLAYER_HEAD);

        LeatherArmorMeta bootmeta = (LeatherArmorMeta) boots.getItemMeta();
        LeatherArmorMeta chestplatemeta = (LeatherArmorMeta) chestplate.getItemMeta();
        LeatherArmorMeta leggingsmeta = (LeatherArmorMeta) leggings.getItemMeta();
        SkullMeta helmetmeta = (SkullMeta) helmet.getItemMeta();

        bootmeta.setColor(Color.RED);
        chestplatemeta.setColor(Color.RED);
        leggingsmeta.setColor(Color.RED);
        helmetmeta.setOwner("PrestonPlayz");

        helmetmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        chestplatemeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        bootmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        leggingsmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);

        chestplatemeta.setUnbreakable(true);
        helmetmeta.setUnbreakable(true);
        bootmeta.setUnbreakable(true);
        leggingsmeta.setUnbreakable(true);

        leggingsmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        helmetmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        chestplatemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bootmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        bootmeta.setDisplayName(ChatColor.RED + "Fire Boots");
        chestplatemeta.setDisplayName(ChatColor.RED + "Fire Chestplate");
        leggingsmeta.setDisplayName(ChatColor.RED + "Fire Leggings");
        helmetmeta.setDisplayName(ChatColor.RED + "Fire Helmet");

        ArrayList<String> armorlore = new ArrayList<>();
        armorlore.add("Laufe in und über Feuer!");
        armorlore.add(0,"Level: 1");
        armorlore.add(1, "Exp: 0");

        bootmeta.setLore(armorlore);
        chestplatemeta.setLore(armorlore);
        leggingsmeta.setLore(armorlore);
        helmetmeta.setLore(armorlore);

        boots.setItemMeta(bootmeta);
        chestplate.setItemMeta(chestplatemeta);
        leggings.setItemMeta(leggingsmeta);
        helmet.setItemMeta(helmetmeta);

        NamespacedKey helmetkey = new NamespacedKey(this, "fire_helmet");
        NamespacedKey chestplatekey = new NamespacedKey(this, "fire_chestplate");
        NamespacedKey leggingskey = new NamespacedKey(this, "fire_leggings");
        NamespacedKey bootskey = new NamespacedKey(this, "fire_boots");

        // Create our custom recipe variable
        ShapedRecipe firehelmet = new ShapedRecipe(helmetkey, helmet);
        ShapedRecipe firechestplate = new ShapedRecipe(chestplatekey, chestplate);
        ShapedRecipe fireleggings = new ShapedRecipe(leggingskey, leggings);
        ShapedRecipe fireboots = new ShapedRecipe(bootskey, boots);

        firehelmet.shape("BBB", "B B", "   ");
        firechestplate.shape("B B", "BFB", "BBB");
        fireleggings.shape("BBB", "B B", "B B");
        fireboots.shape("   ", "B B", "F F");

        firehelmet.setIngredient('B', Material.BLAZE_POWDER);

        firechestplate.setIngredient('B', Material.BLAZE_POWDER);
        firechestplate.setIngredient('F', Material.FIRE_CHARGE);

        fireleggings.setIngredient('B', Material.BLAZE_POWDER);

        fireboots.setIngredient('B', Material.BLAZE_POWDER);
        fireboots.setIngredient('F', Material.FIRE_CHARGE);

        Bukkit.addRecipe(firehelmet);
        Bukkit.addRecipe(firechestplate);
        Bukkit.addRecipe(fireleggings);
        Bukkit.addRecipe(fireboots);
    }
    public void loadWaterArmor() {
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
        armorlore.add("Aquaman!");
        armorlore.add("Level: 1");
        armorlore.add("Exp: 0");

        bootmeta.setLore(armorlore);
        chestplatemeta.setLore(armorlore);
        leggingsmeta.setLore(armorlore);
        helmetmeta.setLore(armorlore);

        boots.setItemMeta(bootmeta);
        chestplate.setItemMeta(chestplatemeta);
        leggings.setItemMeta(leggingsmeta);
        helmet.setItemMeta(helmetmeta);

        NamespacedKey helmetkey = new NamespacedKey(this, "water_helmet");
        NamespacedKey chestplatekey = new NamespacedKey(this, "water_chestplate");
        NamespacedKey leggingskey = new NamespacedKey(this, "water_leggings");
        NamespacedKey bootskey = new NamespacedKey(this, "water_boots");

        // Create our custom recipe variable
        ShapedRecipe waterhelmet = new ShapedRecipe(helmetkey, helmet);
        ShapedRecipe waterchestplate = new ShapedRecipe(chestplatekey, chestplate);
        ShapedRecipe waterleggings = new ShapedRecipe(leggingskey, leggings);
        ShapedRecipe waterboots = new ShapedRecipe(bootskey, boots);

        ItemStack bottle = new ItemStack(Material.POTION, 1);
        ItemMeta meta = bottle.getItemMeta();
        PotionMeta pmeta = (PotionMeta) meta;
        PotionData pdata = new PotionData(PotionType.WATER);
        pmeta.setBasePotionData(pdata);
        bottle.setItemMeta(meta);

        waterhelmet.shape("WWW", "W W", "   ");
        waterchestplate.shape("W W", "WHW", "WWW");
        waterleggings.shape("WWW", "W W", "W W");
        waterboots.shape("   ", "W W", "H H");

        waterhelmet.setIngredient('W', bottle);

        waterchestplate.setIngredient('W', bottle);
        waterchestplate.setIngredient('H', Material.HEART_OF_THE_SEA);

        waterleggings.setIngredient('W', bottle);

        waterboots.setIngredient('W', bottle);
        waterboots.setIngredient('H', Material.HEART_OF_THE_SEA);

        Bukkit.addRecipe(waterhelmet);
        Bukkit.addRecipe(waterchestplate);
        Bukkit.addRecipe(waterleggings);
        Bukkit.addRecipe(waterboots);
    }
    public void loadLightArmor() {
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
        armorlore.add("Aquaman!");
        armorlore.add("Level: 1");
        armorlore.add("Exp: 0");

        bootmeta.setLore(armorlore);
        chestplatemeta.setLore(armorlore);
        leggingsmeta.setLore(armorlore);
        helmetmeta.setLore(armorlore);

        boots.setItemMeta(bootmeta);
        chestplate.setItemMeta(chestplatemeta);
        leggings.setItemMeta(leggingsmeta);
        helmet.setItemMeta(helmetmeta);

        NamespacedKey helmetkey = new NamespacedKey(this, "water_helmet");
        NamespacedKey chestplatekey = new NamespacedKey(this, "water_chestplate");
        NamespacedKey leggingskey = new NamespacedKey(this, "water_leggings");
        NamespacedKey bootskey = new NamespacedKey(this, "water_boots");

        // Create our custom recipe variable
        ShapedRecipe waterhelmet = new ShapedRecipe(helmetkey, helmet);
        ShapedRecipe waterchestplate = new ShapedRecipe(chestplatekey, chestplate);
        ShapedRecipe waterleggings = new ShapedRecipe(leggingskey, leggings);
        ShapedRecipe waterboots = new ShapedRecipe(bootskey, boots);

        ItemStack bottle = new ItemStack(Material.POTION, 1);
        ItemMeta meta = bottle.getItemMeta();
        PotionMeta pmeta = (PotionMeta) meta;
        PotionData pdata = new PotionData(PotionType.WATER);
        pmeta.setBasePotionData(pdata);
        bottle.setItemMeta(meta);

        waterhelmet.shape("WWW", "W W", "   ");
        waterchestplate.shape("W W", "WHW", "WWW");
        waterleggings.shape("WWW", "W W", "W W");
        waterboots.shape("   ", "W W", "H H");

        waterhelmet.setIngredient('W', bottle);

        waterchestplate.setIngredient('W', bottle);
        waterchestplate.setIngredient('H', Material.HEART_OF_THE_SEA);

        waterleggings.setIngredient('W', bottle);

        waterboots.setIngredient('W', bottle);
        waterboots.setIngredient('H', Material.HEART_OF_THE_SEA);

        Bukkit.addRecipe(waterhelmet);
        Bukkit.addRecipe(waterchestplate);
        Bukkit.addRecipe(waterleggings);
        Bukkit.addRecipe(waterboots);
    }
}