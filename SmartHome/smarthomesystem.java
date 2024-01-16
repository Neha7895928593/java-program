package SmartHome;

import java.util.ArrayList;
import java.util.List;

interface UserPreference {
    void applyUserPreference(String user);

    void displayUserPreference();
}

interface SmartHomeSystem extends UserPreference {
    void turnOn();

    void turnOff();

    void control(boolean turnOn);
}

abstract class Devices implements SmartHomeSystem {
    private String name;
    private boolean isOn;

    public Devices(String name) {
        this.name = name;
        this.isOn = false;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println(name + " is on");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println(name + " is Off");
    }

    @Override
    public void control(boolean turnOn) {
        if (turnOn) {
            turnOn();
        } else {
            turnOff();
        }
    }
}

class Light extends Devices {
    private int brightness;

    public Light(String name) {
        super(name);
        this.brightness = 50;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        System.out.println(getName() + " brightness set to " + brightness);
    }

    @Override
    public void applyUserPreference(String user) {
        setBrightness(80);
        System.out.println(user + "'s brightness preference applied to " + getName());
    }

    @Override
    public void displayUserPreference() {
        System.out.println("Current brightness setting for " + getName() + ": " + brightness);
    }
}

class Appliance extends Devices {
    private String mode;

    public Appliance(String name) {
        super(name);
        this.mode = "Normal";
    }

    public void setMode(String mode) {
        this.mode = mode;
        System.out.println(getName() + " mode set to " + mode);
    }

    @Override
    public void applyUserPreference(String user) {
        setMode("Eco");
        System.out.println(user + "'s mode preference applied to " + getName());
    }

    @Override
    public void displayUserPreference() {
        System.out.println("Current mode setting for " + getName() + ": " + mode);
    }
}

class Room {
    private String name;
    private List<SmartHomeSystem> devices;

    public Room(String name) {
        this.name = name;
        this.devices = new ArrayList<SmartHomeSystem>();
    }

    public String getName() {
        return name;
    }

    void addDevices(SmartHomeSystem device) {
        devices.add(device);
    }

    void controlDevices(boolean turnOn) {
        System.out.println("Controlling devices in: " + name);
        for (SmartHomeSystem device : devices) {
            device.control(turnOn);
        }
    }

    public List<SmartHomeSystem> getDevices() {
        return devices;
    }
}
class SmartThermostat extends Devices {
    private int temperature;

    public SmartThermostat(String name) {
        super(name);
        this.temperature = 22;
    }

    public void setTemperature(int temperatureChange) {
        this.temperature += temperatureChange;
        System.out.println(getName() + " temperature set to " + temperature + "°C.");
    }

    @Override
    public void applyUserPreference(String userName) {
        setTemperature(2); 

        System.out.println(userName + "'s temperature preference applied to " + getName());
    }

    @Override
    public void displayUserPreference() {
        System.out.println("Current temperature setting for " + getName() + ": " + temperature + "°C");
    }
}



    
class NotificationSystem {
    public void sendNotification(String message) {
        System.out.println("Notification: " + message);
    }
}

class UserProfile {
    private String name;

    public UserProfile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class SmartHome {
    private List<UserProfile> users;
    private List<Room> rooms;
    private NotificationSystem notificationSystem;

    public SmartHome() {
        this.users = new ArrayList<UserProfile>();
        this.rooms = new ArrayList<Room>();
        this.notificationSystem = new NotificationSystem();
    }

    public void addUser(UserProfile user) {
        users.add(user);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void manageEnergyConsumption() {
        System.out.println("Managing energy consumption in the smart home.");
        activateEnergySavingMode();
        notificationSystem.sendNotification("Energy-saving mode activated.");
    }

    private void activateEnergySavingMode() {
        for (Room room : rooms) {
            for (SmartHomeSystem device : room.getDevices()) {
                if (device instanceof Light || device instanceof Appliance) {
                    device.turnOff();
                }
            }
        }
    }

    public void welcomeUsers() {
        for (UserProfile user : users) {
            System.out.println("Welcome, " + user.getName() + "!");
        }
    }
}

 class Main {
    public static void main(String[] args) {
        Light livingRoomLight = new Light("Living Room Light");
        Appliance coffeeMachine = new Appliance("Coffee Machine");
        SmartThermostat smartThermostat = new SmartThermostat("Smart Thermostat");
        
        

        Room livingRoom = new Room("Living Room");
        livingRoom.addDevices(livingRoomLight);
        livingRoom.addDevices(coffeeMachine);
        livingRoom.addDevices(smartThermostat);

        UserProfile Neha= new UserProfile("Neha");

        SmartHome smartHome = new SmartHome();
        smartHome.addUser(Neha);
        smartHome.addRoom(livingRoom);

        smartHome.welcomeUsers();
        smartHome.manageEnergyConsumption();

        livingRoom.controlDevices(false);
       
        
        
        smartThermostat.applyUserPreference("neha");
        smartThermostat.displayUserPreference();
        
    }
}
