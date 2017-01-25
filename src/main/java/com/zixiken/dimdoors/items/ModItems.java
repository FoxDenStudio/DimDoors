package com.zixiken.dimdoors.items;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import com.zixiken.dimdoors.blocks.ModBlocks;

public class ModItems {

    public static ItemDimDoorGold itemDimDoorGold;
    public static ItemDoorGold itemDoorGold;
    public static ItemWorldThread itemWorldThread;
    public static ItemDimDoor itemDimDoor;
    public static ItemDimDoorWarp itemDimDoorWarp;
    public static ItemStableFabric itemStableFabric;
    public static ItemDimDoorUnstable itemDimDoorChaos;
    public static ItemDoorQuartz itemDoorQuartz;
    public static ItemDimDoorPersonal itemDimDoorPersonal;
    public static ItemBlockDimWall itemBlockDimWall;

    public static void registerItems() {
        GameRegistry.register(itemDoorQuartz = new ItemDoorQuartz());
        GameRegistry.register(itemDimDoorPersonal = new ItemDimDoorPersonal());
        GameRegistry.register(itemDoorGold = new ItemDoorGold());
        GameRegistry.register(itemDimDoorGold = new ItemDimDoorGold());
        GameRegistry.register(itemDimDoor = new ItemDimDoor());
        GameRegistry.register(itemDimDoorWarp = new ItemDimDoorWarp());
        GameRegistry.register(itemStableFabric = new ItemStableFabric());
        GameRegistry.register(itemDimDoorChaos = new ItemDimDoorUnstable());
        GameRegistry.register(itemWorldThread = new ItemWorldThread());

        //ItemBlocks
        GameRegistry.register(itemBlockDimWall = new ItemBlockDimWall());
        GameRegistry.register(new ItemBlock(ModBlocks.blockDimHatch)
                .setRegistryName(ModBlocks.blockDimHatch.getRegistryName()));
        GameRegistry.register(new ItemBlock(ModBlocks.blockRift)
                .setRegistryName(ModBlocks.blockRift.getRegistryName()));
    }
}
