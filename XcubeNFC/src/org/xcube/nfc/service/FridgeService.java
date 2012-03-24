package org.xcube.nfc.service;

import org.xcube.nfc.domain.Basket;
import org.xcube.nfc.domain.Fridge;

/**
 * Simulates persistence, so basket can add itself to the fridge, 
 * then fridge can get all the baskets that were added (on checkout)
 * 
 * @author pete
 */
public interface FridgeService {

    void checkout(Basket basket);

    Fridge getFridge();
}
