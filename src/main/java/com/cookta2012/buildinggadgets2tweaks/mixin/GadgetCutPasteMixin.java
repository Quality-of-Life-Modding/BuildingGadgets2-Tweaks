package com.cookta2012.buildinggadgets2tweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.cookta2012.buildinggadgets2tweaks.Helper;
import com.direwolf20.buildinggadgets2.common.items.GadgetCutPaste;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;


@Mixin({GadgetCutPaste.class})
public abstract class GadgetCutPasteMixin {
    @Redirect(method = {"customCutValidation(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;)Z"},
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;mayInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;)Z"), require = 1)
    private static boolean cutMayInteract(Level level, Player player, BlockPos pos) {
        return Helper.canBreak(level, player, pos);
    }
}
