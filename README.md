Social Networking Kata
----------------------

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below.

### Scenarios

**Posting**: Alice can publish messages to a personal timeline

> \> Alice -> I love the weather today    
> \> Bob -> Damn! We lost!     
> \> Bob -> Good game though.    

**Reading**: Bob can view Alice’s timeline

> \> Alice    
> \> I love the weather today (5 minutes ago)    
> \> Bob    
> \> Good game though. (1 minute ago)     
> \> Damn! We lost! (2 minutes ago)    

**Following**: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions

> \> Charlie -> I'm in New York today! Anyone wants to have a coffee?     
> \> Charlie follows Alice    
> \> Charlie wall    
> \> Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)    
> \> Alice - I love the weather today (5 minutes ago)    

> \> Charlie follows Bob    
> \> Charlie wall    
> \> Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)     
> \> Bob - Good game though. (1 minute ago)     
> \> Bob - Damn! We lost! (2 minutes ago)     
> \> Alice - I love the weather today (5 minutes ago)    

### General requirements 

- Application must use the console for input and output; 
- User submits commands to the application: 
    - posting: \<user name> -> \<message> 
    - reading: \<user name> 
    - following: \<user name> follows \<another user> 
    - wall: \<user name> wall 
- Don't worry about handling any exceptions or invalid commands. Assume that the user will always type the correct commands. Just focus on the sunny day scenarios.
- Use whatever language and frameworks you want. (provide instructions on how to run the application)
- **NOTE:** "posting:", "reading:", "following:" and "wall:" are not part of the command. All commands start with the user name.

**IMPORTANT:**  Implement the requirements focusing on **writing the best code** you can produce.

**CODE SUBMISSION:** Add the code to your own Github account and send us the link.

## Building modules from commandline 

+ Cleaning and recompiling modules in module output folder

```$sh
rm -rf outcli/*;javac -d outcli --module-source-path src/modules -m domain,presentation,application,infrastructure
```

+ Compile the normal src/main with modules on the classpath
```$sh
javac -d out -cp outcli/domain:outcli/application:outcli/infrastructure:outcli/presentation  src/main/java/**/*.java
```

+ Run the java application, module path with class path

```$sh
java --module-path outcli -cp out --add-modules domain,application,infrastructure,presentation io.tripled.social.client.SocialNetworkApplication
```

+ If the application runs,

```text
guido -> toffe jongen
\q
``` 

+ Running application without classpath
```$sh
java --module-path outcli --add-modules domain,application,infrastructure,presentation io.tripled.social.client.SocialNetworkApplication
```
