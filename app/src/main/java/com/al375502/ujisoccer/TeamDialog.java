package com.al375502.ujisoccer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.al375502.ujisoccer.database.Team;

public class TeamDialog extends AppCompatDialogFragment {
    public Team teamSelected;

    public TeamDialog(Team teamSelected) {
        this.teamSelected = teamSelected;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.team_dialog, null);
        builder.setView(view).setTitle(teamSelected.name);
        return builder.create();
    }
}
