package com.cntysoft.ganji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cntysoft.ganji.R;
import com.cntysoft.ganji.model.ChatMessage;

import java.util.List;

/**
 * @Author：Tiger on 2015/4/18 16:59
 * @Email: ielxhtiger@163.com
 */
public class ChatMessageAdapter extends BaseAdapter {

    private Context context;
    private List<ChatMessage> chatMessages;
    public ChatMessageAdapter(Context context, List<ChatMessage> chatMessages){
        this.context = context;
        this.chatMessages = chatMessages;
    }
    @Override
    public int getCount() {
        return chatMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return chatMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ChatMessage chatMessage = chatMessages.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_chat_list,null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.img_contact_avator);
            viewHolder.title = (TextView) convertView.findViewById(R.id.message_cat);
            viewHolder.time = (TextView) convertView.findViewById(R.id.answer_time);
            viewHolder.content = (TextView) convertView.findViewById(R.id.talk_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.icon.setImageBitmap(chatMessage.getIcon());
        viewHolder.title.setText(chatMessage.getTitle());
        viewHolder.time.setText(chatMessage.getTime());
        viewHolder.content.setText(chatMessage.getContent());
        return convertView;
    }


    static class ViewHolder{
        ImageView icon;
        TextView title;
        TextView time;
        TextView content;
    }
}
