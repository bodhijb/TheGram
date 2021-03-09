package com.jf2mc1.a015004vthegram;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class ProfileTab extends Fragment {

    private EditText edtPName, edtPBio, edtPProf, edtPHob, edtPSport;
    private Button btnPUpdate;


    public ProfileTab() {
        // Required empty public constructor
    }


    public static ProfileTab newInstance() {
        ProfileTab fragment = new ProfileTab();

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
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtPName = view.findViewById(R.id.edt_p_name);
        edtPBio = view.findViewById(R.id.edt_p_bio);
        edtPProf = view.findViewById(R.id.edt_p_prof);
        edtPHob = view.findViewById(R.id.edt_p_hob);
        edtPSport = view.findViewById(R.id.edt_p_sport);
        btnPUpdate = view.findViewById(R.id.btn_p_update);
        Log.i("BBBB", "in onCreateView");

        ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser != null) {
            checkUserInfo(parseUser, "profileName", edtPName);
            checkUserInfo(parseUser, "profileBio", edtPBio);
            checkUserInfo(parseUser, "profileProfession", edtPProf);
            checkUserInfo(parseUser, "profileHobbies", edtPHob);
            checkUserInfo(parseUser, "profileFavSport", edtPSport);
        }

        btnPUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BBBB", "in onClick");
                parseUser.put("profileName", edtPName.getText().toString());
                parseUser.put("profileBio", edtPBio.getText().toString());
                parseUser.put("profileProfession", edtPProf.getText().toString());
                parseUser.put("profileHobbies", edtPHob.getText().toString());
                parseUser.put("profileFavSport", edtPSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(getContext(),
                                    ":) info updated",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS,
                                    true).show();
                        } else {
                            FancyToast.makeText(getContext(),
                                    e.getMessage() + ":(",
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR,
                                    true).show();

                        }
                    }
                });
            }
        });

        return view;

    }

    private void checkUserInfo(ParseUser user, String columnName, EditText editText) {
        if(user.get(columnName) == null) {
            editText.setText("");
        } else {
            editText.setText(user.get(columnName)+"");
        }

    }


}