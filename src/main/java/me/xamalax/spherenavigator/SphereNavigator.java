package me.xamalax.spherenavigator;

import me.xamalax.spherenavigator.command.Spt;
import me.xamalax.spherenavigator.command.SptAdd;
import me.xamalax.spherenavigator.command.SptRem;
import me.xamalax.spherenavigator.command.SptStatus;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class SphereNavigator extends JavaPlugin {

    private Tracker tracker;

    @Override
    public void onEnable() {
        // Plugin startup logic

        tracker = new Tracker(this);
        tracker.startTracker();

        Bukkit.getPluginManager().registerEvents(new SptListener(tracker), this);

        getCommand("spt").setExecutor(new Spt(
                List.of(
                        new SptAdd(this.tracker),
                        new SptRem(this.tracker),
                        new SptStatus(this.tracker)
                )
        ));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        tracker.stopTracker();
    }
}
