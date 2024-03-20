interface Teever {
    double calculateTime(double distance);
    double calculateCost(double distance);
}

class Niseh implements Teever {
    private double speed;
    private double costPerKm;

    public Niseh(double speed, double costPerKm) {
        this.speed = speed;
        this.costPerKm = costPerKm;
    }

    @Override
    public double calculateTime(double distance) {
        return distance / speed;
    }

    @Override
    public double calculateCost(double distance) {
        return distance * costPerKm;
    }
}


class GaltTereg implements Teever {
    private double speed;
    private double costPerKm;

    public GaltTereg(double speed, double costPerKm) {
        this.speed = speed;
        this.costPerKm = costPerKm;
    }

    @Override
    public double calculateTime(double distance) {
        return distance / speed;
    }

    @Override
    public double calculateCost(double distance) {
        return distance * costPerKm;
    }
}


class Mashin implements Teever {
    private double speed;
    private double costPerKm;

    public Mashin(double speed, double costPerKm) {
        this.speed = speed;
        this.costPerKm = costPerKm;
    }

    @Override
    public double calculateTime(double distance) {
        return distance / speed;
    }

    @Override
    public double calculateCost(double distance) {
        return distance * costPerKm;
    }
}

public class TeevriinTest {
    public static void main(String[] args) {
        double distance = 500;

        Teever Niseh = new Niseh(800, 0.1);
        Teever GaltTereg = new GaltTereg(120, 0.05);
        Teever Mashin = new Mashin(100, 0.08);

        // zartsuulah hugatsaa zardlaa olno
        double NisehTime = Niseh.calculateTime(distance);
        double NisehCost = Niseh.calculateCost(distance);

        double GaltTeregTime = GaltTereg.calculateTime(distance);
        double GaltTeregCost = GaltTereg.calculateCost(distance);

        double MashinTime = Mashin.calculateTime(distance);
        double MashinCost = Mashin.calculateCost(distance);

        System.out.println("Ongots: " + NisehTime + " tsag zartsuulj, Zardal=" + NisehCost + " MNT");
        System.out.println("GaltTereg: " + GaltTeregTime + " tsag zartsuulj, Zardal=" + GaltTeregCost + " MNT");
        System.out.println("Mashin: " + MashinTime + " tsag zartsuulj, Zardal=" + MashinCost + " MNT");
    }
}
