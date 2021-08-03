/*GUI program to add, view and search properties
Student: Ana Cosic
Student ID: L00161887
Date 03.05.2021.
*/

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class EstateAgentsGUI extends Application
{
   ArrayList<Property> propertyList = new ArrayList<>();
   Alert a = new Alert(AlertType.NONE);
 
   //Make some GUI controls public(visible)
   TextField txtPropertyID;
   TextField txtStreet;
   TextField txtTown;
   TextField txtCounty;
   TextField txtBeds;
   TextField txtReceptions;
   TextField txtBaths;
   TextField txtType;
   TextField txtPrice;
   TextArea txtWindow;
   //second GUI window controls
   TextField txtTerm;
   TextField txtRepayment;
   TextField txtPropertyPrice;
                                    
   public void start(Stage stage)//calling start() method to develop a window
   {
      //Create all labels
      Label lblEnter = new Label("***Enter Property Details***");
      Label lblPropertyID = new Label("Property ID");
      Label lblStreet = new Label("Street");
      Label lblTown = new Label("Town");
      Label lblCounty = new Label("County");
      Label lblBeds = new Label("Beds");
      Label lblReceptions = new Label("Receptions");
      Label lblBaths = new Label("Baths");
      Label lblType = new Label("Type");
      Label lblPrice = new Label("Price");
      Label lblSearch = new Label("***Property Search***");
      
      //Create all TextFields and give them size
      txtPropertyID = new TextField();
      txtPropertyID.setMaxWidth(100);
      txtStreet = new TextField();
      txtStreet.setMaxWidth(150);
      txtTown = new TextField();
      txtTown.setMaxWidth(150);
      txtCounty = new TextField();
      txtCounty.setMaxWidth(150);
      txtBeds = new TextField();
      txtBeds.setMaxWidth(50);
      txtReceptions = new TextField();
      txtReceptions.setMaxWidth(50);
      txtBaths = new TextField();
      txtBaths.setMaxWidth(50);
      txtType = new TextField();
      txtType.setMaxWidth(100);
      txtPrice = new TextField();
      txtPrice.setMaxWidth(100);

      //Create Buttons + set Lambda expressions for each button
      Button btnAddProperty = new Button("Add Property");
      btnAddProperty.setOnAction(e -> addProperty(e));  
      Button btnViewProperties = new Button("View All Properties");
      btnViewProperties.setOnAction(e -> viewProperties(e));    
      Button btnSearchType = new Button("Search by Type");
      btnSearchType.setOnAction(e -> searchType(e));  
      Button btnSearchNoBedrooms = new Button("Search by Number of Bedrooms");
      btnSearchNoBedrooms.setOnAction(e -> searchBedrooms(e));
      Button btnSearchPriceRange = new Button("Search by Price Range");
      btnSearchPriceRange.setOnAction(e -> searchPrice(e));
      Button btnDeleteProperty = new Button("Delete Property");
      btnDeleteProperty.setOnAction(e -> deleteProperty(e));
      Button btnMortgageCalculator = new Button("Mortgage Calculator");
           
      txtWindow = new TextArea();
      txtWindow.setMaxSize(630,480);
      txtWindow.setEditable(false);
      
      //HBOx
      HBox propID = new HBox(8);
      propID.getChildren().addAll(lblPropertyID, txtPropertyID);
      propID.setAlignment(Pos.CENTER);
      
      //HBox
      HBox input1 = new HBox(8);
      input1.getChildren().addAll(lblStreet, txtStreet, lblTown, txtTown, lblCounty, txtCounty);
      input1.setAlignment(Pos.CENTER);
      
      //HBox
      HBox input2 = new HBox(8);
      input2.getChildren().addAll(lblBeds, txtBeds, lblReceptions, txtReceptions, lblBaths, txtBaths, lblType, txtType, lblPrice, txtPrice);
      input2.setAlignment(Pos.CENTER);
      
      //HBox
      HBox buttons = new HBox(30);  //spacing between each controls in this HBox
      buttons.getChildren().addAll(btnViewProperties, btnSearchType, btnSearchNoBedrooms, btnSearchPriceRange);
      buttons.setAlignment(Pos.CENTER);
      
      //HBox
      HBox functions = new HBox(30);
      functions.getChildren().addAll(btnDeleteProperty, btnMortgageCalculator);
      functions.setAlignment(Pos.CENTER);
      
      //Create VBox and add all HBoxes to it + TextArea
      VBox root = new VBox(8);
      root.getChildren().addAll(lblEnter, propID, input1, input2, btnAddProperty, lblSearch, buttons, txtWindow, functions);
      root.setAlignment(Pos.CENTER);
      
      //Create Scene and add VBox to the scene
      Scene scene = new Scene(root, 800, 600);
      stage.setScene(scene);
      stage.setTitle("Estate Agents");
      stage.show();  
      
      
      //Creating controls for separate scene (when "Calculate mortgage" button is clicked)
      Label lblPropertyPrice = new Label("Insert Property Price");
      Label lblTerm = new Label("Insert Repayment In Years");
      txtPropertyPrice = new TextField();
      txtPropertyPrice.setMaxWidth(100);
      txtTerm = new TextField();
      txtTerm.setMaxWidth(100);
      Button btnRepayment = new Button("Calculate Mortgage");
      btnRepayment.setOnAction(e -> mortgageRepayment(e));
      Button btnReturn = new Button("Return to the main window");
      btnReturn.setStyle("-fx-background-color: #ADD8E6; "); //looked online how to style with color, hope its ok
      btnReturn.setOnAction(e -> stage.setScene(scene));
      
      HBox inp = new HBox(8);
      inp.getChildren().addAll(lblPropertyPrice, txtPropertyPrice);
      inp.setAlignment(Pos.CENTER);
      
      HBox inpt = new HBox(8);
      inpt.getChildren().addAll(lblTerm, txtTerm);
      inpt.setAlignment(Pos.CENTER);
      
      HBox repayment = new HBox(8);
      repayment.getChildren().addAll(btnRepayment);
      repayment.setAlignment(Pos.CENTER);

      VBox root2 = new VBox(15);
      root2.getChildren().addAll(inp, inpt, repayment, btnReturn);
      root2.setAlignment(Pos.CENTER);
      //Create scene and add root2 to the scene
      Scene scene2 = new Scene(root2, 500, 300);
      btnMortgageCalculator.setOnAction(e -> stage.setScene(scene2));
      //Lambda expression for mortgage calculator button that will open separete(second) scene when clicked
      
   }//close start method
   
   
   //Method to add properties
   public void addProperty(ActionEvent e)
   {
      try
      {
         //Check if TextFields are empty
         if(txtPropertyID.getText().isEmpty() || txtStreet.getText().isEmpty() || txtTown.getText().isEmpty() || txtCounty.getText().isEmpty() || txtBeds.getText().isEmpty() || txtReceptions.getText().isEmpty() || txtBaths.getText().isEmpty() || txtType.getText().isEmpty() || txtPrice.getText().isEmpty())
         {
            txtWindow.setText("You must enter all property values");
         }
         else   //get the values from all TextFields
         { 
            int id = Integer.parseInt(txtPropertyID.getText());
            String street = txtStreet.getText();
            String town = txtTown.getText();
            String county = txtCounty.getText();
            int beds = Integer.parseInt(txtBeds.getText());
            int receptions = Integer.parseInt(txtReceptions.getText());
            int baths = Integer.parseInt(txtBaths.getText());
            String type = txtType.getText();
            double price = Double.parseDouble(txtPrice.getText());
            //Create object and add it to ArrayList propertyList
            propertyList.add(new Property(id, street, town, county, beds, receptions, baths, type, price));     
            
            a.setAlertType(AlertType.INFORMATION);//message window
            a.setContentText("Property Successfully Added");
            a.show();
            
            //Clear the TextFields after employee is added
            txtPropertyID.clear();
            txtStreet.clear();
            txtTown.clear();
            txtCounty.clear();
            txtBeds.clear();
            txtReceptions.clear();
            txtBaths.clear();
            txtType.clear();
            txtPrice.clear();
         }//close else
      }//close try
      
      catch(IllegalArgumentException ex)//Output message if wrong input in the code above
      {
         a.setAlertType(AlertType.INFORMATION);
         a.setContentText("Property ID, number of beds/receptions/baths and price \ncan not be negative numbers or letters.\nPlease check your input.");
         a.show();
      }      
   }//Close method
   
   
   
   //Method to view all properties
   public void viewProperties(ActionEvent e)
   {
      txtWindow.clear();
      
      for(Property p: propertyList)   
      {                                     
         txtWindow.appendText(p +"\n");  
      }                                                                                                              
   }//close method 
   
   
   
   //Method to search by property's type
   public void searchType(ActionEvent e)
   {
      txtWindow.clear(); //Clear TextArea of any previous output
      String searchType = txtType.getText(); //get property type the from user
      
      //search for every element in the ArrayList for possible match of property type
      boolean isFound = false;
      for(int i = 0; i < propertyList.size(); i++)//to get the position of each element
      {
         //checking if any of the values entered matches property type
         if(searchType.equalsIgnoreCase(propertyList.get(i).type()))
         {   
            txtWindow.appendText(propertyList.get(i).toString()+"\n");
            isFound = true; //Change variable isFound from false to true
         }
      }//Close for loop
      if(isFound == false) //if the variable isFound is still False(no match found) then output message to the user
      {
         txtWindow.setText("No property found matching that type");
      }
      txtType.clear();
  }//Close method
  
  
  
  //Method to search by number od bedrooms
   public void searchBedrooms(ActionEvent e)
   {
      txtWindow.clear();
      int searchBedrooms = Integer.parseInt(txtBeds.getText()); //get number of beds the from the user
      
      //search for every element in the ArrayList for possible match
      boolean isFound = false;
      for(int i = 0; i < propertyList.size(); i++)
      {
         //checking if any values entered matches
         if(propertyList.get(i).bed() == searchBedrooms)
         {   
            txtWindow.appendText(propertyList.get(i).toString()+"\n");
            isFound = true;
         }
      }//Close for loop
      
      if(isFound == false) //if no match found - output message to the user
      {
         txtWindow.setText("No property found with " + txtBeds.getText() + " bedrooms");
      }
      txtBeds.clear();
  }//Close method
  
  
  //Method to search by price
   public void searchPrice(ActionEvent e)
   {
      txtWindow.clear();
      //Get price for property from the user
      double propertyPrice = Double.parseDouble(txtPrice.getText()); 
      
      //search for every element in the ArrayList for possible match
      boolean isFound = false;
      for(int i = 0; i < propertyList.size(); i++)
      {
         //checking if any of the values entered matches property price
         if(propertyList.get(i).viewPrice() <= propertyPrice)
         {   
            txtWindow.appendText(propertyList.get(i).toString()+"\n");
            isFound = true;
         }
      }//Close for loop
      if(isFound == false) //Output message to the user if no matches
      {
         txtWindow.setText("No property found for €" + txtPrice.getText());
      }  
   }//Close method
   
   

   //Method to delete property
   public void deleteProperty(ActionEvent e)
   {
      txtWindow.clear();
      //Get user to enter propertyID they wish to delete
      int propertyID = Integer.parseInt(txtPropertyID.getText());
      
      //Check entire ArrayList to see if inserted ID exists
      for(int i = 0; i < propertyList.size(); i++)
      {
         if(propertyID == propertyList.get(i).getId())
         {
            //Remove that property from the arrayList (at that position)
            propertyList.remove(i);
            txtWindow.setText("Property successfully deleted");
         }
      }
      //Clear property ID text field
      txtPropertyID.clear();
   }
      
      
   //Method to calculate mortgage for the property
   public void mortgageRepayment(ActionEvent e)
   {
      txtWindow.clear();
      //Get user to enter property price and term length
      double propertyPrice = Double.parseDouble(txtPropertyPrice.getText());
      if (propertyPrice <= 0)//check for positive input for price
      {
         a.setAlertType(AlertType.INFORMATION);
         a.setContentText("Error - Property price has to be positive number");
         a.show();
      }
      else
      {
         int termYears = Integer.parseInt(txtTerm.getText());
         double monthly, repayment;
         int months = termYears * 12;
         monthly = (propertyPrice / months);
         repayment = monthly + monthly * 0.03;

         a.setAlertType(AlertType.INFORMATION);
         a.setContentText("Monthly repayment over " +termYears+ " years is € " + repayment);
         a.show();
      }
   }

 
      public static void main(String[]args)
   {
      launch(args);
   }
}
      