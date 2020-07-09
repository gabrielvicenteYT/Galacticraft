package micdoodle8.mods.galacticraft.planets.mars.dimension;

import micdoodle8.mods.galacticraft.api.vector.Vector3D;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.mars.entities.EntityLandingBalloons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class TeleportTypeMars implements ITeleportType
{
    @Override
    public boolean useParachute()
    {
        return false;
    }

    @Override
    public Vector3D getPlayerSpawnLocation(ServerWorld world, ServerPlayerEntity player)
    {
        if (player != null)
        {
            GCPlayerStats stats = GCPlayerStats.get(player);
            double x = stats.getCoordsTeleportedFromX();
            double z = stats.getCoordsTeleportedFromZ();
            int limit = ConfigManagerCore.otherPlanetWorldBorders - 2;
            if (limit > 20)
            {
                if (x > limit)
                {
                    z *= limit / x;
                    x = limit;
                }
                else if (x < -limit)
                {
                    z *= -limit / x;
                    x = -limit;
                }
                if (z > limit)
                {
                    x *= limit / z;
                    z = limit;
                }
                else if (z < -limit)
                {
                    x *= -limit / z;
                    z = -limit;
                }
            }
            return new Vector3D(x, ConfigManagerCore.disableLander ? 250.0 : 900.0, z);
        }

        return null;
    }

    @Override
    public Vector3D getEntitySpawnLocation(ServerWorld world, Entity entity)
    {
        return new Vector3D(entity.posX, ConfigManagerCore.disableLander ? 250.0 : 900.0, entity.posZ);
    }

    @Override
    public Vector3D getParaChestSpawnLocation(ServerWorld world, ServerPlayerEntity player, Random rand)
    {
        return null;
    }

    @Override
    public void onSpaceDimensionChanged(World newWorld, ServerPlayerEntity player, boolean ridingAutoRocket)
    {
        if (!ridingAutoRocket && player != null)
        {
            GCPlayerStats stats = GCPlayerStats.get(player);

            if (stats.getTeleportCooldown() <= 0)
            {
                if (player.abilities.isFlying)
                {
                    player.abilities.isFlying = false;
                }

                EntityLandingBalloons lander = EntityLandingBalloons.createEntityLandingBalloons(player);

                if (!newWorld.isRemote)
                {
                    boolean previous = CompatibilityManager.forceLoadChunks((ServerWorld) newWorld);
                    lander.forceSpawn = true;
                    newWorld.addEntity(lander);
                    CompatibilityManager.forceLoadChunksEnd((ServerWorld) newWorld, previous);
                }

                stats.setTeleportCooldown(10);
            }
        }
    }

    @Override
    public void setupAdventureSpawn(ServerPlayerEntity player)
    {
        // TODO Auto-generated method stub

    }
}
