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
    @DeleteMapping("/delete/{id}")
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

          @PutMapping("/apply-discount")
    public ResponseEntity<String> applyDiscount(@RequestParam String merchantID,
                                                @RequestParam String productID,
                                                @RequestParam double discountPercentage) {

        double discountedPrice = prodectService.applyDiscount(merchantID, productID, discountPercentage);

        if (discountedPrice == -1) {
            return ResponseEntity.status(400).body("Discount cannot be applied: Either the product or merchant does not exist.");
        }

        return ResponseEntity.status(200).body("Discount applied successfully! New price: " + discountedPrice);
    }
    }

   










    
   

    //************** extra credit*****************

    // //  1 of 3 extra credit
    // @GetMapping("/top-products")
    // public ResponseEntity getTopProducts() {
    //     ArrayList<ProductModel> topProducts = prodectService.getTopProducts();
    //     if (topProducts == null) {
    //         return ResponseEntity.status(400).body(new Api("There is no top selling found"));
    //     }
    //     return ResponseEntity.status(200).body(topProducts);
    // }


    // // 2 of 3 extera credit
    // @PostMapping("/rate-product/{productId}/{rating}")
    // public ResponseEntity rateProduct(@PathVariable String productId, @PathVariable int rating) {
    //     boolean success = prodectService.addRating(productId, rating);

    //     if (success) {
    //         return ResponseEntity.status(200).body(new Api("Product rated successfully"));
    //     }
    //     return ResponseEntity.status(400).body(new Api("Invalid rating or product not found"));
    // }





}
