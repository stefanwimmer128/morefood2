package eu.stefanwimmer128.morefood2.proxy

import eu.stefanwimmer128.morefood2.MoreFood2
import eu.stefanwimmer128.morefood2.api.block.BlockStrawberryBush
import eu.stefanwimmer128.morefood2.api.item.ItemDrink
import eu.stefanwimmer128.morefood2.api.item.ItemMilkBottle
import eu.stefanwimmer128.morefood2.api.item.ItemStrawberry
import eu.stefanwimmer128.morefood2.api.item.ItemStrawberrySeed
import eu.stefanwimmer128.morefood2.api.registry.BlockRegistry
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
        
        MoreFood2.instance.blocks.inject<BlockRegistry> {
            add(BlockStrawberryBush(resourceDomain = resourceDomain))
        }
        MoreFood2.instance.items.inject<ItemRegistry> {
            add("apple_chocolate")
            add("apple_chocolate_coated", 8)
            add("apple_glaceed", 6)
            add("apple_sugar")
            add("bread_dough")
            add("bread_slice")
            add("chocolate", 8)
            add(ItemDrink(4, name = "chocolate_drink", resourceDomain = resourceDomain))
            add("chocolate_piece", 1)
            add("cocoa_bean_roasted")
            add("cocoa_bean_ground")
            add("egg_boiled", 3)
            add("fish_stick", 6)
            add("fish_stick_raw")
            add("flour")
            add("french_fries", 6)
            add("french_fries_raw")
            add("ice_cream_chocolate", 6)
            add("ice_cream_cone")
            add("ice_cream_cone_dough")
            add("ice_cream_strawberry", 6)
            add(ItemMilkBottle(resourceDomain = resourceDomain))
            add("sandwich_chicken", 5)
            add("sandwich_fish", 5)
            add("sandwich_porkchop", 5)
            add("sandwich_schnitzel", 5)
            add("schnitzel", 4)
            add("schnitzel_raw")
            add(ItemStrawberry(resourceDomain = resourceDomain))
            add("strawberry_chocolate")
            add(ItemStrawberrySeed(resourceDomain = resourceDomain))
            add("strawberry_sugar")
            add("strawberry_chocolate_coated", 8)
            add("strawberry_glaceed", 6)
        }
    }
    
    open fun init(e: FMLInitializationEvent) {
        val items = MoreFood2.instance.items
        
        MinecraftForge.addGrassSeed(ItemStack(items["strawberry_seed"]!!), 8)
        
        GameRegistry.addSmelting(items["apple_chocolate"], ItemStack(items["apple_chocolate_coated"]!!), 0.8f)
        GameRegistry.addSmelting(items["apple_sugar"], ItemStack(items["apple_glaceed"]!!), 0.8f)
        GameRegistry.addSmelting(items["bread_dough"], ItemStack(Items.BREAD), 0.8f)
        GameRegistry.addSmelting(ItemStack(Items.DYE, 1, 3), ItemStack(items["cocoa_bean_roasted"]!!), 0.8f)
        GameRegistry.addSmelting(Items.EGG, ItemStack(items["egg_boiled"]!!), 0.8f)
        GameRegistry.addSmelting(items["fish_stick_raw"], ItemStack(items["fish_stick"]!!), 0.8f)
        GameRegistry.addSmelting(items["french_fries_raw"], ItemStack(items["french_fries"]!!), 0.8f)
        GameRegistry.addSmelting(items["ice_cream_cone_dough"], ItemStack(items["ice_cream_cone"]!!), 0.8f)
        GameRegistry.addSmelting(items["schnitzel_raw"], ItemStack(items["schnitzel"]!!), 0.8f)
        GameRegistry.addSmelting(items["strawberry_chocolate"], ItemStack(items["strawberry_chocolate_coated"]!!), .6f)
        GameRegistry.addSmelting(items["strawberry_sugar"], ItemStack(items["strawberry_glaceed"]!!), 0.8f)
    }
    
    open fun postInit(e: FMLPostInitializationEvent) {}
}
