package eu.stefanwimmer128.morefood2.api.registry

import net.minecraft.item.Item
import net.minecraft.item.ItemFood
import net.minecraft.util.ResourceLocation

open class ItemFoodRegistry(resourceDomain: String, itemRegistry: Map<String, ItemFood> = mutableMapOf(), baseInitializer: Item.(food: Item) -> Unit = {}): Registry<Item>(resourceDomain, itemRegistry, baseInitializer) {
    open fun add(name: String, amount: Int, saturation: Float = .6f, isWolfFood: Boolean = false, initializer: ItemFood.(food: ItemFood) -> Unit = {}) {
        val itemFood = object: ItemFood(amount, saturation, isWolfFood) {
            init {
                unlocalizedName = name
                registryName = ResourceLocation(resourceDomain, name)
            }
        }
        add(itemFood)
        initializer(itemFood, itemFood)
    }
}
