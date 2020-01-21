import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.Random;

public class FeatureRNG extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // How to call: !coin, !dice, !card, !numberbetween [num1] [num2], !choose opt1 | opt2 - NOT YET IMPLEMENTED

        Random ran = new Random();
        String[] messageSent = event.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!coin")) {
            int rand = ran.nextInt(2); // 0 or 1.

            String headsOrTails = (rand == 0 ? "HEADS" : "TAILS");
            event.getChannel().sendMessage("Result: " + headsOrTails).queue();
        }

        if (messageSent[0].equalsIgnoreCase("!dice")) {
            int rand = ran.nextInt(6) + 1; // 0-5, +1, 1-6

            event.getChannel().sendMessage("Result: " + rand).queue();
        }

        if (messageSent[0].equalsIgnoreCase("!choose")) {
            // Given two choices such as "!choose [arg1] | [arg2]" - generate one of the two choices.
        }

        /* oh god penniless when you get to the code below...plz dont hate me i'm sure there's a better way i just don't know how lol */

        if (messageSent[0].equalsIgnoreCase("!card")) { // 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A, Clubs, Spades, Diamonds, Hearts
            int rand = ran.nextInt(13) + 2; // 0-12, 2-14
            int rand2 = ran.nextInt(4); // 0-3
            String result = "Result: ";

            if (rand < 11) {
                result += rand + " of ";
            }
            else if (rand == 11) {
                result += "Jack of ";
            }
            else if (rand == 12) {
                result += "Queen of ";
            }
            else if (rand == 13) {
                result += "King of ";
            }
            else if (rand == 14) {
                result += "Ace of ";
            }
            if (rand2 == 0) { // Clubs
                result += "Clubs";
            }
            else if (rand2 == 1) { // Spades
                result += "Spades";
            }
            else if (rand2 == 2) { // Diamonds
                result += "Diamonds";
            }
            else if (rand2 == 3) { // Hearts
                result += "Hearts";
            }

            event.getChannel().sendMessage(result).queue();
        }

        if (messageSent[0].equalsIgnoreCase("!numberbetween")) {

            if (Integer.parseInt(messageSent[1]) > Integer.parseInt(messageSent[2])) {
                event.getChannel().sendMessage("Your lower bound can't be less than or equal to your upper bound! :angry:").queue();
            }

            else {
                int max = Integer.parseInt(messageSent[2]);
                int min = Integer.parseInt(messageSent[1]);
                int result = ran.nextInt((max - min) + 1) + min; // algorithm to find numbers inclusive
                event.getChannel().sendMessage("Result: " + result).queue();
            }

        }
    }
}