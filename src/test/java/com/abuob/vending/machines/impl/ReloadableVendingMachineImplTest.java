package com.abuob.vending.machines.impl;

import com.abuob.vending.auth.AdminAuthenticator;
import com.abuob.vending.functions.VendingMachineHardwareFunctions;
import com.abuob.vending.machines.ReloadableVendingMachine;
import com.abuob.vending.product.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ReloadableVendingMachineImplTest {
    private final String USERNAME = "username";
    private final String PASSWORD = "password123";

    private ReloadableVendingMachine reloadableVendingMachine;
    private VendingMachineHardwareFunctions vendingMachineHardwareFunctions = mock(VendingMachineHardwareFunctions.class);
    private AdminAuthenticator adminAuthenticator = mock(AdminAuthenticator.class);

    private Item item1 = new Item("product1", 35);
    private Item item2 = new Item("product2", 20);
    private Item item3 = new Item("product1", 35);
    private Item item4 = new Item("product4", 90);

    @Before
    public void setup() {
        when(adminAuthenticator.isValid(anyString(), anyString())).thenReturn(Boolean.TRUE);

        LinkedHashMap<Item, Integer> initialCapacityMap = new LinkedHashMap<>();
        reloadableVendingMachine = new ReloadableVendingMachineImpl(vendingMachineHardwareFunctions,
                initialCapacityMap,
                adminAuthenticator);
    }

    @Test
    public void test_addItemWithQuantity_existingProduct() {
        Boolean isItemAddedResult;

        //Initial add
        isItemAddedResult = reloadableVendingMachine.addItemWithQuantity(item1, 1, USERNAME, PASSWORD);
        assertThat(isItemAddedResult).isTrue();
        isItemAddedResult = reloadableVendingMachine.addItemWithQuantity(item2, 1, USERNAME, PASSWORD);
        assertThat(isItemAddedResult).isTrue();

        verify(vendingMachineHardwareFunctions, times(2)).showMessage(anyString());
        assertThat(reloadableVendingMachine.getInventoryMap()).hasSize(2);

        //Add exiting product
        isItemAddedResult = reloadableVendingMachine.addItemWithQuantity(item3, 1, USERNAME, PASSWORD);
        assertThat(isItemAddedResult).isTrue();

        assertThat(reloadableVendingMachine.getInventoryMap()).hasSize(2);
        verify(vendingMachineHardwareFunctions, times(3)).showMessage(anyString());


        //Verify contents (increased quantity for item1/item3)
        assertThat(reloadableVendingMachine.getPositionList()).hasSize(2);
        Item returnedItem1 = reloadableVendingMachine.getPositionList().get(0);
        Item returnedItem2 = reloadableVendingMachine.getPositionList().get(1);

        assertThat(returnedItem1.getProductName()).isEqualTo(item1.getProductName());
        assertThat(returnedItem1.getPriceInCents()).isEqualTo(item1.getPriceInCents());

        assertThat(returnedItem2.getProductName()).isEqualTo(item2.getProductName());
        assertThat(returnedItem2.getPriceInCents()).isEqualTo(item2.getPriceInCents());

        Map<Item, Integer> contentsMap = reloadableVendingMachine.getInventoryMap();
        assertThat(contentsMap).hasSize(2);
        assertThat(contentsMap.get(item1)).isEqualTo(2);
        assertThat(contentsMap.get(item2)).isEqualTo(1);

        //Verify method invocations
        verify(vendingMachineHardwareFunctions, never()).dispenseChange(anyInt());
        verify(vendingMachineHardwareFunctions, never()).dispenseProduct(anyInt(), anyString());
        verify(vendingMachineHardwareFunctions, times(3)).showMessage(anyString());
    }

    @Test
    public void test_addItemWithQuantity_newProduct() {
        Boolean isItemAddedResult;

        //Initial add
        isItemAddedResult = reloadableVendingMachine.addItemWithQuantity(item1, 1, USERNAME, PASSWORD);
        assertThat(isItemAddedResult).isTrue();
        isItemAddedResult = reloadableVendingMachine.addItemWithQuantity(item2, 1, USERNAME, PASSWORD);
        assertThat(isItemAddedResult).isTrue();

        verify(vendingMachineHardwareFunctions, times(2)).showMessage(anyString());
        assertThat(reloadableVendingMachine.getInventoryMap()).hasSize(2);

        //Add exiting product
        isItemAddedResult = reloadableVendingMachine.addItemWithQuantity(item4, 1, USERNAME, PASSWORD);
        assertThat(isItemAddedResult).isTrue();

        assertThat(reloadableVendingMachine.getInventoryMap()).hasSize(3);
        verify(vendingMachineHardwareFunctions, times(3)).showMessage(anyString());


        //Verify contents (increased quantity for item4)
        assertThat(reloadableVendingMachine.getPositionList()).hasSize(3);
        Item returnedItem1 = reloadableVendingMachine.getPositionList().get(0);
        Item returnedItem2 = reloadableVendingMachine.getPositionList().get(1);
        Item returnedItem3 = reloadableVendingMachine.getPositionList().get(2);

        assertThat(returnedItem1.getProductName()).isEqualTo(item1.getProductName());
        assertThat(returnedItem1.getPriceInCents()).isEqualTo(item1.getPriceInCents());

        assertThat(returnedItem2.getProductName()).isEqualTo(item2.getProductName());
        assertThat(returnedItem2.getPriceInCents()).isEqualTo(item2.getPriceInCents());

        assertThat(returnedItem3.getProductName()).isEqualTo(item4.getProductName());
        assertThat(returnedItem3.getPriceInCents()).isEqualTo(item4.getPriceInCents());

        Map<Item, Integer> contentsMap = reloadableVendingMachine.getInventoryMap();
        assertThat(contentsMap).hasSize(3);
        assertThat(contentsMap.get(item1)).isEqualTo(1);
        assertThat(contentsMap.get(item2)).isEqualTo(1);
        assertThat(contentsMap.get(item4)).isEqualTo(1);

        //Verify method invocations
        verify(vendingMachineHardwareFunctions, never()).dispenseChange(anyInt());
        verify(vendingMachineHardwareFunctions, never()).dispenseProduct(anyInt(), anyString());
        verify(vendingMachineHardwareFunctions, times(3)).showMessage(anyString());
    }
}
