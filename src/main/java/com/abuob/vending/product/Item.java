package com.abuob.vending.product;

import java.util.Objects;

public class Item {

    private String productName;

    private Integer priceInCents;

    public Item(String productName, Integer priceInCents) {
        this.productName = productName;
        this.priceInCents = priceInCents;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return productName.equals(item.productName) &&
                priceInCents.equals(item.priceInCents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, priceInCents);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("productName='").append(productName).append('\'');
        sb.append(", priceInCents=").append(priceInCents);
        sb.append('}');
        return sb.toString();
    }
}
