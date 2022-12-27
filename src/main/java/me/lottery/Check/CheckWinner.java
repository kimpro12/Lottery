package me.lottery.Check;

import me.lottery.Color.color;
import me.lottery.Item.Item;
import me.lottery.Lottery;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Calendar;

public class CheckWinner {
    private final JavaPlugin plugin;
    private final Calendar calendar = Calendar.getInstance();
    public CheckWinner(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public void Check() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Bukkit.getScheduler().runTaskTimer(plugin, new BukkitRunnable() {
                @Override
                public void run() {
                    if (calendar.getTime().getHours() == getHour() && calendar.getTime().getMinutes() == plugin.getConfig().getInt("Time-Check-Trung-Thuong.Minute")) {
                        ItemStack[] items = p.getInventory().getContents();
                        for (ItemStack i : items) {
                            if (i.getItemMeta().getLore() == new Item(plugin).getItemWinner().getItemMeta().getLore()) {
                                Lottery.getEconomy().depositPlayer(p, plugin.getConfig().getInt("Tien-Trung-Thuong"));
                                Bukkit.broadcastMessage(color.transalate("Player " + p.getName() + " is the winner"));
                            }
                        }
                    }
                }
            }, 0, 20);
        }
    }
    public Integer getHour() {
        if (plugin.getConfig().getInt("Time-Check-Trung-Thuong.Hour") < 7) {
            return (plugin.getConfig().getInt("Time-Check-Trung-Thuong.Hour")) + 17;
        }
        return (plugin.getConfig().getInt("Time-Check-Trung-Thuong.Hour")) - 7;
    }
}
