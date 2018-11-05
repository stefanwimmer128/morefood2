package eu.stefanwimmer128.morefood2

import eu.stefanwimmer128.morefood2.api.MoreFood2API
import eu.stefanwimmer128.morefood2.api.registry.BlockRegistry
import eu.stefanwimmer128.morefood2.api.registry.ItemRegistry
import eu.stefanwimmer128.morefood2.proxy.CommonProxy
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

@Mod(modid = MoreFood2API.MODID, version = MoreFood2API.VERSION, name = MoreFood2API.NAME, modLanguage = "kotlin", modLanguageAdapter = "eu.stefanwimmer128.morefood2.api.lang.KotlinLanguageAdapter", updateJSON = "https://raw.githubusercontent.com/stefanwimmer128/morefood2/master/versions.json")
class MoreFood2: MoreFood2API {
    companion object {
        @Mod.Instance
        lateinit var instance: MoreFood2
        
        @SidedProxy(serverSide = CommonProxy.SERVER, clientSide = CommonProxy.CLIENT)
        lateinit var proxy: CommonProxy
    }
    
    override lateinit var logger: Logger
    
    override val blocks = object: BlockRegistry(MoreFood2API.MODID) {
        override fun onEntryAdd(entry: Block) {
            entry.setCreativeTab(CreativeTabs.FOOD)
        }
    }
    override val items = object: ItemRegistry(MoreFood2API.MODID) {
        override fun onEntryAdd(entry: Item) {
            entry.creativeTab = CreativeTabs.FOOD
        }
    }
    
    @Mod.EventHandler
    fun preInit(e: FMLPreInitializationEvent) {
        logger = e.modLog
        
        proxy.preInit(e)
    }
    
    @Mod.EventHandler
    fun init(e: FMLInitializationEvent) {
        proxy.init(e)
    }
    
    @Mod.EventHandler
    fun postInit(e: FMLPostInitializationEvent) {
        proxy.postInit(e)
    }
}
