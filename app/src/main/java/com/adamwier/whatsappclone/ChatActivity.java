package com.adamwier.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.adamwier.whatsappclone.Chat.MessageAdapter;
import com.adamwier.whatsappclone.Chat.ChatObject;
import com.adamwier.whatsappclone.Chat.MessageAdapter;
import com.adamwier.whatsappclone.Chat.MessageObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mChat;
    private RecyclerView.Adapter mChatAdapter;
    private RecyclerView.LayoutManager mChatLayourManager;

    ArrayList<MessageObject> messageList;

    String chatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        chatID = getIntent().getExtras().getString("chatID");

        Button mSend = findViewById(R.id.send);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
        initaliseRecyclerView();
    }

    private void sendMessage(){
        EditText mMessage = findViewById(R.id.message);
        if(!mMessage.getText().toString().isEmpty()){
            DatabaseReference newMessageDb = FirebaseDatabase.getInstance().getReference().child("chat").child("chatID").push();

            Map newMessageMap = new HashMap();
            newMessageMap.put("text", mMessage.getText().toString());
            newMessageMap.put("creator", FirebaseAuth.getInstance().getUid());

            newMessageDb.updateChildren(newMessageMap);
        }

        mMessage.setText(null);
    }

    private void initaliseRecyclerView(){
        messageList = new ArrayList<>();
        mChat = findViewById(R.id.messageList);
        mChat.setNestedScrollingEnabled(false);
        mChat.setHasFixedSize(false);
        mChatLayourManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        mChat.setLayoutManager(mChatLayourManager);
        mChatAdapter = new MessageAdapter(messageList);
        mChat.setAdapter(mChatAdapter);
    }
}
