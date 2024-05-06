package me.xamalax.spherenavigator.command;

import me.xamalax.spherenavigator.Tracker;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SptRem extends BaseCommand {
    public SptRem(Tracker tracker) {
        super(tracker);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
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

        Set<Player> trackList = tracker.getTrackList().getOrDefault(player, new HashSet<>());
        if (trackList.contains(target)) {
            trackList.remove(target);
            tracker.getTrackList().put(player, trackList);
            sender.sendMessage("§aThe player successfully removed from the track list.");
            target.sendMessage("§c" + player.getName() + " §7doesn't track you anymore.");
        } else {
            sender.sendMessage("§cThere is no such player in your track list.");
        }
        return true;
    }

    @Override
    public String getLabel() {
        return "rem";
    }
}
