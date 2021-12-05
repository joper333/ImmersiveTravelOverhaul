package com.joper333.sextant.mixins;

import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.blaze3d.platform.GlDebugInfo;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongSets;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.SharedConstants;
import net.minecraft.block.BlockState;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.ShaderEffect;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.DebugHud;


import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.fluid.FluidState;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.chunk.light.LightingProvider;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Mixin (DebugHud.class)
public abstract class Coordinates extends DrawableHelper {

    //variables
    @Shadow
    @Final private MinecraftClient client;

    @Shadow
    private HitResult blockHit;
    @Shadow
    private HitResult fluidHit;

    @Shadow
    @Nullable private ChunkPos pos;

    @Shadow
    @Final static Map<Heightmap.Type, String> HEIGHT_MAP_TYPES;


    //methods
    @Shadow abstract void resetChunk();
    @Shadow abstract World getWorld();

    @Shadow
    @Nullable abstract String getServerWorldDebugString();

    @Shadow abstract WorldChunk getClientChunk();
    @Shadow abstract WorldChunk getChunk();
    @Shadow abstract String propertyToString(Map.Entry<Property<?>, Comparable<?>> propEntry);

    @Shadow
    @Nullable abstract ServerWorld getServerWorld();

    @Shadow
    static long toMiB(long bytes) {
        return 0;
    }

    /**
     * @author
     */
    @Overwrite
    public List<String> getLeftText() {
        IntegratedServer integratedServer = this.client.getServer();
        ClientConnection clientConnection = this.client.getNetworkHandler().getConnection();
        float f = clientConnection.getAveragePacketsSent();
        float g = clientConnection.getAveragePacketsReceived();
        String string;
        if (integratedServer != null) {
            string = String.format("Integrated server @ %.0f ms ticks, %.0f tx, %.0f rx", integratedServer.getTickTime(), f, g);
        } else {
            string = String.format("\"%s\" server, %.0f tx, %.0f rx", this.client.player.getServerBrand(), f, g);
        }

        BlockPos blockPos = this.client.getCameraEntity().getBlockPos();
        String[] var10000;
        String var10003;
        if (this.client.hasReducedDebugInfo()) {
            var10000 = new String[9];
            var10003 = SharedConstants.getGameVersion().getName();
            var10000[0] = "Minecraft " + var10003 + " (" + this.client.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ")";
            var10000[1] = this.client.fpsDebugString;
            var10000[2] = string;
            var10000[3] = this.client.worldRenderer.getChunksDebugString();
            var10000[4] = this.client.worldRenderer.getEntitiesDebugString();
            var10003 = this.client.particleManager.getDebugString();
            var10000[5] = "P: " + var10003 + ". T: " + this.client.world.getRegularEntityCount();
            var10000[6] = this.client.world.asString();
            var10000[7] = "";
            var10000[8] = String.format("Chunk-relative: %d %d %d", blockPos.getX() & 15, blockPos.getY() & 15, blockPos.getZ() & 15);
            return Lists.newArrayList(var10000);
        } else {
            Entity entity = this.client.getCameraEntity();
            Direction direction = entity.getHorizontalFacing();
            String string2;
            switch(direction) {
                case NORTH:
                    string2 = "Towards negative Z";
                    break;
                case SOUTH:
                    string2 = "Towards positive Z";
                    break;
                case WEST:
                    string2 = "Towards negative X";
                    break;
                case EAST:
                    string2 = "Towards positive X";
                    break;
                default:
                    string2 = "Invalid";
            }

            ChunkPos chunkPos = new ChunkPos(blockPos);
            if (!Objects.equals(this.pos, chunkPos)) {
                this.pos = chunkPos;
                this.resetChunk();
            }

            World world = this.getWorld();
            LongSet longSet = world instanceof ServerWorld ? ((ServerWorld)world).getForcedChunks() : LongSets.EMPTY_SET;
            var10000 = new String[7];
            var10003 = SharedConstants.getGameVersion().getName();
            var10000[0] = "Minecraft " + var10003 + " (" + this.client.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ("release".equalsIgnoreCase(this.client.getVersionType()) ? "" : "/" + this.client.getVersionType()) + ")";
            var10000[1] = this.client.fpsDebugString;
            var10000[2] = string;
            var10000[3] = this.client.worldRenderer.getChunksDebugString();
            var10000[4] = this.client.worldRenderer.getEntitiesDebugString();
            var10003 = this.client.particleManager.getDebugString();
            var10000[5] = "P: " + var10003 + ". T: " + this.client.world.getRegularEntityCount();
            var10000[6] = this.client.world.asString();
            List<String> list = Lists.newArrayList(var10000);
            String string3 = this.getServerWorldDebugString();
            if (string3 != null) {
                list.add(string3);
            }

            Identifier var10001 = this.client.world.getRegistryKey().getValue();
            list.add(var10001 + " FC: " + ((LongSet)longSet).size());
            list.add("");
            list.add(String.format(Locale.ROOT, "Facing: %s (%s) (%.1f / %.1f)", direction, string2, MathHelper.wrapDegrees(entity.getYaw()), MathHelper.wrapDegrees(entity.getPitch())));
            WorldChunk worldChunk = this.getClientChunk();
            int var23;
            if (worldChunk.isEmpty()) {
                list.add("Waiting for chunk...");
            } else {
                int i = this.client.world.getChunkManager().getLightingProvider().getLight(blockPos, 0);
                int j = this.client.world.getLightLevel(LightType.SKY, blockPos);
                int k = this.client.world.getLightLevel(LightType.BLOCK, blockPos);
                list.add("Client Light: " + i + " (" + j + " sky, " + k + " block)");
                WorldChunk worldChunk2 = this.getChunk();
                StringBuilder stringBuilder = new StringBuilder("CH");
                Heightmap.Type[] var21 = Heightmap.Type.values();
                int var22 = var21.length;

                Heightmap.Type type;
                for(var23 = 0; var23 < var22; ++var23) {
                    type = var21[var23];
                    if (type.shouldSendToClient()) {
                        stringBuilder.append(" ").append((String)HEIGHT_MAP_TYPES.get(type)).append(": ").append(worldChunk.sampleHeightmap(type, blockPos.getX(), blockPos.getZ()));
                    }
                }

                list.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                stringBuilder.append("SH");
                var21 = Heightmap.Type.values();
                var22 = var21.length;

                for(var23 = 0; var23 < var22; ++var23) {
                    type = var21[var23];
                    if (type.isStoredServerSide()) {
                        stringBuilder.append(" ").append((String)HEIGHT_MAP_TYPES.get(type)).append(": ");
                        if (worldChunk2 != null) {
                            stringBuilder.append(worldChunk2.sampleHeightmap(type, blockPos.getX(), blockPos.getZ()));
                        } else {
                            stringBuilder.append("??");
                        }
                    }
                }

                list.add(stringBuilder.toString());
                if (blockPos.getY() >= this.client.world.getBottomY() && blockPos.getY() < this.client.world.getTopY()) {
                    Registry var27 = this.client.world.getRegistryManager().get(Registry.BIOME_KEY);
                    Biome var10002 = this.client.world.getBiome(blockPos);
                    list.add("Biome: " + var27.getId(var10002));
                    long l = 0L;
                    float h = 0.0F;
                    if (worldChunk2 != null) {
                        h = world.getMoonSize();
                        l = worldChunk2.getInhabitedTime();
                    }

                    LocalDifficulty localDifficulty  = new LocalDifficulty(world.getDifficulty(), world.getTimeOfDay(), l, h);
                    list.add(String.format(Locale.ROOT, "Local Difficulty: %.2f // %.2f (Day %d)", localDifficulty .getLocalDifficulty(), localDifficulty .getClampedLocalDifficulty(), this.client.world.getTimeOfDay() / 24000L));
                }
            }

            ServerWorld i = this.getServerWorld();
            if (i != null) {
                ServerChunkManager j = i.getChunkManager();
                ChunkGenerator k = j.getChunkGenerator();
                MultiNoiseUtil.MultiNoiseSampler worldChunk2 = k.getMultiNoiseSampler();
                BiomeSource stringBuilder = k.getBiomeSource();
                stringBuilder.addDebugInfo(list, blockPos, worldChunk2);
                SpawnHelper.Info l = j.getSpawnInfo();
                if (l != null) {
                    Object2IntMap<SpawnGroup> object2IntMap = l.getGroupToCount();
                    int m = l.getSpawningChunkCount();
                    list.add("SC: " + m + ", " + (String)Stream.of(SpawnGroup.values()).map((group) -> {
                        char var1000 = Character.toUpperCase(group.getName().charAt(0));
                        return var1000 + ": " + object2IntMap.getInt(group);
                    }).collect(Collectors.joining(", ")));
                } else {
                    list.add("SC: N/A");
                }
            }

            ShaderEffect j = this.client.gameRenderer.getShader();
            if (j != null) {
                list.add("Shader: " + j.getName());
            }

            String var29 = this.client.getSoundManager().getDebugString();
            list.add(var29 + String.format(" (Mood %d%%)", Math.round(this.client.player.getMoodPercentage() * 100.0F)));
            return list;
        }
    }
    /**
     * @author
     */
    @Overwrite
    public List<String> getRightText() {
        long l = Runtime.getRuntime().maxMemory();
        long m = Runtime.getRuntime().totalMemory();
        long n = Runtime.getRuntime().freeMemory();
        long o = m - n;
        List<String> list = Lists.newArrayList(new String[]{String.format("Java: %s %dbit", System.getProperty("java.version"), this.client.is64Bit() ? 64 : 32), String.format("Mem: % 2d%% %03d/%03dMB", o * 100L / l, toMiB(o), toMiB(l)), String.format("Allocated: % 2d%% %03dMB", m * 100L / l, toMiB(m)), "", String.format("CPU: %s", GlDebugInfo.getCpuInfo()), "", String.format("Display: %dx%d (%s)", MinecraftClient.getInstance().getWindow().getFramebufferWidth(), MinecraftClient.getInstance().getWindow().getFramebufferHeight(), GlDebugInfo.getVendor()), GlDebugInfo.getRenderer(), GlDebugInfo.getVersion()});
        if (this.client.hasReducedDebugInfo()) {
            return list;
        } else {
            BlockPos blockPos;
            UnmodifiableIterator var12;
            Map.Entry entry;
            Iterator var16;
            Formatting var10001;
            Identifier identifier;
            if (this.blockHit.getType() == net.minecraft.util.hit.HitResult.Type.BLOCK) {
                blockPos = ((BlockHitResult)this.blockHit).getBlockPos();
                BlockState blockState = this.client.world.getBlockState(blockPos);
                list.add("");
                var10001 = Formatting.UNDERLINE;
                list.add(var10001 + "Targeted Block: ");
                list.add(String.valueOf(Registry.BLOCK.getId(blockState.getBlock())));
                var12 = blockState.getEntries().entrySet().iterator();

                while(var12.hasNext()) {
                    entry = (Map.Entry)var12.next();
                    list.add(this.propertyToString(entry));
                }

                var16 = this.client.getNetworkHandler().getTagManager().getOrCreateTagGroup(Registry.BLOCK_KEY).getTagsFor(blockState.getBlock()).iterator();

                while(var16.hasNext()) {
                    identifier = (Identifier)var16.next();
                    list.add("#" + identifier);
                }
            }

            if (this.fluidHit.getType() == net.minecraft.util.hit.HitResult.Type.BLOCK) {
                blockPos = ((BlockHitResult)this.fluidHit).getBlockPos();
                FluidState blockState = this.client.world.getFluidState(blockPos);
                list.add("");
                var10001 = Formatting.UNDERLINE;
                list.add(var10001 + "Targeted Fluid: ");
                list.add(String.valueOf(Registry.FLUID.getId(blockState.getFluid())));
                var12 = blockState.getEntries().entrySet().iterator();

                while(var12.hasNext()) {
                    entry = (Map.Entry)var12.next();
                    list.add(this.propertyToString(entry));
                }

                var16 = this.client.getNetworkHandler().getTagManager().getOrCreateTagGroup(Registry.FLUID_KEY).getTagsFor(blockState.getFluid()).iterator();

                while(var16.hasNext()) {
                    identifier = (Identifier)var16.next();
                    list.add("#" + identifier);
                }
            }

            Entity entity = this.client.targetedEntity;
            if (entity != null) {
                list.add("");
                list.add(Formatting.UNDERLINE + "Targeted Entity");
                list.add(String.valueOf(Registry.ENTITY_TYPE.getId(entity.getType())));
            }

            return list;
        }
    }
}

