/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blaues.myplugin;

import blaues.myplugin.Commands.Commands;
import blaues.myplugin.Event.PvPEvent;
import blaues.myplugin.Event.SetCompassEvent;
import blaues.myplugin.Event.StepOnBlock;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 *
 * @author blauesbild
 */
public class MainClass extends JavaPlugin {

    public static MainClass instance;

    private static Map<Player, Material> players = new HashMap<>();

    public static Map<Player, Material> getPlayers() {
        return players;
    }

    public static void setBlocksForPlayer() {
        List<Material> mats = new ArrayList<>();
        for (Material m: Material.values()) {
            if(m.isBlock() & m.isSolid() & m.isItem()){
                mats.add(m);
            }
        }
        System.out.println(mats.size());
        Random rd = new Random();
        for (Map.Entry<Player, Material> entry : players.entrySet()) {
            entry.setValue(mats.get(rd.nextInt(mats.size()-1)));
        }
    }

    @Override
    public void onEnable() {
        instance = this;
        for (Player p: Bukkit.getOnlinePlayers()) {
            if(p.getGameMode() == GameMode.SURVIVAL)
                players.put(p, null);
        }
        for (Player p: players.keySet()) {
            p.sendMessage("§l§6Participating Players: ");
            for (Player pT: players.keySet()){
                p.sendMessage("§6" + pT.getDisplayName() + " ");
            }
        }
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new StepOnBlock(), this);
        pm.registerEvents(new PvPEvent(), this);
        pm.registerEvents(new SetCompassEvent(), this);
        this.getCommand("start").setExecutor(new Commands());
        this.getCommand("rlPlayer").setExecutor(new Commands());
        System.out.println("Enabling this plugin works.");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    
    @Override
    public void onDisable() {
        System.out.println("Disabling this plugin works.");
    }



    public static MainClass getInstance() {
        return instance;
    }

}
