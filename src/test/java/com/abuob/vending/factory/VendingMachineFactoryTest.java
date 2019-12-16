package com.abuob.vending.factory;

import com.abuob.vending.functions.impl.DefaultVendingMachineHardwareFunctions;
import com.abuob.vending.machines.impl.ReloadableVendingMachineImpl;
import com.abuob.vending.machines.impl.VendingMachineImpl;
import com.abuob.vending.product.Item;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class VendingMachineFactoryTest {

    private LinkedHashMap<Item, Integer> initialCapacityMap = new LinkedHashMap<>();

    @Test
    public void createVendingMachine_nullInput() {
        assertThat(VendingMachineFactory.createVendingMachine(true, null, initialCapacityMap))
                .isNull();
        assertThat(VendingMachineFactory.createVendingMachine(false, null, initialCapacityMap))
                .isNull();
    }

    @Test
    public void createVendingMachine_success() {
        assertThat(VendingMachineFactory.createVendingMachine(false, new DefaultVendingMachineHardwareFunctions(), initialCapacityMap))
                .isNotNull().isInstanceOf(VendingMachineImpl.class);

        assertThat(VendingMachineFactory.createVendingMachine(true, new DefaultVendingMachineHardwareFunctions(), initialCapacityMap))
                .isNotNull().isInstanceOf(ReloadableVendingMachineImpl.class);
    }
}
