package com.example.capstone1.Service;

import com.example.capstone1.Model.StockModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StockService {
    ArrayList<StockModel> list=new ArrayList<>();

    public ArrayList<StockModel>getAll(){
        return list;}


    //ADD
    public void addCate(StockModel model){
        list.add(model);}


    //UPDATE
    public boolean update(String id,StockModel stockModel){
        for (StockModel model:list){
            if (model.getId().equals(id)){
                int i=list.indexOf(model);
                list.set(i,stockModel);
                return true;}}
        return false;}


//DELETE
    public boolean delete(String id){
        for (StockModel model:list){
            if (model.getId().equals(id)){
                int i=list.indexOf(model);
                list.remove(i);
                return true;}}
        return false;
    }

// اضيف في المخزون زيادة عناصر في حال توفر شرطين الي هي pId ,MId,
    //بعد التحقق اذا الشرطين صح اضضيف العنصر يعني بيكون معي 3 متغيرات في البراميتر

    //add aditional to the stock

    public boolean addStock(String productId, String merchantId, int additionalStock) {
        for (StockModel model : list) {
            if (model.getProductid().equals(productId) && model.getMerchantID().equals(merchantId)) {
                model.setStock(model.getStock() + additionalStock);
                return true;
            }
        }
        return false;
    }


    // getStockByProductAndMerchant to use in prodect layer
public StockModel getStockByProductAndMerchant (String merchantID,String productid ){
        for (StockModel stockModel:list){
            if (stockModel.getMerchantID().equals(merchantID)&&stockModel.getProductid().equals(productid)){
                return stockModel;
            }
        }
    return null;
    }





   




    
    //************  extra credit ***************
    //3 of 3 extra credit
    // // يساعد التاجر انه يعرف المخزون الي قربت تنفذ كميته
    // public ArrayList<StockModel> getLowStockProducts(int number) {
    //     ArrayList<StockModel> lowStockList = new ArrayList<>();
    //     for (StockModel stock : list) {
    //         if (stock.getStock() < number) {
    //             lowStockList.add(stock);
    //         }
    //     }
    //    if (lowStockList.isEmpty()){
    //        return null;
    //    }
    //    else {
    //        return lowStockList;
    //    }
    // }

}




