package lowleveldesign;


public class TrafficLightMain {
    public static void main(String[] args) {

    }
}

enum Color {
    RED(30),
    GREEN(25),
    YELLOW(5);

    private final int duration;

    Color(int duration) {
        this.duration = duration;
    }

    public int duration() {
        return duration;
    }

    public Color next() {
        switch (this) {
            case RED:
                return GREEN;
            case GREEN:
                return YELLOW;
            case YELLOW:
                return RED;
            default:
                return RED;
        }
    }
}

class TrafficLight {
    private Color current;

    public TrafficLight(String startColor) {
        Color parsed = Color.RED;

        for (Color color : Color.values()) {
            if (color.name().equalsIgnoreCase(startColor)) {
                parsed = color;
                break;
            }
        }

        this.current = parsed;
    }

    public String getColor() {
        return current.name();
    }

    public int getDuration() {
        return current.duration();
    }

    public String next() {
        current = current.next();
        return current.name();
    }

    public String describe() {
        return current.name() + " (" + current.duration() + "s)";
    }
}


