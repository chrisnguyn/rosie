package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

public class Reminder extends Command {

    public Reminder() {
        super("Remindme"); // !remindme 20 minutes take the pizza out of the oven
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) { // !remindme 20 minutes take pizza out of oven / any arbitrary-n length string

        String reminder = "";
        for (int i = 3; i < args.length; i++) { reminder += args[i] + " "; }

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
                event.getChannel().sendMessage("Unknown time argument. Please consult !help for documentation.").queue();
        }

    }

    public void remind(GuildMessageReceivedEvent event, User user, String reminder, long delay, TimeUnit unit) { // helper method. personally modified to include an extra param.
        // IF NOT WORKING, IT'S MOST LIKELY BECAUSE THE PROJECT LANGUAGE YOU HAVE SET DOES NOT SUPPORT LAMBDA EXPRESSIONS.
        // Fix 1. File -> Settings -> Project bytecode version: 8, and delete all per-module bytecode versions
        // Fix 2. File -> Project Structure -> Language level 8
        // Fix 3. IntelliJ IDEA -> Preferences -> change project bytecode from 1.5 to 1.8
        user.openPrivateChannel().queue((channel) -> channel.sendMessage(reminder).queueAfter(delay, unit)); // ctrl + click for more info
        event.getChannel().sendMessage("Your reminder has been set!").queue();
    }

}