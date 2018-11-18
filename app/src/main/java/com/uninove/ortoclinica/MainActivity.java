package com.uninove.ortoclinica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ListView listViewOpcoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        this.CriarComponentes();

        this.CriarEventos();

        this.CarregaOpcoesLista();


    }

    protected void CriarComponentes() {


        listViewOpcoes = (ListView) this.findViewById(R.id.listViewOpcoes);
    }


    protected void CarregaOpcoesLista() {

        String[] itens = new String[2];

        itens[0] = "Cadastrar";
        itens[1] = "Consultar";

        ArrayAdapter<String> arrayItens = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);


        listViewOpcoes.setAdapter(arrayItens);

    }

    protected void CriarEventos() {

        listViewOpcoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String opcaoMenu = ((TextView) view).getText().toString();

                RedirecionaTela(opcaoMenu);


            }
        });
    }

    protected void RedirecionaTela(String opcaoMenu) {

        Intent intentRedirecionar;

        if (opcaoMenu.equals("Cadastrar")) {

            intentRedirecionar = new Intent(this, CadastrarActivity.class);
            startActivity(intentRedirecionar);
            finish();
        } else if (opcaoMenu.equals("Consultar")) {

            intentRedirecionar = new Intent(this, ConsultarActivity.class);
            startActivity(intentRedirecionar);
            finish();
        } else
            Toast.makeText(getApplicationContext(), "Opção inválida!", Toast.LENGTH_SHORT).show();

    }
}
