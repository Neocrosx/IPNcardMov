package cecyt9.ipn.edu.ipncardmov;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.name;

public class inicio_sesion extends AppCompatActivity {

    EditText txtUsr, txtPass;
    TextView datos;
    ImageView logoIPN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion_dis);

        txtUsr = (EditText) findViewById(R.id.txtUsuar);
        txtPass = (EditText) findViewById(R.id.txtPass);
        logoIPN = (ImageView) findViewById(R.id.iVwLogoIPNcard);

        datos = (TextView) findViewById(R.id.txtDatos);

        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.animac);

        logoIPN.setAnimation(animacion);

    }

    public void Ingresar(View v) {
        String usuario = txtUsr.getText().toString();
        String clave = txtPass.getText().toString();
        Boolean entrar = false;

        //Aquí reviso la tabla en la base de datos. Christian López León

//       Ver registros de la tabla BD_IPNcard
//		SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
//		Create a helper object to create, open, and/or manage a database.
        AuxiliarSQL sql = new AuxiliarSQL(this, "DB_IPNcard", null, 1);
        final SQLiteDatabase db = sql.getWritableDatabase();
        String[] campos = {"Boleta", "Nombre", "Paterno", "Materno", "Pass"};
//		query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy);
//		Query the given table, returning a Cursor over the result set.
        Cursor selectAll = db.query("usuarios", campos, null, null, null, null, null);
//
        int Total = selectAll.getCount();
        for (int i = 1; i <= Total; i++) {
            selectAll.moveToNext();
            String nombreSelect = selectAll.getString(1);

            if (selectAll.getString(0).equals(usuario) && selectAll.getString(4).equals(clave)) {
                entrar = true;
            } else {
                entrar = false;
            }

//            datos.append(selectAll.getInt(0) + "|" +
//                    nombreSelect + "|" +
//                    selectAll.getString(1) + "|\n" +
//                    selectAll.getString(4) + "\n");
        }
        db.close();

//        if(!usuario.equals("") && !clave.equals("")){
//            if(usuario.equals("2015090375") && clave.equals("abrid")){
//                Toast.makeText(this, "Entraste", Toast.LENGTH_SHORT).show();
//                Intent envia = new Intent(this, homePage.class);
//                Bundle datos = new Bundle();
//                datos.putString("boleta", usuario.trim());
//               envia.putExtras(datos);
//                finish();
//                startActivity(envia);
//            }else{
//                Toast.makeText(this, "No pudiste entrar", Toast.LENGTH_SHORT).show();
//            }
//        }

        if (entrar) {
            Toast.makeText(this, "Entraste", Toast.LENGTH_SHORT).show();
            Intent envia = new Intent(this, homePage.class);
            Bundle datos = new Bundle();
            datos.putString("boleta", usuario.trim());
            envia.putExtras(datos);
            finish();
            startActivity(envia);
        } else {
            Toast.makeText(this, "No pudiste entrar", Toast.LENGTH_SHORT).show();
        }
    }

    public void registrar(View v){
        Intent envia = new Intent(this, regUsr.class);
        finish();
        startActivity(envia);
    }
}
