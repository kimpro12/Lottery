package me.lottery;

import org.bukkit.ChatColor;

public class Color {
    public static String transalate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
