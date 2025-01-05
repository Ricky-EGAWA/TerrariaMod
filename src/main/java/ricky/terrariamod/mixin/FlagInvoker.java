package ricky.terrariamod.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Entity.class)
public interface FlagInvoker {
    @Invoker("getFlag")
    boolean invokeGetFlag(int index);

    @Invoker("setFlag")
    void invokeSetFlag(int index, boolean value);
}
