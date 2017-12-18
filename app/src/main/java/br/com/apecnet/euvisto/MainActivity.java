package br.com.apecnet.euvisto;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private Button readtag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        readtag = (Button) findViewById(R.id.readtag);
        final Activity activity = this;
        readtag.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                IntentIntegrator integrator = new IntentIntegrator((activity));
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setOrientationLocked(true);
                integrator.setCameraId(0);
                integrator.setPrompt("");
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this,"Leitura de Etiqueta Cancelada", Toast.LENGTH_LONG).show();
            }
            else{
                String leitura = result.getContents();
                //String texto = "Quantidade: "+qtdInfo(leitura);
                String texto =dadosTag(leitura);
                //Toast.makeText(this,texto1, Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(this, VisualizaActivity.class);
                //startActivity(intent);
                Bundle bundle = new Bundle();
                bundle.putString("itens", texto);
                Intent intent = new Intent(this, VisualizaActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    int qtdInfo(String txt)
    {
        int qtd = 0;

        for(int a=0;a<txt.length();a++)
        {
            if (txt.charAt(a)==';')
            {
                qtd+=1;
            }


        }

        return qtd;
    }

    public String dadosTag (String lista)
    {
        String etiqueta="";
        for(int a=0;a<lista.length();a++)
        {
            if (lista.charAt(a)==';')
            {
                etiqueta+="\n\n\n";
            }
            else
            {
                etiqueta+=lista.charAt(a);
            }

        }
        return etiqueta;
    }



}
