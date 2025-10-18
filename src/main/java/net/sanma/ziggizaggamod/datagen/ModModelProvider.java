package net.sanma.ziggizaggamod.datagen;

import com.google.gson.JsonElement;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.common.Mod;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.block.ModBlocks;
import net.sanma.ziggizaggamod.block.custom.PineappleBushBlock;
import net.sanma.ziggizaggamod.items.ModItems;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput pOutput) {
        super(pOutput, ZiggiZaggaMod.MODID);
    }
    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().filter(x -> !x.is(ModBlocks.ZIGGIZITE_ORE));
    }


    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream().filter(x -> x.get() != ModBlocks.ZIGGIZITE_ORE.asItem());
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        itemModels.generateFlatItem(ModItems.TENKAICHI_DOG_TAG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.SANMA_GLASSES.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MIGUEL_STACHE.get(),ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.DFEO_VBUCK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.GABO_LIVER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PEPA_SKULL.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.YONYE_BREAD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.DONVITO_MANJARATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.WOLF_SOUL.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.ZAGGAZITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ZIGGIZITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ZAGGAZITE_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ZIGGIZITE_NUGGET.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.ZIGGIZITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZIGGIZITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZIGGIZITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZIGGIZITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        //itemModels.generateFlatItem(ModItems.ZIGGIZITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateScaledHandHeldItem(ModItems.ZIGGIZITE_SWORD.get(),ModModelTemplates.SCALED_HANDHELD_ITEM,itemModels,
                1.8F, 1.8F, 1.8F,
                0.0F, 10.0F, 0.0F,
                0.0F, 90.0F, 35.0F);

        itemModels.generateFlatItem(ModItems.ZAGGAZITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZAGGAZITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZAGGAZITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ZAGGAZITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        //itemModels.generateFlatItem(ModItems.ZAGGAZITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        this.generateScaledHandHeldItem(ModItems.ZAGGAZITE_SWORD.get(),ModModelTemplates.SCALED_HANDHELD_ITEM,itemModels,
                1.8F, 1.8F, 1.8F,
                0.0F, 10.0F, 0.0F,
                0.0F, 90.0F, 35.0F);

        itemModels.generateFlatItem(ModItems.ESCOBI_SPAWN_EGG.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ANGEL_SPAWN_EGG.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.PINEAPPLE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.PINEAPPLE_SLICE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMPANADA.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.GRAPE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.TERREMOTO.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.PIPENO.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.GRANADINA.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.PINEAPPLE_ICE_CREAM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        blockModels.createTrivialCube(ModBlocks.ZIGGIZITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ZIGGIZITE_DEEPSLATE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ZAGGAZITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ZAGGAZITE_DEEPSLATE_ORE.get());

        this.createPineappleBush(blockModels);

    }
    protected ResourceLocation createFlatScaleItemModel(Item p_378447_, ModModelTemplate p_376080_, ItemModelGenerators gen,
                                                        float scalex, float scaley,float scalez,
                                                        float translatex,float translatey, float translatez,
                                                        float rotx, float roty,float rotz) {
        return p_376080_.create(ModelLocationUtils.getModelLocation(p_378447_), TextureMapping.layer0(p_378447_), gen.modelOutput,scalex,scaley,scalez,translatex,translatey,translatez,rotx,roty,rotz);
    }

    private void generateScaledHandHeldItem(Item item, ModModelTemplate template,ItemModelGenerators gen,
                                            float scalex, float scaley,float scalez,
                                            float translatex,float translatey, float translatez,
                                            float rotx, float roty,float rotz) {
        gen.itemModelOutput.accept(item,ItemModelUtils.plainModel(
                this.createFlatScaleItemModel(item, template,gen,scalex,scaley,scalez,translatex,translatey,translatez,rotx,roty,rotz)));
    }

    public void createPineappleBush(BlockModelGenerators gen) {
        //gen.registerSimpleFlatItemModel(ModItems.PINEAPPLE.get());
        gen.blockStateOutput.accept(MultiVariantGenerator.multiVariant(ModBlocks.PINEAPPLE_BUSH.get()).with(PropertyDispatch.property(BlockStateProperties.AGE_3).generate((p_388136_) -> Variant.variant().with(VariantProperties.MODEL, gen.createSuffixedVariant(ModBlocks.PINEAPPLE_BUSH.get(), "_stage" + p_388136_, ModelTemplates.CROSS, TextureMapping::cross)))));
    }


}