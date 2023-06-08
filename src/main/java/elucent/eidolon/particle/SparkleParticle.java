package elucent.eidolon.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import elucent.eidolon.ClientConfig;
import elucent.eidolon.event.ClientEvents;
import elucent.eidolon.util.RenderUtil;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import org.jetbrains.annotations.NotNull;

public class SparkleParticle extends GenericParticle {
    public SparkleParticle(ClientLevel world, GenericParticleData data, double x, double y, double z, double vx, double vy, double vz) {
        super(world, data, x, y, z, vx, vy, vz);
        BedRenderer ch;
    }

    @Override
    protected int getLightColor(float partialTicks) {
        return 0xF000F0;
    }

    @Override
    public void render(@NotNull VertexConsumer b, @NotNull Camera info, float pticks) {
        super.render(ClientConfig.BETTER_LAYERING.get() ? ClientEvents.getDelayedRender().getBuffer(RenderUtil.GLOWING_PARTICLE) : b, info, pticks);
    }
}
