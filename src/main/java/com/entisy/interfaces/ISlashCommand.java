package com.entisy.interfaces;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface ISlashCommand {
    public boolean execute(SlashCommandInteractionEvent command, TextChannel chat, Member author);
}
