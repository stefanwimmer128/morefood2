package eu.stefanwimmer128.morefood2.api.registry

import net.minecraft.item.Item
import net.minecraft.item.ItemFood
import net.minecraft.util.ResourceLocation

open class ItemRegistry @JvmOverloads constructor(resourceDomain: String, itemRegistry: Map<String, Item> = mutableMapOf()): Registry<Item>(resourceDomain, itemRegistry) {
    open fun add(name: String, initializer: Item.() -> Unit = {}): Item {
        return add(object: Item() {
            init {
                unlocalizedName = name
                registryName = ResourceLocation(resourceDomain, name)
            }
        })
    }
    
    open fun add(name: String, amount: Int, saturation: Float = .6f, isWolfFood: Boolean = false): ItemFood {
        return add(object: ItemFood(amount, saturation, isWolfFood) {
            init {
                unlocalizedName = name
                registryName = ResourceLocation(resourceDomain, name)
            }
        }) as ItemFood
    }
}
