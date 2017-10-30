package com.example.sony.datasalvestamine;

public class Products {

    private int _id;
    private String _productname;
/* create empty constructor so then we aren't constrained to having to make a product name or pass some product name,  every time we decide to create a product object.
set a name later on we can do that with initialize it with empty constructor */

    public Products(){
    }
    /* right click-generate-constructor; saad luua uusi tooteid */
    public Products(String productname) {
        this._productname = productname;
    }
    /* right click-generate-setters; saad anda nendele id või nimed*/
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }

    /* right click-generate-getters; saad nendelt id või nimed*/
    public int get_id() {
        return _id;
    }

    public String get_productname() {
        return _productname;
    }
}
