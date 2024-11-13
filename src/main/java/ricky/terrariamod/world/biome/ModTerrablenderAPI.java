package ricky.terrariamod.world.biome;

import net.minecraft.util.Identifier;
import ricky.terrariamod.TerrariaMod;
import ricky.terrariamod.world.biome.suraface.ModMaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized(){
        Regions.register(new ModOverworldRegion(new Identifier(TerrariaMod.MOD_ID, "overworld"), 4));
        // 表層のルールを登録
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, TerrariaMod.MOD_ID,
                ModMaterialRules.makeCustomSurfaceRules());
    }
}
