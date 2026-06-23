package me.dragoneisbaer.minecraft.customitems.listener;

import me.dragoneisbaer.minecraft.customitems.commands.armor.ArmorTemplate;
import me.dragoneisbaer.minecraft.customitems.commands.armor.EmeraldArmor;

import org.bukkit.event.Listener;

import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class EmeraldArmorEffekt extends ArmorEffectTemplate implements Listener {

    @Override
    public Map<PotionEffectType, Integer> getEffects() {
        return new HashMap<PotionEffectType, Integer>(){{put(PotionEffectType.HERO_OF_THE_VILLAGE, 4);}};
    }

    @Override
    public ArmorTemplate getArmorTemplate() {
        return new EmeraldArmor();
    }

}