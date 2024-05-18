package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar mytoolbar;

    Button btnCalculate, btnClear;

    EditText etUnit , etRebate;

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        etRebate = findViewById(R.id.etRebate);
        if(etRebate.getText().toString().equals("")){
            etRebate.setError("Please insert the rebate");
        }
        etUnit = findViewById(R.id.etUnit);
        if(etUnit.getText().toString().equals("")){
            etUnit.setError("Please insert the unit");
        }
        textView = findViewById(R.id.textView);

        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setSubtitle("Estimating electricity bills");

        getSupportActionBar().setTitle("Electric Bills");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menuAbout){
            Intent aboutIntent = new Intent(this, com.example.assignment.AboutActivity.class);
            startActivity(aboutIntent);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        if (view == btnCalculate){
            String strR = etRebate.getText().toString();
            String strU = etUnit.getText().toString();
            double unit, rebate;
            double bill = 0, total = 0;
            try {
                rebate = Double.parseDouble(strR);
                unit = Double.parseDouble(strU);

                if(unit <= 200){
                    bill = unit * 0.218;

                } else if (unit <= 300) {
                    bill = (200 * 0.218) + ((unit - 200) * 0.334);

                } else if (unit <= 600) {
                    bill = (200 * 0.218) + (100 * 0.334) + ((unit- 300) * 0.516);

                } else{
                    bill = (200 * 0.218) + (100 * 0.334) + ((unit- 300) * 0.516);

                }

                total = bill- (bill * rebate);

            }catch (NumberFormatException nfe){
                Toast.makeText(this, "Please enter numbers in the input field", Toast.LENGTH_SHORT).show();
            }catch (Exception exception){
                Toast.makeText(this, "Please enter numbers in the input field - unspecific error", Toast.LENGTH_SHORT).show();
            }

            textView.setText("Amount of bills: RM " + total);
        }else if (view == btnClear) {
            etUnit.setText("");
            etRebate.setText("");
            textView.setText("");


        }
    }
}