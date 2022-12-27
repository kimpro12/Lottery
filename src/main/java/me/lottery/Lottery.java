package me.lottery;

import me.lottery.Check.CheckWinner;
import me.lottery.Command.Command;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Lottery extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        Bukkit.getConsoleSender().sendMessage("[Lottery] Plugin enabled");
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getHour() < 0 || getMinute() < 0) {
            Bukkit.getConsoleSender().sendMessage("[Lottery] Plugin disabled due to Hour and Minute in config file is a negative number");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        new Command(this);
        new CheckWinner(this).Check();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[Lottery] Plugin enabled");
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public static Economy getEconomy() {
        return econ;
    }
    public Integer getHour() {
        return getConfig().getInt("Time-Check-Trung-Thuong.Hour");
    }
    public Integer getMinute() {
        return getConfig().getInt("Time-Check-Trung-Thuong.Minute");
    }
}
