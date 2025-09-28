package net.sanma.ziggizaggamod.datagen;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Streams;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ModModelTemplate extends ModelTemplate {
    private final Optional<ResourceLocation> model;
    private final Set<TextureSlot> requiredSlots;
    private final Optional<String> suffix;

    public ModModelTemplate(Optional<ResourceLocation> pModel, Optional<String> pSuffix, TextureSlot... pRequiredSlots) {
        super(pModel, pSuffix, pRequiredSlots);
        this.model = pModel;
        this.suffix = pSuffix;
        this.requiredSlots = ImmutableSet.copyOf(pRequiredSlots);
    }

    public ResourceLocation create(Block pBlock, TextureMapping pTextureMapping, BiConsumer<ResourceLocation, ModelInstance> pOutput) {
        if (pBlock instanceof DoorBlock || pBlock instanceof TrapDoorBlock)
            return this.createDoor(ModelLocationUtils.getModelLocation(pBlock, this.suffix.orElse("")), pTextureMapping, pOutput);
        return this.create(ModelLocationUtils.getModelLocation(pBlock, this.suffix.orElse("")), pTextureMapping, pOutput);
    }

    public ResourceLocation create(
            ResourceLocation pModelLocation,
            TextureMapping pTextureMapping,
            BiConsumer<ResourceLocation, ModelInstance> pOutput,
            float scaleX, float scaleY, float scaleZ,
            float translateX, float translateY, float translateZ,
            float rotX, float rotY, float rotZ
    ) {
        Map<TextureSlot, ResourceLocation> map = this.createMap(pTextureMapping);
        pOutput.accept(pModelLocation, () -> {
            JsonObject jsonobject = new JsonObject();

            // parent
            this.model.ifPresent(p_376687_ -> jsonobject.addProperty("parent", p_376687_.toString()));

            // textures
            if (!map.isEmpty()) {
                JsonObject jsonobject1 = new JsonObject();
                map.forEach((p_375899_, p_377821_) ->
                        jsonobject1.addProperty(p_375899_.getId(), p_377821_.toString()));
                jsonobject.add("textures", jsonobject1);
            }

            // display block
            JsonObject display = new JsonObject();

            JsonObject firstPersonRight = new JsonObject();
            firstPersonRight.add("scale", makeJsonArray(scaleX, scaleY, scaleZ));
            firstPersonRight.add("translation", makeJsonArray(translateX, translateY, translateZ));
            firstPersonRight.add("rotation", makeJsonArray(rotX, rotY, rotZ));
            display.add("firstperson_righthand", firstPersonRight);

            JsonObject firstPersonLeft = new JsonObject();
            firstPersonLeft.add("scale", makeJsonArray(scaleX, scaleY, scaleZ));
            firstPersonLeft.add("translation", makeJsonArray(translateX, translateY, translateZ));
            firstPersonLeft.add("rotation", makeJsonArray(rotX, rotY*-1, rotZ*-1));
            display.add("firstperson_lefthand", firstPersonLeft);

            jsonobject.add("display", display);
            // Tercera persona (derecha e izquierda)
            JsonObject thirdRight = new JsonObject();
            thirdRight.add("scale", makeJsonArray(scaleX, scaleY, scaleZ));
            thirdRight.add("translation", makeJsonArray(translateX, translateY, translateZ));
            thirdRight.add("rotation", makeJsonArray(rotX, rotY, rotZ));
            display.add("thirdperson_righthand", thirdRight);

            JsonObject thirdLeft = new JsonObject();
            thirdLeft.add("scale", makeJsonArray(scaleX, scaleY, scaleZ));
            thirdLeft.add("translation", makeJsonArray(translateX, translateY, translateZ));
            thirdLeft.add("rotation", makeJsonArray(rotX, rotY*-1, rotZ*-1));
            display.add("thirdperson_lefthand", thirdLeft);

            jsonobject.add("display", display);

            return jsonobject;
        });
        return pModelLocation;
    }

    private JsonArray makeJsonArray(float x, float y, float z) {
        JsonArray arr = new JsonArray();
        arr.add(x);
        arr.add(y);
        arr.add(z);
        return arr;
    }
    public ResourceLocation createDoor(ResourceLocation pModelLocation, TextureMapping pTextureMapping, BiConsumer<ResourceLocation, ModelInstance> pOutput) {
        Map<TextureSlot, ResourceLocation> map = this.createMap(pTextureMapping);
        pOutput.accept(pModelLocation, () -> {
            JsonObject jsonobject = new JsonObject();
            this.model.ifPresent(p_376687_ -> jsonobject.addProperty("parent", p_376687_.toString()));
            if (!map.isEmpty()) {
                JsonObject jsonobject1 = new JsonObject();
                map.forEach((p_375899_, p_377821_) -> jsonobject1.addProperty(p_375899_.getId(), p_377821_.toString()));
                jsonobject.add("textures", jsonobject1);
            }
            jsonobject.addProperty("render_type", "minecraft:cutout");
            return jsonobject;
        });
        return pModelLocation;
    }
    private Map<TextureSlot, ResourceLocation> createMap(TextureMapping pTextureMapping) {
        return Streams.concat(this.requiredSlots.stream(), pTextureMapping.getForced()).collect(ImmutableMap.toImmutableMap(Function.identity(), pTextureMapping::get));
    }
}