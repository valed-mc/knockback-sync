package me.caseload.knockbacksync.listener;

import me.caseload.knockbacksync.KnockbackSync;
import me.caseload.knockbacksync.manager.KnockbackManager;
import me.caseload.knockbacksync.manager.PingManager;
import me.caseload.knockbacksync.util.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!KnockbackSync.getInstance().getConfig().getBoolean("enabled") || !(event.getEntity() instanceof Player victim) || !(event.getDamager() instanceof Player attacker))
            return;

        Double modifiedYAxis = PlayerUtil.getModifiedYAxis(victim, attacker);
        KnockbackManager.getKnockbackMap().put(victim.getUniqueId(), modifiedYAxis);

        // PingManager.sendPacket(victim);
    }

}