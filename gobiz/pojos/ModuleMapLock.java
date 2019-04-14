package com.example.vaibh.gobiz.pojos;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Paths.get;

public final class ModuleMapLock {
    public static Map<String, Boolean> moduleMap = new HashMap<>();
    public static String userId ;
    private ModuleMapLock(){

    }

    public static void signupModule(){
        moduleMap.put("Mod1", true);
        moduleMap.put("Mod2", false);
        moduleMap.put("Mod3", false);
        moduleMap.put("Mod4", false);
        moduleMap.put("Mod5", false);
        moduleMap.put("Mod6", false);
    }
    public static void getFromDatabase(String uId){
        userId = uId;
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("ModulesUnlocked")){
                    moduleMap = (Map<String, Boolean>) dataSnapshot.child("ModulesUnlocked").getValue();
                }else{
                    ModuleMapLock.signupModule();
                    rootRef.child("ModulesUnlocked").setValue(ModuleMapLock.moduleMap);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void editModule(String moduleId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            moduleMap.replace(moduleId,true);
        }else{
            moduleMap.remove(moduleId);
            moduleMap.put(moduleId, true);
        }
        Log.d("ModuleMap Lock" , moduleMap.get(moduleId).toString());
        Log.d("ModuleMap Lock id" , moduleId);
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    rootRef.child("ModulesUnlocked").setValue(ModuleMapLock.moduleMap);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
