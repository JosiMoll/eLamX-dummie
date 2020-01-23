package standard.example.elamx;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BlankFragment extends android.app.Fragment {


      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

          View view = inflater.inflate(R.layout.fragment_1, container, false);
        // Inflate the layout for this fragment
        return view;
    }

}
