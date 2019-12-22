import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import java.io.BufferedReader;
import java.io.FileReader;

public class main {
    public static void main(String[] args) throws Exception {

        String fileName = "discordToken.txt";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String token = bufferedReader.readLine();
        JDA build = new JDABuilder(token).build();
        bufferedReader.close();

        build.addEventListener(new featureHelp());
        build.addEventListener(new featureArithmetic());
        build.addEventListener(new featureRNG());
        build.addEventListener(new featureReminder());
        build.addEventListener(new featureReddit());
        build.addEventListener(new featureGoogleSearch());
        build.addEventListener(new featureTODO());
    }
}