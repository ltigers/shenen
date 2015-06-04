package com.cntysoft.micromart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.cntysoft.micromart.R;
import com.cntysoft.micromart.model.ShopChoose;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/5/26.
 */
public class ShopChooseAdapter extends BaseAdapter implements SectionIndexer {

    private List<ShopChoose> shopChooseList;
    private Context context;
    private List<String> sectionList;

    public ShopChooseAdapter(Context context, List<ShopChoose> shopChooseList){
        this.context = context;
        this.shopChooseList = shopChooseList;
    }

    private String[] getKey(){
        if(shopChooseList != null){
            sectionList = new ArrayList<>();
            for (ShopChoose shopChoose : shopChooseList){
                if(sectionList.size() == 0){
                   sectionList.add(shopChoose.getFirstLetter().toUpperCase());
                }else{
                    if(!sectionList.contains(shopChoose.getFirstLetter().toUpperCase())){
                        sectionList.add(shopChoose.getFirstLetter().toUpperCase());
                    }
                }
            }
            return (String[])sectionList.toArray();
        }else{
            return null;
        }
    }
    @Override
    public int getCount() {
        return shopChooseList.size();
    }

    @Override
    public ShopChoose getItem(int position) {
        return shopChooseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.choose_shop_item,null);
            viewHolder.catalog = (TextView) convertView.findViewById(R.id.group_index);
            viewHolder.title = (TextView) convertView.findViewById(R.id.shop_name);
            viewHolder.catalogDivider = convertView.findViewById(R.id.catalog_divider);
            viewHolder.catalogDivider1 = convertView.findViewById(R.id.catalog_divider1);
            viewHolder.titleDivider = convertView.findViewById(R.id.name_divider);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        ShopChoose shopChoose = shopChooseList.get(position);
        int section = getSectionForPosition(position);

        if(position == getPositionForSection(section)){
            viewHolder.catalog.setVisibility(View.VISIBLE);
            viewHolder.catalog.setText(shopChoose.getFirstLetter().toUpperCase());
            viewHolder.catalogDivider.setVisibility(View.VISIBLE);
            viewHolder.catalogDivider1.setVisibility(View.VISIBLE);
            viewHolder.titleDivider.setVisibility(View.VISIBLE);
        }else{
            viewHolder.catalog.setVisibility(View.GONE);
            viewHolder.catalogDivider.setVisibility(View.GONE);
            viewHolder.catalogDivider1.setVisibility(View.GONE);
        }
        viewHolder.titleDivider.setVisibility(View.VISIBLE);
        viewHolder.title.setText(shopChoose.getName());

        if(position + 1 < shopChooseList.size()){
            int nextSection = getSectionForPosition(position + 1);
            int nextPosition = getPositionForSection(nextSection);
            if(nextPosition == position + 1){
                viewHolder.titleDivider.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    @Override
    public Object[] getSections() {

        return getKey();
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for(int i = 0; i < shopChooseList.size(); i++){
            char c = shopChooseList.get(i).getFirstLetter().toUpperCase().charAt(0);
            if(c == sectionIndex){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return shopChooseList.get(position).getFirstLetter().toUpperCase().charAt(0);
    }

    static class ViewHolder{
        TextView catalog;
        TextView title;
        View catalogDivider;
        View titleDivider;
        View catalogDivider1;

    }
}
