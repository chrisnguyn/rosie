import chrisngyn.github.rosie.CommandsHandler;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {

        // read Discord token from file and build
        String file = "discordtoken.txt";
        FileReader reader = new FileReader(file);
        BufferedReader buffer = new BufferedReader(reader);
        String token = buffer.readLine();
        buffer.close();
        reader.close();
        JDA build = new JDABuilder(token).build();

        // an EventListener tells JDA to listen to events and transmit the data to CommandsHandler class through a websocket
        // Main --> CommandsHandler --> calls constructor of all other classes --> put into map --> execute when needed
        build.addEventListener(new CommandsHandler());
    }
}