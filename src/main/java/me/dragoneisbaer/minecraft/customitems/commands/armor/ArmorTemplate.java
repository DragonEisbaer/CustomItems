package me.dragoneisbaer.minecraft.customitems.commands.armor;

import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.RecipeChoice;

import java.util.HashMap;

public abstract class ArmorTemplate {

    public abstract Color getColor();
    public abstract String getHelmetOwner();
    public abstract String getName();
    public abstract HashMap<Enchantment, Integer> getCustomEnchants();
    public abstract String getLore();

    /**
     * Returns the material for the armor
     * String stands for the material name which is later used in the recipe
     * ExactChoice stands for the material
     * @return a map which contains all ingredients with the material name as key
     */
    public abstract HashMap<Character, RecipeChoice.ExactChoice> getMaterials();

    /**
     * Returns the recipe shapes for the armor
     * First String is the name of the armor piece
     * Second String is the shape of the armor piece
     * Index 0 = First Row
     * Index 1 = Second Row
     * Index 2 = Third Row
     * @return a map which contains all armor pieces with their shape as key
     */
    public abstract HashMap<String, String[]> getRecipeShapes();
}
