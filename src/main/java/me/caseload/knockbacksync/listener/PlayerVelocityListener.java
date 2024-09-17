package me.caseload.knockbacksync.listener;

import me.caseload.knockbacksync.KnockbackSync;
import me.caseload.knockbacksync.manager.HitManager;
import me.caseload.knockbacksync.util.PlayerUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

public class PlayerVelocityListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerVelocity(PlayerVelocityEvent event) {
        if (!KnockbackSync.getInstance().getConfig().getBoolean("enabled"))
            return;

        Player victim = event.getPlayer();
        EntityDamageEvent entityDamageEvent = victim.getLastDamageCause();
        if (entityDamageEvent == null)
            return;

        EntityDamageEvent.DamageCause damageCause = entityDamageEvent.getCause();
        if (damageCause != EntityDamageEvent.DamageCause.ENTITY_ATTACK)
            return;

        Entity attackerEntity = ((EntityDamageByEntityEvent) entityDamageEvent).getDamager();
        if (!(attackerEntity instanceof Player attacker))
            return;

        Vector knockback = victim.getVelocity();
        if (victim.isOnGround() || !PlayerUtil.predictiveOnGround(victim, knockback.getY()))
            return;

        final int maxDamageAge = KnockbackSync.getInstance().getConfig().getInt("max_damage_age_milliseconds");
        if (!HitManager.hasRecentHit(victim.getUniqueId(), maxDamageAge))
            return;

        final double modifiedYAxis = PlayerUtil.getModifiedYAxis(victim, attacker);
        final Vector newVelocity = knockback.clone().setY(modifiedYAxis);

        victim.setVelocity(newVelocity);
    }

}