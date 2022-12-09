package me.lottery;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ItemVeSo {
    private JavaPlugin plugin;
    public ItemVeSo(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    int min = plugin.getConfig().getInt("Min");
    int max = plugin.getConfig().getInt("Max");
    int random = (int)Math.floor(Math.random()*(max-min+1)+min);
    public String getHour() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        Date date = new Date();
        return formatter.format(date);
    }
    public String getMinute() {
        SimpleDateFormat formatter = new SimpleDateFormat("mm");
        Date date = new Date();
        return formatter.format(date);
    }
    public void Giveveso(Player p) {
        ItemStack soxo = new ItemStack(Material.PAPER);
        ItemMeta meta = soxo.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(Color.transalate(plugin.getConfig().getString("Message-1") + " " + random + " " + getHour() + ":" + getMinute()));
        meta.setLore(lore);
        soxo.setItemMeta(meta);
        p.getInventory().addItem(soxo);
    }
    public ItemStack Itemwinner() {
        ItemStack soxo1 = new ItemStack(Material.PAPER);
        ItemMeta meta = soxo1.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(Color.transalate(plugin.getConfig().getString("Message-1") + " " + random + " " + getHour() + ":" + getMinute()));
        meta.setLore(lore);
        soxo1.setItemMeta(meta);
        return soxo1;
    }

    public int getMin() {
        return min;
    }

    public int getRandom() {
        return random;
    }
}
