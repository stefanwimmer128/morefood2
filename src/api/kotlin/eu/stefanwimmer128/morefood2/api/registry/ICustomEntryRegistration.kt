package eu.stefanwimmer128.morefood2.api.registry

import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryEntry

interface ICustomEntryRegistration<T: IForgeRegistryEntry<T>> {
    fun registerEntry(registry: IForgeRegistry<T>)
}
