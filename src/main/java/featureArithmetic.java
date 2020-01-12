import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class featureArithmetic extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // to do: sin, cos, tan, quadratic, pythagorean, exponential

        // How to call: !calculate [num1] [+, -, *, /, %] [num2]

        String[] messageSent = event.getMessage().getContentRaw().split(" ");

        double num1 = Double.parseDouble(messageSent[1]); // first number
        double num2 = Double.parseDouble(messageSent[3]); // second number

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

            else if (messageSent[2].equalsIgnoreCase("^")) { // exponential works now
                double sum = Math.pow(num1, num2);
            }

            else if (messageSent[1].equalsIgnoreCase("sin")) { // !calculate sin 70
                double sum = Math.sin(Double.parseDouble(messageSent[2]));
            }

            else if (messageSent[1].equalsIgnoreCase("cos")) { // !calculate cos 70
                double sum = Math.cos(Double.parseDouble(messageSent[2]));
            }

            else if (messageSent[1].equalsIgnoreCase("tan")) { // !calculate tan 70
                double sum = Math.tan(Double.parseDouble(messageSent[2]));
            }
        }
    }
}