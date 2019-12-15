package com.abuob.vending.factory;

import com.abuob.vending.VendingMachine;
import com.abuob.vending.VendingMachineHardwareFunctions;
import com.abuob.vending.impl.VendingMachineImpl;

import java.util.Objects;

public final class VendingMachineFactory {

    private VendingMachineFactory() {
    }

    public static VendingMachine createVendingMachine(VendingMachineHardwareFunctions vendingMachineHardwareFunctions) {
        return !Objects.isNull(vendingMachineHardwareFunctions) ?
                new VendingMachineImpl(vendingMachineHardwareFunctions) : null;
    }
}
