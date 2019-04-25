package com.example.vaibh.gobiz.pojos;

import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ModuleScore {

    public static String userId;
    public static Map<String, Object> map = new HashMap<>();

    public static void setMap(){
        map.put("Mod1", 0.0f);
        map.put("Mod2", 0.0f);
        map.put("Mod3", 0.0f);
        map.put("Mod4", 0.0f);
        map.put("Mod5", 0.0f);
        map.put("Mod6", 0.0f);
    }

    public static void getFromDatabase(String uId){
        userId = uId;
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("ModuleScore")){
                    map = (Map<String, Object>) dataSnapshot.child("ModuleScore").getValue();
                }else{
                    ModuleScore.setMap();
                    rootRef.child("ModuleScore").setValue(ModuleScore.map);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void updateScore(String moduleId, float score){

        Log.d("SCORE_UPDATED", Float.toString(score));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            map.replace(moduleId,score);
        }else{
            map.remove(moduleId);
            map.put(moduleId, score);
        }
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                rootRef.child("ModuleScore").setValue(ModuleScore.map);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
