package com.mding.chatfeng.main_code.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mding.chatfeng.R;
import com.mding.chatfeng.main_code.ui.about_contacts.about_custom.Allcity;

import java.util.List;

public class CityAdapter extends BaseAdapter {

    List<Allcity> allCitiesList;
    Context context;
    public CityAdapter(Context context,List<Allcity> allCitiesList) {
        super();
        this.allCitiesList=allCitiesList;
        this.context=context;

    }

    @Override
    public int getCount() {
        return allCitiesList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view =null;
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.cites_item, null);
            TextView tv_zimu = (TextView) view.findViewById(R.id.tv_zimu);
            TextView tv_cities = (TextView) view.findViewById(R.id.tv_cities);
            Allcity allcity = allCitiesList.get(position);
            tv_cities.setText(allcity.getName());
            String firstABC = getFirstABC(allcity.getPinyin());
            if (position==0)
            {
                tv_zimu.setVisibility(View.VISIBLE);
                tv_zimu.setText(firstABC);
            }else {
                String upFirstABC = getFirstABC(allCitiesList.get(position-1).getPinyin());
                if (firstABC.equals(upFirstABC))
                {
                    tv_zimu.setVisibility(View.GONE);
                }
                else {
                    tv_zimu.setVisibility(View.VISIBLE);
                    tv_zimu.setText(firstABC);
                }
            }
        }
        return view;
    }
    public String getFirstABC(String pinyin)
    {
        String upperCase = pinyin.substring(0,1).toUpperCase();
        return upperCase;
//    	char mcharAt = pinyin.charAt(0);
//    	String letter =mcharAt+"";
//		return letter.toUpperCase();
    }
}
