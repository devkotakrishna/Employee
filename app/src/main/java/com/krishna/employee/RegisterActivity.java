package com.krishna.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.krishna.employee.API.EmployeeAPI;
import com.krishna.employee.Model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private final static String BASE_URL="http://dummy.restapiexample.com/api/v1/employee/";

EditText etAge, etSalary, etName;
Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etAge =findViewById(R.id.etAge);
        etSalary=findViewById(R.id.etSalary);
        etName=findViewById(R.id.etName);
        btnAdd=findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }

    private void Register() {
        String name =etName.getText().toString();
        Float salary = Float.parseFloat(etSalary.getText().toString());
        int age =Integer.parseInt(etAge.getText().toString());

        EmployeeCUD employee =new EmployeeCUD(name,salary,age);

        Retrofit retrofit=  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI =retrofit.create((EmployeeAPI.class));

        Call<Void> voidCall =employeeAPI.registerEmployee(employee);
voidCall.enqueue(new Callback<Void>() {
    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        Toast.makeText(RegisterActivity.this, "youu have been successfully registered", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

        Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();

    }
});

    }
}
