package com.example.capstone1.Service;

import com.example.capstone1.Model.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
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
//1 of 5 extra point
// ميثود لتغيير دور المستخدم
    public String updateUserRole(String userId, String newRole,
                                 String currentUserRole) {
        if (!currentUserRole.equalsIgnoreCase("Admin")) {
            return "You do not have permission to change roles";
        }
        // التحقق من صحة الدور الجديد
        if (newRole.equalsIgnoreCase("Admin")
                || newRole.equalsIgnoreCase("Customer")) {
            UserModel user = getUserById(userId);
            if (user != null) {
                user.setRole(newRole); // تحديث الدور
                return "Role updated successfully";
            }
            return "User not found";
        }
        return "Invalid role";
    }


    //2 of 5 extra end point
    // احدث رصيد بعدما اضيف مبلغ
    public String addBalance(String userId, int amount) {
        UserModel user = getUserById(userId);
        if (user != null) {
            user.setBalance(user.getBalance()+amount);
            return "Balance updated successfully";
        }
        return "User not found";
    }




}








