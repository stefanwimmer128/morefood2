package eu.stefanwimmer128.morefood2.api.item

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemFood
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World

open class ItemStrawberry @JvmOverloads constructor(amount: Int = 2, saturation: Float = 0.6f, isWolfFood: Boolean = false, name: String = "strawberry", resourceDomain: String? = null): ItemFood(amount, saturation, isWolfFood) {
    init {
        unlocalizedName = name
        registryName = if (resourceDomain != null) ResourceLocation(resourceDomain, name) else ResourceLocation(name)
        
        setAlwaysEdible()
    }
    
    override fun onFoodEaten(stack: ItemStack, worldIn: World, player: EntityPlayer) {
        player.heal(.5f)
        
        super.onFoodEaten(stack, worldIn, player)
    }

    override fun getMaxItemUseDuration(stack: ItemStack) =
        16
}
