package eu.stefanwimmer128.morefood2.proxy

import eu.stefanwimmer128.morefood2.MoreFood2
import eu.stefanwimmer128.morefood2.api.registry.BlockRegistry
import eu.stefanwimmer128.morefood2.api.registry.ItemFoodRegistry
import eu.stefanwimmer128.morefood2.api.registry.ItemRegistry
import eu.stefanwimmer128.morefood2.event.RegistryHandler
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry

open class CommonProxy {
    companion object {
        const val SERVER = "eu.stefanwimmer128.morefood2.proxy.ServerProxy"
        const val CLIENT = "eu.stefanwimmer128.morefood2.proxy.ClientProxy"
    }
    
    open fun preInit(e: FMLPreInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(RegistryHandler())
        
        MoreFood2.instance.blocks.populate<BlockRegistry> {}
        MoreFood2.instance.items.populate<ItemRegistry> {
            add("bread_dough")
            add("cocoa_bean_roasted")
            add("cocoa_bean_ground")
            add("flour")
        }
        MoreFood2.instance.itemFoods.populate<ItemFoodRegistry> {
            add("chocolate", 8)
        }
    }
    
    open fun init(e: FMLInitializationEvent) {
        val items = MoreFood2.instance.items
        
        GameRegistry.addSmelting(ItemStack(Items.DYE, 1, 3), ItemStack(items["cocoa_bean_roasted"]!!), .8f)
        GameRegistry.addSmelting(items["bread_dough"]!!, ItemStack(Items.BREAD), .8f)
    }
    
    open fun postInit(e: FMLPostInitializationEvent) {}
}
