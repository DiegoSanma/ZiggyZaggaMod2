package net.sanma.ziggizaggamod.items.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.sanma.ziggizaggamod.items.ModItems;


public class WolfSoulItem extends Item {
    public WolfSoulItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        // Verificamos que la entidad sea un lobo
        if (interactionTarget instanceof Wolf wolf) { // declaración inline de wolf

            Level level = wolf.level();

            System.out.println("WolfSoulItem::interactLivingEntity");

            // Solo si el lobo está tameado
            if (!wolf.isTame()) return InteractionResult.PASS;

            // Solo el dueño puede usarlo
            if (!wolf.getOwnerUUID().equals(player.getUUID())) return InteractionResult.PASS;

            if (!level.isClientSide) {
                System.out.println("WolfSoulItem::interactLivingEntity");
                // Generar drop extra
                ItemStack dropStack = new ItemStack(ModItems.DFEO_VBUCK.get()); // tu drop
                ItemEntity drop = new ItemEntity(level, wolf.getX(), wolf.getY(), wolf.getZ(), dropStack);
                level.addFreshEntity(drop);

                // Matar al lobo
                wolf.discard();

                // Reducir el WolfSoul si no es creativo
                if (!player.isCreative()) stack.shrink(1);
            }

            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS; // si no es lobo, no hacemos nada
    }



}
