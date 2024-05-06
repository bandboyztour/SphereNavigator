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

    private final Tracker tracker;

    public SptListener(Tracker tracker) {
        this.tracker = tracker;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player playerToRemove = event.getPlayer();
        for (Set<Player> playerList : this.tracker.getTrackList().values()) {
            playerList.remove(playerToRemove);
        }
        this.tracker.getTrackList().remove(playerToRemove);
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player playerToRemove = event.getPlayer();
        for (Set<Player> playerList : this.tracker.getTrackList().values()) {
            playerList.remove(playerToRemove);
        }
        this.tracker.getTrackList().remove(playerToRemove);
    }
}
