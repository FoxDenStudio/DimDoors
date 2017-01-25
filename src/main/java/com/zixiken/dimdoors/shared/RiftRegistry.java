/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zixiken.dimdoors.shared;

import com.zixiken.dimdoors.DimDoors;
import com.zixiken.dimdoors.tileentities.DDTileEntityBase;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 *
 * @author Robijnvogel
 */
public class RiftRegistry {

    public static final RiftRegistry Instance = new RiftRegistry();

    // Privates
    private int nextRiftID;
    private final Map<Integer, Location> riftList;

    // Methods
    public RiftRegistry() {
        nextRiftID = 0;
        riftList = new HashMap();
    }

    public void reset() {
        nextRiftID = 0;
        riftList.clear();
    }

    public void readFromNBT(NBTTagCompound nbt) {
        nextRiftID = nbt.getInteger("nextUnusedID");
        if (nbt.hasKey("riftData")) {
            NBTTagCompound riftsNBT = nbt.getCompoundTag("riftData");
            int i = 1;
            String tag = "" + i;
            while (riftsNBT.hasKey(tag)) {
                NBTTagCompound riftNBT = riftsNBT.getCompoundTag(tag);
                Location riftLocation = Location.readFromNBT(riftNBT);
                riftList.put(i, riftLocation);

                i++;
                tag = "" + i;
            }
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("nextUnusedID", nextRiftID);
        NBTTagCompound riftsNBT = new NBTTagCompound();
        for (Map.Entry<Integer, Location> entry : riftList.entrySet()) {
            riftsNBT.setTag("" + entry.getKey(), Location.writeToNBT(entry.getValue()));
        }
        nbt.setTag("riftData", riftsNBT);
    }

    public int registerNewRift(DDTileEntityBase rift) {
        riftList.put(nextRiftID, Location.getLocation(rift));
        //DimDoors.log("Rift registered as ID: " + nextRiftID);
        nextRiftID++;
        RiftSavedData.get(DimDoors.getDefWorld()).markDirty(); //Notify that this needs to be saved on world save
        return nextRiftID - 1;
    }

    public void removeRift(int riftID, World world) {
        if (riftList.containsKey(riftID)) {
            riftList.remove(riftID);
            RiftSavedData.get(DimDoors.getDefWorld()).markDirty(); //Notify that this needs to be saved on world save
        }
    }

    public Location getRiftLocation(int ID) {
        return riftList.get(ID);
    }

    public void pair(int riftID, int riftID2) {
        Location location = riftList.get(riftID);
        TileEntity tileEntity = location.getTileEntity(); //@todo this method might need to be in another class?
        if (tileEntity != null && tileEntity instanceof DDTileEntityBase) {
            DDTileEntityBase rift = (DDTileEntityBase) tileEntity;
            rift.pair(riftID2);
        }
    }

    public void unpair(int riftID) {
        Location location = riftList.get(riftID);
        TileEntity tileEntity = location.getTileEntity();
        if (tileEntity != null && tileEntity instanceof DDTileEntityBase) {
            DDTileEntityBase rift = (DDTileEntityBase) tileEntity;
            rift.unpair();
        }
    }
}
