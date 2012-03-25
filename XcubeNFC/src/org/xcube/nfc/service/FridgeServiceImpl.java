package org.xcube.nfc.service;

import org.xcube.nfc.domain.Basket;
import org.xcube.nfc.domain.Fridge;
import org.xcube.nfc.domain.ItemWithQuantity;

public class FridgeServiceImpl implements FridgeService {

    private static Fridge fridge = new Fridge();

    @Override
    public void checkout(Basket basket) {
        fridge.addBasket(basket);
    }

    @Override
    public Fridge getFridge() {
        return fridge;
    }

	@Override
	public ItemWithQuantity getItem(String upc) {
		return fridge.findByUpc(upc);
	}
}
