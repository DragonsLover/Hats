package us.ichun.mods.hats.client.render.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import us.ichun.mods.hats.api.RenderOnEntityHelper;
import us.ichun.mods.hats.common.Hats;

public class HelperSkeleton extends RenderOnEntityHelper {

	@Override
	public Class helperForClass() 
	{
		return EntitySkeleton.class;
	}

    @Override
    public boolean canWearHat(EntityLivingBase living)
    {
        return Hats.config.hatSkeleton== 1;
    }

	@Override
	public float getRotatePointVert(EntityLivingBase ent)
	{
		return ent.isSneaking() ? ent == Minecraft.getMinecraft().thePlayer ? 23F/16F : 21F/16F : 24F/16F ;
	}

	@Override
	public float getOffsetPointVert(EntityLivingBase ent)
	{
		return 8F/16F;
	}
}
