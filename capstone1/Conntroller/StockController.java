package com.example.capstone1.Conntroller;

import com.example.capstone1.ApiResponse.Api;
import com.example.capstone1.Model.StockModel;
import com.example.capstone1.Service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {



    private final StockService stockService;

    @GetMapping("/get")
    public ResponseEntity getALL(){
        ArrayList<StockModel> models=stockService.getAll();
        return ResponseEntity.status(200).body(models);
    }


  //  add new product
    @PostMapping("/add")
    public ResponseEntity addNew(@RequestBody @Valid StockModel stockModel, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        stockService.addCate(stockModel);
        return ResponseEntity.status(200).body(new Api(" add success"));
    }


    //update
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable String id,@RequestBody @Valid StockModel stockModel,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean cheecked= stockService.update(id,stockModel);
        if (cheecked){
            return ResponseEntity.status(200).body(new Api(" update Success"));

        }
        return ResponseEntity.status(400).body(new Api(" id not found"));
    }


   // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        boolean checed=stockService.delete(id);
        if (checed){
            return ResponseEntity.status(200).body(new Api(" delete success"));
        }
        return ResponseEntity.status(400).body(new Api(" id not found"));

    }


    @PostMapping("/add-stock/{productId}/{merchantId}/{additionalStock}")
    public ResponseEntity addStock(@PathVariable String productId,
                                   @PathVariable String merchantId,
                                   @PathVariable int additionalStock) {
        boolean added = stockService.addStock(productId, merchantId, additionalStock);
        if (added) {
            return ResponseEntity.status(200).body(new Api("Stock updated successfully"));
        }
        return ResponseEntity.status(400).body(new Api("Stock or merchant not found"));
    }

}

    










    
//*************** extra credit********************
//     //3 of 3 extra credit
// @GetMapping("/low-stock")
// public ResponseEntity getLowStockProducts(@RequestParam int threshold) {
//     ArrayList<StockModel> lowStock= stockService.getLowStockProducts(threshold);
//     if (lowStock==null) {
//         return ResponseEntity.status(400).body(new Api("No  products found"));
//     }
//     return ResponseEntity.status(200).body(lowStock);
// }

// }

