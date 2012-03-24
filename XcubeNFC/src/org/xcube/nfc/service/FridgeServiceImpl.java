package org.xcube.nfc.service;

import org.xcube.nfc.domain.Basket;
import org.xcube.nfc.domain.Fridge;

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

}
