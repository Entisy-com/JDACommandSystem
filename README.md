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
        CommandManager.get().addCommand("test", new TestCommand());
        SlashCommandManager.get().addCommand("test", new TestSlashCommand());
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        event.getGuild().updateCommands().addCommands(
                Commands.slash("test", "this is a test")
        ).queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        var args = event.getMessage().getContentRaw().trim().split(" ");
        CommandManager.get().execute(args[0], event.getMessage(), event.getChannel().asTextChannel(), event.getMember());
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        SlashCommandManager.get().execute(event.getName(), event);
    }
}
```
