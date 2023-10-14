package gg.tropic.practice.reports

import gg.tropic.practice.games.GameReport
import net.evilblock.cubed.ScalaCommonsSpigot
import net.evilblock.cubed.serializers.Serializers
import java.util.UUID
import java.util.concurrent.CompletableFuture

/**
 * @author GrowlyX
 * @since 1/2/2023
 */
object GameReportService
{
    fun loadSnapshotsForParticipant(uniqueId: UUID): CompletableFuture<List<GameReport>>
    {
        return CompletableFuture
            .supplyAsync {
                ScalaCommonsSpigot.instance.kvConnection.sync()
                    .keys("tropicpractice:snapshots:players:$uniqueId:matches:*")
            }
            .thenApply {
                it.map { key ->
                    // better than calling another GET
                    key.split(":")[5]
                }
            }
            .thenApply {
                it
                    .mapNotNull { uniqueId ->
                        ScalaCommonsSpigot.instance.kvConnection.sync()
                            .get("tropicpractice:snapshots:matches:$uniqueId")
                    }
                    .mapNotNull {
                        kotlin
                            .runCatching {
                                Serializers.gson.fromJson(it, GameReport::class.java)
                            }
                            .getOrNull()
                    }
            }
            .exceptionally {
                it.printStackTrace()
                return@exceptionally listOf()
            }
    }
}