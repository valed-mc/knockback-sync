package me.caseload.knockbacksync.listener;

import me.caseload.knockbacksync.KnockbackSync;
import me.caseload.knockbacksync.manager.KnockbackManager;
import me.caseload.knockbacksync.manager.PingManager;
import me.caseload.knockbacksync.util.MathUtil;
import me.caseload.knockbacksync.util.PlayerUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

import java.util.Optional;
import java.util.UUID;

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
        if (!(attackerEntity instanceof Player))
            return;

        final int maxDamageAge = KnockbackSync.getInstance().getConfig().getInt("max_damage_age_milliseconds");
        final Optional<Double> modifiedYAxis = KnockbackManager.modifiedYAxis(victim.getUniqueId(), maxDamageAge);
        if (modifiedYAxis.isEmpty())
            return;

        final Vector knockback = victim.getVelocity();

        if (PlayerUtil.predictiveOnGround(victim, knockback.getY())) {
            handleOnGround(victim, knockback, modifiedYAxis.get());
        } else if (KnockbackSync.getInstance().getConfig().getBoolean("toggle_offground")) {
            long ping = PingManager.getPingMap().getOrDefault(victim.getUniqueId(), (long) victim.getPing());
            handleOffGround(victim, knockback, (int) ping);
        }
    }

    private void handleOnGround(Player victim, Vector knockback, double modifiedYAxis) {
        final Vector newVelocity = knockback.clone().setY(modifiedYAxis);

        victim.setVelocity(newVelocity);
    }

    private void handleOffGround(Player victim, Vector knockback, int ping) {
        final Vector newVelocity = knockback.clone().setY(MathUtil.getRealVerticalVelocity(knockback.getY(), ping));

        victim.setVelocity(newVelocity);
    }

}