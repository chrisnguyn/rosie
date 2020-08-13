package chrisngyn.github.rosie;

import chrisngyn.github.rosie.commands.*;

import java.util.Map;
import java.util.HashMap;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandsHandler extends ListenerAdapter {
    private final Map<String, Command> commands = new HashMap<>(); // map <trigger word: command object>
    private final static String PREFIX = "r!";

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
        System.out.println("Successfully logged in. All objects created and added to dictionary. Ready to go!");
    }

    private void addCommands(Command... cmds) { // variable number of args; an array of type Command
        for (Command cmd: cmds) {
            this.commands.put(cmd.getName().toLowerCase(), cmd);
        }
    }

    @Override // if you aren't overriding something, you did something wrong and this would light up; this is a 'check'
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw(); // process all received messages

        if (event.getAuthor().isBot() || !message.startsWith(PREFIX)) { return; }

        String[] arguments = message.split(" ");
        String name = arguments[0].substring(PREFIX.length()).toLowerCase(); // cut out prefix; "r!help" becomes "help"
        Command cmd = commands.get(name);

        if (cmd == null) {
            event.getChannel().sendMessage("Unknown command. Type **\"r!help\"** to review available options.").queue();
        } else {
            cmd.execute(event, arguments);
        }
    }
}