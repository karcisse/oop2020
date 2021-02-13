package wsb.devices;

import wsb.App;
import wsb.creatures.Human;
import wsb.devices.enums.OperatingSystem;

import java.util.*;

public class Phone extends Device {
    final Double screenSize;
    public List<App> applications;
    private final OperatingSystem operatingSystem;

    public static Phone createIPhone(String model, Double screenSize) {
        return new Phone("Apple", model, screenSize, OperatingSystem.IOS);
    }

    public static Phone createAndroidPhone(String producer, String model, Double screenSize) {
        return new Phone(producer, model, screenSize, OperatingSystem.ANDROID);
    }

    public static Phone createTrashPhone(String producer, String model, Double screenSize) {
        return new Phone(producer, model, screenSize, OperatingSystem.WINDOWS_MOBILE);
    }

    private Phone(String producer, String model, Double screenSize, OperatingSystem operatingSystem) {
        super(producer, model);
        this.screenSize = screenSize;
        this.operatingSystem = operatingSystem;
        applications = new LinkedList<>();
    }

    @Override
    public void turnOn() {
        String osDependantMsg;
        switch (operatingSystem) {
            case IOS:
                osDependantMsg = "How is your wallet now?";
                break;
            case ANDROID:
                osDependantMsg = "Xiaomi is better";
                break;
            case WINDOWS_MOBILE:
                osDependantMsg = "OMG! Why you do this????";
                break;
            default:
                throw new RuntimeException("Is it even a phone??????");
        }
        System.out.println(osDependantMsg);
    }


    public void sell() {
        System.out.println("already sold");
    }

    public void installAnApp(App app, Human owner) throws Exception {
        if (app.getPrice() > 0) {
            if (owner.cash < app.getPrice()) {
                throw new Exception("not enough money");
            }
            owner.cash -= app.getPrice();
        }
        applications.add(app);
        System.out.println("Application " + app.getName() + " installed");
    }

    public void installAnApp(String name) {
        installAnApp(name, 0.0);
    }


    public void installAnApp(String[] apps) {
        for (String app : apps) {
            installAnApp(app);
        }
    }

    public void installAnApp(String name, Double version) {
        System.out.println("The app " + name + " was installed in version " + version);
    }

    public boolean isInstalled(App app) {
        return applications.contains(app);
    }

    public boolean isInstalled(String appName) {
        for (App application : applications) {
            if (application.getName().equals(appName)) {
                return true;
            }
        }
        return false;
    }

}
