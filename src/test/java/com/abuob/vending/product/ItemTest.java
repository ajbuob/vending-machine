package com.abuob.vending.product;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemTest {

    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;

    @Before
    public void setup() {
        item1 = new Item("item1", 45);
        item2 = new Item("item2", 70);
        item3 = new Item("item3", 45);
        item4 = new Item("item1", 45);
    }

    @Test
    public void test_equals_null() {
        assertThat(item1.equals(null)).isFalse();
    }

    @Test
    public void test_equals_true() {
        assertThat(item1.equals(item1)).isTrue();
        assertThat(item2.equals(item2)).isTrue();
        assertThat(item3.equals(item3)).isTrue();
        assertThat(item4.equals(item4)).isTrue();

        assertThat(item1.equals(item4)).isTrue();
    }

    @Test
    public void test_equals_false() {
        assertThat(item1.equals(item2)).isFalse();
        assertThat(item2.equals(item3)).isFalse();
        assertThat(item3.equals(item4)).isFalse();

        assertThat(item2.equals(item1)).isFalse();
        assertThat(item3.equals(item2)).isFalse();
        assertThat(item4.equals(item3)).isFalse();
    }

    @Test
    public void test_hashCode_true() {
        assertThat(item1.hashCode()).isEqualTo(item1.hashCode());
        assertThat(item2.hashCode()).isEqualTo(item2.hashCode());
        assertThat(item3.hashCode()).isEqualTo(item3.hashCode());
        assertThat(item4.hashCode()).isEqualTo(item4.hashCode());

        assertThat(item1.hashCode()).isEqualTo(item4.hashCode());
        assertThat(item4.hashCode()).isEqualTo(item1.hashCode());
    }

    @Test
    public void test_hashCode_false() {
        assertThat(item1.hashCode()).isNotEqualTo(item2.hashCode());
        assertThat(item2.hashCode()).isNotEqualTo(item3.hashCode());
        assertThat(item3.hashCode()).isNotEqualTo(item4.hashCode());

        assertThat(item2.hashCode()).isNotEqualTo(item1.hashCode());
        assertThat(item3.hashCode()).isNotEqualTo(item2.hashCode());
        assertThat(item4.hashCode()).isNotEqualTo(item3.hashCode());
    }
}
