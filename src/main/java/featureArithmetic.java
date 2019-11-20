import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class featureArithmetic extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // template: !calculate 1120 + 2019 - argument 2 is always the operation

        String[] messageSent = event.getMessage().getContentRaw().split(" ");

        double num1 = Integer.parseInt(messageSent[1]);
        double num2 = Integer.parseInt(messageSent[3]);

        if (messageSent[0].equalsIgnoreCase("!calculate")) {

            if (messageSent[2].equalsIgnoreCase("+")) {
                double sum = num1 + num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            if (messageSent[2].equalsIgnoreCase("-")) {
                double sum = num1 - num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            if (messageSent[2].equalsIgnoreCase("*")) {
                double sum = num1 * num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            if (messageSent[2].equalsIgnoreCase("/")) {
                double sum = num1 / num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            if (messageSent[2].equalsIgnoreCase("%")) {
                double sum = num1 % num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }
        }
    }
}