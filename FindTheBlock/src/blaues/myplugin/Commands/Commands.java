/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blaues.myplugin.Commands;

import blaues.myplugin.Event.PvPEvent;
import blaues.myplugin.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 *
 * @author dejo
 */
public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final Player p = (Player) sender;
        if (command.getName().equals("start")) {
            MainClass.setBlocksForPlayer();
            for (Map.Entry<Player, Material> entry : MainClass.getPlayers().entrySet()) {
                entry.getKey().sendMessage("§6Your Block to find: §l" + entry.getValue().name());
            }
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new LoopRunnableClass2(p, 10),11800L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new LoopRunnableClass(p),12010L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new Runnable() {
                @Override
                public void run() {
                    PvPEvent.setPvp(true);
                    Bukkit.getServer().broadcastMessage("§4§l[§0PvP§4]§c is now enabled! Have fun!");
                }
            }, 12015L);
        } else if (command.getName().equals("rlPlayer")){
            Bukkit.getServer().getScheduler().cancelTasks(MainClass.getInstance());
            for (Player ptmp: Bukkit.getOnlinePlayers()) {
                if(ptmp.getGameMode() == GameMode.SURVIVAL) {
                    MainClass.getPlayers().put(ptmp, null);
                }
            }
            Bukkit.getServer().broadcastMessage("§l§6Participating Players: ");
            for (Player pT: MainClass.getPlayers().keySet()){
                Bukkit.broadcastMessage("§6" + pT.getDisplayName() + " ");
            }

        }
        return true;
    }
}
