package blaues.myplugin.Commands;

import blaues.myplugin.MainClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LoopRunnableClass2 implements Runnable {
    private Player p;
    private int count;

    public LoopRunnableClass2(Player p, int count) {
        this.p = p;
        this.count = count;
    }

    @Override
    public void run() {
        Bukkit.getServer().broadcastMessage("§6§l" + count);
        count--;
        if(count>=0) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MainClass.getInstance(), new LoopRunnableClass2(p, count), 20L);
        }
    }
}
