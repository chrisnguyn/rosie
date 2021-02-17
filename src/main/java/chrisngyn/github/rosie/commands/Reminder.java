package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.*;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Reminder extends Command {
    protected String documentation = "**!remindme** [number] [s, seconds, m, minutes, h, hours] [reminder] - set a reminder; Rosie will send you a direct message reminding you when time is up.";
    private String base = "Hello - you wanted me to remind you of this: **";

    public Reminder() {
        super("remindme");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        String remind = String.join(" ", Arrays.copyOfRange(args, 3, args.length));
        String reminder = base + remind;

        switch (args[2]) {
            case "s":
            case "seconds":
                remind(event, event.getAuthor(), reminder, Integer.parseInt(args[1]), SECONDS);
                break;
            case "m":
            case "minutes":
                remind(event, event.getAuthor(), reminder, Integer.parseInt(args[1]), MINUTES);
                break;
            case "h":
            case "hours":
                remind(event, event.getAuthor(), reminder, Integer.parseInt(args[1]), HOURS);
                break;
            default:
                event.getChannel().sendMessage("Improper use of command. Please use \"**r!help**\" for documentation.").queue();
                return;
        }

    }

    public void remind(GuildMessageReceivedEvent event, User user, String reminder, long delay, TimeUnit unit) {
        user.openPrivateChannel().queue((channel) -> channel.sendMessage(reminder + "**").queueAfter(delay, unit));
        event.getChannel().sendMessage("Your reminder has been set!").queue();
    }
}