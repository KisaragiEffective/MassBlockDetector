package com.github.kisaragieffective.massbreakdetector;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class RawBlockBreakWatcher implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    void onRawBlockBreak(BlockBreakEvent e) {
        MassBreakDetector.detector.increaseFor(e.getPlayer().getUniqueId());
    }
}
