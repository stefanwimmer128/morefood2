package eu.stefanwimmer128.morefood2.api.registry

import net.minecraft.item.Item
import net.minecraft.item.ItemFood
import net.minecraft.util.ResourceLocation

open class ItemRegistry(resourceDomain: String, itemRegistry: Map<String, Item> = mutableMapOf(), baseInitializer: Item.() -> Unit = {}): Registry<Item>(resourceDomain, itemRegistry, baseInitializer) {
    open fun add(name: String, initializer: Item.() -> Unit = {}) {
        val item = object: Item() {
            init {
                unlocalizedName = name
                registryName = ResourceLocation(resourceDomain, name)
            }
        }
        add(item)
        initializer(item)
    }
    
    open fun add(name: String, amount: Int, saturation: Float = .6f, isWolfFood: Boolean = false, initializer: ItemFood.() -> Unit = {}) {
        val itemFood = object: ItemFood(amount, saturation, isWolfFood) {
            init {
                unlocalizedName = name
                registryName = ResourceLocation(resourceDomain, name)
            }
        }
        add(itemFood)
        initializer(itemFood)
    }
}
