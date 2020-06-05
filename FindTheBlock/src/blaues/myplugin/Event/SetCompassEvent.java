package blaues.myplugin.Event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SetCompassEvent implements Listener {
    @EventHandler
    public void handlePlayerMovement(PlayerMoveEvent ev){
        Player p = ev.getPlayer();
        Player p2 = getNearestPlayer(p);
        if(p2 != null)
            p.setCompassTarget(p2.getLocation());
    }

    public Player getNearestPlayer(Player player) {
        double distNear = 0.0D;
        Player playerNear = null;
        for (Player player2 : player.getWorld().getPlayers()) {
            //don't include the player that's checking
            if (player == player2) { continue; }

            Location location2 = player2.getLocation();
            Location location = player.getLocation();
            double dist = location.distance(location2);
            if (playerNear == null || dist < distNear) {
                playerNear = player2;
                distNear = dist;
            }
            System.out.println(distNear);
        }
        return playerNear;
    }
}
