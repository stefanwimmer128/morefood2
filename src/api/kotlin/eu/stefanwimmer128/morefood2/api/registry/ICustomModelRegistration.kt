package eu.stefanwimmer128.morefood2.api.registry

import net.minecraft.item.Item

interface ICustomModelRegistration {
    fun registerModel(setCustomModelResourceLocation: (item: Item, meta: Int, resourcePath: String, variant: String) -> Unit, resourceDomain: String)
}
