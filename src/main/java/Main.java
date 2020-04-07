import chrisngyn.github.rosie.CommandsHandler;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        // read Discord token from file and build
        // an EventListener tells JDA to listen to events and transmit the data to CommandsHandler class through a websocket
        // Run --> Main --> CommandsHandler --> calls constructor of other classes --> puts into map --> execute when prompted
        try {
            FileReader reader = new FileReader("discordtoken.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String token = buffer.readLine();
            buffer.close();
            reader.close();
            JDA build = new JDABuilder(token).build();
            build.addEventListener(new CommandsHandler());
        } catch (Exception e) {
            System.err.println("Error reading file and/or building bot. Perhaps your file name is off, or your token is invalid?");
        }
    }
}