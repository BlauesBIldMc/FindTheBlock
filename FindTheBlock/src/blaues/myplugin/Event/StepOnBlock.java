package blaues.myplugin.Event;

import blaues.myplugin.MainClass;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class StepOnBlock implements Listener {

    @EventHandler
    public void handleStepOnBlock(PlayerMoveEvent ev){
        Player p = ev.getPlayer();
        if(MainClass.getPlayers().containsKey(p)) {
            if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(MainClass.getPlayers().get(p))) {
                for (Player pMsg: MainClass.getPlayers().keySet()) {
                    pMsg.sendMessage("§4§l"+p.getName()+"§r§c found his Block!");
                }
                MainClass.getPlayers().put(p,null);
                System.out.println(MainClass.getPlayers());
            }
        }
    }
}
