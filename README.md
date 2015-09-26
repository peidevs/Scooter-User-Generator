# Scooter-User-Generator
Java App. Queries Meetup to get the event list

# To Build
* install [Gradle](http://gradle.org/gradle-download/)
* type 'gradle scooterBuild'

# To Run
* if built with Gradle, 'cd staging'
* Run generator with 3 arguments: Group Name, meetup date and private key
* example: java -jar ScooterUserGenerator.jar PEI-Developers 2015-10-08 API_KEY 

Example 
```Batchfile
java Generator pei-developers 2015-09-22 jlk234jk234lkj34lk2j4lk23
```
(the key listed is gibberish)

# Requires
Java 8. 
Also some classpath things with lib/ folder. App uses gson to do some deserialization. Eventually we 
can move this to scripts to deal with classpaths 

# Meetup key
* Log into the meetup api site (http://www.meetup.com/meetup_api/)
* Go to the API Key link (https://secure.meetup.com/meetup_api/key/)
* Click the lock to show the key
* Copy the key

This key is your key which is assigned to your meetup profile

Meetup requires every request have a key

# Disclaimer
Doesn't currently exclude elders. Not sure how those are flagged in the meetup api
