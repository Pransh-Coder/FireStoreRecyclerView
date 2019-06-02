package com.example.firestorerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Firestore";
    private RecyclerView mMainList;
   // private FirebaseFirestore mFirestore;

    private UsersListAdapter usersListAdapter;

    private List<Users> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainList=findViewById(R.id.main_list);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(usersListAdapter);

        FirebaseApp.initializeApp(this);
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        userList=new ArrayList<>();

        usersListAdapter=new UsersListAdapter(userList);

        //now to get a list of items
        mFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {         // this will help to retreive data from that collection
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e!=null)
                {
                    Log.d(TAG, "Error:" + e.getMessage());
                }
                /*for(DocumentSnapshot doc : documentSnapshots)  {            // it will get each document and store in doc variable
                    String user_name=doc.getString("Name");
                    Log.d(TAG, "Name:" + user_name);*/
                for (DocumentChange doc : documentSnapshots.getDocumentChanges())       //it look for the changes that are made in our document list
                {                                                                       // those chandes are if data is added , moved or deleted
                    if(doc.getType()==DocumentChange.Type.ADDED)
                    {
                        //String name=doc.getDocument().getString("Name");

                        //Log.d(TAG, "Name:" + name);

                        Users users=doc.getDocument().toObject(Users.class);
                        userList.add(users);

                        usersListAdapter.notifyDataSetChanged();
                    }


                }
            }
        });
    }

    private class TAG {
    }
}
