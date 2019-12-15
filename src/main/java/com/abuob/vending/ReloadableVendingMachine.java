package com.abuob.vending;

import com.abuob.vending.product.Item;

public interface ReloadableVendingMachine extends VendingMachine {

    /**
     * Method which allows of additional product to be added
     * to this VendingMachine
     *
     * @param item     The product to be added.
     * @param quantity The amount of product to be added.
     * @return the success of the add operation
     */
    Boolean addItemWithQuantity(Item item, Integer quantity);
}
