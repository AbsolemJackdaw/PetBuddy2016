package subaraki.petbuddy.registry.modbus;

import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import subaraki.petbuddy.mod.PetBuddy;

@EventBusSubscriber(modid = PetBuddy.MODID, bus = Bus.MOD)
public class RegisterEntityAttributes {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event)
    {

        AttributeModifierMap map = AttributeModifierMap.builder()
                .add(ForgeMod.ENTITY_GRAVITY.get(), 0.1D)
                .add(ForgeMod.SWIM_SPEED.get(), 3D)
                .add(Attributes.ARMOR, 2D)
                .add(Attributes.ARMOR_TOUGHNESS, 2d)
                .add(Attributes.ATTACK_DAMAGE, 0.25D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.05d)
                .add(Attributes.ATTACK_SPEED, 0.25d)
                .add(Attributes.FLYING_SPEED, 0.2d)
                .add(Attributes.FOLLOW_RANGE, 10d)
                .add(Attributes.JUMP_STRENGTH, 1d)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2d)
                .add(Attributes.LUCK, 1.5d)
                .add(Attributes.MAX_HEALTH, 20d)
                .add(Attributes.MOVEMENT_SPEED, 0.3d)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.0d)
                .build();

        event.put(PetBuddy.ENTITY_PETBUDDY_TYPE.get(), map);
    }
}