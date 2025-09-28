package net.sanma.ziggizaggamod.capability;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.items.ModItems;
import net.sanma.ziggizaggamod.util.ModTags;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class HeroItem extends Item implements ICurioItem {

    public HeroItem(Properties properties) {
        super(properties);
    }

    public static void registerPredicate(){
        CuriosApi.registerCurioPredicate(ResourceLocation.fromNamespaceAndPath(ZiggiZaggaMod.MODID,"hero_validator"),
                (slotResult) -> slotResult.stack().is(ModTags.Items.HEROES_ITEM));
    }
}
