package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class AdvancedArithmetic extends Command {

    public AdvancedArithmetic() {
        super("MoreMath");
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) { // !moremath quadratic, pythagorean, sin, cos, tan
        switch (args[1]) {
            case "quadratic": // !moremath quadratic 1 2 3
                double a = Double.parseDouble(args[2]);
                double b = Double.parseDouble(args[3]);
                double c = Double.parseDouble(args[4]);
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
                break;

            case "pythagorean": // !moremath pythagorean 3 4 - get "5"
                double num1 = Double.parseDouble(args[2]);
                double num2 = Double.parseDouble(args[3]);
                double hypotenuse = (Math.sqrt((Math.pow(num1, 2) + Math.pow(num2, 2))));
                event.getChannel().sendMessage("The hypontenuse of the two side length you have provided is: **" + hypotenuse + "**").queue();
                break;

            case "sin": // !moremath sin 70
                event.getChannel().sendMessage("Result: **" + Math.sin(Integer.parseInt(args[2])) + "**").queue();
                break;

            case "cos":
                event.getChannel().sendMessage("Result: **" + Math.cos(Integer.parseInt(args[2])) + "**").queue();
                break;

            case "tan":
                event.getChannel().sendMessage("Result: **" + Math.tan(Integer.parseInt(args[2])) + "**").queue();
                break;
        }
    }
}