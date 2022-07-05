package stepDefinitions;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class dummyRestApiStepDef {
    Response response;
    JsonPath json;
    @And("there are {int} employees")
    public void thereAreEmployees(int numberOfEmployees) {
        response = RestAssured.given().accept("application/json").when().get();

        json = response.jsonPath();
        System.out.println(json.getList("data.id").size());
        assertEquals(numberOfEmployees, json.getList("data.id").size());

    }


    @And("{string} and {string} are among the employees")
    public void andAreAmongTheEmployees(String employee1, String employee2) {
        Set<String> employees = new HashSet<String>();
        employees.add(employee1);
        employees.add(employee2);

       assertTrue(json.getList("data.findAll{it.employee_name}.employee_name").containsAll(employees));

    }

    @And("the greatest age is {int}")
    public void theGreatestAgeIs(int age) {
       List<Integer> ageList =  json.getList("data.findAll{it.employee_age}.employee_age");
        Collections.sort(ageList);
        assertEquals(age,(int)ageList.get(ageList.size() - 1));
    }

    @And("the name of the lowest age is {string}")
    public void theNameOfTheLowestAgeIs(String nameOfEmployee) {
        List<Integer> ageList =  json.getList("data.findAll{it.employee_age}.employee_age");
        Collections.sort(ageList);
        String groovyString = "data.findAll{it.employee_age==" + ageList.get(0)+"}.employee_name";

        System.out.println(groovyString);
        assertEquals("[Tatyana Fitzpatrick]", json.getString(groovyString));


    }


    @And("total salary of all employees is {int}")
    public void totalSalaryOfAllEmployeesIs(int salary) {

        List<Integer> salaryList = json.getList("data.findAll{it.id>0}.employee_salary");

        //1. way to calculate the sum
        int sum1 = 0;
        for(Integer each : salaryList){
            sum1 += each;
        }
        System.out.println("sum1 :" +sum1);
        assertEquals(salary,sum1);

        //2.way to calculate the sum
        int sum2 = salaryList.stream().reduce(0,(x,y)-> x+y);
        System.out.println("sum2 :" +  sum2);

        //3. way to calculate the sum
        int sum3 = salaryList.stream().reduce(0,Math::addExact);
        System.out.println("sum3 :" + sum3);

    }


}
