package edu.uga.cs4370.logic;

import java.io.*;
import java.sql.SQLException;
import java.util.*;


import edu.uga.cs4370.objects.Product;

public class DatabaseInitialization {

	
	public static void initialize() throws SQLException{
		
	
		initializeProducts();
		initializeBakeries();
		initializeMenus();
		initializeProductAllergies();
		
		
	}
	
	
	private static void initializeProducts(){
		
		//HashMap<Integer, ArrayList<String>> map = getProductAllergies();
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		String filePath = "resources/products.csv";
		
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			Product p = new Product();
			int count = 0;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] split = line.split(",");

                if (count == 0){
                	count ++;
                	continue;
                }
                
                try{
                	p = new Product();
                	p.setId(Integer.parseInt(split[0]));
                	p.setType(split[1]);
                	p.setName(split[2]);
                	p.setPrice(Double.parseDouble(split[3]));
                	p.setDescription(split[4]);
                	p.setSRC(split[5]);

                	list.add(p);
                	System.out.println("added " + p.getName());
                	
                } catch (Exception e){
                	//e.printStackTrace();
                	continue;
                }

                
            }
            Database.addProducts(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
		
		//initializeProductAllergies();

	}
	
	private static void initializeBakeries(){

		String filePath = "resources/bakeries.csv";

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {


			int count = 0;

			while ((line = br.readLine()) != null) {

				if (count == 0){
					count ++;
					continue;
				}
				// use comma as separator
				
				String[] split = line.split(",");
				
				Database.addBakery(Integer.parseInt(split[0]), split[1], split[2]);
				//System.out.println(Arrays.toString(split));
				
				count ++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private static void initializeMenus(){
		
		ArrayList<Integer> menuIds = new ArrayList<Integer>();
		ArrayList<Integer> productIds = new ArrayList<Integer>();
		
		String filePath = "resources/menus.csv";
		
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {


			int count = 0;

			while ((line = br.readLine()) != null) {

				if (count == 0){
					count ++;
					continue;
				}
				// use comma as separator
				
				String[] split = line.split(",");
				
				menuIds.add(Integer.parseInt(split[0]));
				productIds.add(Integer.parseInt(split[1]));
				
				//System.out.println(Arrays.toString(split));
				
				count ++;
			}

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Database.addMenuItem(menuIds, productIds);
	}
	
	private static void initializeProductAllergies(){
		
		//HashMap<Integer, ArrayList<String>> map = new HashMap <Integer, ArrayList<String>>();
		ArrayList<Integer> productIds = new ArrayList<Integer>();
		ArrayList<String> allergies = new ArrayList<String>();
		
		String filePath = "resources/product_allergy.csv";
		
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {


			int count = 0;

			while ((line = br.readLine()) != null) {

				//ArrayList<String> allergies = new ArrayList<String>();
				
				if (count == 0){
					count ++;
					continue;
				}

				
				String[] split = line.split(",");
				productIds.add(Integer.parseInt(split[0]));
				allergies.add(split[1]);

				//Database.addMenuItem(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
				//Database.addProductAllergy(Integer.parseInt(split[0]), split[1]);
				
				count ++;
			}//while

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Database.addProductAllergy(productIds, allergies);

	}
}
