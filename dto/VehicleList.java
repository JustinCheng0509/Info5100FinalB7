package dto;

import java.util.*;
import java.io.*;

class VehicleList {
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	public VehicleList() {}

	public VehicleList(Dealer dealer) {
		File file = new File(dealer.getName() + ".txt");
		FileReader fileReader = null;
		try {
			if (file.exists()) {
			fileReader = new FileReader(file);
			}
			else {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(fileReader);
		String line = null;
		try {
			while ((line = reader.readLine())!= null) {
				Vehicle v = new Vehicle();
				String[] vFeatures = new String[11];
				vFeatures = line.split("~");
				v.setId(vFeatures[0]);
				v.setDealerId(vFeatures[1]);
				v.setCategory(vFeatures[2]);
				v.setYear(vFeatures[3]);
				v.setMake(vFeatures[4]);
				v.setModel(vFeatures[5]);
				v.setTrim(vFeatures[6]+vFeatures[7]);
				v.setType(vFeatures[8]);
				v.setPrice(vFeatures[9]);
				v.setImages(vFeatures[10]);
				vehicles.add(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public List<Vehicle> getVehicleList() {
		return vehicles;
	}

	public int getVehicleNo() {
		return vehicles.size();
	}

	public double[] getPriceRange() {
		double[] range = new double[2];
		double max = 0.0;
		double min = Double.MAX_VALUE;
		double price;
		for (Vehicle v : vehicles) {
			price = Double.parseDouble(v.getPrice());
			if (price > max) {
				max = price;
			}

			if (price < min) {
				min = price;
			}
		}
		range[0] = min;
		range[1] = max;
		return range;
	}

	public int[] getYearRange() {
		int[] range = new int[2];
		int max = 0;
		int min = Integer.MAX_VALUE;
		int year;
		for (Vehicle v : vehicles) {
			year = Integer.parseInt(v.getYear());
			if (year > max) {
				max = year;
			}

			if (year < min) {
				min = year;
			}
		}
		range[0] = min;
		range[1] = max;
		return range;
	}

	
}
