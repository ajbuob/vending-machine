package com.abuob.vending.impl;

import com.abuob.vending.ReloadableVendingMachine;
import com.abuob.vending.VendingMachineHardwareFunctions;
import com.abuob.vending.product.Item;

public class ReloadableVendingMachineImpl extends VendingMachineImpl implements ReloadableVendingMachine {

    public ReloadableVendingMachineImpl(VendingMachineHardwareFunctions vendingMachineHardwareFunctions) {
        super(vendingMachineHardwareFunctions);
    }

    @Override
    public Boolean addItemWithQuantity(Item item, Integer quantity) {
        Integer totalQuantity = quantity;
        //Check for any existing items in the inventory
        if (inventoryMap.containsKey(item)) {
            totalQuantity = totalQuantity + inventoryMap.get(item);
        } else {
            //Add the new item to the available options
            positionList.add(item);
        }
        //Update the inventory
        inventoryMap.put(item, totalQuantity);
        vendingMachineHardwareFunctions.showMessage(String.format("Added quantity %s of %s item(s)", totalQuantity, item.getProductName()));
        return true;
    }
}
