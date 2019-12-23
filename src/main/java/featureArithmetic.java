import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class featureArithmetic extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // How to call: !calculate [num1] [+, -, *, /, %] [num2]

        String[] messageSent = event.getMessage().getContentRaw().split(" ");

        double num1 = Integer.parseInt(messageSent[1]); // first number
        double num2 = Integer.parseInt(messageSent[3]); // second number

        if (messageSent[0].equalsIgnoreCase("!calculate")) {

            if (messageSent[2].equalsIgnoreCase("+")) {
                double sum = num1 + num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[2].equalsIgnoreCase("-")) {
                double sum = num1 - num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[2].equalsIgnoreCase("*")) {
                double sum = num1 * num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[2].equalsIgnoreCase("/")) {
                double sum = num1 / num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[2].equalsIgnoreCase("%")) {
                double sum = num1 % num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }
        }
    }
}