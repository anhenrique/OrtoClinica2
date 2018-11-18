package com.uninove.ortoclinica.Uteis;

import android.app.AlertDialog;
import android.content.Context;
import com.uninove.ortoclinica.R;

public class Uteis {


    public static void Alert(Context context, String mensagem){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle(R.string.app_name);

        alertDialog.setMessage(mensagem);

        alertDialog.setPositiveButton("OK", null);

        alertDialog.show();

    }


}