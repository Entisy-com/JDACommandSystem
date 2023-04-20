## Usage/Example

```java
public class Bot extends ListenerAdapter {
    public static void main(String[] args) throws FileNotFoundException {
        var builder = DefaultShardManagerBuilder.createDefault("TOKEN HERE");
        builder.enableIntents(
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT
        );
        
        JDACommandSystem.get().setup();
        setupCommands();
        
        builder.addEventListeners(new Bot());
        builder.build();
    }
    
    private static void setupCommands() {
        CommandManager.get().addCommand("test", (args1, message, chat, author) -> chat.sendMessage("lol").complete() != null);
        SlashCommandManager.get().addCommand("test", (command, chat, author) -> chat.sendMessage("lol").complete() != null);
        // ...
    }
    
    // Commands
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // prefix logic...
        var args = event.getMessage().getContentRaw().trim().split(" ");
        CommandManager.get().execute(args[0], event.getMessage(), event.getChannel().asTextChannel(), event.getMember());
    }
    
    // Slash Commands
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        SlashCommandManager.get().execute(event.getName(), event);
    }
}
```
