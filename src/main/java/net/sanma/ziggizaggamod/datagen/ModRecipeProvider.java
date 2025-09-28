package net.sanma.ziggizaggamod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.sanma.ziggizaggamod.ZiggiZaggaMod;
import net.sanma.ziggizaggamod.block.ModBlocks;
import net.sanma.ziggizaggamod.items.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements DataProvider {
    protected RecipeOutput pOutput;
    protected ModRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
        super(pRegistries, pOutput);
    }

    @Override
    protected void buildRecipes() {
        HolderLookup.RegistryLookup<Item> itemLookup = this.registries.lookup(Registries.ITEM)
                .orElseThrow(() -> new IllegalStateException("No se pudo obtener RegistryLookup<Item>"));

        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.MISC, ModItems.ZIGGIZITE.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ZIGGIZITE_NUGGET.get())
                .unlockedBy(getHasName(ModItems.ZIGGIZITE_NUGGET.get()), has(ModItems.ZIGGIZITE_NUGGET.get())).save(this.output);

        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.MISC, ModItems.ZAGGAZITE.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ZAGGAZITE_NUGGET.get())
                .unlockedBy(getHasName(ModItems.ZAGGAZITE_NUGGET.get()), has(ModItems.ZAGGAZITE_NUGGET.get())).save(this.output);

        ShapelessRecipeBuilder.shapeless(itemLookup,RecipeCategory.MISC, ModItems.ZIGGIZITE_NUGGET.get(), 9)
                .requires(ModItems.ZIGGIZITE.get())
                .unlockedBy(getHasName(ModItems.ZIGGIZITE.get()), has(ModItems.ZIGGIZITE.get())).save(this.output);

        ShapelessRecipeBuilder.shapeless(itemLookup,RecipeCategory.MISC, ModItems.ZAGGAZITE_NUGGET.get(), 9)
                .requires(ModItems.ZAGGAZITE.get())
                .unlockedBy(getHasName(ModItems.ZAGGAZITE.get()), has(ModItems.ZAGGAZITE.get())).save(this.output);
        this.buildToolRecipes(itemLookup);
    }
    private void buildToolRecipes(HolderLookup.RegistryLookup<Item> itemLookup) {
        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.COMBAT, ModItems.ZIGGIZITE_SWORD.get())
                .pattern("A")
                .pattern("A")
                .pattern("B")
                .define('A', ModItems.ZIGGIZITE.get()).define('B',Items.STICK)
                .unlockedBy(getHasName(ModItems.ZIGGIZITE.get()),has(ModItems.ZIGGIZITE.get())).save(this.output);

        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.TOOLS, ModItems.ZIGGIZITE_AXE.get())
                .pattern("AA")
                .pattern("AB")
                .pattern(" B")
                .define('A', ModItems.ZIGGIZITE.get()).define('B',Items.STICK)
                .unlockedBy(getHasName(ModItems.ZIGGIZITE.get()),has(ModItems.ZIGGIZITE.get())).save(this.output);

        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.TOOLS, ModItems.ZIGGIZITE_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.ZIGGIZITE.get()).define('B',Items.STICK)
                .unlockedBy(getHasName(ModItems.ZIGGIZITE.get()),has(ModItems.ZIGGIZITE.get())).save(this.output);
        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.TOOLS, ModItems.ZIGGIZITE_SHOVEL.get())
                .pattern("A")
                .pattern("B")
                .pattern("B")
                .define('A', ModItems.ZIGGIZITE.get()).define('B',Items.STICK)
                .unlockedBy(getHasName(ModItems.ZIGGIZITE.get()),has(ModItems.ZIGGIZITE.get())).save(this.output);
        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.TOOLS, ModItems.ZIGGIZITE_HOE.get())
                .pattern("AA")
                .pattern(" B")
                .pattern(" B")
                .define('A', ModItems.ZIGGIZITE.get()).define('B',Items.STICK)
                .unlockedBy(getHasName(ModItems.ZIGGIZITE.get()),has(ModItems.ZIGGIZITE.get())).save(this.output);

        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.COMBAT, ModItems.ZAGGAZITE_SWORD.get())
                .pattern("A")
                .pattern("A")
                .pattern("B")
                .define('A', ModItems.ZAGGAZITE.get()).define('B',Items.STICK)
                .unlockedBy(getHasName(ModItems.ZAGGAZITE.get()),has(ModItems.ZAGGAZITE.get())).save(this.output);

        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.TOOLS, ModItems.ZAGGAZITE_AXE.get())
                .pattern("AA")
                .pattern("AB")
                .pattern(" B")
                .define('A', ModItems.ZAGGAZITE.get()).define('B',Items.STICK)
                .unlockedBy(getHasName(ModItems.ZAGGAZITE.get()),has(ModItems.ZAGGAZITE.get())).save(this.output);

        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.TOOLS, ModItems.ZAGGAZITE_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModItems.ZAGGAZITE.get()).define('B',Items.STICK)
                .unlockedBy(getHasName(ModItems.ZAGGAZITE.get()),has(ModItems.ZAGGAZITE.get())).save(this.output);

        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.TOOLS, ModItems.ZAGGAZITE_SHOVEL.get())
                .pattern("A")
                .pattern("B")
                .pattern("B")
                .define('A', ModItems.ZAGGAZITE.get()).define('B',Items.STICK)
                .unlockedBy(getHasName(ModItems.ZAGGAZITE.get()),has(ModItems.ZAGGAZITE.get())).save(this.output);
        ShapedRecipeBuilder.shaped(itemLookup,RecipeCategory.TOOLS, ModItems.ZAGGAZITE_HOE.get())
                .pattern("AA")
                .pattern(" B")
                .pattern(" B")
                .define('A', ModItems.ZAGGAZITE.get()).define('B',Items.STICK)
                .unlockedBy(getHasName(ModItems.ZAGGAZITE.get()),has(ModItems.ZAGGAZITE.get())).save(this.output);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput p_236071_) {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput p_365932_, CompletableFuture<HolderLookup.Provider> p_363203_) {
            super(p_365932_, p_363203_);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider pRegistries, RecipeOutput pOutput) {
            return new ModRecipeProvider(pRegistries, pOutput);
        }

        @Override
        public String getName() {
            return "Bean Machine Recipes";
        }
    }
}