package com.bins.agora

import com.bins.agora.cmds.Lobby
import com.bins.agora.cmds.Test
import com.bins.agora.events.interacts.EvtNPCRightClick
import com.bins.agora.structure.objects.GoldRush
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Agora : JavaPlugin() {
    companion object {
        lateinit var ins: Agora
        val goldRush = GoldRush()
    }
    override fun onEnable() {
        ins = this
        getCommand("test")?.apply {
            setExecutor(Test())
        }
        getCommand("lobby")?.apply {
            setExecutor(Lobby())
        }
        server.pluginManager.apply {
            arrayOf(EvtNPCRightClick()).forEach {
                registerEvents(it, this@Agora)
            }
        }
        server.scheduler.runTask(this, Runnable {
            Bukkit.broadcastMessage("얍")
        })
    }

    override fun onDisable() {
    }
}

