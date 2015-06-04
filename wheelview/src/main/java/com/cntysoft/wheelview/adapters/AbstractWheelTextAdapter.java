package com.cntysoft.wheelview.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/5/15.
 */
public abstract class AbstractWheelTextAdapter extends AbstractWheelAdapter{

    /** Text view resource. Used as a default view for adapter. */
    public static final int TEXT_VIEW_ITEM_RESOURCE = -1;


    /** No resource constant. */
    protected static final int NO_RESOURCE = 0;

    public static final int DEFAULT_TEXT_COLOR = 0xFF101010;

    /** Default text color */
    public static final int LABEL_COLOR = 0xFF700070;

    /** Default text size */
    public static final int DEFAULT_TEXT_SIZE = 24;

    // Text settings
    private int textColor = DEFAULT_TEXT_COLOR;
    private int textSize = DEFAULT_TEXT_SIZE;

    // Current context
    protected Context context;
    // Layout inflater
    protected LayoutInflater inflater;

    // Items resources
    protected int itemResourceId;
    protected int itemTextResourceId;

    // Empty items resources
    protected int emptyItemResourceId;

    private int currentIndex = 0;
    private static int maxsize = 24;
    private static int minsize = 14;
    private ArrayList<View> arrayList = new ArrayList<View>();

    protected AbstractWheelTextAdapter(Context context){
        this(context,TEXT_VIEW_ITEM_RESOURCE);
    }

    /**
     * Constructor
     *
     * @param context
     *            the current context
     * @param itemResource
     *            the resource ID for a layout file containing a TextView to use
     *            when instantiating items views
     */
    protected AbstractWheelTextAdapter(Context context,int itemResource){
        this(context, itemResource, NO_RESOURCE, 0, maxsize, minsize);
    }

    /**
     * Constructor
     *
     * @param context
     *            the current context
     * @param itemResource
     *            the resource ID for a layout file containing a TextView to use
     *            when instantiating items views
     * @param itemTextResource
     *            the resource ID for a text view in the item layout
     */
    protected AbstractWheelTextAdapter(Context context, int itemResource, int itemTextResource, int currentIndex,
                                       int maxsize, int minsize) {
        this.context = context;
        itemResourceId = itemResource;
        itemTextResourceId = itemTextResource;
        this.currentIndex = currentIndex;
        this.maxsize = maxsize;
        this.minsize = minsize;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * get the list of show textview
     *
     * @return the array of textview
     */
    public ArrayList<View> getTestViews(){
        return arrayList;
    }

    public int getTextColor(){
        return textColor;
    }

    public void setTextColor(int textColor){
        this.textColor = textColor;
    }

    public int getTextSize(){
        return textSize;
    }

    public void setTextSize(int textSize){
        this.textSize = textSize;
    }

    public int getItemResource(){
        return itemResourceId;
    }

    public void setItemResource(int itemResourceId){
        this.itemResourceId = itemResourceId;
    }

    public int getItemTextResource(){
        return itemTextResourceId;
    }

    public void setItemTextResource(int itemTextResourceId){
        this.itemTextResourceId = itemTextResourceId;
    }

    public int getEmptyItemResource(){
        return emptyItemResourceId;
    }

    public void setEmptyItemResource(int emptyItemResourceId){
        this.emptyItemResourceId = emptyItemResourceId;
    }

    /**
     * Returns text for specified item
     *
     * @param index
     *            the item index
     * @return the text of specified items
     */
    protected abstract CharSequence getItemText(int index);

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        if(index >= 0 && index < getItemsCount()){
            if(convertView == null){
                convertView = getView(itemResourceId, parent);
            }
            TextView textView = getTextView(convertView,itemTextResourceId);
            if(!arrayList.contains(textView)){
                arrayList.add(textView);
            }
            if(textView != null){
                CharSequence text = getItemText(index);
                if(text == null){
                    text = "";
                }
                textView.setText(text);
                if(index == currentIndex){
                    textView.setTextSize(maxsize);
                }else{
                    textView.setTextSize(minsize);
                }

                if(itemResourceId == TEXT_VIEW_ITEM_RESOURCE){
                    configureTextView(textView);
                }
            }
            return convertView;
        }
        return null;
    }

    @Override
    public View getEmptyItem(View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = getView(emptyItemResourceId,parent);
        }
        if(emptyItemResourceId == TEXT_VIEW_ITEM_RESOURCE && convertView instanceof TextView){
            configureTextView((TextView) convertView);
        }

        return convertView;
    }

    /**
     * Configures text view. Is called for the TEXT_VIEW_ITEM_RESOURCE views.
     *
     * @param view
     *            the text view to be configured
     */
    protected void configureTextView(TextView view){
        view.setTextColor(textColor);
        view.setGravity(Gravity.CENTER);
        view.setTextSize(textSize);
        view.setLines(1);
        view.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
    }

    /**
     * Loads a text view from view
     *
     * @param view
     *            the text view or layout containing it
     * @param textResource
     *            the text resource Id in layout
     * @return the loaded text view
     */
    private TextView getTextView(View view,int textResource){
        TextView text = null;
        try{
            if(textResource == NO_RESOURCE && view instanceof TextView){
                text = (TextView)view;
            }else if(textResource != NO_RESOURCE){
                text = (TextView)view.findViewById(textResource);
            }
        }catch(ClassCastException e){
            Log.e("AbstractWheelAdapter", "You must supply a resource ID for a TextView");
            throw new IllegalStateException("AbstractWheelAdapter requires the resource ID to be a TextView", e);
        }

        return text;
    }

    /**
     * Loads view from resources
     *
     * @param resource
     *            the resource Id
     * @return the loaded view or null if resource is not set
     */
    private View getView(int resource, ViewGroup parent) {
        switch (resource) {
            case NO_RESOURCE:
                return null;
            case TEXT_VIEW_ITEM_RESOURCE:
                return new TextView(context);
            default:
                return inflater.inflate(resource, parent, false);
        }
    }
}
