package com.example.capstone1.Conntroller;

import com.example.capstone1.ApiResponse.Api;
import com.example.capstone1.Model.UserModel;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getALL(){
        ArrayList<UserModel> models=userService.getAll();
        return ResponseEntity.status(200).body(models);
    }


    //add new product
    @PostMapping("/add")
    public ResponseEntity addNew(@RequestBody @Valid UserModel userModel, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addnew(userModel);
        return ResponseEntity.status(200).body(new Api(" add success"));
    }


    //update
    @PutMapping("/update/{userId}")
    public ResponseEntity update(@PathVariable String userId,@RequestBody @Valid UserModel userModel,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean cheecked= userService.update(userId,userModel);
        if (cheecked){
            return ResponseEntity.status(200).body(new Api(" update Success"));

        }
        return ResponseEntity.status(400).body(new Api(" id not found"));
    }


    //delete
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity delete(@PathVariable String userId){
        boolean checed=userService.delete(userId);
        if (checed){
            return ResponseEntity.status(200).body(new Api(" delete success"));
        }
        return ResponseEntity.status(400).body(new Api(" id not found"));

    }


    //*************** extra end point********************

     //2 of 5 extra end point
    @PutMapping("/transfer-balance/{senderId}/{receiverId}/{amount}")
    public ResponseEntity<String> transferBalance(
            @PathVariable String senderId,
            @PathVariable String receiverId,
            @PathVariable double amount) {

        String response = userService.transferBalance(senderId, receiverId, amount);

        if (response.equals("Sender not found")
                || response.equals("Receiver not found")
                || response.equals("Insufficient balance")) {
            return ResponseEntity.status(400).body(response);
        }

        return ResponseEntity.status(200).body(response);}

    //3 of 5 extra end point
    // Add product to wishlist endpoint
    @PostMapping("/{userId}/wishlist/{productId}")
    public ResponseEntity<String> addProductToWishlist(@PathVariable String userId,
                                                       @PathVariable String productId) {
        String response = userService.addProductToWishlist(userId, productId);

        if (response.contains("not found") || response.contains("already")) {
            return ResponseEntity.status(400).body(response);
        } else {
            return ResponseEntity.status(200).body(response);
        }
    }
    //4 of 5
    @PostMapping("/rateProduct")
    public ResponseEntity<String> rateProduct(@RequestParam String userId, @RequestParam String productId, @RequestParam int rating) {
        String responseMessage = userService.rateProduct(userId, productId, rating);

        if (responseMessage.contains("successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } else if (responseMessage.contains("User not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        } else if (responseMessage.contains("Rating")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseMessage);
        }
    }

    
//5 of 5

    @PostMapping("/{userId}/cart/{productId}")
    public ResponseEntity<String> addProductToCart(@PathVariable String userId, @PathVariable String productId) {
        String response = userService.addProductToCart(userId, productId);

        if (response.contains("not found")) {
            return ResponseEntity.status(400).body(response);
        } else if (response.contains("already")) {
            return ResponseEntity.status(400).body(response);
        } else {
            return ResponseEntity.status(200).body(response);
        }
    }




}














