package elucent.eidolon.datagen;

import elucent.eidolon.Eidolon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Eidolon.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Datagen {

    //use runData configuration to generate stuff, event.includeServer() for data, event.includeClient() for assets
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        PackOutput output = gen.getPackOutput();

        var blockGen = new EidBlockTagProvider(gen, provider, fileHelper);
        gen.addProvider(event.includeServer(), blockGen);
        gen.addProvider(event.includeServer(), new EidItemTagProvider(gen, provider, blockGen, fileHelper));
        gen.addProvider(event.includeServer(), new ModLootTables(gen));
        gen.addProvider(event.includeServer(), new EidRecipeProvider(gen));
        gen.addProvider(event.includeServer(), new EidBiomeTagProvider(gen, provider, fileHelper));
        gen.addProvider(event.includeServer(), new EidWorldgenProvider(output, provider));
    }

}