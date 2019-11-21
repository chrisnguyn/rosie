import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class featureReminder extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] messageSent = event.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("Ping!")) {
            sendPrivateMessage(event.getAuthor(), "Pong!");
        }
    }

    public void sendPrivateMessage(User user, String content) {
        user.openPrivateChannel().queue((channel) -> {
            channel.sendMessage(content).queue();
        });
    }
}