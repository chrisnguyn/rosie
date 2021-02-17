package chrisngyn.github.rosie;

import chrisngyn.github.rosie.commands.*;
import java.util.Map;
import java.util.HashMap;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandsHandler extends ListenerAdapter {
    private final Map<String, Command> commands = new HashMap<>();  // map of { triggerWord : command }
    private final static String PREFIX = "!";

    public CommandsHandler() {
        Help help = new Help();
        Ping ping = new Ping();
        BotInvite botInvite = new BotInvite();
        ServerInvite serverInvite = new ServerInvite();
        Arithmetic math = new Arithmetic();
        AdvancedArithmetic advancedMath = new AdvancedArithmetic();
        EightBall eightBall = new EightBall();
        Reminder remind = new Reminder();
        RandomNumberGeneration rng = new RandomNumberGeneration();
        RedditSearch reddit = new RedditSearch();
        GoogleSearch search = new GoogleSearch();
        ToDoList todo = new ToDoList();
        addCommands(help, ping, botInvite, serverInvite, math, advancedMath, eightBall, remind, rng, reddit, search, todo);
    }

    private void addCommands(Command... cmds) {
        for (Command cmd: cmds) {
            this.commands.put(cmd.getName().toLowerCase(), cmd);
        }
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();  // process all incoming messages to the server

        if (event.getAuthor().isBot() || !message.startsWith(PREFIX)) {
            return;
        }

        String[] arguments = message.split(" ");
        String name = arguments[0].substring(PREFIX.length()).toLowerCase();
        Command cmd = commands.get(name);

        if (cmd != null) {
            cmd.execute(event, arguments);
        } else {
            event.getChannel().sendMessage("Unknown command. Type **\"r!help\"** for commands.").queue();
        }
    }
}