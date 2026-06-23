package me.dragoneisbaer.minecraft.customitems;

import me.dragoneisbaer.minecraft.customitems.commands.armor.*;
import me.dragoneisbaer.minecraft.customitems.listener.DisableHeadPlacement;
import me.dragoneisbaer.minecraft.customitems.listener.EmeraldArmorEffekt;
import me.dragoneisbaer.minecraft.customitems.listener.FeuerArmorEffekt;
import me.dragoneisbaer.minecraft.customitems.listener.WasserArmorEffekt;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class CustomItems extends JavaPlugin {

    private static CustomItems instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getLogger().log(Level.INFO, "[CustomItems] started.");
        getCommand("emeraldarmor").setExecutor(new EmeraldArmor());
        getCommand("firearmor").setExecutor(new FeuerArmor());
        getCommand("waterarmor").setExecutor(new WasserArmor());

        ArmorBuilder buildEmerald = new ArmorBuilder(new EmeraldArmor(), true);
        ArmorBuilder buildWater = new ArmorBuilder(new WasserArmor(), true);
        ArmorBuilder buildFire = new ArmorBuilder(new FeuerArmor(), true);

        cleanup();

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

    public static CustomItems getInstance() {
        return instance;
    }

    private void cleanup() {
        System.gc();
    }
}