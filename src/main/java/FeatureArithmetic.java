import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class FeatureArithmetic extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // How to call: !calculate, !pythagorean, !quadratic

        String[] messageSent = event.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!pythagorean")) {                          // !pythagorean 3 4, get 5
            double num1 = Double.parseDouble(messageSent[1]);
            double num2 = Double.parseDouble(messageSent[2]);
            double hypotenuse = (Math.sqrt((Math.pow(num1, 2) + Math.pow(num2, 2))));
            event.getChannel().sendMessage("The hypontenuse of the two side length you have provided is: " + hypotenuse).queue();
        }

        if (messageSent[0].equalsIgnoreCase("!quadratic")) {                            // !quadratic 1 2 3
            double a = Double.parseDouble(messageSent[1]);
            double b = Double.parseDouble(messageSent[2]);
            double c = Double.parseDouble(messageSent[3]);
            double discriminant = (b * b) - (4 * a * c);

            if (discriminant > 0) {
                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                event.getChannel().sendMessage("As your discriminant is positive, you have two roots. Your roots are: **" + root1 + "** and **" + root2 + "**").queue();
            }

            else if (discriminant == 0) {
                double root = (-b / (2 * a));
                event.getChannel().sendMessage("As your discriminant is zero, you have one root. Your root is: **" + root + "**").queue();
            }

            else {
                event.getChannel().sendMessage("As your discriminant is negative, you have zero real roots.").queue();
            }
        }

        if (messageSent[0].equalsIgnoreCase("!calculate")) {
            if (messageSent.length < 4) {                                                            // !calculate sin 70 - three args
                double num = Double.parseDouble(messageSent[2]);

                if (messageSent[1].equalsIgnoreCase("sin")) {                           // SIN
                    event.getChannel().sendMessage("Result: " + Math.sin(num)).queue();
                } else if (messageSent[1].equalsIgnoreCase("cos")) {                    // COS
                    event.getChannel().sendMessage("Result: " + Math.cos(num)).queue();
                } else if (messageSent[1].equalsIgnoreCase("tan")) {                    // TAN
                    event.getChannel().sendMessage("Result: " + Math.tan(num)).queue();
                }
            }

            else {                                                                                   // !calculate 10 + 10 - four args
                double num1 = Double.parseDouble(messageSent[1]); // first number
                double num2 = Double.parseDouble(messageSent[3]); // second number

                if (messageSent[2].equalsIgnoreCase("+")) {                             // ADDITION
                    event.getChannel().sendMessage("Result: " + (num1 + num2)).queue();
                } else if (messageSent[2].equalsIgnoreCase("-")) {                      // SUBTRACTION
                    event.getChannel().sendMessage("Result: " + (num1 - num2)).queue();
                } else if (messageSent[2].equalsIgnoreCase("*")) {                      // MULTIPLICATION
                    event.getChannel().sendMessage("Result: " + (num1 * num2)).queue();
                } else if (messageSent[2].equalsIgnoreCase("/")) {                      // DIVISION
                    event.getChannel().sendMessage("Result: " + (num1 / num2)).queue();
                } else if (messageSent[2].equalsIgnoreCase("%")) {                      // MODULO
                    event.getChannel().sendMessage("Result: " + (num1 % num2)).queue();
                } else if (messageSent[2].equalsIgnoreCase("^")) {                      // EXPONENTIAL
                    event.getChannel().sendMessage("Result: " + Math.pow(num1, num2)).queue();
                }
            }
        }
    }
}