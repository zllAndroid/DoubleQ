package com.doubleq.xm6leefunz.main_code.about_fragment.about_discovery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubleq.xm6leefunz.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryFragment extends Fragment {
    public DiscoveryFragment() {
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_discovery, container, false);


        }

        //        朋友圈
        view.findViewById(R.id.lin_discovery_friendcircle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FriendCircleActivity.class));
            }
        });

        return view;
    }

}
