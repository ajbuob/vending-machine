package com.abuob.vending.factory;

import com.abuob.vending.functions.VendingMachineHardwareFunctions;
import com.abuob.vending.machines.VendingMachine;
import com.abuob.vending.machines.impl.ReloadableVendingMachineImpl;
import com.abuob.vending.machines.impl.VendingMachineImpl;
import com.abuob.vending.product.Item;

import java.util.LinkedHashMap;
import java.util.Objects;

public final class VendingMachineFactory {

    private VendingMachineFactory() {
    }

    public static VendingMachine createVendingMachine(Boolean isReloadable,
                                                      VendingMachineHardwareFunctions vendingMachineHardwareFunctions,
                                                      LinkedHashMap<Item, Integer> initialCapacity) {
        if (Objects.isNull(isReloadable) || Objects.isNull(vendingMachineHardwareFunctions)) {
            return null;
        }
        return isReloadable ? new ReloadableVendingMachineImpl(vendingMachineHardwareFunctions, initialCapacity) :
                new VendingMachineImpl(vendingMachineHardwareFunctions, initialCapacity);
    }
}
