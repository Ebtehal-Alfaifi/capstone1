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

    //1 of 5 extra end point
    //update role
    @PutMapping("/update-role/{userId}/{newRole}/{currentUserRole}")
    public ResponseEntity updateRole(@PathVariable String userId, @PathVariable String newRole
            ,@PathVariable String currentUserRole) {
        String result = userService.updateUserRole(userId, newRole,currentUserRole);
        if ("Role updated successfully".equals(result)) {
            return ResponseEntity.status(200).body(new Api("Role updated successfully"));
        } else {
            return ResponseEntity.status(400).body(new Api(result));
        }
    }


    //2 of 5 extra end point
    //update balance
    @PutMapping("/add-balance/{userId}/{amount}")
    public ResponseEntity addBalance(@PathVariable String userId,
                                     @PathVariable int amount) {
        String result = userService.addBalance(userId, amount);

        if ("Balance updated successfully".equals(result)) {

            return ResponseEntity.status(200).body(new Api(result));
        } else {

            return ResponseEntity.status(400).body(new Api(result));
        }
    }






}














