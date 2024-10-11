package com.example.appmenulateral2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Declaración de instancias
    private EditText mail, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Se asocian componentes a instancias
        mail = (EditText) findViewById(R.id.txtCorreo);
        password = (EditText) findViewById(R.id.txtContrasena);
    }//onCreate
    public void ingresarMenu(View view){
//Se obtienen los datos del cajas de texto
        String usr = mail.getText().toString().trim();
        String con = password.getText().toString().trim();
//Se valida si son datos correctos
        if(usr.equals("admin") && con.equals("12345")){
            Intent intent = new
                    Intent(getApplicationContext(),MenuLateralActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Datos incorrectos, intente de nuevo.",
                    Toast.LENGTH_SHORT).show();
        }
    }//ingresarMenu
    public void salirMenu(View view){
//Finalizar activity
        finish();
    }//salirMenu
}//class
