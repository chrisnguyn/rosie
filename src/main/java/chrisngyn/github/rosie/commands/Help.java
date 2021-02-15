package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Help extends Command {
    protected String documentation = "**r!help** - if you want to know about me and all the things I can do!";
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

    public Help() {
        super("help");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        String messageOne =
                "Hello and thank you for using me; I am a personal assistant Discord bot that aims to improve your quality of life.\n\n" +
                "**FEATURES LIST - LAST UPDATED ON AUGUST 11TH, 2020**\n\n" +
                "**HELP**\n"                        +
                "> " + this.documentation           + "\n" +
                "> " + ping.documentation           + "\n" +
                "> " + botInvite.documentation      + "\n" +
                "> " + serverInvite.documentation   + "\n\n" +
                "**MATH**\n"                        +
                "> " + math.documentation           + "\n" +
                "> " + advancedMath.documentation   + "\n\n" +
                "**RANDOM RESPONSES**\n"            +
                "> " + eightBall.documentation      + "\n" +
                "> " + rng.documentation;
        String messageTwo =
                "**REMINDERS**\n"                   +
                "> " + remind.documentation         + "\n\n" +
                "**REDDIT**\n"                      +
                "> " + reddit.documentation         + "\n\n" +
                "**GOOGLE SEARCH**\n"               +
                "> " + search.documentation         + "\n\n" +
                "**TODO LIST**\n"                   +
                "> " + todo.documentation;

        event.getChannel().sendMessage(messageOne).queue(); // 2,000 character limit...
        event.getChannel().sendMessage(messageTwo).queue(); // ...need to split
    }
}