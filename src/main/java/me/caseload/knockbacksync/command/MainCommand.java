package me.caseload.knockbacksync.command;

import dev.jorel.commandapi.CommandAPICommand;
import me.caseload.knockbacksync.command.subcommand.MaxDamageAgeSubcommand;
import me.caseload.knockbacksync.command.subcommand.OffsetSubcommand;
import me.caseload.knockbacksync.command.subcommand.PingSubcommand;
import me.caseload.knockbacksync.command.subcommand.ToggleSubcommand;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;

public class MainCommand {

    public void register() {
        new CommandAPICommand("knockbacksync")
                .withAliases("kbsync")
                .withSubcommand(new PingSubcommand().getCommand())
                .withSubcommand(new ToggleSubcommand().getCommand())
                .withSubcommand(new OffsetSubcommand().getCommand())
                .withSubcommand(new MaxDamageAgeSubcommand().getCommand())
                .executes((sender, args) -> {
                    sender.sendMessage(
                            ChatColor.translateAlternateColorCodes(
                                    '&',
                                    "&eThis server is running the &6&lValedKnockbackSync &ePlugin by the &6&lValed Development Team&e. &fhttps://github.com/valed-mc/knockback-sync\n&eA fork of &6&lKnockbackSync &eby &6&lCASELOAD7000&e."
                            ));
                })
                .register();
    }
}