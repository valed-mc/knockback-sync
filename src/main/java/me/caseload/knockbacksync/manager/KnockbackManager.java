package me.caseload.knockbacksync.manager;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class KnockbackManager {

    private static final HashMap<UUID, Long> wasHit = new HashMap<>();

    private static final HashMap<UUID, Double> knockbackY = new HashMap<>();

    public static void giveHit(UUID uuid, double modifiedYAxis) {
        wasHit.put(uuid, System.currentTimeMillis());
        knockbackY.put(uuid, modifiedYAxis);
    }

    public static Optional<Double> modifiedYAxis(UUID uuid, long maxAge) {
        final boolean isHit = Optional.ofNullable(wasHit.remove(uuid)).orElse(0L) > System.currentTimeMillis() - maxAge;

        return isHit ? Optional.of(knockbackY.get(uuid)) : Optional.empty();
    }

    public static void cleanup(UUID uuid) {
        wasHit.remove(uuid);
        knockbackY.remove(uuid);
    }

}
