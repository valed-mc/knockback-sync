package me.caseload.knockbacksync.listener;

import me.caseload.knockbacksync.manager.KnockbackManager;
import me.caseload.knockbacksync.manager.PingManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final UUID uuid = event.getPlayer().getUniqueId();

        PingManager.cleanup(uuid);
        KnockbackManager.cleanup(uuid);
    }

}
