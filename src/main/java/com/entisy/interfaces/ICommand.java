package com.entisy.interfaces;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public interface ICommand {
    public boolean execute(String[] args, Message message, TextChannel chat, Member author);
}
