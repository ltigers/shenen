package com.cntysoft.viewpagefragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/5/28.
 */
public class Fragment2 extends Fragment {

    private FragmentTabHost tabHost;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_item2, container,false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    private void initView(View view){
        tabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        tabHost.setup(getActivity(),getChildFragmentManager(),R.id.realtabcontent);
        tabHost.addTab(tabHost.newTabSpec("taotu").setIndicator("套图"), Fragment1.class, null);
        tabHost.addTab(tabHost.newTabSpec("dantu").setIndicator("单图"),Fragment4.class,null);
    }
}
