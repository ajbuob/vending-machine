package com.abuob.vending.machines;

import com.abuob.vending.product.Item;

public interface ReloadableVendingMachine extends VendingMachine {

    /**
     * Method which allows of additional product to be added
     * to this VendingMachine. Client must provide a valid username/password to add Items
     *
     * @param item     The product to be added.
     * @param quantity The amount of product to be added.
     * @param username The supplied username
     * @param password The supplied password
     * @return The success of the add operation
     */
    Boolean addItemWithQuantity(Item item, Integer quantity, String username, String password);
}
