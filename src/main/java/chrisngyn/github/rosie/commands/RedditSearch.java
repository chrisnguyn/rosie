package chrisngyn.github.rosie.commands;

import chrisngyn.github.rosie.Command;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.SubredditSort;
import net.dean.jraw.models.TimePeriod;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class RedditSearch extends Command {
    protected String documentation = "**!reddit** [name of subreddit] - responds with top five current hottest posts of that Reddit.";
    private String error = ""; // in our .execute(), if this is EMPTY, we're good. else, there was an error in building the bot
    private Credentials oauth;
    private UserAgent user;
    private RedditClient client;
    private String[] credentials = new String[4];

    public RedditSearch() {
        super("reddit");
        try {
            FileReader reader = new FileReader("./credentials/redditcredentials.txt");
            BufferedReader buffer = new BufferedReader(reader);
            this.credentials[0] = buffer.readLine();
            this.credentials[1] = buffer.readLine();
            this.credentials[2] = buffer.readLine();
            this.credentials[3] = buffer.readLine();
            buffer.close();
            reader.close();
            this.oauth = Credentials.script(credentials[0], credentials[1], credentials[2], credentials[3]);
            this.user = new UserAgent("bot", "Rosie", "1.0.0", "hemp3n");
            this.client = OAuthHelper.automatic(new OkHttpNetworkAdapter(user), oauth); // create Reddit bot instance
        } catch (Exception e) {
            System.err.println("Error trying to build RedditSearch instance.");
            this.error = "Error executing the Reddit command. Please contact the bot creator.";
        }
    }

    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        if (!this.error.isEmpty()) {
            event.getChannel().sendMessage(error).queue(); // if it DID fail to create, our error string won't be empty.
            return;
        }

        List<Submission> posts = getRedditPosts(args[1]); // get subreddit from user message
        for (Submission post : posts) { // error checking with status code 403? 404?
            String returnedPosts = "";
            returnedPosts += post.getTitle() + "\n" + "http://reddit.com" + post.getPermalink() + "\n"; // build the string to be displayed as hyperlink in Discord
            event.getChannel().sendMessage(returnedPosts).queue();
        }
    }

    public List<Submission> getRedditPosts(String subreddit) { // https://mattbdean.gitbooks.io/jraw/pagination.html
        DefaultPaginator<Submission> paginator = client.subreddit(subreddit).posts()
                .limit(3)
                .sorting(SubredditSort.HOT)
                .timePeriod(TimePeriod.DAY)
                .build();
        Listing<Submission> topMostPopular = paginator.next();
        return topMostPopular.getChildren();
    }
}

// Functions from JRAW documentation:
// getTitle - title of post
// getPermalink - link to the post. Need to append it to "htttp://reddit.com" first if you want it to appear as a hyperlink in Discord
// getSelfText - the self text in the post, if it exists. Otherwise null. Can use "isSelfPost" to see if it's a text only post
// getUrl - if the post contains a picture, then a link directly to that media