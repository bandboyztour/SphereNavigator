package me.xamalax.spherenavigator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Tracker {
    private final Plugin plugin;
    private final Map<Player, Set<Player>> trackList = new HashMap<>();
    private BukkitTask task = null;
    public Tracker(Plugin plugin) {
        this.plugin = plugin;
    }

    public Map<Player, Set<Player>> getTrackList() {
        return trackList;
    }

    public void startTracker() {
        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            trackList.forEach((player, players) -> {

                Location center = player.getEyeLocation();

                // draw spheres for this player if the following conditions satisfied:
                // + target is online
                // + target is in the same world
                for (Player player1 : players) {
                    if (!(player1.isOnline() && player.getWorld() == player1.getWorld()))
                        continue;
                    Location target = player1.getEyeLocation();
                    Vector loc = target.subtract(center).toVector().normalize();

                    // Sphere center
                    Location sphereCenter = center.add(loc);
                    player.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, sphereCenter.getX(), sphereCenter.getY(), sphereCenter.getZ(), 0, 0, 0, 0, 0);
                }
            });
        }, 0, 40);
    }

    public void stopTracker() {
        if (this.task != null) {
            task.cancel();
        }
    }
}
