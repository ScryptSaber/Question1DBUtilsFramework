package stepDefs;

import io.cucumber.java.en.Given;
import org.testng.Assert;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class Question1DBStepDefs {
    private static int count = 0;

    @Given("{string} {string} {string} {string} {string} should match with the result")
    public void should_match_with_the_result(String customer_id, String fullname, String city, String country, String sum) {
        String query = "SELECT c.customer_id,CONCAT(first_name,' ',last_name) AS fullname,city,country,SUM(amount) \n" +
                "FROM customer c\n" +
                "JOIN payment p ON p.customer_id=c.customer_id\n" +
                "JOIN address a ON a.address_id=c.address_id\n" +
                "JOIN city  ON a.city_id=city.city_id\n" +
                "JOIN country  ON country.country_id=city.country_id\n" +
                "GROUP BY c.customer_id,fullname,city,country \n" +
                "ORDER BY SUM(amount) DESC\n" +
                "LIMIT 10";
        List<Map<String, Object>> resultMap = DBUtils.getQueryResultMap(query);
        Map<String, Object> map = resultMap.get(count++);
        Assert.assertEquals(map.get("customer_id") + "", customer_id);
        Assert.assertEquals(map.get("fullname"), fullname);
        Assert.assertEquals(map.get("city"), city);
        Assert.assertEquals(map.get("country"), country);
        Assert.assertEquals(map.get("sum") + "", sum);


    }
}
