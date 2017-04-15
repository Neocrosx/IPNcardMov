package cecyt9.ipn.edu.ipncardmov;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class homePage extends AppCompatActivity {

    Button csesion;
    ImageButton llamar, mapa, hp;
    TextView usuario;
    String boleta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_dis);

        Bundle recibe = new Bundle();
        recibe = this.getIntent().getExtras();
        boleta = recibe.getString("boleta");

        llamar = (ImageButton) findViewById(R.id.imgBtnCall);
        mapa = (ImageButton) findViewById(R.id.imgBtnMap);
        hp = (ImageButton) findViewById(R.id.imgBtnHP);

        csesion = (Button) findViewById(R.id.btnSalir);
        usuario = (TextView) findViewById(R.id.txtVwBoleta);
        usuario.setText(boleta);
    }

    public void abrirPaginaWeb(View paginaWeb)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.cecyt9.ipn.mx/Paginas/inicio.aspx"));
        startActivity(intent);
    }

    public void llamadaTelefono(View llamada)
    {
        Intent intent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:5557596000"));
                int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE);
        startActivity(intent);
    }

    public void googleMaps(View maps)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:19.4535855,-99.1773856"));
        startActivity(intent);
    }

    public void cerrarS(View v){
        Toast.makeText(this, "Sesión Cerrada", Toast.LENGTH_SHORT).show();
        Intent envia = new Intent(this, inicio_sesion.class);
        finish();
        startActivity(envia);
    }



}
