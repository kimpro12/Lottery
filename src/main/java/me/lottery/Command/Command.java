package me.lottery.Command;

import me.lottery.Color.color;
import me.lottery.Item.Item;
import me.lottery.Lottery;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Command implements CommandExecutor {
    private final JavaPlugin plugin;
    private final Economy eco = Lottery.getEconomy();

    public Command(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand("lottery").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if  (!(sender instanceof Player) && args.length != 0 && !(args[0].equalsIgnoreCase("reload"))) {
            sender.sendMessage("Only player can run this command");
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage("Reload config successfully");
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.AQUA + "Plugin made by FIP");
            sender.sendMessage(ChatColor.AQUA + "lottery help to see list of command");
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("lottery")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("buy")) {
                        EconomyResponse r = eco.withdrawPlayer(player, plugin.getConfig().getInt("Gia-Tien-Ve-So"));
                        if (r.transactionSuccess()) {
                            player.sendMessage(color.transalate(plugin.getConfig().getString("Message-3") + " " + plugin.getConfig().getInt("Gia-Tien-Ve-So")));
                            player.sendMessage(color.transalate(plugin.getConfig().getString("Message-4") + " " + eco.getBalance(player)));
                            new Item(plugin).giveItem(player);
                        } else {
                            player.sendMessage(color.transalate(plugin.getConfig().getString("Message-5")));
                        }
                    }
                    if (args[0].equalsIgnoreCase("help")) {
                        player.sendMessage(ChatColor.AQUA + "/lottery buy to buy a lottery ticket");
                    }
                }
            }
        }
        return true;
    }
}
