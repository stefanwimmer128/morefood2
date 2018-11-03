package eu.stefanwimmer128.morefood2.api.item

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

open class ItemMilkBottle(amount: Int = 2, saturation: Float = 0.6f, isWolfFood: Boolean = false, name: String = "milk_bottle", resourceDomain: String? = null): ItemDrink(amount, saturation, isWolfFood, name, resourceDomain) {
    override fun onFoodEaten(stack: ItemStack, worldIn: World, player: EntityPlayer) {
        player.clearActivePotions()
        
        super.onFoodEaten(stack, worldIn, player)
    }
}
