import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Cell{
    private String oem;
    private String model;
    private Integer launchAnnounced;
    private String launchStatus;
    private String bodyDimensions;
    private Float bodyWeight;
    private String bodySim;
    private String displayType;
    private Float displaySize;
    private String displayResolution;
    private String featuresSensors;
    private String platformOs;

    // Constructors
    public Cell() {
        // Default constructor
    }

    public Cell(String oem, String model, Integer launchAnnounced, String launchStatus, String bodyDimensions, Float bodyWeight, String bodySim, String displayType, Float displaySize, String displayResolution, String featuresSensors, String platformOs) {
        this.oem = oem;
        this.model = model;
        this.launchAnnounced = launchAnnounced;
        this.launchStatus = launchStatus;
        this.bodyDimensions = bodyDimensions;
        this.bodyWeight = bodyWeight;
        this.bodySim = bodySim;
        this.displayType = displayType;
        this.displaySize = displaySize;
        this.displayResolution = displayResolution;
        this.featuresSensors = featuresSensors;
        this.platformOs = platformOs;
    }

    // Getter and Setter methods for each attribute
    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getLaunchAnnounced() {
        return launchAnnounced;
    }

    public void setLaunchAnnounced(Integer launchAnnounced) {
        this.launchAnnounced = launchAnnounced;
    }

    public String getLaunchStatus() {
        return launchStatus;
    }

    public void setLaunchStatus(String launchStatus) {
        this.launchStatus = launchStatus;
    }

    public String getBodyDimensions() {
        return bodyDimensions;
    }

    public void setBodyDimensions(String bodyDimensions) {
        this.bodyDimensions = bodyDimensions;
    }

    public Float getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(Float bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public String getBodySim() {
        return bodySim;
    }

    public void setBodySim(String bodySim) {
        this.bodySim = bodySim;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public Float getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(Float displaySize) {
        this.displaySize = displaySize;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public void setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
    }

    public String getFeaturesSensors() {
        return featuresSensors;
    }

    public void setFeaturesSensors(String featuresSensors) {
        this.featuresSensors = featuresSensors;
    }

    public String getPlatformOs() {
        return platformOs;
    }

    public void setPlatformOs(String platformOs) {
        this.platformOs = platformOs;
    }

    // Override the toString() method
    @Override
    public String toString() {
        return "Cell{" +
                "oem='" + oem + '\'' +
                ", model='" + model + '\'' +
                ", launchAnnounced=" + launchAnnounced +
                ", launchStatus='" + launchStatus + '\'' +
                ", bodyDimensions='" + bodyDimensions + '\'' +
                ", bodyWeight=" + bodyWeight +
                ", bodySim='" + bodySim + '\'' +
                ", displayType='" + displayType + '\'' +
                ", displaySize=" + displaySize +
                ", displayResolution='" + displayResolution + '\'' +
                ", featuresSensors='" + featuresSensors + '\'' +
                ", platformOs='" + platformOs + '\'' +
                '}';
    }

        // Method to read data from CSV file, clean it, and store in a HashMap
        public static HashMap<Integer, Cell> readCSV(String filename) {
            HashMap<Integer, Cell> cellMap = new HashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
    
                // Skip the header line
                br.readLine();
    
                int index = 0;
                while ((line = br.readLine()) != null) {
                    // Split the CSV line by comma and include empty values
                    String[] data = line.split(",", -1); 
                    // Clean each data element and create a Cell object
                    if (data.length >= 12) { // Make sure there are at least 12 columns
                        Cell cell = cleanData(data);
                        // Store Cell object in HashMap
                        cellMap.put(index++, cell);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return cellMap;
        }

        // Method to clean data and create a Cell object
    private static Cell cleanData(String[] data) {
        String oem = data.length > 0 && !data[0].equals("-") ? data[0] : null;
        String model = data.length > 1 && !data[1].equals("-") ? data[1] : null;
        Integer launchAnnounced = null;
        if (data[2].matches("\\d{4}")) {
            launchAnnounced = Integer.parseInt(data[2]);
        }
        String launchStatus = data[3];
        String bodyDimensions = data[4].equals("-") ? null : data[4];
        Float bodyWeight = null;
        String weightStr = data[5].split(" ")[0]; // This splits the weight by space and takes the first part
        if (weightStr.matches("\\d+")) {
        bodyWeight = Float.parseFloat(weightStr);
}
        String bodySim = data[6].equals("No") ? null : data[6];
        String displayType = data[7].equals("-") ? null : data[7];
        Float displaySize = null;
        if (data[8].matches("\\d+(\\.\\d+)?")) {
            displaySize = Float.parseFloat(data[8]);
        }
        String displayResolution = data[9].equals("-") ? null : data[9];
        String featuresSensors = data[10].equals("V1") ? "V1" : null; // Assuming "V1" is a valid sensor
        String platformOs = data.length > 11 && !data[11].isEmpty() ? data[11].split(",")[0].trim() : null;
        return new Cell(oem, model, launchAnnounced, launchStatus, bodyDimensions, bodyWeight, bodySim, displayType, displaySize, displayResolution, featuresSensors, platformOs);
    }

    // Method to find the company with the highest average weight of the phone body
    public static String findHighestAvgBodyWeightOEM(HashMap<Integer, Cell> cellMap) {
        HashMap<String, Float> totalWeightMap = new HashMap<>();
        HashMap<String, Integer> countMap = new HashMap<>();
        for (Cell cell : cellMap.values()) {
            if (cell.getBodyWeight() != null) {
                String company = cell.getOem();
                Float weight = cell.getBodyWeight();
                if (totalWeightMap.containsKey(company)) {
                    totalWeightMap.put(company, totalWeightMap.get(company) + weight);
                    countMap.put(company, countMap.get(company) + 1);
                } else {
                    totalWeightMap.put(company, weight);
                    countMap.put(company, 1);
                }
            }
        }
        float maxAvg = 0;
        String maxCompany = null;
        for (String company : totalWeightMap.keySet()) {
            float avgWeight = totalWeightMap.get(company) / countMap.get(company);
            if (avgWeight > maxAvg) {
                maxAvg = avgWeight;
                maxCompany = company;
            }
        }
        return maxCompany;
    }

        // Method to find phones announced in one year and released in another
public static ArrayList<String> findPhonesAnnouncedReleasedDiffYears(HashMap<Integer, Cell> cellMap) {
    ArrayList<String> mismatchedPhones = new ArrayList<>();
    for (Cell cell : cellMap.values()) {
        if (cell.getLaunchAnnounced() != null && !cell.getLaunchStatus().contains(cell.getLaunchAnnounced().toString())) {
            mismatchedPhones.add(cell.getOem() + " " + cell.getModel());
        }
    }
    return mismatchedPhones;
}

     // Method to count phones with only one feature sensor
     public static int countPhonesWithOneSensor(HashMap<Integer, Cell> cellMap) {
        int count = 0;
        for (Cell cell : cellMap.values()) {
            if (cell.getFeaturesSensors() != null && cell.getFeaturesSensors().equals("V1")) {
                count++;
            }
        }
        return count;
    }

    // Method to find the year with the most phones launched after 1999
    public static int findYearWithMostPhones(HashMap<Integer, Cell> cellMap) {
        HashMap<Integer, Integer> yearCountMap = new HashMap<>();
        for (Cell cell : cellMap.values()) {
            if (cell.getLaunchAnnounced() != null && cell.getLaunchAnnounced() > 1999) {
                int year = cell.getLaunchAnnounced();
                yearCountMap.put(year, yearCountMap.getOrDefault(year, 0) + 1);
            }
        }
        int maxCount = 0;
        int maxYear = 0;
        for (int year : yearCountMap.keySet()) {
            if (yearCountMap.get(year) > maxCount) {
                maxCount = yearCountMap.get(year);
                maxYear = year;
            }
        }
        return maxYear;
    }
}