package com.example.capstone1.Service;

import com.example.capstone1.Model.ProductModel;
import com.example.capstone1.Model.StockModel;
import com.example.capstone1.Model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProdectService {
    private final UserService userService;   // بتخليني اقدر اتعامل مع يوزر
    private final StockService stockService;
    ArrayList<ProductModel>list=new ArrayList<>();

    //Get All prodect

    public ArrayList<ProductModel>getAllProdect(){
        return list;
    }

    //Add new
    public void addNewProdect(ProductModel productModel){
        list.add(productModel);
    }

    //update
    public boolean updateProdect(String id,ProductModel productModel){
        for (ProductModel model:list){
            if (model.getPID().equals(id)){
                int i=list.indexOf(model);
                list.set(i,productModel);
                return true;
            }
        }
        return false;
    }


    public boolean delete(String id){
        for (ProductModel productModel:list){
            if (productModel.getPID().equals(id)){
                int i=list.indexOf(productModel);
                list.remove(i);
                return true;
            }
        }
        return false;

    }



    //get prodect id to  check if founded or no

    public ProductModel getProductById(String id) {
        for (ProductModel product : list) {
            if (product.getPID().equals(id)) {
                return product;
            }
        }
        return null;
    }


public String buyProdect(String userId,String productid,String merchantID ) {
    UserModel user = userService.getUserById(userId);
    if (user == null) {
        return " User not found";
    }
    ProductModel product = getProductById(productid);
    if (product == null) {
        return " prodect not found";
    }
    StockModel stockModel = stockService.getStockByProductAndMerchant(merchantID,productid);
    if (stockModel == null ||stockModel.getStock()<= 0) {
        return "bad request";
    }
    if (user.getBalance() < product.getPrice()) {
        return "you dont have enough money";
    }
    user.setBalance(user.getBalance() - product.getPrice());
    stockModel.setStock(stockModel.getStock() - 1);
    return "Purchase successful";
}


//************** extra endpoint *****************

    //3 of 5 extra endpoint
    // ميثود لحساب الخصم على المنتج
    public double applyDiscount( String pID ,double discountPercentage){
        ProductModel product = getProductById(pID);
        if (product != null) {
            double discountedPrice = product.getPrice()
                    - (product.getPrice() * discountPercentage / 100);
            product.setPrice(discountedPrice);
            return discountedPrice;
        }
        return -1;
    }


    // 5 of 5 extra endpoint
    //احدث السعر
    public boolean updateProductPrice(String productId, double newPrice) {
        for (ProductModel product : list) {
            if (product.getPID().equals(productId)) {
                product.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }
    //************** extra credit*****************
    //  1 of 3 extra credit
    // المنتجات الاكثر مبيعا مبيعا
    public ArrayList<ProductModel> getTopProducts() {
        ArrayList<ProductModel> top = new ArrayList<>();

        for (ProductModel product : list) {
            if (product.getSaleCount() > 50) {
                top.add(product);
            }}
        if (top.isEmpty()){
           return null;
        }
       return top;
    }

    //2 of 3 extra credit
    // اضيف تقييم للمنتج

    public boolean addRating(String productId, int rating) {
        for (ProductModel product : list) {
            if (product.getPID().equals(productId)) {

                if (rating >= 1 && rating <= 5) {
                    product.setTotalRatingSum(product.getTotalRatingSum() + rating);
                    product.setTotalRatingCount(product.getTotalRatingCount() + 1);

                    return true;
                }
            }
        }
        return false;
    }






}










