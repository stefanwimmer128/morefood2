package eu.stefanwimmer128.morefood2.api.registry

import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation

class ItemRegistry(resourceDomain: String, itemRegistry: Map<String, Item> = mutableMapOf(), val baseInitializer: Item.(item: Item) -> Unit = {}): Registry<Item>(resourceDomain, itemRegistry) {
    fun add(name: String, initializer: Item.(item: Item) -> Unit = {}) {
        add(object: Item() {
            init {
                unlocalizedName = name
                registryName = ResourceLocation(resourceDomain, name)
                
                baseInitializer(this, this)
                initializer(this, this)
            }
        })
    }
}
