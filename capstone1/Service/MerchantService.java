package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
    ArrayList<MerchantModel>list=new ArrayList<>();


    public ArrayList<MerchantModel>getAll(){
        return list;
    }

    public void addnew(MerchantModel merchantModel){
        list.add(merchantModel);

    }

    public boolean update(String id,MerchantModel merchantModel){
        for (MerchantModel model:list){
            if (model.getMerchantID().equals(id))
            {
                int i=list.indexOf(model);
                list.set(i,merchantModel);
                return true;
            }
        }
        return true;
    }

    public boolean delete(String id){
        for (MerchantModel model:list){
            if (model.getMerchantID().equals(id)){
                int i=list.indexOf(model);
                list.remove(i);
                return true;

            }
        }
       return false;
    }







}
