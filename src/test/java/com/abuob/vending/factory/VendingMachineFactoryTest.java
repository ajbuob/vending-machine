package com.abuob.vending.factory;

import com.abuob.vending.auth.impl.InMemoryAdminAuthenticator;
import com.abuob.vending.functions.impl.DefaultVendingMachineHardwareFunctions;
import com.abuob.vending.machines.impl.ReloadableVendingMachineImpl;
import com.abuob.vending.machines.impl.VendingMachineImpl;
import com.abuob.vending.product.Item;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class VendingMachineFactoryTest {

    private LinkedHashMap<Item, Integer> initialCapacityMap = new LinkedHashMap<>();

    @Test
    public void createVendingMachine_nullInput() {
        assertThat(VendingMachineFactory.createVendingMachine(null, initialCapacityMap))
                .isNull();

        assertThat(VendingMachineFactory.createVendingMachine(new DefaultVendingMachineHardwareFunctions(), null))
                .isNull();
    }

    @Test
    public void createReloadableVendingMachine_nullInput() {
        assertThat(VendingMachineFactory.createReloadableVendingMachine(null,
                initialCapacityMap,
                new InMemoryAdminAuthenticator(new HashMap<>())))
                .isNull();

        assertThat(VendingMachineFactory.createReloadableVendingMachine(new DefaultVendingMachineHardwareFunctions(),
                null,
                new InMemoryAdminAuthenticator(new HashMap<>())))
                .isNull();

        assertThat(VendingMachineFactory.createReloadableVendingMachine(new DefaultVendingMachineHardwareFunctions(),
                new LinkedHashMap<>(),
                null)).
                isNull();
    }

    @Test
    public void createVendingMachine_success() {
        assertThat(VendingMachineFactory.createVendingMachine(new DefaultVendingMachineHardwareFunctions(), initialCapacityMap))
                .isNotNull().isInstanceOf(VendingMachineImpl.class);
    }

    @Test
    public void createReloadableVendingMachine_success() {
        assertThat(VendingMachineFactory.createReloadableVendingMachine(new DefaultVendingMachineHardwareFunctions(),
                initialCapacityMap,
                new InMemoryAdminAuthenticator(new HashMap<>())))
                .isNotNull().isInstanceOf(ReloadableVendingMachineImpl.class);
    }
}
