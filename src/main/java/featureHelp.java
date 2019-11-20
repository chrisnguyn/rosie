import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class featureHelp extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // template: !rosiecommands

        if (event.getMessage().getContentRaw().equalsIgnoreCase("!rosiecommands")) {
            event.getChannel().sendMessage("Rosie is a personal assistant Discord bot that aims to improve quality of life and help reduce the number of trivialities and menial tasks one faces in their day-to-day life.").queue();
            event.getChannel().sendMessage("> **FEATURES LIST**\n" + "> \n" +
                    "> **ARITHMETIC**: able to perform basic calculations. Supports +, -, \\*, /, % operations (i.e. **!compute 1120 * 2019**)\n" + "> \n" +
                    "> **RNG**: print out results of randomized actions. Supports !coinflip, !dice, !card, !numberbetween (i.e. **!rolldice; !numberbetween 1 10**)\n" +
                    "> \n" + "> **REMINDERS**: can set reminders and be notified through direct messages (i.e. **!setreminder 20min take pizza out of the oven**)\n" +
                    "> \n" + "> **REDDIT**: able to pull current top 5 hottest posts of a given subreddit (i.e. **!reddit memes; !reddit news**)\n" + "> \n" +
                    "> **GOOGLE CALENDAR**: able to add and view events from personal Google Calendar (i.e. **!calendaradd Wednesday November 20 11:00-13:00 geography lecture; !calendarview Wednesday November 20**)\n" +
                    "> \n" + "> **GOOGLE SEARCHING**: able to perform searches (i.e. **!search apples; bot replies with the first link as if you were to search 'apples'**)\n" +
                    "> \n" + "> **TO DO LIST**: able to queue and remove items to and from a to do list (i.e. **!todo data structures assignment; !todo work on Rosie bot; !displaytodo**)").queue();
        }
    }
}