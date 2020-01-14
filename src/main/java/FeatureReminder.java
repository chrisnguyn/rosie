import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.*;

public class FeatureReminder extends ListenerAdapter {

    // How to call: !remindme [number] [time unit] [query]

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] messageSent = event.getMessage().getContentRaw().split(" ");
        String reminder = "Hello! You wanted me to remind you of this: ";

        if (messageSent[0].equalsIgnoreCase("!remindme")) {

            for (int i = 3; i < messageSent.length; i++) { // 0 is command call, 1 is number, 2 is unit
                reminder += messageSent[i] + " ";
            }

            if (messageSent[2].equalsIgnoreCase("s") || messageSent[2].equalsIgnoreCase("seconds")) {
                remind(event.getAuthor(), reminder, Integer.parseInt(messageSent[1]), SECONDS);
                event.getChannel().sendMessage("Your reminder has been set!").queue();
            }

            else if (messageSent[2].equalsIgnoreCase("m") || messageSent[2].equalsIgnoreCase("minutes")) {
                remind(event.getAuthor(), reminder, Integer.parseInt(messageSent[1]), MINUTES);
                event.getChannel().sendMessage("Your reminder has been set!").queue();
            }

            else if (messageSent[2].equalsIgnoreCase("h") || messageSent[2].equalsIgnoreCase("hours")) {
                remind(event.getAuthor(), reminder, Integer.parseInt(messageSent[1]), HOURS);
                event.getChannel().sendMessage("Your reminder has been set!").queue();
            }
        }
    }

    public void remind(User user, String reminder, long delay, TimeUnit unit) { // helper method
        // IF NOT WORKING, IT'S MOST LIKELY BECAUSE THE PROJECT LANGUAGE YOU HAVE SET DOES NOT SUPPORT LAMBDA EXPRESSIONS.
        // Fix 1. File -> Settings -> Project bytecode version: 8, and delete all per-module bytecode versions
        // Fix 2. File -> Project Structure -> Language level 8
        user.openPrivateChannel().queue (
                (channel) -> channel.sendMessage(reminder).queueAfter(delay, unit)
        );
    }
}

//    Documentation example code:
//    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
//        String[] messageSent = event.getMessage().getContentRaw().split(" ");
//
//        if (messageSent[0].equalsIgnoreCase("Ping!")) {
//            sendPrivateMessage(event.getAuthor(), "Pong!");
//        }
//    }
//
//    public void sendPrivateMessage(User user, String content) {
//        user.openPrivateChannel().queue((channel) -> {
//            channel.sendMessage(content).queue();
//        });
//    }