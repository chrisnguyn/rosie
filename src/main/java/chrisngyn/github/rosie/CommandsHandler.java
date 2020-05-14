package chrisngyn.github.rosie;

import chrisngyn.github.rosie.commands.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.HashMap;
import java.util.Map;

public class CommandsHandler extends ListenerAdapter {
    private final Map<String, Command> commands = new HashMap<>(); // map command trigger --> object; get object then call execute
    private final static String PREFIX = "r!"; // r!help, r!ping, etc. to denote command calls

    public CommandsHandler() { // create objects of all command classes and store them into the map
        /* 1. HELP commands */
        Ping ping = new Ping();
        Help help = new Help();
        ServerInvite serverInvite = new ServerInvite();
        BotInvite botInvite = new BotInvite();
        commands.put(ping.getName().toLowerCase(), ping);
        commands.put(help.getName().toLowerCase(), help);
        commands.put(serverInvite.getName().toLowerCase(), serverInvite);
        commands.put(botInvite.getName().toLowerCase(), botInvite);

        /* 2. ARITHMETIC */
        Arithmetic math = new Arithmetic();
        AdvancedArithmetic advMath = new AdvancedArithmetic();
        commands.put(math.getName().toLowerCase(), math);
        commands.put(advMath.getName().toLowerCase(), advMath);

        /* 3. RNG RESPONSES */
        RandomNumberGeneration random = new RandomNumberGeneration();
        EightBall eightBall = new EightBall();
        commands.put(random.getName().toLowerCase(), random);
        commands.put(eightBall.getName().toLowerCase(), eightBall);

        /* 4. OTHER */
        Reminder remind = new Reminder();
        RedditSearch reddit = new RedditSearch();
        ToDoList todo = new ToDoList();
        GoogleSearch search = new GoogleSearch();
        commands.put(remind.getName().toLowerCase(), remind);
        commands.put(reddit.getName().toLowerCase(), reddit);
        commands.put(todo.getName().toLowerCase(), todo);
        commands.put(search.getName().toLowerCase(), search);
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw(); // on every message that comes into the server, log it to be processed

        if (event.getAuthor().isBot() || !message.startsWith(PREFIX)) {
            return; // if reached, no need to continue; we know they're either a bot or not trying to use a command...
        }

        String[] arguments = message.split(" "); // ...else, you know someone is trying to execute a command
        String name = arguments[0].substring(PREFIX.length()).toLowerCase(); // cut out prefix; "!help" becomes "help"
        Command command = commands.get(name); // get the command object from the map, provided a command name; returns null if doesn't exist

        if (command != null) {
            command.execute(event, arguments); // if map doesn't provide null value, execute...
        } else {
            return; // ...else, Map.get() will return null if you get a key that isn't in the map; if we get null, return
        }
    }
}