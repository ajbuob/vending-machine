package com.abuob.vending.machines;

import com.abuob.vending.functions.VendingMachineHardwareFunctions;
import com.abuob.vending.machines.impl.VendingMachineImpl;
import com.abuob.vending.product.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class VendingMachineImplTest {

    private VendingMachine vendingMachine;
    private VendingMachineHardwareFunctions vendingMachineHardwareFunctions = mock(VendingMachineHardwareFunctions.class);

    private Item item1 = new Item("product1", 35);
    private Item item2 = new Item("product2", 60);

    @Before
    public void setup() {
        LinkedHashMap<Item, Integer> initialCapacityMap = new LinkedHashMap<>();
        vendingMachine = new VendingMachineImpl(vendingMachineHardwareFunctions, initialCapacityMap);
    }

    @Test
    public void test_buttonPress_NoMoneyAdded() {
        LinkedHashMap<Item, Integer> initialCapacityMap = new LinkedHashMap<>();
        initialCapacityMap.put(item1, 1);
        vendingMachine = new VendingMachineImpl(vendingMachineHardwareFunctions, initialCapacityMap);
        verify(vendingMachineHardwareFunctions, times(1)).showMessage(anyString());

        //Display the price of what is in position 1
        vendingMachine.buttonPress(1);
        verify(vendingMachineHardwareFunctions, times(2)).showMessage(anyString());

        //Verify contents
        assertThat(vendingMachine.getPositionList()).hasSize(1);
        Item returnedItem = vendingMachine.getPositionList().get(0);

        assertThat(returnedItem.getProductName()).isEqualTo(item1.getProductName());
        assertThat(returnedItem.getPriceInCents()).isEqualTo(item1.getPriceInCents());

        Map<Item, Integer> contentsMap = vendingMachine.getInventoryMap();
        assertThat(contentsMap).hasSize(1);
        assertThat(contentsMap.get(item1)).isEqualTo(1);

        //Verify method invocations
        verify(vendingMachineHardwareFunctions, never()).dispenseChange(anyInt());
        verify(vendingMachineHardwareFunctions, never()).dispenseProduct(anyInt(), anyString());
    }

    @Test
    public void test_buttonPress_notEnoughMoney() {
        LinkedHashMap<Item, Integer> initialCapacityMap = new LinkedHashMap<>();
        initialCapacityMap.put(item1, 1);
        vendingMachine = new VendingMachineImpl(vendingMachineHardwareFunctions, initialCapacityMap);
        verify(vendingMachineHardwareFunctions, times(1)).showMessage(anyString());

        //Add money to the vending machine
        vendingMachine.addUserMoney(25);

        //Try to buy what is in position 1
        vendingMachine.buttonPress(1);
        verify(vendingMachineHardwareFunctions, times(2)).showMessage(anyString());

        //Verify contents
        assertThat(vendingMachine.getPositionList()).hasSize(1);
        Item returnedItem = vendingMachine.getPositionList().get(0);

        assertThat(returnedItem.getProductName()).isEqualTo(item1.getProductName());
        assertThat(returnedItem.getPriceInCents()).isEqualTo(item1.getPriceInCents());

        Map<Item, Integer> contentsMap = vendingMachine.getInventoryMap();
        assertThat(contentsMap).hasSize(1);
        assertThat(contentsMap.get(item1)).isEqualTo(1);

        //Verify method invocations
        verify(vendingMachineHardwareFunctions, never()).dispenseChange(anyInt());
        verify(vendingMachineHardwareFunctions, never()).dispenseProduct(anyInt(), anyString());
    }

    @Test
    public void test_buttonPress_WithMoneyExactChange() {
        LinkedHashMap<Item, Integer> initialCapacityMap = new LinkedHashMap<>();
        initialCapacityMap.put(item1, 1);
        vendingMachine = new VendingMachineImpl(vendingMachineHardwareFunctions, initialCapacityMap);
        verify(vendingMachineHardwareFunctions, times(1)).showMessage(anyString());

        //Add money to the vending machine
        vendingMachine.addUserMoney(25);
        vendingMachine.addUserMoney(10);

        //Buy what is in position 1
        vendingMachine.buttonPress(1);

        //Verify contents
        assertThat(vendingMachine.getPositionList()).hasSize(1);
        Item returnedItem = vendingMachine.getPositionList().get(0);

        assertThat(returnedItem.getProductName()).isEqualTo(item1.getProductName());
        assertThat(returnedItem.getPriceInCents()).isEqualTo(item1.getPriceInCents());

        Map<Item, Integer> contentsMap = vendingMachine.getInventoryMap();
        assertThat(contentsMap).hasSize(1);
        assertThat(contentsMap.get(item1)).isEqualTo(0);

        //Verify method invocations
        verify(vendingMachineHardwareFunctions, never()).dispenseChange(anyInt());
        verify(vendingMachineHardwareFunctions, times(1)).dispenseProduct(anyInt(), anyString());
    }

    @Test
    public void test_buttonPress_WithMoneyChangeGiven() {
        LinkedHashMap<Item, Integer> initialCapacityMap = new LinkedHashMap<>();
        initialCapacityMap.put(item1, 1);
        vendingMachine = new VendingMachineImpl(vendingMachineHardwareFunctions, initialCapacityMap);
        verify(vendingMachineHardwareFunctions, times(1)).showMessage(anyString());

        //Add money to the vending machine
        vendingMachine.addUserMoney(25);
        vendingMachine.addUserMoney(25);

        //Buy what is in position 1
        vendingMachine.buttonPress(1);

        //Verify contents
        assertThat(vendingMachine.getPositionList()).hasSize(1);
        Item returnedItem = vendingMachine.getPositionList().get(0);

        assertThat(returnedItem.getProductName()).isEqualTo(item1.getProductName());
        assertThat(returnedItem.getPriceInCents()).isEqualTo(item1.getPriceInCents());

        Map<Item, Integer> contentsMap = vendingMachine.getInventoryMap();
        assertThat(contentsMap).hasSize(1);
        assertThat(contentsMap.get(item1)).isEqualTo(0);

        //Verify method invocations
        verify(vendingMachineHardwareFunctions, times(1)).dispenseChange(anyInt());
        verify(vendingMachineHardwareFunctions, times(1)).dispenseProduct(anyInt(), anyString());
    }

    @Test
    public void test_buttonPress_WithMoneyChangeGiven_MultipleProducts() {
        LinkedHashMap<Item, Integer> initialCapacityMap = new LinkedHashMap<>();
        initialCapacityMap.put(item1, 1);
        initialCapacityMap.put(item2, 1);
        vendingMachine = new VendingMachineImpl(vendingMachineHardwareFunctions, initialCapacityMap);
        verify(vendingMachineHardwareFunctions, times(2)).showMessage(anyString());

        //Add money to the vending machine
        vendingMachine.addUserMoney(25);
        vendingMachine.addUserMoney(25);

        //Buy what is in position 1
        vendingMachine.buttonPress(1);

        //Verify contents
        assertThat(vendingMachine.getPositionList()).hasSize(2);
        Item returnedItem1 = vendingMachine.getPositionList().get(0);
        Item returnedItem2 = vendingMachine.getPositionList().get(1);

        assertThat(returnedItem1.getProductName()).isEqualTo(item1.getProductName());
        assertThat(returnedItem1.getPriceInCents()).isEqualTo(item1.getPriceInCents());

        assertThat(returnedItem2.getProductName()).isEqualTo(item2.getProductName());
        assertThat(returnedItem2.getPriceInCents()).isEqualTo(item2.getPriceInCents());

        Map<Item, Integer> contentsMap = vendingMachine.getInventoryMap();
        assertThat(contentsMap).hasSize(2);
        assertThat(contentsMap.get(item1)).isEqualTo(0);
        assertThat(contentsMap.get(item2)).isEqualTo(1);

        //Verify method invocations
        verify(vendingMachineHardwareFunctions, times(1)).dispenseChange(anyInt());
        verify(vendingMachineHardwareFunctions, times(1)).dispenseProduct(anyInt(), anyString());
    }
}
