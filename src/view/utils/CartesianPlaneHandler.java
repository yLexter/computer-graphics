package view.utils;

import geomtry.planeCartesians.BaseCartesianPlane;

import java.util.LinkedHashMap;
import java.util.Map;

public class CartesianPlaneHandler {

    private BaseCartesianPlane cartesianPlane;

    private final Map<String, BaseCartesianPlane> cartesiansPlane;

    public CartesianPlaneHandler() {
        this.cartesiansPlane = new LinkedHashMap<>();
    }

    public BaseCartesianPlane getCurrentCartesianPlane() {
        return cartesianPlane;
    }

    public void setCartesianPlane(BaseCartesianPlane cartesianPlane) {
        this.cartesianPlane = cartesianPlane;
    }

    public void addCartesianPlane(String category, BaseCartesianPlane cartesianPlane) {
        cartesiansPlane.put(category, cartesianPlane);
    }

    public BaseCartesianPlane getCartesianPlaneByCategory (String category) {
        return cartesiansPlane.get(category);
    }


}
