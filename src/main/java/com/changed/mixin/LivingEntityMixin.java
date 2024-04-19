package com.changed.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World.ExplosionSourceType;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity{
  private LivingEntityMixin(){
    super(null, null);
  }

  @Inject(method = "onDeath", at = @At("HEAD"))
  private void changed$onDeath(DamageSource damageSource, CallbackInfo ci){
    LivingEntity entity = (LivingEntity)(Object) this;
    if(entity instanceof CreeperEntity creeper){
      if(damageSource.getAttacker() instanceof PlayerEntity){
        creeper.getWorld().createExplosion(creeper,creeper.getX() , creeper.getY(), creeper.getZ(), 15, ExplosionSourceType.MOB);
      } 
    }
  }
  
}
