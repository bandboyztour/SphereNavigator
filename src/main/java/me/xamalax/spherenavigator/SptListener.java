package me.xamalax.spherenavigator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.sound.midi.Track;
import java.util.List;
import java.util.Set;

public class SptListener implements Listener {

    private Tracker tracker;

    public SptListener(Tracker tracker) {
        this.tracker = tracker;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player playerToRemove = event.getPlayer();
        for (Set<Player> playerList : this.tracker.getTrackList().values()) {
            // Remove the player from the current list
            playerList.remove(playerToRemove);
        }
        this.tracker.getTrackList().remove(playerToRemove);
        // remove this shit
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player playerToRemove = event.getPlayer();
        for (Set<Player> playerList : this.tracker.getTrackList().values()) {
            // Remove the player from the current list
            playerList.remove(playerToRemove);
        }
        this.tracker.getTrackList().remove(playerToRemove);
    }

//    @EventHandler
//    public void chat(AsyncPlayerChatEvent event) {
//        String[] args = event.getMessage().split("\\|");
//        if (args.length == 2) {
//            if (args[0].equalsIgnoreCase("add")) {
//                String playerName = args[1];
//                Player target = Bukkit.getPlayer(playerName);
//                if (target != null) {
//                    if (target.isOnline()) {
//                        tracker.getTrackList().put(event.getPlayer(), List.of(target));
//                        event.getPlayer().sendMessage("Player added");
//                    }
//                }
//            }
//        }
//    }
}
