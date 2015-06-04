package com.cntysoft.ganji.fragment;

import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cntysoft.ganji.R;
import com.cntysoft.ganji.adapter.ChatMessageAdapter;
import com.cntysoft.ganji.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：Tiger on 2015/4/18 14:52
 * @Email: ielxhtiger@163.com
 */
public class ChatListFragment extends Fragment implements View.OnClickListener{

    private View view;
    private ListView listView;
    private ImageView imageView;
    private RelativeLayout noDataView;
    
    private View searchBox;
    private LinearLayout searchRoot;
    private EditText editText;
    private List<ChatMessage> chatMessages;
    
    private ChatMessageAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_chatlist,null);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent != null){
            parent.removeView(view);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
    private void initView(){
        imageView = (ImageView) view.findViewById(R.id.right_image_view);
        imageView.setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.all_talks);
        noDataView = (RelativeLayout) view.findViewById(R.id.item_webim_no_chat_history);
        searchBox = getActivity().getLayoutInflater().inflate(R.layout.item_searchbox,null);
        searchRoot = (LinearLayout) searchBox.findViewById(R.id.input_search_layoutroot);
        searchRoot.setOnClickListener(this);
        editText = (EditText) searchBox.findViewById(R.id.edittext_search);
        editText.setHint("搜索群组和好友");
        editText.setFocusable(false);
        editText.setOnClickListener(this); 
        listView.addHeaderView(searchBox);
        if(chatMessages == null){
            noDataView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else{
            noDataView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            adapter = new ChatMessageAdapter(getActivity(),chatMessages);
            listView.setAdapter(adapter);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    if(listView.getHeaderViewsCount() > position){
                        return false;
                    }
                    int who = position - listView.getHeaderViewsCount();
                    showOneDialog("操作","删除",who);
                    return true;
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.right_image_view){
            showDialog("编辑",new String[] { "创建群组", "忽略未读" });
        }else if(id == R.id.edittext_search || id == R.id.input_search_layoutroot){
            Toast.makeText(getActivity(),"跳到搜索朋友",Toast.LENGTH_SHORT).show();
        }
    }

    private Dialog dialog;
   
    private void showDialog(String title,String[] item){
        TextView titleTextView;
        TextView leftBtn;
        TextView firstTextView;
        TextView secondTextView;
        View contentView = View.inflate(getActivity(),R.layout.dialog_pub_two_choice,null);
        titleTextView = (TextView) contentView.findViewById(R.id.title_choices);
        leftBtn = (TextView) contentView.findViewById(R.id.left_text_btn);
        firstTextView = (TextView) contentView.findViewById(R.id.choice_one_text);
        secondTextView = (TextView) contentView.findViewById(R.id.choice_two_text);
        titleTextView.setText(title);
        firstTextView.setText(item[0]);
        secondTextView.setText(item[1]);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        firstTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"创建群组",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        secondTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"忽略阅读",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        dialog = new Dialog(getActivity(),R.style.dialog);
        dialog.setContentView(contentView,new ViewGroup.LayoutParams(width - 60, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.show();
    }
    
    private void initData(){
        chatMessages = new ArrayList<ChatMessage>();
        for(int i = 0; i < 5; i++){
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setContent("属性动画就是，动画的执行类来设置动画操作的对象的属性、持续时间，开始和结束的属性值，时间差值等，然后系统会根据设置的参数动态的变化对象的属性");
            chatMessage.setTime("15-4-" + (14 +i));
            chatMessage.setTitle("属性动画");
            chatMessage.setIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon_contact_default));
            chatMessages.add(chatMessage);
        }
    }
    private void showOneDialog(String title,String item,final int who){
        TextView titleTextView;
        TextView leftBtn;
        TextView firstTextView;
        View contentView = View.inflate(getActivity(),R.layout.dialog_one_choice,null);
        titleTextView = (TextView) contentView.findViewById(R.id.title_choices);
        leftBtn = (TextView) contentView.findViewById(R.id.left_text_btn);
        firstTextView = (TextView) contentView.findViewById(R.id.choice_one_text);
        titleTextView.setText(title);
        firstTextView.setText(item);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        firstTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"删除",Toast.LENGTH_SHORT).show();
                chatMessages.remove(who);
                adapter.notifyDataSetChanged();
                changViewShow();
                dialog.dismiss();
            }
        });
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        dialog = new Dialog(getActivity(),R.style.dialog);
        dialog.setContentView(contentView,new ViewGroup.LayoutParams(width - 60, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.show();
    }
    
    private void changViewShow(){
        
        if(adapter.getCount() == 0){
            noDataView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }else{
            noDataView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }
    }
}
