package com.abuob.vending.impl;

import com.abuob.vending.VendingMachine;
import com.abuob.vending.VendingMachineHardwareFunctions;
import com.abuob.vending.product.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {

    private VendingMachineHardwareFunctions vendingMachineHardwareFunctions;
    private Integer currentBalance = 0;

    private List<Item> positionList = new ArrayList<>();
    private Map<Item, Integer> inventoryMap = new HashMap<>();

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

    @Override
    public void addItemWithQuantity(Item item, Integer quantity) {
        Integer totalQuantity = quantity;
        //Check for any existing items in the inventory
        if (inventoryMap.containsKey(item)) {
            totalQuantity = totalQuantity + inventoryMap.get(item);
        } else {
            //Add the new item to the available options
            positionList.add(item);
        }
        //Update the inventory
        inventoryMap.put(item, totalQuantity);
        vendingMachineHardwareFunctions.showMessage(String.format("Added quantity %s of %s item(s)", totalQuantity, item.getProductName()));
    }

    public List<Item> getPositionList() {
        return new ArrayList<>(positionList);
    }

    public Map<Item, Integer> getInventoryMap() {
        return new HashMap<>(inventoryMap);
    }
}
