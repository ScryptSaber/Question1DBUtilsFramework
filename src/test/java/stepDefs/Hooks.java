package stepDefs;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.DBUtils;

import java.sql.SQLException;

public class Hooks {
    @Before
    public void setup(){
        System.out.println("I am setting up the test from the Hooks class!!!");
    }
    @Before("@db")
    public void setUpDBConnection(){
        System.out.println("I am setting up the DB connection !!");
        DBUtils.createConnection();
    }
    @After("@db")
    public static void tearDownDBConnection(){
        System.out.println("I am closing the DB connection");
        try {
            DBUtils.destroy();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
