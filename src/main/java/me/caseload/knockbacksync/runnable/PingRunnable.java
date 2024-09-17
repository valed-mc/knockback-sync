package me.caseload.knockbacksync.runnable;

import me.caseload.knockbacksync.manager.PingManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PingRunnable extends BukkitRunnable {

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PingManager.sendPacket(player);
        }
    }

}
