package com.keyin.http.cli;
import java.util.Scanner;

import com.keyin.http.client.RESTClient;


public class HTTPRestCLIApplication {

    private RESTClient restClient;

    // Methods to handle specific actions

    private void giveMeCityIdIReturnAllItsAirports(int cityId) {
        restClient.giveMeCityIdIReturnAllItsAirports(cityId);
    }

    private void giveMePassengerIdIReturnAllAircraftsTheyTravelledOn(int passengerID) {
                restClient.giveMePassengerIdIReturnAllAircraftsTheyTravelledOn(passengerID);
        // restClient.giveMePassengerIdIReturnAllAircraftsTheyTravelledOn(passengerID);
    }

    private void giveMeAircraftIdIReturnAllAirportsItCanUse(){
        System.out.println("Logic not added Yet");
        }

    private void giveMePassengerIdIReturnAllAirportsTheyUsed(){
        System.out.println("Logic not added Yet");
        }
        
    private void exitApplication() {
        System.out.println("Exiting application...");
        System.exit(0);
    }

    //     public void insertSampleDataToDatabase(){
    //     this.restClient.insertSampleData();
    // }

    public RESTClient getRestClient() {
        if (restClient == null) {
            restClient = new RESTClient();
        }
        return restClient;
    }

    // Setter for RESTClient
    public void setRestClient(RESTClient restClient) {
        this.restClient = restClient;
    }

    public static void main(String[] args) {
        HTTPRestCLIApplication cliApp = new HTTPRestCLIApplication();
        String serverURL = "http://localhost:8080";  

 

        if (serverURL != null && !serverURL.isEmpty()) {
            RESTClient restClient = new RESTClient();
            restClient.setServerURL(serverURL);
            cliApp.setRestClient(restClient);
        }

        cliApp.restClient.InsertSampleData();

        Scanner scanner = new Scanner(System.in);

        while (true) {


            // Display menu options
            System.out.println("Please choose an option:");
            System.out.println("1. What airports are in what cities?");
            System.out.println("2. List all aircraft passengers have travelled on?");
            System.out.println("3. Which airports can aircraft take off from and land at?");
            System.out.println("4. What airports have passengers used?");
            System.out.println("5. Exit");

            // Read user input
            int choice = scanner.nextInt();

            // Handle user input using switch statement
            switch (choice) {
                case 1:
                    System.out.println("Type The City Id: ");
                    int cityID = scanner.nextInt();
                    System.out.println("");
                    System.out.println("--------------------------------");
                    cliApp.giveMeCityIdIReturnAllItsAirports(cityID); 
                    System.out.println("--------------------------------");
                    System.out.println("");
                    break;

                case 2:
                    System.out.println("Type The Passenger Id: ");
                    int passengerID = scanner.nextInt();
                    System.out.println("");
                    System.out.println("----------------------------------------");
                    cliApp.giveMePassengerIdIReturnAllAircraftsTheyTravelledOn(passengerID); 
                    System.out.println("-----------------------------------------");
                    break;

                case 3:
                 System.out.println("Type The Aircraft Id: ");
                    int aircraftID = scanner.nextInt();
                    System.out.println("");
                    System.out.println("----------------------------------------");
                    cliApp.giveMeAircraftIdIReturnAllAirportsItCanUse();  
                    System.out.println("-----------------------------------------");

                    break;
                case 4:
                    System.out.println("Type The Passenger Id: ");
                    int passengerId = scanner.nextInt();
                    System.out.println("");
                    System.out.println("----------------------------------------");
                    cliApp.giveMePassengerIdIReturnAllAirportsTheyUsed();  
                    System.out.println("-----------------------------------------");

                    break;
                case 5:
                    cliApp.exitApplication();  
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
