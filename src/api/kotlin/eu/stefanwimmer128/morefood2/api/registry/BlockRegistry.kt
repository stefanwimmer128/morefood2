package eu.stefanwimmer128.morefood2.api.registry

import net.minecraft.block.Block
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.util.ResourceLocation

open class BlockRegistry @JvmOverloads constructor(resourceDomain: String, blockRegistry: Map<String, Block> = mutableMapOf()): Registry<Block>(resourceDomain, blockRegistry) {
    open fun add(name: String, material: Material, color: MapColor = material.materialMapColor): Block {
        return add(object: Block(material, color) {
            init {
                unlocalizedName = name
                registryName = ResourceLocation(resourceDomain, name)
            }
        })
    }
}
