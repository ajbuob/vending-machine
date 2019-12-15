package com.abuob.vending.factory;

import com.abuob.vending.VendingMachine;
import com.abuob.vending.VendingMachineHardwareFunctions;
import com.abuob.vending.impl.ReloadableVendingMachineImpl;
import com.abuob.vending.impl.VendingMachineImpl;

import java.util.Objects;

public final class VendingMachineFactory {

    private VendingMachineFactory() {
    }

    public static VendingMachine createVendingMachine(Boolean isReloadable, VendingMachineHardwareFunctions vendingMachineHardwareFunctions) {
        if(Objects.isNull(isReloadable) || Objects.isNull(vendingMachineHardwareFunctions)) {
            return null;
        }
        return isReloadable ? new ReloadableVendingMachineImpl(vendingMachineHardwareFunctions) :
                new VendingMachineImpl(vendingMachineHardwareFunctions);
    }
}
