package com.abuob.vending.factory;

import com.abuob.vending.VendingMachine;
import com.abuob.vending.VendingMachineHardwareFunctions;
import com.abuob.vending.impl.VendingMachineImpl;

public final class VendingMachineFactory {

    private VendingMachineFactory() {
    }

    public static VendingMachine createVendingMachine(VendingMachineHardwareFunctions vendingMachineHardwareFunctions) {
        return new VendingMachineImpl(vendingMachineHardwareFunctions);
    }
}
