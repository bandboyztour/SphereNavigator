package me.xamalax.spherenavigator.command;

import me.xamalax.spherenavigator.Tracker;
import org.bukkit.command.CommandExecutor;

public abstract class BaseCommand implements CommandExecutor {
    final Tracker tracker;

    protected BaseCommand(Tracker tracker) {
        this.tracker = tracker;
    }

    public String getLabel() {
        return "spt";
    }
}
