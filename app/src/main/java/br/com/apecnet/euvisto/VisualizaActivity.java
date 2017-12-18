package br.com.apecnet.euvisto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VisualizaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza);

        String infoEtiqueta;

        Intent intent = getIntent();

        Bundle dados = intent.getExtras();

        infoEtiqueta = dados.getString("itens").toString();

        TextView tag = (TextView) findViewById(R.id.etiqueta);

        tag.setContentDescription(infoEtiqueta);

        tag.setText(infoEtiqueta);

        Button sair = (Button) findViewById(R.id.voltar);

        sair.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inicial = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(inicial);
            }

        });
    }
}
