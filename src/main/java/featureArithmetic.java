import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class featureArithmetic extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // TODO: sin cos tan quad pytha expon, !choose [arg1] | [args]

        // to do: sin, cos, tan, quadratic, pythagorean, exponential

        // How to call: !calculate [num1] [+, -, *, /, %] [num2]

        String[] messageSent = event.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!calculate") && messageSent[1].equalsIgnoreCase("sin")) {
            double result = Math.sin(70);
            event.getChannel().sendMessage("" + result).queue();
        }

        double num1 = Double.parseDouble(messageSent[1]); // first number /* !calculate sin 70 doesn't work. when it reaches this line it aborts. not enough args */
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

            else if (messageSent[2].equalsIgnoreCase("%")) { // !calculate 10 ^ 10
                double sum = num1 % num2;
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[2].equalsIgnoreCase("^")) { // exponential works now
                double sum = Math.pow(num1, num2);
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[1].equalsIgnoreCase("sin")) { // !calculate sin 70
                double sum = Math.sin(Double.parseDouble(messageSent[2]));
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[1].equalsIgnoreCase("cos")) { // !calculate cos 70
                double sum = Math.cos(Double.parseDouble(messageSent[2]));
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[1].equalsIgnoreCase("tan")) { // !calculate tan 70
                double sum = Math.tan(Double.parseDouble(messageSent[2]));
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[1].equalsIgnoreCase("arcsin")) {
                double sum = Math.asin(Double.parseDouble(messageSent[2]));
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[1].equalsIgnoreCase("arcos")) {
                double sum = Math.acos(Double.parseDouble(messageSent[2]));
                event.getChannel().sendMessage("Result: " + sum).queue();
            }

            else if (messageSent[1].equalsIgnoreCase("arctan")) {
                double sum = Math.atan(Double.parseDouble(messageSent[2]));
                event.getChannel().sendMessage("Result: " + sum).queue();
            }
        }
    }
}