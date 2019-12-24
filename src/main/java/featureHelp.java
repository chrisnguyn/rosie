import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class featureHelp extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // How to call: Ping!, !rosie, !invitation

        if (event.getMessage().getContentRaw().equalsIgnoreCase("Ping!")) {
            // .queue() runs the line when next available, since multiple users are using it at once, it will queue them accordingly.
            event.getChannel().sendMessage("Pong!").queue(); // .complete() blocks all other threads and makes that line priority
        }

        if (event.getMessage().getContentRaw().equalsIgnoreCase("!helpme")) {
            event.getChannel().sendMessage("Hello and thank you for using me!\n" +
                    "\n" +
                    "I am a personal assistant Discord Bot that aims to improve quality of life and reduce number of trivialities one faces in their everyday life.\n" +
                    "\n" +
                    "**FEATURES LIST**\n" +
                    "\n" +
                    "**HELP**\n" +
                    "> Ping! - tests to see if I am up and running. I should respond with, \"Pong!\" if so!\n" +
                    "> !serverinvite - replies with an invitation link to the server, which expires in 10 minutes.\n" +
                    "> !rosieinvite - replies with an invitation link to add Rosie to more servers.\n" +
                    "> !helpme - if you want to know about me and all the things I can do, type this command.\n" +
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

        if (event.getMessage().getContentRaw().equalsIgnoreCase("!serverinvite")) { // create invite link. setMaxAge takes arguments in seconds
            event.getChannel().sendMessage("This link will expire in 10 minutes: " + event.getChannel().createInvite().setMaxAge(600).complete().getURL()).queue();
        }

        if (event.getMessage().getContentRaw().equalsIgnoreCase("!rosieinvite")) {
            event.getChannel().sendMessage("Link to invite Rosie to more servers: https://discordapp.com/api/oauth2/authorize?client_id=646757636221435924&permissions=0&scope=bot").queue();
        }
    }
}