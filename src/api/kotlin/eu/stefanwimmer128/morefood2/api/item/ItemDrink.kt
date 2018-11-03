package eu.stefanwimmer128.morefood2.api.item

import net.minecraft.item.EnumAction
import net.minecraft.item.ItemFood
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

open class ItemDrink(amount: Int, saturation: Float = 0.6f, isWolfFood: Boolean = false, name: String? = null, resourceDomain: String? = null): ItemFood(amount, saturation, isWolfFood) {
    init {
        if (name != null) {
            unlocalizedName = name
            registryName = if (resourceDomain != null) ResourceLocation(resourceDomain, name) else ResourceLocation(name)
        }
    }
    
    override fun getItemUseAction(stack: ItemStack) =
        EnumAction.DRINK
}
