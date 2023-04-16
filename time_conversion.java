//package java_classes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class time_conversion 
{
    private static final Map<String, String> ct = new HashMap<>(); // Map to store country-time zone mapping

    static 
    {
        
        ct.put("UNITED STATES", "US/Pacific"); 
        ct.put("UNITED KINGDOM", "Europe/London"); 
        ct.put("CANADA", "Canada/Pacific");
        ct.put("AUSTRALIA", "Australia/Sydney");
        ct.put("CHINA", "Asia/Shanghai");
        ct.put("INDIA", "Asia/Kolkata");
        ct.put("ANTARCTICA", "Dumont-d'Urville Station");
    }

    public static void main(String[] args) 
    {
        Scanner s = new Scanner(System.in);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime lti = LocalDateTime.now();  
        System.out.println("Your current date and time is :"+dtf.format(lti));  
        
        System.out.println("Enter country for time conversion: ");
        System.out.println("UNITED STATES");
        System.out.println("UNITED KINGDOM");
        System.out.println("CANADA");
        System.out.println("AUSTRALIA");
        System.out.println("CHINA");
        System.out.println("INDIA");
        System.out.println("ANTARCTICA");
        String country = s.nextLine();

        try 
        {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime lt = LocalDateTime.parse(dtf.format(lti), f);
            ZoneId lzid = ZoneId.systemDefault();
            ZoneId czid = ZoneId.of(getZoneIdForCountry(country));

            ZonedDateTime localZonedDateTime = ZonedDateTime.of(lt, lzid);
            ZonedDateTime countryZonedDateTime = localZonedDateTime.withZoneSameInstant(czid);

            String countryTime = f.format(countryZonedDateTime);
            System.out.println("Time in " + country + ": " + countryTime);
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println("Error: " + e.getMessage());
        } 
        catch (Exception e) 
        {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        }

        s.close();
    }

    public static String getZoneIdForCountry(String country) 
    {
        String timeZone = ct.get(country.toUpperCase());
        if (timeZone == null) 
        {
            throw new IllegalArgumentException("Invalid country input");
        }
        return timeZone;
    }
}