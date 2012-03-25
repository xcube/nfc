package org.xcube.nfc.service;

import java.util.List;

import org.xcube.nfc.domain.Basket;
import org.xcube.nfc.domain.Item;
import org.xcube.nfc.domain.ItemWithQuantity;

public class BasketServiceImpl implements BasketService {
	
	private static Basket basket = new Basket();

	@Override
	public void addItemToBasket(Item item) {
		basket.addItem(item);
	}

	@Override
	public void removeItemFromBasket(Item item) {
		basket.removeItem(item);
	}
	
	@Override
	public List<ItemWithQuantity> getItems() {
		return basket.getItems();
	}
}
