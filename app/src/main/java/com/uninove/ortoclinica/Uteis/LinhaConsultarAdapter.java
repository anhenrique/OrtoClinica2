package com.uninove.ortoclinica.Uteis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.uninove.ortoclinica.ConsultarActivity;
import com.uninove.ortoclinica.EditarActivity;
import com.uninove.ortoclinica.R;
import com.uninove.ortoclinica.model.PessoaModel;
import com.uninove.ortoclinica.repository.PessoaRepository;

public class LinhaConsultarAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater = null;

    List<PessoaModel> pessoaModels =  new ArrayList<PessoaModel>();

    PessoaRepository  pessoaRepository;

    private ConsultarActivity consultarActivity;

    public LinhaConsultarAdapter(ConsultarActivity consultarActivity, List<PessoaModel> pessoaModels ) {

        this.pessoaModels       =  pessoaModels;
        this.consultarActivity  =  consultarActivity;
        this.layoutInflater     = (LayoutInflater) this.consultarActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.pessoaRepository   = new PessoaRepository(consultarActivity);
    }

    @Override
    public int getCount(){

        return pessoaModels.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha_consultar,null);

        TextView textViewCodigo          = (TextView) viewLinhaLista.findViewById(R.id.textViewCodigo);

        TextView textViewNome            = (TextView) viewLinhaLista.findViewById(R.id.textViewNome);

        TextView textViewEndereco        = (TextView) viewLinhaLista.findViewById(R.id.textViewEndereco);

        TextView textViewSexo            = (TextView) viewLinhaLista.findViewById(R.id.textViewSexo);

        TextView textViewEstadoCivil     = (TextView) viewLinhaLista.findViewById(R.id.textViewEstadoCivil);

        TextView textViewNascimento      = (TextView) viewLinhaLista.findViewById(R.id.textViewNascimento);

        TextView textViewRegsitroAtivo   = (TextView) viewLinhaLista.findViewById(R.id.textViewRegistroAtivo);

        Button buttonExcluir             = (Button)   viewLinhaLista.findViewById(R.id.buttonExcluir);

        Button   buttonEditar            = (Button)   viewLinhaLista.findViewById(R.id.buttonEditar);

        textViewCodigo.setText(String.valueOf(pessoaModels.get(position).getCodigo()));

        textViewNome.setText(pessoaModels.get(position).getNome());

        textViewEndereco.setText(pessoaModels.get(position).getEndereco());

        if(pessoaModels.get(position).getSexo().toUpperCase().equals("M"))
            textViewSexo.setText("Masculino");
        else
            textViewSexo.setText("Feminino");

        textViewEstadoCivil.setText(this.GetEstadoCivil(pessoaModels.get(position).getEstadoCivil()));


        textViewNascimento.setText(pessoaModels.get(position).getDataNascimento());

        if(pessoaModels.get(position).getRegistroAtivo() == 1)
            textViewRegsitroAtivo.setText("Registro Ativo:Sim");
        else
            textViewRegsitroAtivo.setText("Registro Ativo:Não");

        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pessoaRepository.Excluir(pessoaModels.get(position).getCodigo());

                Toast.makeText(consultarActivity, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();

                AtualizarLista();

            }
        });

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intentRedirecionar = new Intent(consultarActivity, EditarActivity.class);

                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                intentRedirecionar.putExtra("id_pessoa",pessoaModels.get(position).getCodigo());

                consultarActivity.startActivity(intentRedirecionar);

                consultarActivity.finish();


            }
        });


        return viewLinhaLista;
    }

    public String GetEstadoCivil(String codigoEstadoCivil){


        if(codigoEstadoCivil.equals("S"))
            return "Solteiro(a)";
        else if(codigoEstadoCivil.equals("C"))
            return "Casado(a)";
        else if(codigoEstadoCivil.equals("V"))
            return "Viuvo(a)";
        else
            return "Divorciado(a)";

    }

    public void AtualizarLista(){

        this.pessoaModels.clear();
        this.pessoaModels = pessoaRepository.SelecionarTodos();
        this.notifyDataSetChanged();
    }


}
