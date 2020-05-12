# Rosie
Rosie is a personal assistant Discord bot that aims to improve quality of life.

![](rosie.gif)

# Features - last updated 04/08/2020

### HELP
+ r!ping - tests to see if Rosie is up and running. Responds with "Pong!" if so.
+ r!help - responds with all features and documentation on how to use each command.
+ r!serverinvite - generates an invitation link to the server that expires in 10 minutes.
+ r!botinvite - generates a link to invite Rosie to more Discord servers.

### ARITHMETIC
+ r!calculate [number1] [+, -, *, /, %, ^] [number2] - computes basic math.
+ r!moremath quadratic [number1] [number2] [number3] - quadratic formula; enter values to get the roots of an equation.
+ r!moremath pythagorean [number1] [number2] - pythagorean theorem; enter two side lengths to get the hypotenuse. 
+ r!moremath [sin, cos, tan, c2f, f2c] [number] - computes more math operations; C2F and F2C are Celsius to Fahrenheit and Fahrenheit to Celsius respectively.

### RANDOM RESPONSES
+ r!random coin - flip a coin.
+ r!random dice - roll a die.
+ r!random card - draw a card.
+ r!random choose option1 | option2 | option3 | ... - choose an option.
+ r!random between [number1] [number2] - get a number in that range (inclusive).
+ r!8ball [anything] - get a random Magic 8-Ball response!

### REMIND ME
+ r!remindme [number] [s, seconds, m, minutes, h, hours] [reminder] - set a reminder; Rosie will send you a direct message reminding you when time is up.

### REDDIT
+ r!reddit [name of subreddit] - responds with the top five current hottest posts of a subreddit.  

### GOOGLE SEARCH
+ r!googlesearch [query] - responds with the top three links to come up in Google.  

### TO DO LIST (supports basic CRUD features)
+ r!todo add - add an entry to your todo list.
+ r!todo view - view your current todo list.
+ r!todo complete [existing entry] - mark an entry as completed.
+ r!todo remove [existing entry] - delete an entry from your to do list.

# Configure
+ discordtoken.txt - your Discord bot token (get this from Discord developer portal)
+ googlecredentials.txt - your search engine ID and API key (get this from the Custom Search API page).
+ MySQLconnector.txt - your database URL, username, and password. Make sure you have a MySQL server installed, and the schema is configured.
+ redditcredentials.txt - your Reddit username, password, personal use script, and bot secret token (under your Reddit account, click on Apps and register an instance).
