package com.bins.agora.cmds

import com.bins.agora.Agora
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Test: CommandExecutor {
    override fun onCommand(p: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(p is Player){
            Agora.goldRush.start()
        }
        return false
    }
}