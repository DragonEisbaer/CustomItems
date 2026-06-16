package me.dragoneisbaer.minecraft.customitems.commands.armor;

import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;

public abstract class ArmorTemplate {

    public abstract Color getColor();
    public abstract String getHelmetOwner();
    public abstract String getName();
    public abstract HashMap<Enchantment, Integer> getCustomEnchants();
    public abstract String getLore();
}
