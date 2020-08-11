package chrisngyn.github.rosie;

import chrisngyn.github.rosie.commands.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.HashMap;
import java.util.Map;

public class CommandsHandler extends ListenerAdapter {
    private final Map<String, Command> commands = new HashMap<>(); // map <cmd trigger word> to <object>
    private final static String PREFIX = "r!";

    public CommandsHandler() {
        Ping ping = new Ping();
        Help help = new Help();
        ServerInvite serverInvite = new ServerInvite();
        BotInvite botInvite = new BotInvite();
        Arithmetic math = new Arithmetic();
        AdvancedArithmetic advancedMath = new AdvancedArithmetic();
        RandomNumberGeneration rng = new RandomNumberGeneration();
        EightBall eightBall = new EightBall();
        Reminder remind = new Reminder();
        RedditSearch reddit = new RedditSearch();
        ToDoList todo = new ToDoList();
        GoogleSearch search = new GoogleSearch();
        addCommands(ping, help, serverInvite, botInvite, math, advancedMath, rng, eightBall, remind, reddit, todo, search);
    }

    private void addCommands(Command... cmds) { // variable number of args - an array of type Command
        for (Command cmd: cmds) {
            this.commands.put(cmd.getName().toLowerCase(), cmd);
        }
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw(); // process all received messages

        if (event.getAuthor().isBot() || !message.startsWith(PREFIX)) {
            return;
        }

        String[] arguments = message.split(" ");
        String name = arguments[0].substring(PREFIX.length()).toLowerCase(); // cut out prefix; "r!help" becomes "help"
        Command cmd = commands.get(name); // get the command object from the map, provided a command name; returns null if doesn't exist

        if (cmd == null) {
            event.getChannel().sendMessage("Unknown command. Type \"r!help\" to review available commands.").queue();
        } else {
            cmd.execute(event, arguments);
        }
    }
}