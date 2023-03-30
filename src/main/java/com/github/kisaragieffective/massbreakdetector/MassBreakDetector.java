package com.github.kisaragieffective.massbreakdetector;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executors;
import java.util.logging.Logger;

public final class MassBreakDetector extends JavaPlugin {
    static Logger logger;

    static final PeriodicalBlockBreakCounterRepository detector = new PeriodicalBlockBreakCounterRepository();

    @Override
    public void onEnable() {
        // Plugin startup logic
        logger = this.getLogger();
        Executors.defaultThreadFactory().newThread(() -> {
            detector.checkAndFlush();
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                logger.severe("exception during detector waiting: " + e.getMessage());
            }
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
