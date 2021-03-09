package com.jf2mc1.a015004vthegram;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class UsersTab extends Fragment {

    private ListView listView;
    private boolean rotate = true;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;


    public UsersTab() {
        // Required empty public constructor
    }


    public static UsersTab newInstance() {
        UsersTab fragment = new UsersTab();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_users_tab, container, false);

        listView = view.findViewById(R.id.list_view);
        TextView tvLoadingUsers = view.findViewById(R.id.tv_loading_users);
        //object Alist
        arrayList = new ArrayList<>();
        //context == socialmediaActivity, listItem == generic, data == arrayList
        arrayAdapter =  new ArrayAdapter(getContext(),
                android.R.layout.simple_list_item_activated_1,
                arrayList);


        tvLoadingUsers.animate().rotationX(3600).setDuration(5000);

        // get data from Parse, list of all users except the current user
        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        if(ParseUser.getCurrentUser() != null) {
            parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
            parseQuery.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> users, ParseException e) {
                    if (e == null) {
                        if (users.size() > 0) {

                            for (ParseUser user : users) {
                                arrayList.add(user.getUsername());
                            }
                            listView.setAdapter(arrayAdapter);

                            tvLoadingUsers.animate().alpha(0).setDuration(2000);
                            tvLoadingUsers.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);

                        }


                    }
                }
            });
        }


        return view;
    }















}