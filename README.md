# Rosie
A [Discord](https://discord.com) bot with ~20 features that serves as a centralized platform to perform many actions in one place, from retrieving Google search results to Reddit posts, no more flipping through 50 tabs and applications to do what you want!
    
Here's a short GIF demoing some of Rosie's features:
<p align="center"><img src="https://github.com/chrisngyn/Rosie/blob/master/media/rosie.gif" width="80%"></p>

## Commands

### HELP
+ **r!ping** - tests to see if Rosie is up and running. Responds with "Pong!" if so.
+ **r!help** - responds with all features and documentation on how to use each command.
+ **r!serverinvite** - generates an invitation link to the server that expires in 10 minutes.
+ **r!botinvite** - generates a link to invite Rosie to more Discord servers.

### ARITHMETIC
+ **r!calculate** [number1] [+, -, *, /, %, ^] [number2] - computes basic math.
+ **r!moremath quadratic** [number1] [number2] [number3] - quadratic formula; enter values to get the roots of an equation.
+ **r!moremath pythagorean** [number1] [number2] - pythagorean theorem; enter two side lengths to get the hypotenuse. 
+ **r!moremath** [sin, cos, tan, c2f, f2c] [number] - computes more math operations; C2F and F2C are Celsius to Fahrenheit and Fahrenheit to Celsius respectively.

### RANDOM RESPONSES
+ **r!random coin** - flip a coin.
+ **r!random dice** - roll a die.
+ **r!random card** - draw a card.
+ **r!random choose** option1 | option2 | option3 | ... - choose an option.
+ **r!random between** [number1] [number2] - get a number in that range (inclusive).
+ **r!8ball [anything]** - get a random Magic 8-Ball response!

### REMIND ME
+ **r!remindme** [number] [s, seconds, m, minutes, h, hours] [reminder] - set a reminder; Rosie will send you a direct message reminding you when time is up.

### REDDIT
+ **r!reddit** [name of subreddit] - responds with the top five current hottest posts of a subreddit.  

### GOOGLE SEARCH
+ **r!googlesearch** [query] - responds with the top three links to come up in Google.  

### TO DO LIST (supports basic CRUD features)
+ **r!todo add** [query] - add an entry to your todo list.
+ **r!todo view** - view your current todo list.
+ **r!todo complete** [existing entry] - mark an entry as completed.
+ **r!todo remove** [existing entry] - delete an entry from your to do list.

## Configuration
1. Go to `.env-sample`, provide the following keys, and rename file to `.env`:  
  
    [Get your Discord token](https://discord.com/developers/applications)  
    `DISCORD_TOKEN=replace_me`  
      
    [Get your Reddit keys](https://www.reddit.com/prefs/apps)  
    `REDDIT_USER=replace_me`  
    `REDDIT_PW=replace_me`  
    `REDDIT_PUBLIC_KEY=replace_me`  
    `REDDIT_PRIVATE_KEY=replace_me`  
      
    [Get your Google Custom Search keys](https://developers.google.com/custom-search/v1/introduction)  
    `GCSE_ENGINE_ID=replace_me`  
    `GCSE_API_KEY=replace_me`  
      
    [Install MySQL](https://dev.mysql.com/downloads/installer/)  
    `MYSQL_URL=replace_me`  
    `MYSQL_USER=replace_me`  
    `MYSQL_PW=replace_me`

2. Go to `docker-compose-sample.yml`, replace lines 11 and 12 with your respective DB name and password, and rename file to `docker-compose.yml`

3. `docker build -t rosiebot .`

4. `docker-compose up -d`

## Miscellaneous
1. If you ever need to recompile the `.jar` file

      `mvn package`
      
2. Two `.jar` files will be generated in the `target/` directory - rename the one with dependencies to `rosie.jar` and move it to the root directory
