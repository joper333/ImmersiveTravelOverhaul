package com.beatrix.itoneo.mixin;

import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(DebugScreenOverlay.class)
public class DebugScreenOverlayMixin {

    /**
     * Strips Minecraft color/formatting codes (§a, §r, etc.) for plain-text matching.
     */
    private static String strip(String s) {
        return s == null ? "" : s.replaceAll("§[0-9a-fk-or]", "");
    }

    /**
     * Returns true for any debug line that reveals the player's position in the world.
     *
     * Covers all vanilla F3 lines that show coordinates:
     *   XYZ: x.xx / y.yy / z.zz        — exact player position
     *   Block: x y z                    — player block position
     *   Chunk: cx cy cz in cx cy cz     — chunk coordinates
     *   Targeted Block: x y z           — block under crosshair
     *   Targeted Fluid: x y z           — fluid under crosshair
     *   Velocity: x y z                 — movement vector
     */
    private static boolean isPositionLine(String raw) {
        if (raw == null || raw.isBlank()) return false;
        String line = strip(raw).trim();
        return line.startsWith("XYZ:")
            || line.startsWith("Block:")
            || line.startsWith("Chunk:")
            || line.startsWith("Targeted Block:")
            || line.startsWith("Targeted Fluid:")
            || line.startsWith("Velocity:");
    }

    /**
     * Intercepts the LEFT panel of the F3 screen (player info, XYZ, block, chunk, etc.)
     * Vanilla method: DebugScreenOverlay#getGameInformation()
     */
    @Inject(method = "getGameInformation", at = @At("RETURN"), cancellable = true)
    private void hideGamePositionInfo(CallbackInfoReturnable<List<String>> cir) {
        List<String> filtered = new ArrayList<>();
        for (String line : cir.getReturnValue()) {
            if (!isPositionLine(line)) {
                filtered.add(line);
            }
        }
        cir.setReturnValue(filtered);
    }

    /**
     * Intercepts the RIGHT panel of the F3 screen (system/GPU info side).
     * Vanilla method: DebugScreenOverlay#getSystemInformation()
     * Included for completeness in case any mod adds position data here.
     */
    @Inject(method = "getSystemInformation", at = @At("RETURN"), cancellable = true)
    private void hideSystemPositionInfo(CallbackInfoReturnable<List<String>> cir) {
        List<String> filtered = new ArrayList<>();
        for (String line : cir.getReturnValue()) {
            if (!isPositionLine(line)) {
                filtered.add(line);
            }
        }
        cir.setReturnValue(filtered);
    }
}
