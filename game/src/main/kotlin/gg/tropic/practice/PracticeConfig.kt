package gg.tropic.practice

import gg.scala.commons.config.annotations.Config
import org.bukkit.Bukkit
import org.bukkit.Location

/**
 * @author GrowlyX
 * @since 9/11/2022
 */
@Config("config")
class PracticeConfig
{
    val waitingLocation = Location(
        Bukkit.getWorld("world"),
        243.500, 52.000, 1348.500
    )
}