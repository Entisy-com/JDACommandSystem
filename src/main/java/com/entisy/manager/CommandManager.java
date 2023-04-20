package com.entisy.manager;

import com.entisy.JDACommandSystem;
import com.entisy.interfaces.ICommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {
    private final Map<String, ICommand> commands;
    private static CommandManager instance = null;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private CommandManager() {
        this.commands = new ConcurrentHashMap<>();
    }

    public static CommandManager get() {
        if (CommandManager.instance == null)
            CommandManager.instance = new CommandManager();
        return CommandManager.instance;
    }

    public boolean addCommand(String name, ICommand command) {
        return this.commands.put(name, command) != null;
    }

    public boolean execute(String name, Message message, TextChannel chat, Member author) {
        var args = message.getContentRaw().trim().split(" ");
        var command = this.commands.get(name);

        if (command == null) {
            if (JDACommandSystem.get().getConfig().isUnknown_command())
                logger.error("Unknown command '{}'", name);
            return false;
        }

        var result = command.execute(args, message, chat, author);

        if (result)
            return true;

        if (JDACommandSystem.get().getConfig().isExecution_error())
            logger.error("Unable to execute command '{}'", name);
        return false;
    }
}
