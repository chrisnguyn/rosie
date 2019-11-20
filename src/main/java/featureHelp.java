import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class featureHelp extends ListenerAdapter{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        // template: !help

        if (event.getMessage().getContentRaw().equalsIgnoreCase("!help")) {
            event.getChannel().sendMessage("").queue();
        }
    }
}