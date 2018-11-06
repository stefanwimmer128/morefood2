package eu.stefanwimmer128.morefood2.api

import eu.stefanwimmer128.morefood2.api.registry.BlockRegistry
import eu.stefanwimmer128.morefood2.api.registry.ItemRegistry
import org.apache.logging.log4j.Logger

interface MoreFood2API {
    companion object {
        const val MODID = "morefood2"
        const val VERSION = "#{VERSION}"
        const val NAME = "MoreFood 2"
        
        @JvmStatic
        val instance: MoreFood2API? by lazy {
            try {
                Class.forName("eu.stefanwimmer128.morefood2.MoreFood2").getField("instance").get(null) as MoreFood2API
            } catch (e: Exception) {
                null
            }
        }
    }
    
    val logger: Logger
    
    val blocks: BlockRegistry
    val items: ItemRegistry
}
