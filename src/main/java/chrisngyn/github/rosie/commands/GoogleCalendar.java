package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class GoogleCalendar extends Command {
    protected String documentation = "**r!gcal** - pull the most recent upcoming event today";
    private String error = "";

    public GoogleCalendar(String name) {
        super("gcal"); // r!gcal

        try {
            // attempt login
        } catch (Exception e) {
            System.err.println("Error logging in.");
            this.error = "Error logging in.";
        }
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        if (!this.error.isEmpty()) { // if it's NOT empty, it means it failed the try and went into the catch
            event.getChannel().sendMessage("Error executing command. Please contact the bot creator.").queue();
        }

        // write method
    }
}