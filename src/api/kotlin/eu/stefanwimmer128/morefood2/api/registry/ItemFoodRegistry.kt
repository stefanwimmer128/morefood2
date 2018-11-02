package eu.stefanwimmer128.morefood2.api.registry

import net.minecraft.item.Item
import net.minecraft.item.ItemFood
import net.minecraft.util.ResourceLocation

class ItemFoodRegistry(resourceDomain: String, itemRegistry: Map<String, ItemFood> = mutableMapOf(), val baseInitializer: ItemFood.(food: ItemFood) -> Unit = {}): Registry<Item>(resourceDomain, itemRegistry) {
    fun add(name: String, amount: Int, saturation: Float = .6f, isWolfFood: Boolean = false, initializer: ItemFood.(food: ItemFood) -> Unit = {}) {
        add(object: ItemFood(amount, saturation, isWolfFood) {
            init {
                unlocalizedName = name
                registryName = ResourceLocation(resourceDomain, name)

                baseInitializer(this, this)
                initializer(this, this)
            }
        })
    }
}
