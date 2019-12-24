package com.krishna.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.krishna.employee.API.EmployeeAPI;
import com.krishna.employee.Model.Employee;
import com.krishna.employee.URL.URL;

import java.lang.ref.Reference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.krishna.employee.URL.URL.createInstance;

public class UpdateDeleteActivity extends AppCompatActivity {
    private final static String BASE_URL="http://dummy.restapiexample.com/api/v1/";

    EditText etName, etAge,etSalary;
    Button btnS,btnDelete, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        btnDelete =findViewById(R.id.btnDelete);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnS=findViewById(R.id.btnS);

        etAge=findViewById(R.id.etAge);
        etName=findViewById(R.id.etName);
        etSalary=findViewById(R.id.etSalary);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

private void loadData(){
        createInstance();
    EmployeeAPI employeeAPI = URL.createInstance().create(EmployeeAPI.class);

    Call<Employee>listCall=employeeAPI.getEmployeeByID(Integer.parseInt(etEmpNo.getText()));
    listCall.enqueue(new Callback<Employee>() {
        @Override
        public void onResponse(Call<Employee> call, Response<Employee> response) {
            etName.setText(response.body().getEmployee_name());
            etAge.setText(response.body().getEmployee_age());
            etSalary.setText(Float.toString(response.body().getEmployee_salary()));
        }

        @Override
        public void onFailure(Call<Employee> call, Throwable t) {
            Toast.makeText(UpdateDeleteActivity.this, "error"+t.getMessage(),Toast.LENGTH_SHORT).show();

        }
    });
}

private void updateEmployee(){
        createInstance();
        EmployeeAPI employeeAPI =URL.createInstance().create(EmployeeAPI.class);
Call<Void>voisCall=employeeAPI.updateEmployee(Integer.parseInt(etEmpno.getText().toString(),updateEmployee()));
;
    voidCall.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Toast.makeText(UpdateDeleteActivity.this, "Updated", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Toast.makeText(UpdateDeleteActivity.this, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }

    private void deleteEmployee(){
        createInstance();
        Call<Void>voidCall=deleteEmployee(Integer.parseInt(etempno.getText().toString));
   voidCall.enqueue(new Callback<Void>() {
       @Override
       public void onResponse(Call<Void> call, Response<Void> response) {
           Toast.makeText(UpdateDeleteActivity.this, "successfully deleted ", Toast.LENGTH_SHORT).show();
       }

       @Override
       public void onFailure(Call<Void> call, Throwable t) {
           Toast.makeText(UpdateDeleteActivity.this, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();

       }
   });

    }

}



