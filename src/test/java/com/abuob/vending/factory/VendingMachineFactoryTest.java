package com.abuob.vending.factory;

import com.abuob.vending.impl.DefaultVendingMachineHardwareFunctions;
import com.abuob.vending.impl.ReloadableVendingMachineImpl;
import com.abuob.vending.impl.VendingMachineImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VendingMachineFactoryTest {

    @Test
    public void createVendingMachine_nullInput() {
        assertThat(VendingMachineFactory.createVendingMachine(true,null)).isNull();
        assertThat(VendingMachineFactory.createVendingMachine(false,null)).isNull();
    }

    @Test
    public void createVendingMachine_success() {
        assertThat(VendingMachineFactory.createVendingMachine(false, new DefaultVendingMachineHardwareFunctions()))
                .isNotNull().isInstanceOf(VendingMachineImpl.class);

        assertThat(VendingMachineFactory.createVendingMachine(true, new DefaultVendingMachineHardwareFunctions()))
                .isNotNull().isInstanceOf(ReloadableVendingMachineImpl.class);
    }
}
