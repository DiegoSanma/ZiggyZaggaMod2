# ZiggyZagga Mod

A NeoForge mod for Minecraft 1.20+ that adds two new minerals with full tool sets, eight unique curio-slot "HeroItems" each granting special powers, and two custom entities including a multi-phase boss fight.

---

## Table of Contents

1. [Project Structure](#project-structure)
2. [Registration Flow](#registration-flow)
3. [New Minerals: Ziggizite & Zaggazite](#new-minerals-ziggizite--zaggazite)
4. [HeroItems (Curio Slot)](#heroitems-curio-slot)
5. [Entities](#entities)
6. [Custom Effect: Spiderman](#custom-effect-spiderman)
7. [Data Generation](#data-generation)

---

## Project Structure

```
src/main/java/net/sanma/ziggizaggamod/
├── ZiggiZaggaMod.java              # Main mod class — wires all registries and event listeners
├── Config.java                     # Mod configuration
│
├── block/                          # Block definitions
│   └── ModBlocks.java              # DeferredRegister for all blocks + auto BlockItem creation
│
├── items/                          # Item definitions
│   ├── ModItems.java               # DeferredRegister for all items
│   ├── ModCreativeModeTabs.java    # Single creative tab "Ziggi Zagga Mod"
│   └── ModToolTiers.java           # Tool material tiers (durability, speed, damage)
│
├── capability/                     # HeroItem implementations (curio items)
│   ├── HeroItem.java               # Base class: implements ICurioItem, registers validator
│   ├── TenkaichiItem.java          # Tenkaichi's Dog Tag
│   ├── SanmaItem.java              # Sanma's Glasses
│   ├── MiguelItem.java             # Miguel's Stache
│   ├── DfeoItem.java               # Dfeo's VBuck
│   ├── PepaItem.java               # Pepa's Skull
│   ├── YonyeItem.java              # Yonye's Bread
│   ├── GaboItem.java               # Gabo's Liver
│   └── DonvitoItem.java            # Donvito's Manjarate
│
├── entity/
│   ├── ModEntity.java              # DeferredRegister for entity types
│   ├── custom/
│   │   ├── EscobiEntity.java       # Ranged flying mob that shoots fireballs
│   │   └── AngelEntity.java        # 3-phase boss entity with boss bar
│   └── client/                     # Renderers and Geckolib animated models
│
├── effect/
│   ├── ModEffects.java             # DeferredRegister for effects
│   └── SpidermanEffect.java        # Custom wall-climb + no fall damage effect
│
├── sound/
│   └── ModSounds.java              # DeferredRegister for sound events
│
├── worldgen/
│   ├── ModConfiguredFeatures.java  # Ore feature configs (vein sizes, block targets)
│   ├── ModPlacedFeatures.java      # Placement rules (frequency, Y range)
│   └── ModBiomeModifiers.java      # Attaches ores and mob spawns to biomes
│
├── loot/
│   ├── ModLootModifiers.java       # Loot modifier serializer registration
│   └── AddItemModifiers.java       # Generic "add item to loot table" modifier
│
├── event/
│   └── ModEventBusEvents.java      # Attribute creation, layer registration, spawn rules, wolf interaction
│
├── datagen/                        # All data generation providers
│   ├── DataGenerators.java         # Master GatherDataEvent subscriber
│   ├── ModRecipeProvider.java      # Shapeless/shaped recipe generation
│   ├── ModModelProvider.java       # Item and block model generation
│   ├── ModBlockTagProvider.java    # Block tag definitions
│   ├── ModItemTagProvider.java     # Item tag definitions (heroes_item tag)
│   ├── ModBlockLootTableProvider.java
│   ├── ModGlobalLootModifierProvider.java
│   ├── ModDatapackProvider.java
│   └── CuriosSlotProvider.java     # Curio slot definition with validator predicate
│
├── util/
│   └── ModTags.java                # Tag constants
│
└── common/network/                 # Network packets (e.g., battle music trigger)
```

```
src/main/resources/
├── META-INF/neoforge.mods.toml
├── assets/ziggizaggamod/
│   ├── textures/
│   │   ├── block/      # 4 ore textures (stone + deepslate × 2 minerals)
│   │   ├── item/       # HeroItems, tools, ingots, nuggets, spawn eggs
│   │   ├── entity/     # angel.png, escobi.png
│   │   ├── mob_effect/ # spiderman.png
│   │   └── slot/       # hero_slot.png (curio slot icon)
│   ├── sounds/         # angel_battle_music.ogg, angel_hurt.ogg, phase2.ogg, phase3.ogg
│   └── sounds.json
└── data/ziggizaggamod/
    ├── recipes/
    ├── loot_table/
    ├── tags/
    └── curios/
```

---

## Registration Flow

Every component follows the same `DeferredRegister` pattern. `ZiggiZaggaMod` is the single entry point that bootstraps everything at mod load time.

```
Game Launch
    └── ZiggiZaggaMod constructor
            ├── Subscribes listeners on the MOD bus:
            │       commonSetup()  →  HeroItem.registerPredicate()  (curio validator)
            │       addCreative()  →  populates creative tab output
            │
            ├── Calls .register(modEventBus) on every DeferredRegister:
            │       ModItems        →  HeroItems, tools, ingots, nuggets, spawn eggs
            │       ModBlocks       →  ore blocks + auto BlockItems
            │       ModEntity       →  Escobi, Angel
            │       ModSounds       →  4 sound events
            │       ModLootModifiers→  loot modifier serializer
            │       ModEffects      →  SpidermanEffect
            │
            └── Registers mod config

MOD bus events (ModEventBusEvents.java):
    EntityRenderersEvent.RegisterLayerDefinitions
        └── Registers Geckolib model layers for Escobi and Angel

    EntityAttributeCreationEvent
        └── Binds attribute suppliers (health, speed, damage…) to both entity types

    RegisterSpawnPlacementsEvent
        └── ESCOBI: NO_RESTRICTIONS placement, Monster spawn rules

FORGE/NEO event bus:
    PlayerInteractEvent.EntityInteract
        └── Right-clicking a tamed wolf with WOLF_SOUL
              → plays death sound, spawns soul particles, drops DFEO_VBUCK, kills wolf

Data generation (DataGenerators.java — GatherDataEvent):
    Client: models, block states, curio slot config
    Server: recipes, block/item tags, loot tables, global loot modifiers, biome modifiers
```

### How a new item is added end-to-end

1. **Register** — add a `DeferredHolder` in `ModItems` via `ITEMS.registerItem()`.
2. **Tag** — add it to relevant tags in `ModItemTagProvider` (e.g. `heroes_item` for curio validation).
3. **Model** — `ModModelProvider` generates it automatically via `basicItem()`.
4. **Recipe** — define in `ModRecipeProvider` with `ShapedRecipeBuilder` or `ShapelessRecipeBuilder`.
5. **Creative tab** — add the holder to the tab's `output` list in `ModCreativeModeTabs`.

For a HeroItem, step 1 uses a subclass of `HeroItem` and the curio validator registered in `commonSetup` handles slot eligibility automatically via the `heroes_item` tag.

---

## New Minerals: Ziggizite & Zaggazite

Two minerals that generate underground in all Overworld biomes, each with a stone and deepslate ore variant.

### Ore Generation

| Variant | Vein Size | Per Chunk | Y Range |
|---------|-----------|-----------|---------|
| Standard | 4 blocks | 8 | −64 to 10 |
| Large | 6 blocks | 2 | −64 to −10 |
| Small | 2 blocks | 5 | −64 to 30 |
| Very small | 1 block | 12 | −64 to 60 |

Both minerals follow this same placement pattern. Deepslate variants generate automatically when the ore replaces deepslate. All ore blocks drop 2–4 XP.

### Tool Material Properties

| Property | Ziggizite | Zaggazite |
|----------|-----------|-----------|
| Durability | 10,000 | 15,000 |
| Mining Speed | 12.0 | 11.0 |
| Attack Damage Bonus | +3.5 | +3.5 |
| Enchantability | 15 | 15 |

### Tool Sets

Both minerals craft the complete vanilla tool set. Recipe shapes follow vanilla conventions (sword: 2 ingots + stick; pickaxe: 3-1-1; axe: 2×2 + sticks; shovel: 1 + sticks; hoe: 2 horizontal + sticks). Nugget ↔ Ingot conversion uses the standard 9:1 ratio.

---

## HeroItems (Curio Slot)

HeroItems occupy a dedicated curio slot called **`hero_slot`**, shown in the Curios inventory panel. Only one HeroItem can be worn at a time.

### How the slot works

`CuriosSlotProvider` generates a slot definition that references a predicate named `hero_validator`. That predicate checks whether the item in the slot carries the item tag `ziggizaggamod:heroes_item`. All HeroItem subclasses receive this tag in `ModItemTagProvider`.

During `commonSetup`, `HeroItem.registerPredicate()` registers `hero_validator` with the Curios API. From then on, Curios enforces the tag check automatically — no per-item slot logic needed.

### HeroItem base class

`HeroItem extends Item implements ICurioItem`

Every HeroItem overrides three Curios callbacks:

| Method | When called | Typical use |
|--------|-------------|-------------|
| `onEquip()` | Item placed into slot | Add attribute modifiers, apply initial effects |
| `onUnequip()` | Item removed from slot | Remove attribute modifiers, restore state |
| `curioTick()` | Every game tick while equipped | Apply continuous effects, poll/refresh effects |

### The eight HeroItems

#### Tenkaichi's Dog Tag
- **Equip**: Adds +4 max health while worn.
- **Tick**: Every second, if the player has 10+ XP levels, randomly adds or subtracts 1–10 XP levels (50/50 gamble).

#### Sanma's Glasses
- **Equip**: Applies infinite Night Vision; scales player model down to 0.5×.
- **Unequip**: Restores player scale to normal.
- **Tick**: Refreshes Night Vision so it never expires.

#### Miguel's Stache
- **Tick**: Constantly applies Luck V (refreshed every tick so it never expires).

#### Dfeo's VBuck
- **Tick**: Applies the custom `SPIDERMAN_EFFECT` (see below), refreshed every tick.
- **Special interaction**: Using a `WOLF_SOUL` item on a tamed wolf consumes both items and drops a DFEO_VBUCK.

#### Pepa's Skull
- **Equip**: Reduces max health by 16.
- **Tick**: Sets the player as invulnerable every tick (immune to all damage).
- **Unequip**: Removes invulnerability and restores max health.

#### Yonye's Bread
- **Equip**: Adds +5 attack damage and +5 attack knockback via attribute modifiers.
- **Tick**: Applies Hunger II every tick (player can never be satiated).
- **Unequip**: Removes both attribute modifiers.

#### Gabo's Liver
- **Tick**: Removes any active Poison effect from the player instantly every tick.

#### Donvito's Manjarate
- **Tick**: Forces food level to 20 and restores +0.1 saturation per tick, but applies a slight movement speed penalty.

---

## Entities

### Escobi — "Escovirus"

A small flying ranged mob that spawns naturally in Swamp and Plains biomes.

| Attribute | Value |
|-----------|-------|
| Health | 50 HP |
| Size | 0.5 × 2.5 blocks |
| Spawn biomes | Swamp, Plains (weight 20, 1 per group) |
| Category | Monster |

**Behavior**:
- Floats in a 16-block radius using a random stroll goal.
- Targets players within 10–20 blocks.
- Charges for 80 ticks, then fires a fireball at the target.
- Idle animation loops every 80 ticks; attack animation plays during the charge.

Powered by Geckolib with separate idle and attack animation states defined in the model file.

---

### Angel — "The Fallen Angel"

A large flying boss with a 3-phase fight, a bossbar, and a battle music track.

| Attribute | Value |
|-----------|-------|
| Health | 2,000 HP |
| Size | 2.5 × 4.0 blocks |
| XP on death | 30,000 |
| Boss bar | Yellow, Notched-10 overlay |
| Immunities | Fire, fall damage, despawn |
| Follow range | 64 blocks |

When a player first sees the Angel, a network packet triggers `angel_battle_music` on the client.

#### Phase 1 — 100% to 50% health

- **Lightning strike**: 5 lightning bolts spread around the target every 20 ticks.
- **Wing attack**: Melee hit accompanied by particle effects.
- **Minion spawn**: 1 minion spawned every 300 ticks (15 seconds).

#### Phase 2 — triggered at 50% health (fires once)

- Attack Damage raised to 15.0.
- 5 lightning bolts strike around the Angel's own position.
- `phase2` sound plays.

#### Phase 3 — triggered at 25% health (fires once)

- Movement Speed raised to 0.06.
- Players within 12 blocks receive Slowness II + Blindness.
- Continuous smoke particles.
- 5 Skeleton Horses spawn, each carrying a Zombie rider named "Angel's Warrior" equipped with a Ziggizite Sword, Iron Helmet, and Damage Boost II.
- `phase3` sound plays.

**Minion spawn mechanic**: Each minion spawns 6–8 blocks away in a random horizontal direction and immediately targets nearby players.

---

## Custom Effect: Spiderman

Registered as `ziggizaggamod:spiderman`. Applied passively while DFEO_VBUCK is equipped.

| Property | Value |
|----------|-------|
| Category | NEUTRAL |
| Color | `#36ebab` (teal) |
| Fall damage | Completely disabled (`SAFE_FALL_DISTANCE` set to `INT.MAX_VALUE`) |
| Wall climb | On horizontal wall collision, adds 0.2 upward velocity per tick |

The effect fires every tick (`shouldApplyEffectTickThisTick` always returns `true`) and is refreshed by `curioTick` before it expires, making it effectively permanent while the item is worn.

---

## Data Generation

All JSON assets (recipes, models, tags, loot tables, curio slots, biome modifiers) are produced programmatically. Run `gradlew runData` to regenerate them into `src/generated/`.

| Provider | Output |
|----------|--------|
| `ModRecipeProvider` | Tool crafting recipes, nugget ↔ ingot conversions |
| `ModModelProvider` | `basicItem()` models for all items; block state + models for ores |
| `ModBlockTagProvider` | Ore block tags |
| `ModItemTagProvider` | `heroes_item` tag (curio slot predicate) |
| `ModBlockLootTableProvider` | Ore blocks drop raw items (silk touch aware) |
| `CuriosSlotProvider` | `hero_slot` with `hero_validator` predicate, max stack 1 |
| `ModBiomeModifierProvider` | Attaches ore placed features to IS_OVERWORLD; adds Escobi spawns to Swamp + Plains |
| `ModGlobalLootModifierProvider` | Registers the `AddItemModifiers` serializer |
