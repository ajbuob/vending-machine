package com.abuob.vending.machines.impl;

import com.abuob.vending.functions.VendingMachineHardwareFunctions;
import com.abuob.vending.product.Item;

import java.util.*;

abstract class AbstractVendingMachine {

    protected VendingMachineHardwareFunctions vendingMachineHardwareFunctions;
    private Integer currentBalance = 0;

    protected LinkedHashMap<Item, Integer> inventoryMap = new LinkedHashMap<>();

    public AbstractVendingMachine(VendingMachineHardwareFunctions vendingMachineHardwareFunctions) {
        this.vendingMachineHardwareFunctions = vendingMachineHardwareFunctions;
    }

    protected void buttonPressOnMachine(Integer productPosition) {
        //Decrement 1 as position list starts with index of 0
        Integer position = productPosition - 1;

        List<Item> positionList = getPositionList();

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

    protected void addUserMoneyToVendingMachine(Integer cents) {
        currentBalance = currentBalance + cents;
    }

    protected Boolean addItemWithQuantityToVendingMachine(Item item, Integer quantity) {
        Integer totalQuantity = quantity;
        //Check for any existing items in the inventory
        if (inventoryMap.containsKey(item)) {
            totalQuantity = totalQuantity + inventoryMap.get(item);
        }

        //Update the inventory
        //NOTE:The underlying linked list defines the iteration ordering, so a re-insert doesn't effect the order
        inventoryMap.put(item, totalQuantity);
        vendingMachineHardwareFunctions.showMessage(String.format("Added quantity %s of %s item(s)", quantity, item.getProductName()));
        return Boolean.TRUE;
    }

    public List<Item> getPositionList() {
        //Copy of positions for client
        return new ArrayList<>(inventoryMap.keySet());
    }

    public Map<Item, Integer> getInventoryMap() {
        //Copy of inventory for client
        return new HashMap<>(inventoryMap);
    }
}
