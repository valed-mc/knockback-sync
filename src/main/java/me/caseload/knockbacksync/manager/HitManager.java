package me.caseload.knockbacksync.manager;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class HitManager {

    private static final HashMap<UUID, Long> wasHit = new HashMap<>();

    public static void giveHit(UUID uuid) {
        wasHit.put(uuid, System.currentTimeMillis());
    }

    public static boolean hasRecentHit(UUID uuid, long maxAge) {
        return Optional.ofNullable(wasHit.remove(uuid)).orElse(0L) > System.currentTimeMillis() - maxAge;
    }

    public static void cleanup(UUID uuid) {
        wasHit.remove(uuid);
    }

}
