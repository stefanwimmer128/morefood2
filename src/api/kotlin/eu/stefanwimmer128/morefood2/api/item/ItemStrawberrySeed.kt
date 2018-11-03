package eu.stefanwimmer128.morefood2.api.item

import net.minecraft.block.Block
import net.minecraft.item.ItemSeeds
import net.minecraft.util.ResourceLocation

open class ItemStrawberrySeed(block: Block, val name: String? = null, val resourceDomain: String? = null): ItemSeeds(block, null) {
    init {
        if (name != null) {
            unlocalizedName = name
            registryName = if (resourceDomain != null) ResourceLocation(resourceDomain, name) else ResourceLocation(name)
        }
    }
}
