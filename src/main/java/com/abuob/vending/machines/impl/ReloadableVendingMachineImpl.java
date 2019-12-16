package com.abuob.vending.machines.impl;

import com.abuob.vending.functions.VendingMachineHardwareFunctions;
import com.abuob.vending.machines.ReloadableVendingMachine;
import com.abuob.vending.product.Item;

import java.util.LinkedHashMap;

public class ReloadableVendingMachineImpl extends VendingMachineImpl implements ReloadableVendingMachine {

    public ReloadableVendingMachineImpl(VendingMachineHardwareFunctions vendingMachineHardwareFunctions, LinkedHashMap<Item, Integer> initialCapacityMap) {
        super(vendingMachineHardwareFunctions, initialCapacityMap);
    }

    @Override
    public Boolean addItemWithQuantity(Item item, Integer quantity) {
        return this.addItemWithQuantityToVendingMachine(item, quantity);
    }
}
