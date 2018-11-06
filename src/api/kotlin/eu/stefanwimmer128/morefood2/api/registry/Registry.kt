package eu.stefanwimmer128.morefood2.api.registry

import net.minecraft.block.Block
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry

open class Registry<T: IForgeRegistryEntry<T>> @JvmOverloads constructor(val resourceDomain: String, registry: Map<String, T> = mutableMapOf()): MutableMap<String, T> by registry.toMutableMap() {
    fun add(entry: T): T {
        this[entry.registryName!!.resourcePath] = entry
        onEntryAdd(entry)
        return entry
    }
    
    open fun registerEntries(registry: IForgeRegistry<T>) {
        this.values.forEach {
            @Suppress("UNCHECKED_CAST")
            if ((it as? ICustomEntryRegistration<T>)?.registerEntry(registry) == null)
                registry.register(it)
        }
    }
    
    open fun registerModels() {
        fun setCustomModelResourceLocation(item: Item, meta: Int, resourcePath: String, variant: String) {
            ModelLoader.setCustomModelResourceLocation(item, meta, ModelResourceLocation(ResourceLocation(resourceDomain, resourcePath), variant))
        }
        
        this.values.forEach {
            when (it) {
                is ICustomModelRegistration ->
                    it.registerModel(::setCustomModelResourceLocation, resourceDomain)
                is Item ->
                    setCustomModelResourceLocation(it, 0, it.registryName!!.resourcePath, "inventory")
                is Block ->
                    setCustomModelResourceLocation(Item.getItemFromBlock(it), 0, it.registryName!!.resourcePath, "inventory")
            }
        }
    }
    
    open fun onEntryAdd(entry: T) {}
}
