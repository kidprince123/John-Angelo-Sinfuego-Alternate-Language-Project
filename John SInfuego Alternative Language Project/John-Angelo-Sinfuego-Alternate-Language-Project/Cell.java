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
}