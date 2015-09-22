# Scooter-User-Generator
Java App. Queries Meetup to get the event list

# Usage
Run generator, and passes in 3 arguments. Group Name, meetup date and private key.

example 
java Generator pei-developers 2015-09-22 jlk234jk234lkj34lk2j4lk23

# Requires
Java 8. 
Also some classpath things with lib/ folder. App uses gson to do some deserialization. Eventually we 
can move this to scripts to deal with classpaths 

# Meetup key
Log into the meetup api site
http://www.meetup.com/meetup_api/
Go to the API Key link (https://secure.meetup.com/meetup_api/key/)
Copy the key

Meetup requires every request have a key

# Disclaimer
Doesn't currently exclude elders. Not sure how those are flagged in the meetup api
