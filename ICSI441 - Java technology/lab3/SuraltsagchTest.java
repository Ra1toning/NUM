abstract class Suraltsagch {
    public String name;

    public Suraltsagch(String name) {
        this.name = name;
    }

    public abstract void suraltsagchid();

    public String getName() {
        return name;
    }
}
class Suragch extends Suraltsagch {
    public Suragch(String name) {
        super(name);
    }

    @Override
    public void suraltsagchid() {
        System.out.println(name + "-chn suragch ymaa.");
    }
}
class Oyutan extends Suraltsagch {
    public Oyutan(String name) {
        super(name);
    }

    @Override
    public void suraltsagchid() {
        System.out.println(name + "-chn oyutanshdee.");
    }
}

public class SuraltsagchTest {
    public static void main(String[] args) {
        Suraltsagch[] suraltsagch = new Suraltsagch[4];
        suraltsagch[0] = new Suragch("Bold");
        suraltsagch[1] = new Suragch("Javkhlan");
        suraltsagch[2] = new Oyutan("Sengee");
        suraltsagch[3] = new Oyutan("Baldan");

        System.out.println("Suragchid:");
        for (Suraltsagch learner : suraltsagch) {
            if (learner instanceof Suragch) {
                learner.suraltsagchid();
            }
        }

        System.out.println("\nOyutnuud:");
        for (Suraltsagch learner : suraltsagch) {
            if (learner instanceof Oyutan) {
                learner.suraltsagchid();
            }
        }
    }
}