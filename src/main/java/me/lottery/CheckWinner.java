package me.lottery;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckWinner {
    private int axit;
    private JavaPlugin plugin;
    public CheckWinner(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public Player getPlayerWinner() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getInventory().contains(new ItemVeSo(plugin).Itemwinner())) {
                return p;
            }
        }
        return null;
    }
    public void run() {
        axit = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat formatter = new SimpleDateFormat("HH");
                SimpleDateFormat formatter1 = new SimpleDateFormat("mm");
                Date date = new Date();
                String k = formatter.format(date);
                String k1 = formatter.format(date);
                if (plugin.getConfig().getString("Time-Check-Player-Winner.hour").equals(k) && plugin.getConfig().getString("Time-Check-Player-Winner.minute").equals(k1)) {
                    if (getPlayerWinner() != null) {
                        Bukkit.broadcastMessage(getPlayerWinner() + " is the winner lottery today");
                    }
                }
            }
        }, 0, 20);
    }
}
