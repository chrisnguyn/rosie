package chrisngyn.github.rosie;

import chrisngyn.github.rosie.commands.Arithmetic;
import chrisngyn.github.rosie.commands.Help;
import chrisngyn.github.rosie.commands.Ping;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.HashMap;
import java.util.Map;

public class CommandsHandler extends ListenerAdapter {

    private final Map<String, Command> commands = new HashMap<>();
    private final static String PREFIX = "!"; // can be subject to change from one central point rather than multiple areas.

    public CommandsHandler() { /* this is going to get ugly really fast, use var args (?) */

        /* FeatureHelp.java */
        Ping ping = new Ping();
        commands.put(ping.getName().toLowerCase(), ping);
        Help help = new Help();
        commands.put(help.getName().toLowerCase(), help);
        Arithmetic arithmetic = new Arithmetic();
        commands.put(arithmetic.getName().toLowerCase(), arithmetic);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw();

        if (event.getAuthor().isBot() || !message.startsWith(PREFIX)) {
            return; // no need to continue at this point and create arrays and waste memory.
        }

        String[] arguments = message.split(" "); // if you get to this point, you know someone is trying to execute a cmd.
        String name = arguments[0].substring(PREFIX.length()).toLowerCase(); // cut out the designated prefix. !calculate becomes calculate

        Command command = commands.get(name); // punch in String name, return Command object.

        if(command != null) {
            command.execute(event, arguments); // execute the boy
        }
    }
}