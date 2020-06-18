package chrisngyn.github.rosie;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        // read Discord token from file and build Discord bot instance. An EventListener listen to events and transmits data to CommandsHandler class
        // program flow: Run --> Main --> CommandsHandler --> call constructors of classes --> put objects into map --> look up and execute when prompted
        try {
            FileReader reader = new FileReader("discordtoken.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String token = buffer.readLine();
            buffer.close();
            reader.close();
            JDA bot = new JDABuilder(token).build();
            bot.addEventListener(new CommandsHandler()); // one event listener > 20
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\n Error reading file and/or building the bot. Maybe your file name is off, or your token is invalid.");
        }
    }
}