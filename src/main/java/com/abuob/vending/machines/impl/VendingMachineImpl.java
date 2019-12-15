package com.abuob.vending.machines.impl;

import com.abuob.vending.functions.VendingMachineHardwareFunctions;
import com.abuob.vending.product.Item;
import com.abuob.vending.machines.VendingMachine;

import java.util.Map;

public class VendingMachineImpl extends AbstractVendingMachine implements VendingMachine {

    public VendingMachineImpl(VendingMachineHardwareFunctions vendingMachineHardwareFunctions, Map<Item, Integer> initialContentsMap) {
        super(vendingMachineHardwareFunctions);
        initialContentsMap.forEach((item, quantity) -> this.addItemWithQuantityToVendingMachine(item, quantity));
    }

    @Override
    public void buttonPress(Integer productPosition) {
        this.buttonPressOnMachine(productPosition);
    }

    @Override
    public void addUserMoney(Integer cents) {
        this.addUserMoneyToVendingMachine(cents);
    }
}