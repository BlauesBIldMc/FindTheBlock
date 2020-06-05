package blaues.myplugin.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PvPEvent implements Listener {

    private static boolean pvp = false;

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if(!pvp) {
            if (event.getEntity() instanceof Player && event.getDamager() instanceof Player){
                event.setCancelled(true);
            }
        }
    }

    public static boolean isPvp() {
        return pvp;
    }

    public static void setPvp(boolean pvp) {
        PvPEvent.pvp = pvp;
    }
}
