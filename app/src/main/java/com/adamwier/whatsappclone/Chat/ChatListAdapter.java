package com.adamwier.whatsappclone.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adamwier.whatsappclone.ChatActivity;
import com.adamwier.whatsappclone.R;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<com.adamwier.whatsappclone.Chat.ChatListAdapter.ChatListViewHolder> {

    ArrayList<ChatObject> ChatList;

    public ChatListAdapter(ArrayList<ChatObject> ChatList) {
        this.ChatList = ChatList;
    }

    @NonNull
    @Override
    public com.adamwier.whatsappclone.Chat.ChatListAdapter.ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);

        ChatListViewHolder rcv = new ChatListViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatListViewHolder holder, final int position) {
        holder.mTitle.setText(ChatList.get(position).getChatId());

        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("chatID", ChatList.get(holder.getAdapterPosition()).getChatId());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ChatList.size();
    }

    public class ChatListViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public LinearLayout mLayout;

        public ChatListViewHolder(View view){
            super(view);

            mTitle = view.findViewById(R.id.title);
            mLayout = view.findViewById(R.id.layout);
        }
    }
}

