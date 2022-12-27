package me.lottery.Item;

import me.lottery.Color.color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


public class Item {
    private final Calendar calendar = Calendar.getInstance();
    private final Random r = new Random();
    private final JavaPlugin plugin;
    public Item(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public void giveItem(Player p) {
        int x = r.nextInt(plugin.getConfig().getInt("Max-Number"));
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta im = i.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(color.transalate(plugin.getConfig().getString("Name-Item")));
        lore.add(color.transalate(plugin.getConfig().getString("Message-1") + " " + x + " " + plugin.getConfig().getString("Message-2") + " " + calendar.get(Calendar.DATE) + " "+ calendar.get(Calendar.MONTH) + " "+ calendar.get(Calendar.YEAR)));
        im.setLore(lore);
        i.setItemMeta(im);
        p.getInventory().addItem(i);
    }
    public ItemStack getItemWinner() {
        int x = r.nextInt(plugin.getConfig().getInt("Max-Number"));
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta im = i.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add(color.transalate(plugin.getConfig().getString("Name-Item")));
        lore.add(color.transalate(plugin.getConfig().getString("Message-1") + " " + x + " " + plugin.getConfig().getString("Message-2") + " " + (calendar.get(Calendar.DATE) - 1) + " "+ calendar.get(Calendar.MONTH) + " "+ calendar.get(Calendar.YEAR)));
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }
}
