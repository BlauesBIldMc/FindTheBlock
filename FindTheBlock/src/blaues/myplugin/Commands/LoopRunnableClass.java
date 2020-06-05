package blaues.myplugin.Commands;

import blaues.myplugin.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;

public class LoopRunnableClass implements Runnable {
    private Player p;

    public LoopRunnableClass(Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        for (Map.Entry<Player, Material> entry: MainClass.getPlayers().entrySet()){
            if(entry.getValue() != null){
                System.out.println("Player: " + entry.getKey() + " has " + entry.getValue());
                MainClass.getPlayers().remove(entry.getKey());
                entry.getKey().setGameMode(GameMode.SPECTATOR);
                for (Player ptmp : Bukkit.getServer().getOnlinePlayers()) {
                    ptmp.sendMessage("§6Player §4§l" + p.getName() + " §r§6 lost!");
                } }
        }
        if(!MainClass.getPlayers().isEmpty() && MainClass.getPlayers().size() >= 1){
            MainClass.setBlocksForPlayer();
            for (Map.Entry<Player, Material> entry : MainClass.getPlayers().entrySet()) {
                entry.getKey().sendMessage("§6Your Block to find: §l" + entry.getValue().name());
            }
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new LoopRunnableClass2(p, 10), 11800L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new LoopRunnableClass(p),12010L);
        } else if(MainClass.getPlayers().size() == 1){
            Bukkit.getServer().broadcastMessage("§6Your Winner is: §l§n§4" + MainClass.getPlayers().keySet().stream().findFirst().get().getName());
        } else {
            Bukkit.getServer().broadcastMessage("§6No Winner this round!");
        }
    }
}
