package com.example.cajerofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usuario, clave, dinero, retirarDinero;
    Button ingresar, retirar, transaccion;
    TextView usuariotv, monto;
    int  resta;
    String total, subtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.etUsuario);
        clave = findViewById(R.id.etContrasena);
        dinero = findViewById(R.id.etMonto);
        retirarDinero = findViewById(R.id.etRetiro);
        ingresar = findViewById(R.id.btnIngresar);
        retirar = findViewById(R.id.btnRetirar);
        transaccion = findViewById(R.id.btnTransaccion);
        usuariotv = findViewById(R.id.tvUsuario);
        monto = findViewById(R.id.tvMonto);
        SharedPreferences datosCajero = getSharedPreferences("datos", Context.MODE_PRIVATE);
        usuario.setText(datosCajero.getString("user", ""));
        clave.setText(datosCajero.getString("password",""));
        dinero.setText(datosCajero.getString("cash",""));
        total = dinero.getText().toString();
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usuario.getText().toString().isEmpty() && !clave.getText().toString().isEmpty() && !dinero.getText().toString().isEmpty()){
                    SharedPreferences datosCajero = getSharedPreferences("datos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor insertar= datosCajero.edit();
                    insertar.putString("user", usuario.getText().toString());
                    insertar.putString("password", clave.getText().toString());
                    insertar.putString("cash", dinero.getText().toString());
                    insertar.commit();
                    Toast.makeText(MainActivity.this, "Datos correctos", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Debes ingresar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });
        usuario.setText("");
        clave.setText("");
        dinero.setText("");
        retirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences restaDinero = getSharedPreferences("datos", Context.MODE_PRIVATE);
                subtotal = retirarDinero.getText().toString();
                int a= Integer.parseInt(total);
                int b= Integer.parseInt(subtotal);
                if(b<=a){
                    resta = a-b;
                    String operacion = String.valueOf(resta);
                    dinero.setText(operacion);
                    SharedPreferences.Editor editar= restaDinero.edit();
                    editar.putString("cash", dinero.getText().toString());
                    editar.commit();
                    Toast.makeText(MainActivity.this, "Datos correctos", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "La cantidad que desea retirar es mayor a la que tiene en la cuenta", Toast.LENGTH_SHORT).show();
                }
            }
        });;
        transaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
                usuariotv.setText(preferences.getString("user",""));
                monto.setText(preferences.getString("cash", ""));
                if (!retirarDinero.getText().toString().isEmpty()){
                    finish();
                }
            }
        });

    }
}