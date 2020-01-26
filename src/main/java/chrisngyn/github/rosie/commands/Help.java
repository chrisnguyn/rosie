package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Help extends Command {

    public Help() {
        super("Help");
    }

    // needs to be updated! 01 22 2020 12:50am
    public void execute(GuildMessageReceivedEvent event, String[] args) {

        /* Discord uses special formatting for their text. This is just me adapting to that. Please ignore why it is so messy. */

        // EXTREMELY OUTDATED! 01 25 2020

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
                "> !calculate [sin, cos, tan] [number] - compute trigonometric values.\n" +
                "> !quadratic [a value] [b value] [c value] - compute the quadratic formula.\n" +
                "> !pythagorean [side length 1] [side length 2] - compute the hypotenuse of a triangle given two side lengths.\n" +
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