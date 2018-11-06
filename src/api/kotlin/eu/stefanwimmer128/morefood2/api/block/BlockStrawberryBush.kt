package eu.stefanwimmer128.morefood2.api.block

import eu.stefanwimmer128.morefood2.api.MoreFood2API
import eu.stefanwimmer128.morefood2.api.registry.ICustomModelRegistration
import net.minecraft.block.BlockCrops
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

open class BlockStrawberryBush @JvmOverloads constructor(name: String = "strawberry_bush", resourceDomain: String? = null) : BlockCrops(), ICustomModelRegistration {

    init {
        unlocalizedName = name
        registryName = if (resourceDomain != null) ResourceLocation(resourceDomain, name) else ResourceLocation(name)
    }
    
    override fun getBoundingBox(state: IBlockState, source: IBlockAccess, pos: BlockPos): AxisAlignedBB =
        arrayListOf(
            AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.0625, 1.0),
            AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0),
            AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0),
            AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.625, 1.0),
            AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0),
            AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0),
            AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0),
            AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.875, 1.0)
        )[getAge(state)]
    
    override fun getCrop() =
        MoreFood2API.instance!!.items["strawberry"]
    
    override fun getSeed() =
        MoreFood2API.instance!!.items["strawberry_seed"]
    
    override fun registerModel(setCustomModelResourceLocation: (item: Item, meta: Int, resourcePath: String, variant: String) -> Unit, resourceDomain: String) {}
}
