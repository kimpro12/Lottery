package me.lottery;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {
    private JavaPlugin plugin;
    public Commands(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player");
            return false;
        }
        if (cmd.getName().equalsIgnoreCase("lottery")) {
            if (args[0].equals("mua")) {
                EconomyResponse r = Lottery.getEcon().withdrawPlayer(p.getPlayer(), plugin.getConfig().getInt("Gia-Ban-Ve-So"));
                if (r.transactionSuccess()) {
                    sender.sendMessage(String.format("You were given %s and now have %s", Lottery.getEcon().format(r.amount), Lottery.getEcon().format(r.balance)));
                    new ItemVeSo(plugin).Giveveso(p);
                } else {
                    sender.sendMessage(Color.transalate(plugin.getConfig().getString("Ko-Du-Tien-Mua")));
                }
            }
            /*
            if (args[0].equals("taoveso")) {
                if (p.hasPermission("Lottery.taovesotrungthuong")) {

                } else {
                    p.sendMessage(Color.transalate(plugin.getConfig().getString("Message-2")));
                }
            }*/
        }
        return false;
    }
}
