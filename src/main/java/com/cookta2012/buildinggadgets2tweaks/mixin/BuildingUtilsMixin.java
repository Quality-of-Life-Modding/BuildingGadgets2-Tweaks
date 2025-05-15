package com.cookta2012.buildinggadgets2tweaks.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.direwolf20.buildinggadgets2.util.BuildingUtils;
import com.cookta2012.buildinggadgets2tweaks.Helper;

@Mixin(BuildingUtils.class)
public abstract class BuildingUtilsMixin{
    @Redirect(
      method = "build(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Ljava/util/ArrayList;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;Z)Ljava/util/UUID;",
      at = @At(
        value = "INVOKE",
        target = "Lnet/minecraft/world/level/Level;mayInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;)Z"
      )
    )
    private static boolean buildMayInteract(Level level, Player player, BlockPos pos)
    {
        ServerLevel _level = (ServerLevel)level;
        //level.mayInteract()
    	return Helper.mayPlace(_level, player, pos);
    }
    
    @Redirect(
        method = "exchange(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Ljava/util/ArrayList;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;ZZ)Ljava/util/UUID;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;mayInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;)Z"
        )
    )
    private static boolean redirectExchangeMayInteract(Level level, Player player, BlockPos pos) {
		return Helper.mayExchange((ServerLevel) level, player, pos);
    }
    @Redirect(
    		method = "removeTickHandler(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Ljava/util/List;ZZLnet/minecraft/world/item/ItemStack;)Ljava/util/UUID;",
    		at = @At(
    				value = "INVOKE",
    				target = "Lnet/minecraft/world/level/Level;mayInteract(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;)Z"
    				)
    		)
    private static boolean redirectTickSomething(Level level, Player player, BlockPos pos) {
    	return Helper.canBreak(level, player, pos);
    }


    
}
 
