import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class featureHelp extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // template: Ping!, !rosiecommands

        if (event.getMessage().getContentRaw().equalsIgnoreCase("Ping!")) {
            event.getChannel().sendMessage("Pong!").queue(); // test if bot is up and running
        }

        if (event.getMessage().getContentRaw().equalsIgnoreCase("!rosiecommands")) {
            event.getChannel().sendMessage("Hello and thank you for using me!\n" +
                    "\n" +
                    "I am a personal assistant Discord Bot that aims to improve quality of life and reduce number of trivialities one faces in their everyday life.\n" +
                    "\n" +
                    "**FEATURES LIST**\n" +
                    "\n" +
                    "**HELP**\n" +
                    "> Ping! - tests to see if I am up and running. I should respond with, \\\"Pong!\\\" if so!\n" +
                    "> !rosiecommands - if you want to know about me and all the things I can do, type this command.\n" +
                    "\n" +
                    "**ARITHMETIC**\n" +
                    "> !calculate [number1] [+, -, *, /, %] [number2] - can compute basic math.\n" +
                    "\n" +
                    "**RNG RESPONSE**\n" +
                    "> !coin - responds with HEADS or TAILS.\n" +
                    "> !dice - responds with a random dice roll from 0-6.\n" +
                    "> !card - responds with a random card.\n" +
                    "> !numberbetween [number1] [number2] - responds with a random number between these two values; whole numbers only. [number1] must always be a smaller number than [number2].\n" +
                    "\n" +
                    "**REMINDERS**\n" +
                    "> !remindme [number] [unit] [query] - sets reminders for you and will send you a direct message accordingly. Units can be s, seconds, m, minutes, h, or hours.\n" +
                    "\n" +
                    "**REDDIT**\n" +
                    "> !reddit [name of subreddit] - responds with the top 5 current hottest posts of a subreddit.\n" +
                    "\n" +
                    "**GOOGLE SEARCH**\n" +
                    "> !googlesearch [query] - responds with the top 3 Google searches for that query.\n" +
                    "\n" +
                    "**TO DO LIST**\n" +
                    "> !todoadd [query] - add an entry to your list\n" +
                    "> !todoview [query] - view your current list of items\n" +
                    "> !todocomplete [query] - check an item on your list as complete (does not remove it)\n" +
                    "> !todoremove [query] - remove an item on your list").queue();
        }
    }
}