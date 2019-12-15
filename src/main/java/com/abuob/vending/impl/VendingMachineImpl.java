package com.abuob.vending.impl;

import com.abuob.vending.VendingMachine;
import com.abuob.vending.VendingMachineHardwareFunctions;
import com.abuob.vending.product.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {

    protected VendingMachineHardwareFunctions vendingMachineHardwareFunctions;
    private Integer currentBalance = 0;

    protected List<Item> positionList = new ArrayList<>();
    protected Map<Item, Integer> inventoryMap = new HashMap<>();

    public VendingMachineImpl(VendingMachineHardwareFunctions vendingMachineHardwareFunctions) {
        this.vendingMachineHardwareFunctions = vendingMachineHardwareFunctions;
    }

    @Override
    public void buttonPress(Integer productPosition) {
        //Decrement 1 as position list starts with index of 0
        Integer position = productPosition - 1;

        Item selectedItem = positionList.get(position);
        Integer itemPrice = selectedItem.getPriceInCents();
        String itemName = selectedItem.getProductName();

        Integer numItems = inventoryMap.get(selectedItem);

        //Check inventory
        if (numItems == 0) {
            vendingMachineHardwareFunctions.showMessage(String.format("%s is out of stock, Please make another selection", itemName));
            return;
        }

        //Check item cost
        if (currentBalance < itemPrice) {
            vendingMachineHardwareFunctions.showMessage(String.format("%s costs %s cents", itemName, itemPrice));
            return;
        }

        //Decrement inventory and dispense product
        inventoryMap.put(selectedItem, numItems - 1);
        vendingMachineHardwareFunctions.dispenseProduct(productPosition, itemName);

        //If a balance remains, dispense change to the user
        Integer change = currentBalance - itemPrice;
        if (change > 0) {
            vendingMachineHardwareFunctions.dispenseChange(change);
        }
    }

    @Override
    public void addUserMoney(Integer cents) {
        currentBalance = currentBalance + cents;
    }

    public List<Item> getPositionList() {
        //Copy of positions for client
        return new ArrayList<>(positionList);
    }

    public Map<Item, Integer> getInventoryMap() {
        //Copy of inventory for client
        return new HashMap<>(inventoryMap);
    }
}