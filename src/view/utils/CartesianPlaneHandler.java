package view.utils;

import geometry.planeCartesians.bases.BaseCartesianPlane;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartesianPlaneHandler {

    private final Map<String, BaseCartesianPlane> cartesiansPlane;

    private String currentCartesianPlaneString;

    public CartesianPlaneHandler() {
        this.cartesiansPlane = new LinkedHashMap<>();
    }

    public BaseCartesianPlane getCurrentCartesianPlane() {
        return cartesiansPlane.get(currentCartesianPlaneString);
    }

    public String getCurrentCategory () {
        return currentCartesianPlaneString;
    }

    public void setCurrentCategory(String currentCartesianPlaneString) {
        this.currentCartesianPlaneString = currentCartesianPlaneString;
    }

    public List<String> getCategories() {
        return new ArrayList<>(cartesiansPlane.keySet().stream().toList());
    }

    public void resetCurrentCartesianPlane() {
        BaseCartesianPlane currentCartesianPlane = getCurrentCartesianPlane();
        this.cartesiansPlane.put(currentCartesianPlaneString, currentCartesianPlane.reset());
    }

    public void addCartesianPlane(String category, BaseCartesianPlane cartesianPlane) {
        cartesiansPlane.put(category, cartesianPlane);
    }

    public BaseCartesianPlane getCartesianPlaneByCategory (String category) {
        return cartesiansPlane.get(category);
    }


}
