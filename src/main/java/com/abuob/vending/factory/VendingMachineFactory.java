package com.abuob.vending.factory;

import com.abuob.vending.functions.VendingMachineHardwareFunctions;
import com.abuob.vending.machines.ReloadableVendingMachine;
import com.abuob.vending.machines.VendingMachine;
import com.abuob.vending.machines.impl.ReloadableVendingMachineImpl;
import com.abuob.vending.machines.impl.VendingMachineImpl;
import com.abuob.vending.product.Item;

import java.util.LinkedHashMap;
import java.util.Objects;

public final class VendingMachineFactory {

    private VendingMachineFactory() {
    }

    public static VendingMachine createVendingMachine(VendingMachineHardwareFunctions vendingMachineHardwareFunctions,
                                                      LinkedHashMap<Item, Integer> initialCapacity) {
        if (Objects.isNull(vendingMachineHardwareFunctions) || Objects.isNull(initialCapacity)) {
            return null;
        }
        return new VendingMachineImpl(vendingMachineHardwareFunctions, initialCapacity);
    }

    public static ReloadableVendingMachine createReloadableVendingMachine(VendingMachineHardwareFunctions vendingMachineHardwareFunctions,
                                                                          LinkedHashMap<Item, Integer> initialCapacity) {
        if (Objects.isNull(vendingMachineHardwareFunctions) || Objects.isNull(initialCapacity)) {
            return null;
        }
        return new ReloadableVendingMachineImpl(vendingMachineHardwareFunctions, initialCapacity);
    }
}
