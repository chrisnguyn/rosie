package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Help extends Command {

    protected String documentation = "**!help** - if you want to know about me and all the things I can do!";

    public Help() {
        super("help");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {

        // HELP SECTION
        Ping ping = new Ping();
        BotInvite botInvite = new BotInvite();
        ServerInvite serverInvite = new ServerInvite();

        // MATH SECTION
        Arithmetic arithmetic = new Arithmetic();
        AdvancedArithmetic advArithmetic = new AdvancedArithmetic();

        // RANDOM RESPONSE
        RandomNumberGeneration rng = new RandomNumberGeneration();
        EightBall eightBall = new EightBall();

        // REMINDERS
        Reminder reminder = new Reminder();

        // REDDIT
        RedditSearch reddit = new RedditSearch();

        // GOOGLE SEARCH
        GoogleSearch google = new GoogleSearch();

        // 2_DO LIST
        ToDoList todo = new ToDoList();

        // MUSIC
        Music music = new Music();

        String ret1 =
                "Hello and thank you for using me! I am a personal assistant Discord bot that aims to improve quality of life.\n\n" +
                "**FEATURES LIST - LAST UPDATED ON FEBRUARY 2ND, 2020**\n\n" +
                "**HELP**\n" +
                "> " + this.documentation           + "\n" +
                "> " + ping.documentation           + "\n" +
                "> " + botInvite.documentation      + "\n" +
                "> " + serverInvite.documentation   + "\n" +
                "**CALCULATIONS**\n" +
                "> " + arithmetic.documentation     + "\n" +
                "> " + advArithmetic.documentation  + "\n" +
                "**RANDOM RESPONSE**\n" +
                "> " + rng.documentation            + "\n" +
                "> " + eightBall.documentation;
        String ret2 =
                "**REMINDERS**\n" +
                "> " + reminder.documentation       + "\n" +
                "**REDDIT**\n" +
                "> " + reddit.documentation         + "\n" +
                "**GOOGLE SEARCH**\n" +
                "> " + google.documentation         + "\n" +
                "**TODO LIST**\n" +
                "> " + todo.documentation           + "\n" +
                "**MUSIC PLAYER**\n" +
                "> " + music.documentation;

        event.getChannel().sendMessage(ret1).queue(); // 2,000 character limit.
        event.getChannel().sendMessage(ret2).queue(); // need to split.
    }
}