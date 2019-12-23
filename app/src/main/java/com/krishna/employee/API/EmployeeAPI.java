package com.krishna.employee.API;

import com.krishna.employee.Model.Employee;
import com.krishna.employee.Model.EmployeeCUD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeAPI {

    @GET("employee")
    Call<List<Employee>> getAllEmployees();

    @POST("create")
    Call<Void>registerEmployee(@Body EmployeeCUD emp);


    @GET("employee/{empID}")
    Call<Employee> getEmployeeByID(@Path("empID") int empId);



}
