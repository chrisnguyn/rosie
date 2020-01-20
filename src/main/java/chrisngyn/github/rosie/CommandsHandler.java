package chrisngyn.github.rosie;

import chrisngyn.github.rosie.commands.Ping;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.HashMap;
import java.util.Map;

public class CommandsHandler extends ListenerAdapter {

    private final Map<String, Command> commands = new HashMap<>();
    private final static String prefix = "!";
    String name;

    private CommandsHandler() {
        Ping ping = new Ping();
        commands.put(ping.getName().toLowerCase(), ping);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Command command = commands.get(name);
        if(command != null) {
            command.execute(event, null);
        }
    }
}