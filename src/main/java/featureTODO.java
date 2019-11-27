import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class featureTODO extends ListenerAdapter {

    /* !todoadd !todoview !todoremove [id] */
    /* todoList(id, entry, timeAdded, isCompleted)*/

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] messageSent = event.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!todoadd")) {

        }
        else if (messageSent[0].equalsIgnoreCase("!todoview")) {

        }
        else if (messageSent[0].equalsIgnoreCase("todoremove")) {

        }
    }
}
