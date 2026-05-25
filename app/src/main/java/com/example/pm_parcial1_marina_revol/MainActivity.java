package com.example.pm_parcial1_marina_revol;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RadioButton rb1, rb2, rb3, rb4;
    private Spinner sp1;
    private Switch sw1;
    private TextView tv4;
    private ImageButton ibtn2;
    private Button btn1;
    private RadioGroup rg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        sp1 = findViewById(R.id.sp1);
        sw1 = findViewById(R.id.sw1);
        tv4 = findViewById(R.id.tv4);
        btn1 = findViewById(R.id.btn1);
        ibtn2 = findViewById(R.id.ibtn2);
        rg1 = findViewById(R.id.rg1);

        //Vector definido para el Spinner
        String[] pago = {"Efectivo", "Tarjeta de crédito", "Transferencia"};

        //ArrayAdapter, creación y definición
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pago);
        //Asignamos el Spinner
        sp1.setAdapter(adaptador);

        //Configuracion del Radio Group
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb1) {
                    Toast.makeText(MainActivity.this, "Plan Básico seleccionado", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.rb2) {
                    Toast.makeText(MainActivity.this, "Plan Estándar seleccionado", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.rb3) {
                    Toast.makeText(MainActivity.this, "Plan Premium seleccionado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configuracion del Button calcular
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //If para asegurar que haya una opción seleccionada
            if (!rb1.isChecked() && !rb2.isChecked() && !rb3.isChecked()){
                Toast.makeText(MainActivity.this, "Seleccione una opcion para seguir!.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double basico = 500.00;
                double estandar = 1000.00;
                double premium = 1500.00;
                double hd = 300.00;
                double total = 0.00;
                double valorDolar = 1000.00;
                String selec = sp1.getSelectedItem().toString(); // extraer contenido del control spinner

                //Logica para calcular el precio del plan
                if (rb1.isChecked()){
                    total +=basico;
                }else if (rb2.isChecked()){
                    total += estandar;
                } else if (rb3.isChecked()){
                    total += premium;
                }

                if(rb4.isChecked()){
                    total += hd;
                }

                // Logica para los descuentos en los pagos
                if (selec.equals("Efectivo")){
                    total = total * 0.80;
                } else if (selec.equals("Tarjeta de crédito")) {
                    total = total *1.10;
                } else if (selec.equals("Transferencia")) {
                    total = total * 0.90;
                }

                //Configuración del Switch
                if (sw1.isChecked()) {
                    total = total / valorDolar;
                    tv4.setText(String.format("Total en USD: $%.2f", total));
                } else {
                    tv4.setText(String.format("Total en ARS: $%.2f", total));
                }

            } catch (Exception e){
                tv4.setText("Error, intente de nuevo!.");
                }

            }
        });

        //Configuración de Imagen Button para reiniciar el formulario.
        ibtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rg1.clearCheck();
                rb4.setChecked(false);
                sp1.setSelection(0);
                sw1.setChecked(false);
                tv4.setText("");
                Toast.makeText(MainActivity.this, "Formulario reiniciado", Toast.LENGTH_SHORT).show();
            }
        });

    }
}