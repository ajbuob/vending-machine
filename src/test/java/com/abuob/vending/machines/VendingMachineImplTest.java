package com.abuob.vending.machines;

import com.abuob.vending.functions.VendingMachineHardwareFunctions;
import com.abuob.vending.machines.impl.VendingMachineImpl;
import com.abuob.vending.product.Item;
import org.junit.Before;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class VendingMachineImplTest {

    private VendingMachine vendingMachine;
    private VendingMachineHardwareFunctions vendingMachineHardwareFunctions = mock(VendingMachineHardwareFunctions.class);

    @Before
    public void setup() {
        Item item1 = new Item("product1", 35);
        Item item2 = new Item("product2", 20);

        Map<Item, Integer> initialCapacityMap = new HashMap<>();
        initialCapacityMap.put(item1, 1);
        initialCapacityMap.put(item2, 1);

        vendingMachine = new VendingMachineImpl(vendingMachineHardwareFunctions, initialCapacityMap);
    }
}
