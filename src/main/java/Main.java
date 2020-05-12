import chrisngyn.github.rosie.CommandsHandler;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        // read Discord token from file and build bot instance; an EventListener listen to events and transmits data to CommandsHandler class
        // Run --> Main --> CommandsHandler --> call constructor of other classes --> puts objects into map --> execute when prompted
        try {
            FileReader reader = new FileReader("discordtoken.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String token = buffer.readLine();
            buffer.close();
            reader.close();
            JDA build = new JDABuilder(token).build();
            build.addEventListener(new CommandsHandler());
        } catch (Exception e) {
            System.err.println("Error reading file and/or building the bot. Perhaps your file name is off, or your token is invalid.");
        }
    }
}