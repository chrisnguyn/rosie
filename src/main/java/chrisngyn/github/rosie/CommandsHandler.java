package chrisngyn.github.rosie;

import chrisngyn.github.rosie.commands.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.HashMap;
import java.util.Map;

public class CommandsHandler extends ListenerAdapter {

    private final Map<String, Command> commands = new HashMap<>();
    private final static String PREFIX = "!"; // subject to change from one central point rather than multiple areas

    public CommandsHandler() { // this is going to get longer as I add more classes, use varargs?
        /* FeatureHelp.java */
        Ping ping = new Ping();
        commands.put(ping.getName().toLowerCase(), ping);
        Help help = new Help();
        commands.put(help.getName().toLowerCase(), help);

        /* FeatureArithmetic.java */
        Arithmetic calculate = new Arithmetic();
        commands.put(calculate.getName().toLowerCase(), calculate);

        /* FeatureRNG.java */
        RandomNumberGeneration random = new RandomNumberGeneration();
        commands.put(random.getName().toLowerCase(), random);

        /* FeatureReminder.java */
        Reminder remindme = new Reminder();
        commands.put(remindme.getName().toLowerCase(), remindme);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) { // my listener; on each event received this executes
        String message = event.getMessage().getContentRaw();

        if (event.getAuthor().isBot() || !message.startsWith(PREFIX)) {
            return; // no need to continue at this point and waste memory, we know they're either a bot or are not trying to use a command
        }

        String[] arguments = message.split(" "); // if you get to this point, you know someone is trying to execute a command
        String name = arguments[0].substring(PREFIX.length()).toLowerCase(); // cut out the designated prefix, !calculate becomes calculate

        Command command = commands.get(name); // punch in String name, return Command object

        if(command != null) {
            command.execute(event, arguments);
        }
    }
}