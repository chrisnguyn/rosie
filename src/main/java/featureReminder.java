import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.*;

public class featureReminder extends ListenerAdapter {

    // template: !remindme [time] [unit] [stuff], [arg1] [arg2] [arg3] [rest of args]

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] messageSent = event.getMessage().getContentRaw().split(" ");
        String reminder = "Hello! You wanted me to remind you of this: ";

        if (messageSent[0].equalsIgnoreCase("!remindme")) {

            for (int i = 3; i < messageSent.length; i++) {
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

    public void remind(User user, String reminder, long delay, TimeUnit unit) {
        user.openPrivateChannel().queue (
                (channel) -> channel.sendMessage(reminder).queueAfter(delay, unit)
        );
    }
}

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