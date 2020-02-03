package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class AdvancedArithmetic extends Command {

    // I hope you're happy Bonnie smh. Need me to make these features because you're too lazy to do math on your own. >:-\

    protected String documentation = "**!moremath quadratic** [number1] [number2] [number3] - quadratic formula, enter values to get the roots of an equation. \n" +
            "> **!moremath pythagorean** [number1] [number2] - pythagorean theorem, enter two side lengths to get the hypotenuse. \n" +
            "> **!moremath** [**sin**, **cos**, **tan**, **c2f**, **f2c**] [number] - sin, cos, tan, celsius to fahrenheit, and fahrenheit to celsius conversions.";

    public AdvancedArithmetic() {
        super("moremath");
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) { // !moremath quadratic, pythagorean, sin, cos, tan, c2f, f2c
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

            case "c2f": // !moremath c2f 70
                double c2f = ((Double.parseDouble(args[2]) * 9) / 5) + 32;
                event.getChannel().sendMessage(args[2] + " degrees Celsius to Fahrenheit is: **" + c2f + "**").queue();
                break;

            case "f2c":
                double f2c = ((Double.parseDouble(args[2]) - 32) * 5) / 9;
                event.getChannel().sendMessage(args[2] + " degrees Fahrenheit to Celsius is: **" + f2c + "**").queue();
                break;
        }
    }
}