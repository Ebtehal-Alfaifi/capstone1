package com.example.capstone1.Conntroller;

import com.example.capstone1.ApiResponse.Api;
import com.example.capstone1.Model.ProductModel;
import com.example.capstone1.Service.ProdectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProdectController {
    private final ProdectService prodectService;

    //get All product

    @GetMapping("/get")
    public ResponseEntity getALL(){
        ArrayList<ProductModel>models=prodectService.getAllProdect();
        return ResponseEntity.status(200).body(models);
    }


    //add new product
    @PostMapping("/add")
    public ResponseEntity addNew(@RequestBody @Valid ProductModel productModel, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        prodectService.addNewProdect(productModel);
        return ResponseEntity.status(200).body(new Api(" add success"));
    }


    //update
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id,@RequestBody @Valid ProductModel productModel,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean cheecked= prodectService.updateProdect(id,productModel);
if (cheecked){
    return ResponseEntity.status(200).body(new Api(" update Success"));

}
        return ResponseEntity.status(400).body(new Api(" id not found"));
    }


    //delete
    @DeleteMapping("/delete{id}")
    public ResponseEntity delete(@PathVariable String id){
        boolean checed=prodectService.delete(id);
        if (checed){
            return ResponseEntity.status(200).body(new Api(" delete success"));
        }
        return ResponseEntity.status(400).body(new Api(" id not found"));

    }


    @PostMapping("/buy/{userId}/{productid}/{merchantID}")
    public ResponseEntity buyProduct(@PathVariable String userId,
                                     @PathVariable String productid,
                                     @PathVariable String merchantID) {
        String result = prodectService.buyProdect(userId,productid, merchantID);

        if (result.equals("Purchase successful")) {
            return ResponseEntity.status(200).body("Purchase successful");
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }

    // 3 of 5 extera endpoint
    @PostMapping("/apply-discount/{productId}/{discountPercentage}")
    public ResponseEntity applyDiscount(@PathVariable String productId,
                                        @PathVariable double discountPercentage) {
        double discountedPrice = prodectService.applyDiscount(productId, discountPercentage);  // استدعاء الميثود من ProductService

        if (discountedPrice != -1) {
            return ResponseEntity.status(200).body(new Api("Discount applied successfully," +
                    " new price: " + discountedPrice));
        }
        return ResponseEntity.status(400).body(new Api("Product not found"));
    }


    //5 of 5  extra endpoint
// احدث السعر
    @PutMapping("/update-price/{pID}/{newPrice}")
    public ResponseEntity updateProductPrice(@PathVariable String pID,
                                             @PathVariable double newPrice) {
        boolean updated = prodectService.updateProductPrice(pID, newPrice);
        if (updated) {
            return ResponseEntity.status(200).body(new Api("Price updated successfully"));
        }
        return ResponseEntity.status(400).body(new Api("Product not found"));
    }

    //************** extra credit*****************

    //  1 of 3 extra credit
    @GetMapping("/top-products")
    public ResponseEntity getTopProducts() {
        ArrayList<ProductModel> topProducts = prodectService.getTopProducts();
        if (topProducts == null) {
            return ResponseEntity.status(400).body(new Api("There is no top selling found"));
        }
        return ResponseEntity.status(200).body(topProducts);
    }


    // 2 of 3 extera credit
    @PostMapping("/rate-product/{productId}/{rating}")
    public ResponseEntity rateProduct(@PathVariable String productId, @PathVariable int rating) {
        boolean success = prodectService.addRating(productId, rating);

        if (success) {
            return ResponseEntity.status(200).body(new Api("Product rated successfully"));
        }
        return ResponseEntity.status(400).body(new Api("Invalid rating or product not found"));
    }





}
