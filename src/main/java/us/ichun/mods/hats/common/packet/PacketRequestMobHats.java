package us.ichun.mods.hats.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import us.ichun.mods.hats.common.Hats;
import us.ichun.mods.ichunutil.common.core.network.AbstractPacket;

import java.util.ArrayList;

public class PacketRequestMobHats extends AbstractPacket
{
    public ArrayList<Integer> entIds;

    public PacketRequestMobHats(){}

    public PacketRequestMobHats(ArrayList<Integer> list)
    {
        entIds = new ArrayList<Integer>(list);
    }

    @Override
    public void writeTo(ByteBuf buffer, Side side)
    {
        for(Integer i : entIds)
        {
            buffer.writeInt(i);
        }
        buffer.writeInt(-2);
    }

    @Override
    public void readFrom(ByteBuf buffer, Side side)
    {
        entIds = new ArrayList<Integer>();
        int id = buffer.readInt();
        while(id != -2)
        {
            entIds.add(id);
            id = buffer.readInt();
        }
    }

    @Override
    public void execute(Side side, EntityPlayer player)
    {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> names = new ArrayList<String>();

        for(Integer id : entIds)
        {
            Entity ent = player.worldObj.getEntityByID(id);
            if(ent instanceof EntityLivingBase)
            {
                String hatName = Hats.proxy.tickHandlerServer.mobHats.get((EntityLivingBase)ent);
                if(hatName != null)
                {
                    ids.add(id);
                    names.add(hatName.trim());
                }
            }
        }
        ids.add(-2);

        Hats.channel.sendToPlayer(new PacketMobHatsList(ids, names), player);
    }
}
