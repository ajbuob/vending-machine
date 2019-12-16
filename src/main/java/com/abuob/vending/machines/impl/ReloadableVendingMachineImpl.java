package com.abuob.vending.machines.impl;

import com.abuob.vending.auth.AdminAuthenticator;
import com.abuob.vending.functions.VendingMachineHardwareFunctions;
import com.abuob.vending.machines.ReloadableVendingMachine;
import com.abuob.vending.product.Item;

import java.util.LinkedHashMap;

public class ReloadableVendingMachineImpl extends VendingMachineImpl implements ReloadableVendingMachine {

    private AdminAuthenticator adminAuthenticator;

    public ReloadableVendingMachineImpl(VendingMachineHardwareFunctions vendingMachineHardwareFunctions,
                                        LinkedHashMap<Item, Integer> initialCapacityMap,
                                        AdminAuthenticator adminAuthenticator) {
        super(vendingMachineHardwareFunctions, initialCapacityMap);
        this.adminAuthenticator = adminAuthenticator;
    }

    @Override
    public Boolean addItemWithQuantity(Item item, Integer quantity, String username, String password) {
        if (!adminAuthenticator.isValid(username, password)) {
            return Boolean.FALSE;
        }
        return this.addItemWithQuantityToVendingMachine(item, quantity);
    }
}
