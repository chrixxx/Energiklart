package menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.venovu.energiklart.Kund_activity;
import com.venovu.energiklart.Make_performance;
import com.venovu.energiklart.R;
import com.venovu.energiklart.Rond_activity;

public class BlankFragment extends Fragment {

    //Button buttonK;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_blank,
                container, false);

        //Start new activity Kund
        Button buttonK = (Button) view.findViewById(R.id.buttonKund);
        buttonK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Make_performance.class);
                startActivity(i);
            }
        });

        //Start new activity Rond
        Button buttonR = (Button) view.findViewById(R.id.buttonRond);
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Rond_activity.class);
                startActivity(i);
            }
        });
        return view;
    }

}
