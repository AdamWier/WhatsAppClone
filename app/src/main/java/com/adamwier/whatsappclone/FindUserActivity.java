package com.adamwier.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class FindUserActivity extends AppCompatActivity {

    private RecyclerView mUserList;
    private RecyclerView.Adapter mUserListAdapter;
    private RecyclerView.LayoutManager mUserListLayourManager;
    ArrayList<UserObject> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);

        userList = new ArrayList<>();

        initalizeRecycleView();
        getContactList();
    }

    private void getContactList(){
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()){
            String name = phones.getString(phones.getColumnIndex((ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            String phone = phones.getString(phones.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER)));

            UserObject mContacts = new UserObject(name, phone);
            userList.add(mContacts);
            mUserListAdapter.notifyDataSetChanged();;
        }
    }
    private void initalizeRecycleView() {
        mUserList = findViewById(R.id.userList);
        mUserList.setNestedScrollingEnabled(false);
        mUserList.setHasFixedSize(false);
        mUserListLayourManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        mUserList.setLayoutManager(mUserListLayourManager);
        mUserListAdapter = new UserListAdapter(userList);
        mUserList.setAdapter(mUserListAdapter);
    }

}
