package cecyt9.ipn.edu.ipncardmov;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class regUsr extends AppCompatActivity {

    EditText boleta, nomb, pat, mat, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_usr);
        boleta = (EditText) findViewById(R.id.txtBoleta);
        nomb = (EditText) findViewById(R.id.txtNomb);
        pat = (EditText) findViewById(R.id.txtPat);
        mat = (EditText) findViewById(R.id.txtMat);
        pass = (EditText) findViewById(R.id.txtPass);
    }

    public void registrarBD(View v){

//		Almacenamiento en BD
        // Definimos el nombre de la base de datos
		AuxiliarSQL sql = new AuxiliarSQL(this,"DB_IPNcard", null, 1);
//		// Ocupamos la base para escribir
		final SQLiteDatabase db = sql.getWritableDatabase();

        String sboleta = boleta.getText().toString();
        String snomb = nomb.getText().toString();
        String spat = pat.getText().toString();
        String smat = mat.getText().toString();
        String spass = pass.getText().toString();
//
		ContentValues datos = new ContentValues();
		datos.put("Boleta", sboleta);
		datos.put("Nombre", snomb);
		datos.put("Paterno", spat);
		datos.put("Materno", smat);
        datos.put("Pass", spass);
//
//		// Si la "conexion" se realiza
		if(db != null){
			try{
				db.insert("usuarios", null, datos);
				Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show();

			} catch (Exception e){
				Toast.makeText(getApplicationContext(),
						"Error en Insert " + e,
						Toast.LENGTH_LONG).show();
			}
		}else{
			Toast.makeText(getApplicationContext(),
					"No existe la base de datos",
					Toast.LENGTH_LONG).show();
		}
		Intent cambia = new Intent(this, inicio_sesion.class);
		finish();
		startActivity(cambia);
    }

}
