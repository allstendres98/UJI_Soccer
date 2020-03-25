package com.al375502.ujisoccer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.al375502.ujisoccer.database.Team;

public class TeamDialog extends AppCompatDialogFragment {
    public Team teamSelected;
    public ListTeamActivity view;
    public TextView sh, f, st, cc;


    public TeamDialog(Team teamSelected, ListTeamActivity view) {
        this.teamSelected = teamSelected;
        this.view = view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View viewInflate = inflater.inflate(R.layout.team_dialog, null);
        builder.setView(viewInflate).setTitle(teamSelected.name);

        builder.setPositiveButton("WEB", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                view.GetIntoWebsite(teamSelected.website);
            }
        });
        builder.setNeutralButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("NEXT MATCHES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        sh = viewInflate.findViewById(R.id.sh);
        f = viewInflate.findViewById(R.id.f);
        st = viewInflate.findViewById(R.id.st);
        cc = viewInflate.findViewById(R.id.cc);

            sh.setText(teamSelected.shortName + "");
            f.setText(teamSelected.yearFoundation + "");
            st.setText(teamSelected.stadium + "");
            cc.setText(teamSelected.colours + "");


        return builder.create();
    }

    /*public interface TeamDialogListener{

    }*/
}
