package net.sanma.ziggizaggamod.datagen;


import com.google.gson.JsonObject;
import com.ibm.icu.text.Normalizer2;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ClientItem;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.sanma.ziggizaggamod.block.ModBlocks;
import net.sanma.ziggizaggamod.items.ModItems;

import java.util.Map;
import java.util.Optional;

public class ModItemModelGenerators{
    public ModItemModelGenerators(ItemModelOutput items) {
        ;
    }

    //protected ResourceLocation createFlatScaleItemModel(Item p_378447_, ModModelTemplate p_376080_,
    //                                                    float scalex, float scaley,float scalez,
    //                                                    float translatex,float translatey, float translatez,
    //                                                    float rotx, float roty,float rotz) {
    //    //return p_376080_.create(ModelLocationUtils.getModelLocation(p_378447_), TextureMapping.layer0(p_378447_), this.modelOutput,scalex,scaley,scalez,translatex,translatey,translatez,rotx,roty,rotz);
    //}
//
    private void generateScaledHandHeldItem(Item item, ModModelTemplate template,
                                            float scalex, float scaley,float scalez,
                                            float translatex,float translatey, float translatez,
                                            float rotx, float roty,float rotz) {
        //this.itemModelOutput.accept(item,ItemModelUtils.plainModel(
        //        this.createFlatScaleItemModel(item, template,scalex,scaley,scalez,translatex,translatey,translatez,rotx,roty,rotz)));
    }



}
