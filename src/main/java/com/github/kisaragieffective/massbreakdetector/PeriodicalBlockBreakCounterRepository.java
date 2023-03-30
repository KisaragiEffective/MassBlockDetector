package com.github.kisaragieffective.massbreakdetector;

import org.bukkit.Bukkit;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PeriodicalBlockBreakCounterRepository {
    final ConcurrentHashMap<UUID, Integer> counter;

    PeriodicalBlockBreakCounterRepository() {
        this.counter = new ConcurrentHashMap<>();
    }

    void increaseFor(UUID uuid) {
        counter.compute(uuid, (key, previousBreak) -> previousBreak == null ? 1 : previousBreak + 1);
    }

    void checkAndFlush() {
        counter.entrySet().stream().filter(e -> e.getValue() != null).forEach(e -> {
            if (e.getValue() != 0) {
                if (e.getValue() >= 50) {
                    MassBreakDetector.logger.warning(String.format("%s committed %d break per period!!", e.getKey(), e.getValue()));
                } else {
                    MassBreakDetector.logger.info(String.format("%s committed %d break per period", e.getKey(), e.getValue()));
                }
            }
        });

        counter.clear();
    }
}
