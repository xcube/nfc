package org.xcube.nfc.service;

import java.util.List;

import org.xcube.nfc.domain.Item;
import org.xcube.nfc.domain.ItemWithQuantity;


public interface BasketService {

	void addItemToBasket(Item item);

	void removeItemFromBasket(Item item);

	List<ItemWithQuantity> getItems();
	
}
