package com.krishna.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.krishna.employee.API.EmployeeAPI;
import com.krishna.employee.Model.Employee;
import com.krishna.employee.URL.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowEmployeeActivity extends AppCompatActivity {

    TextView tvShowEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee);
        tvShowEmployee=findViewById(R.id.tvEmployee);

        Retrofit retrofit=new  Retrofit.Builder()
                .baseUrl(URL.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeAPI employeeAPI =retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall=employeeAPI.getAllEmployees();

        //Asynchronous call
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(ShowEmployeeActivity.this, "Error Code"+ response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Employee>employeeList=response.body();

                for (Employee emp: employeeList){
                    String data ="";
                 data +="Name is:" +emp.getEmployee_name()+ "\n";
                    data +="Salary is:" +emp.getEmployee_salary()+ "\n";
                    data +="------------------"+ "\n";

                    tvShowEmployee.append(data);


                }


            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Msg","onFailure:"+ t.getLocalizedMessage());
                Toast.makeText(ShowEmployeeActivity.this, "Error" +t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }
}
