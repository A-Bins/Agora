package com.bins.agora.structure.objects

import com.bins.agora.Agora.Companion.goldRush
import com.bins.agora.Agora.Companion.ins
import com.bins.ruins.utilities.Receiver.bb
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import kotlin.collections.ArrayList

//        if(!isStarting) return false
//        if(waiters.size != 8) return false
class GoldRush {
    val waiters: ArrayList<Player> = arrayListOf()
    var isStarting = false
    fun start(): Boolean{
        "?".bb()
        timer(10,
            {
                return@timer it != 8
            },
            {
                return@timer when {
                    it == 10 -> "§a"
                    (5 downTo 4).contains(it) -> "§6"
                    it == 3 -> "§e"
                    else -> "§c"
                }
            },
            {
                isStarting = true
                Bukkit.broadcastMessage("끝!")
            })
            return true
        }
    private fun timer(timer: Int, isCancelled: (Int) -> Boolean, title: (Int) -> String, end: () -> Any) {
        ins.server.scheduler.runTaskLater(ins, Runnable {
            if(isCancelled(waiters.size)) return@Runnable
            if(timer == 10 || (5 downTo 1).contains(timer)) goldRush.waiters.forEach { it.sendTitle("","시작까지 ${title(timer)}${timer}초 남았습니다..", 20, 20, 20)}
            if(timer != 0) timer(timer-1, isCancelled, title, end)
            else end()
        }, 20)
    }
}