package com.krishna.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.krishna.employee.API.EmployeeAPI;
import com.krishna.employee.Model.Employee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmployeeActivity extends AppCompatActivity {
    private final static String BASE_URL="http://dummy.restapiexample.com/api/v1/";

    EditText etName;
    Button btnSearch;
   TextView tvEmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        etName = findViewById(R.id.etName);
        btnSearch = findViewById(R.id.btnSearch);
        tvEmp = findViewById(R.id.tvData);

        btnSearch.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();

            }
        }));
    }

        private void loadData() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);

            Call<Employee> listCall = employeeAPI.getEmployeeByID(Integer.parseInt(etName.getText().toString()));

            listCall.enqueue(new Callback<Employee>() {
                @Override
                public void onResponse(Call<Employee> call, Response<Employee> response) {
                    Toast.makeText(SearchEmployeeActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                    String content = "";
                    content += "Id:" + response.body().getId() + "\n";
                    content += "Name:" + response.body().getEmployee_name() + "\n";
                    content += "Age:" + response.body().getEmployee_age() + "\n";
                    content += "Salary:" + response.body().getEmployee_salary() + "\n";
                    tvEmp.setText(content);
                }

                @Override
                public void onFailure(Call<Employee> call, Throwable t) {
                    Toast.makeText(SearchEmployeeActivity.this, "error", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

