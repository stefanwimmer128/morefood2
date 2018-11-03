package eu.stefanwimmer128.morefood2.event

import eu.stefanwimmer128.morefood2.MoreFood2
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class RegistryHandler {
    @SubscribeEvent
    fun registerBlocks(e: RegistryEvent.Register<Block>) {
        MoreFood2.instance.blocks.registerEntries(e.registry)
    }
    
    @SubscribeEvent
    fun registerItems(e: RegistryEvent.Register<Item>) {
        MoreFood2.instance.items.registerEntries(e.registry)
    }
    
    @SubscribeEvent
    fun registerModels(e: ModelRegistryEvent) {
        MoreFood2.instance.blocks.registerModels()
        MoreFood2.instance.items.registerModels()
    }
}
