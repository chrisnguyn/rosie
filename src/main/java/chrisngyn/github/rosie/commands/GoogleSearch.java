package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class GoogleSearch extends Command {

    // How to call: !googlesearch [query]
    // https://www.googleapis.com/customsearch/v1?q=[QUERY_HERE]&cx=[SEARCH_ENGINE_ID_HERE]&num=3&key=[API_KEY_HERE]

    public GoogleSearch() {
        super("googlesearch");
    }

    public void execute(GuildMessageReceivedEvent event, String[] args) {

    }

}