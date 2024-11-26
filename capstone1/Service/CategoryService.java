package com.example.capstone1.Service;

import com.example.capstone1.Model.CategoryModel;
import com.example.capstone1.Model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
  ArrayList<CategoryModel>list=new ArrayList<>();

  public ArrayList<CategoryModel>getAll(){
      return list;
  }

public void addCate(CategoryModel model){
      list.add(model);

}

public boolean update(String id,CategoryModel categoryModel){
      for (CategoryModel model:list){
          if (model.getId().equals(id)){
              int i=list.indexOf(model);
              list.set(i,categoryModel);
              return true;
          }
      }
      return false;
}

public boolean delete(String id){
      for (CategoryModel model:list){
          if (model.getId().equals(id)){
              int i=list.indexOf(model);
              list.remove(i);
              return true;
          }
      }
      return false;
}



}
