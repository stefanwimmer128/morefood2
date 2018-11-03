package eu.stefanwimmer128.morefood2.api.item

import eu.stefanwimmer128.morefood2.api.MoreFood2API
import net.minecraft.block.Block
import net.minecraft.item.ItemSeeds
import net.minecraft.util.ResourceLocation

open class ItemStrawberrySeed(block: Block = MoreFood2API.instance.blocks["strawberry_bush"]!!, val name: String = "strawberry_seed", val resourceDomain: String? = null): ItemSeeds(block, null) {
    init {
        unlocalizedName = name
        registryName = if (resourceDomain != null) ResourceLocation(resourceDomain, name) else ResourceLocation(name)
    }
}
