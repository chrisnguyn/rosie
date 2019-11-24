import net.dean.jraw.RedditClient;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.*;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class featureReddit extends ListenerAdapter {
    Credentials oauthCreds;
    UserAgent userAgent;
    RedditClient redditClient;

    public featureReddit() throws Exception {
        String credentials[] = this.getRedditCredentials();
        this.oauthCreds = Credentials.script(credentials[0], credentials[1], credentials[2], credentials[3]);
        this.userAgent = new UserAgent("bot", "Rosie", "1.0.0", "hemp3n");
        this.redditClient = OAuthHelper.automatic(new OkHttpNetworkAdapter(userAgent), oauthCreds);
    }

    public String[] getRedditCredentials() throws Exception {
        String fileName = "redditCredentials.txt";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String username = bufferedReader.readLine();
        String password = bufferedReader.readLine();
        String clientId = bufferedReader.readLine();
        String clientSecret = bufferedReader.readLine();
        bufferedReader.close();

        String credentials[] = {username, password, clientId, clientSecret};
        return credentials;
    }

    // getting reddit posts

    public List<Submission> getRedditPosts(String subredditName) {
        DefaultPaginator<Submission> paginator = redditClient.subreddit(subredditName).posts()
                .limit(3)
                .sorting(SubredditSort.HOT)
                .timePeriod(TimePeriod.DAY)
                .build();

        Listing<Submission> topMostPopular = paginator.next();
        return topMostPopular.getChildren();
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] messageSent = event.getMessage().getContentRaw().split(" ");

        if (messageSent[0].equalsIgnoreCase("!reddit")) {
            List<Submission> posts = getRedditPosts(messageSent[1]);

            for (Submission x : posts) {
                String returnedPosts = "";
                returnedPosts += x.getTitle() + "\n" + "http://reddit.com" + x.getPermalink() + "\n";
                event.getChannel().sendMessage(returnedPosts).queue();
            }
        }
    }
}

/*
getTitle - title of post
getPermalink - link to the post. Need to append it to "htttp://reddit.com" first if you want it to appear as a hyperlink in Discord
getSelfText - the self text in the post, if it exists. Otherwise null. Can use "isSelfPost" to see if it's a text only post
getUrl - if the post contains a picture, then a link directly to that media
*/