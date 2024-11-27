package com.example.capstone1.Service;

import com.example.capstone1.Model.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
        private final ProdectService prodectService;

    ArrayList<UserModel> list=new ArrayList<>();


    public ArrayList<UserModel>getAll(){

        return list;
    }

    public void addnew(UserModel userModel){
        list.add(userModel);

    }

    public boolean update(String id,UserModel userModel){
        for (UserModel model:list){
            if (model.getUserId().equals(id))
            {
                int i=list.indexOf(model);
                list.set(i,userModel);
                return true;
            }
        }
        return true;
    }

    public boolean delete(String id){
        for (UserModel model:list){
            if (model.getUserId().equals(id)){
                int i=list.indexOf(model);
                list.remove(i);
                return true;

            }
        }
        return false;
    }

//methode to check if user id found to use in prodect layer

public UserModel getUserById(String id) {
    for (UserModel merch : list) {
        if (merch.getUserId().equals(id)) {
            return merch;
        }
    }
    return null;
}



    //************** extra endpoint *****************

// 2 of 5 extra point
    public String transferBalance(String senderId, String receiverId, double amount) {
        UserModel sender = getUserById(senderId);
        if (sender == null) {
            return "Sender not found";}

        UserModel receiver = getUserById(receiverId);
        if (receiver == null) {
            return "Receiver not found";
        }

        // لتحقق من الرصيد الكافي للمرسل
        if (sender.getBalance() < amount) {
            return "Insufficient balance";
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        return "Transfer successful! New balance: " + sender.getBalance();
    }



  //3 of 5 extra end point
  public String addProductToWishlist(String userId, String productId) {
      UserModel user = getUserById(userId);
      if (user == null) {
          return "User with ID: " + userId + " not found.";
      }

      ProductModel product = prodectService.getProductById(productId);
      if (product == null) {
          return "Product with ID: " + productId + " not found.";
      }

      if (user.getFavoriteList() == null) {
          user.setFavoriteList(new ArrayList<>());
      }

      if (user.getFavoriteList().contains(productId)) {
          return "Product is already in the wishlist.";
      }

      user.getFavoriteList().add(productId);
      return "Product added to wishlist successfully.";
  }


  // 4 of 5 extra end point

    public String addPurchasedProduct(String userId, String productId) {
        UserModel user = getUserById(userId);
        if (user == null) {
            return "User with ID: " + userId + " not found.";
        }

        ProductModel product = prodectService.getProductById(productId);
        if (product == null) {
            return "Product with ID: " + productId + " not found.";
        }

        user.addPurchasedProduct(productId);
        return "Product purchased successfully.";
    }

    private Map<String, Integer> productRatings = new HashMap<>();  // خريطة لتخزين التقييمات
    public void addProductRating(String productId, int rating) {
        productRatings.put(productId, rating);
    }
    public String rateProduct(String userId, String productId, int rating) {
        UserModel user = getUserById(userId);
        if (user == null) {
            return "User not found.";
        }

        if (rating < 1 || rating > 5) {
            return "Rating must be between 1 and 5.";
        }
        String purchaseResponse = addPurchasedProduct(userId, productId);
        if (!purchaseResponse.equals("Product purchased successfully.")) {
            return "You must purchase the product .";
        }

        addProductRating(productId, rating);
        return "Rating added successfully.";
    }
}
   













