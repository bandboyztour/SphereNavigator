package me.xamalax.spherenavigator.command;

import me.xamalax.spherenavigator.Tracker;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SptAdd extends BaseCommand {

    public SptAdd(Tracker tracker) {
        super(tracker);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] strings) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (strings.length == 0) {
            sender.sendMessage("§cSpecify the target.");
            return true;
        }
        Player player = (Player) sender;

        String name = strings[0];
        Player target = Bukkit.getPlayer(name);

        if (target == null) {
            sender.sendMessage("§cCan't find this player.");
            return true;
        }
        if (!target.isOnline()) {
            sender.sendMessage("§cThe player is offline.");
            return true;
        }
        if (!target.getWorld().getUID().equals(player.getWorld().getUID())) {
            sender.sendMessage("§cYou're in different worlds. You §l§nMUST§r§c be in the same world to use this command!");
            return true;
        }
        Set<Player> trackList = tracker.getTrackList().getOrDefault(player, new HashSet<>());
        trackList.add(target);
        tracker.getTrackList().put(player, trackList);

        sender.sendMessage("§aThe player successfully added into the track list.");
        target.sendMessage("§c" + player.getName() + " §7is tracking you now.");
        return true;
    }

    @Override
    public String getLabel() {
        return "add";
    }
}
