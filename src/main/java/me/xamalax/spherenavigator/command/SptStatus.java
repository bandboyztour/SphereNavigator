package me.xamalax.spherenavigator.command;

import me.xamalax.spherenavigator.Tracker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class SptStatus extends BaseCommand {

    public SptStatus(Tracker tracker) {
        super(tracker);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        Set<Player> playerSet = tracker.getTrackList().getOrDefault(player, new HashSet<>());

        if (!playerSet.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();

            sender.sendMessage("§aHere's your track list:");
            for (Player player1 : playerSet) {
                stringBuilder.append("§a")
                        .append(player1.getName())
                        .append("§7 - ")
                        .append(player1.getLocation().getBlockX())
                        .append(", ")
                        .append(player1.getLocation().getBlockY())
                        .append(", ")
                        .append(player1.getLocation().getBlockZ());
                sender.sendMessage(
                        stringBuilder.toString()
                );
                stringBuilder.setLength(0);
            }
        } else {
            sender.sendMessage("§cYour track list is empty.");
        }
        return true;
    }

    @Override
    public String getLabel() {
        return "status";
    }
}
