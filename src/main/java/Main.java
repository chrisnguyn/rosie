import chrisngyn.github.rosie.CommandsHandler;
import chrisngyn.github.rosie.commands.Ping;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {

        // Read Discord token from file and build
        String fileName = "discordToken.txt";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String token = bufferedReader.readLine();
        JDA build = new JDABuilder(token).build();
        bufferedReader.close();
        fileReader.close();
//
//        build.addEventListener(new FeatureHelp());
//        build.addEventListener(new FeatureArithmetic());
//        build.addEventListener(new FeatureRNG());
//        build.addEventListener(new FeatureReminder());
//        build.addEventListener(new FeatureReddit());
//        build.addEventListener(new FeatureGoogleSearch());
//        build.addEventListener(new FeatureTODO());

//        new Ping();
//        new Ping().execute(null, null);

        build.addEventListener(new CommandsHandler());
    }
}