package com.entisy.manager;

import com.entisy.JDACommandSystem;
import com.entisy.interfaces.ISlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlashCommandManager {
    private final Map<String, ISlashCommand> commands;
    private static SlashCommandManager instance = null;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SlashCommandManager() {
        this.commands = new ConcurrentHashMap<>();
    }

    public static SlashCommandManager get() {
        if (SlashCommandManager.instance == null)
            SlashCommandManager.instance = new SlashCommandManager();
        return SlashCommandManager.instance;
    }

    public boolean addCommand(String name, ISlashCommand command) {
        return this.commands.put(name, command) != null;
    }

    public boolean execute(String name, SlashCommandInteractionEvent event) {
        var command = this.commands.get(name);

        if (command == null) {
            if (JDACommandSystem.get().getConfig().isUnknown_command())
                logger.error("Unknown command '{}'", name);
            return false;
        }

        var result = command.execute(event, event.getChannel().asTextChannel(), event.getMember());

        if (result)
            return true;

        if (JDACommandSystem.get().getConfig().isExecution_error())
            logger.error("Unable to execute command '{}'", name);
        return false;
    }
}
