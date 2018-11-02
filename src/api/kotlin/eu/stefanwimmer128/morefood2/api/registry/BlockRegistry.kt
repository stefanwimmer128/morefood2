package eu.stefanwimmer128.morefood2.api.registry

import net.minecraft.block.Block
import net.minecraft.block.material.MapColor
import net.minecraft.block.material.Material
import net.minecraft.util.ResourceLocation

open class BlockRegistry(resourceDomain: String, blockRegistry: Map<String, Block> = mutableMapOf(), val baseInitializer: Block.(block: Block) -> Unit = {}): Registry<Block>(resourceDomain, blockRegistry) {
    fun add(name: String, material: Material, color: MapColor = material.materialMapColor, initializer: Block.(block: Block) -> Unit = {}) {
        add(object: Block(material, color) {
            init {
                unlocalizedName = name
                registryName = ResourceLocation(resourceDomain, name)
                
                baseInitializer(this, this)
                initializer(this, this)
            }
        })
    }
}
