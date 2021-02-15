package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import java.util.List;
import io.github.cdimascio.dotenv.Dotenv;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.SubredditSort;
import net.dean.jraw.models.TimePeriod;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;
import net.dean.jraw.RedditClient;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class RedditSearch extends Command {
    Dotenv env = Dotenv.load();
    protected String documentation = "**r!reddit** [name of subreddit] - responds with top five current hottest posts of that Reddit.";
    private String error = "";
    private Credentials oauth;
    private UserAgent user;
    private RedditClient client;  // https://mattbdean.gitbooks.io/jraw/content/quickstart.html

    public RedditSearch() {
        super("reddit");
        try {
            this.oauth = Credentials.script(env.get("REDDIT_USER"), env.get("REDDIT_PW"), env.get("REDDIT_PUBLIC_KEY"), env.get("REDDIT_PRIVATE_KEY"));
            this.user = new UserAgent("bot", "Rosie", "1.0.0", "hemp3n");
            this.client = OAuthHelper.automatic(new OkHttpNetworkAdapter(user), oauth);
        } catch (Exception e) {
            System.err.println(e + "\n Error trying to build Reddit instance.");
            this.error = "Error executing the Reddit command. Please contact the bot creator.";
        }
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        if (!this.error.isEmpty()) {
            event.getChannel().sendMessage(error).queue();
            return;
        }

        List<Submission> posts = getRedditPosts(args[1]);
        for (Submission post : posts) {
            String returnedPosts = "";
            returnedPosts += post.getTitle() + "\n" + "http://reddit.com" + post.getPermalink() + "\n";
            event.getChannel().sendMessage(returnedPosts).queue();
        }
    }

    public List<Submission> getRedditPosts(String subreddit) {
        DefaultPaginator<Submission> paginator = client.subreddit(subreddit).posts()
                .limit(3)
                .sorting(SubredditSort.HOT)
                .timePeriod(TimePeriod.DAY)
                .build();
        Listing<Submission> topMostPopular = paginator.next();
        return topMostPopular.getChildren();
    }
}

// more docs: https://mattbdean.gitbooks.io/jraw/pagination.html
// getTitle - title of post
// getPermalink - link to the post. Need to append it to "htttp://reddit.com" first if you want it to appear as a hyperlink in Discord
// getSelfText - the self text in the post, if it exists. Otherwise null. Can use "isSelfPost" to see if it's a text only post
// getUrl - if the post contains a picture, then a link directly to that media