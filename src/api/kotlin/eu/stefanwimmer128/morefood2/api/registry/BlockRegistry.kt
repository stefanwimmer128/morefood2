package eu.stefanwimmer128.morefood2.api.registry

import net.minecraft.block.Block
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.util.ResourceLocation

open class BlockRegistry(resourceDomain: String, blockRegistry: Map<String, Block> = mutableMapOf(), baseInitializer: Block.(block: Block) -> Unit = {}): Registry<Block>(resourceDomain, blockRegistry, baseInitializer) {
    open fun add(name: String, material: Material, color: MapColor = material.materialMapColor, initializer: Block.(block: Block) -> Unit = {}) {
        val block = object: Block(material, color) {
            init {
                unlocalizedName = name
                registryName = ResourceLocation(resourceDomain, name)
            }
        }
        add(block)
        initializer(block, block)
    }
}
