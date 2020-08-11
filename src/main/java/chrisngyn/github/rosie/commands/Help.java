package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Help extends Command {
    protected String documentation = "**!help** - if you want to know about me and all the things I can do!";
    Ping ping = new Ping();
    ServerInvite serverInvite = new ServerInvite();
    BotInvite botInvite = new BotInvite();
    Arithmetic math = new Arithmetic();
    AdvancedArithmetic advMath = new AdvancedArithmetic();
    RandomNumberGeneration random = new RandomNumberGeneration();
    EightBall eightBall = new EightBall();
    Reminder remind = new Reminder();
    RedditSearch reddit = new RedditSearch();
    ToDoList todo = new ToDoList();
    GoogleSearch search = new GoogleSearch();

    public Help() {
        super("help");
    }

    @Override // if you aren't overriding something, you did something wrong and this would light up
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        String msg1 =
                "Hello and thank you for using me; I am a personal assistant Discord bot that aims to improve quality of life.\n\n" +
                "**FEATURES LIST - LAST UPDATED ON AUGUST 8TH, 2020**\n\n" +
                "**HELP**\n" +
                "> " + this.documentation           + "\n" +
                "> " + ping.documentation           + "\n" +
                "> " + botInvite.documentation      + "\n" +
                "> " + serverInvite.documentation   + "\n\n" +
                "**MATH**\n" +
                "> " + math.documentation           + "\n" +
                "> " + advMath.documentation        + "\n\n" +
                "**RANDOM RESPONSES**\n" +
                "> " + random.documentation         + "\n" +
                "> " + eightBall.documentation;
        String msg2 =
                "**REMIND ME**\n" +
                "> " + remind.documentation         + "\n\n" +
                "**REDDIT**\n" +
                "> " + reddit.documentation         + "\n\n" +
                "**GOOGLE SEARCH**\n" +
                "> " + search.documentation         + "\n\n" +
                "**TODO LIST**\n" +
                "> " + todo.documentation;

        event.getChannel().sendMessage(msg1).queue(); // 2,000 character limit...
        event.getChannel().sendMessage(msg2).queue(); // ...need to split
    }
}