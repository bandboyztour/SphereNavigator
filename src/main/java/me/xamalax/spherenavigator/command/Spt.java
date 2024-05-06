package me.xamalax.spherenavigator.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This class is used for handling sub commands.
 */
public class Spt implements CommandExecutor {

    private final List<BaseCommand> commandExecutors;

    public Spt(List<BaseCommand> commandExecutors) {
        this.commandExecutors = commandExecutors;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) return false; // just info
        String label = strings[0];

        for (BaseCommand commandExecutor : commandExecutors) {
            if (commandExecutor.getLabel().equalsIgnoreCase(label)) {
                return commandExecutor.onCommand(commandSender, command, s, Arrays.stream(strings).skip(1).toArray(String[]::new));
            }
        }
        return false;
    }
}
