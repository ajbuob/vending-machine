package com.abuob.vending.factory;

import com.abuob.vending.impl.DefaultVendingMachineHardwareFunctions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VendingMachineFactoryTest {

    @Test
    public void createVendingMachine_nullInput() {
        assertThat(VendingMachineFactory.createVendingMachine(null)).isNull();
    }

    @Test
    public void createVendingMachine_success() {
        assertThat(VendingMachineFactory.createVendingMachine(new DefaultVendingMachineHardwareFunctions())).isNotNull();
    }
}
