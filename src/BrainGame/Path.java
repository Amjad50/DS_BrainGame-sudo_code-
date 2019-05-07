package BrainGame;

import java.util.LinkedList;

public class Path {

    private LinkedList<Neuron> path = new LinkedList<>();
    private int Distance, Time;

    public Path() {
        this.Distance = 0;
        this.Time = 0;
    }

    public Path(int Distance, int Time) {
        this.Distance = Distance;
        this.Time = Time;
    }

    public void addFirst(Neuron e) {
        path.addFirst(e);
    }

    public void addTime(int Time) {
        this.Time = Time;
    }

    public void addDistance(int Distance) {
        this.Distance = Distance;
    }

    public LinkedList<Neuron> getPath() {
        return path;
    }

    public void setPath(LinkedList<Neuron> path) {
        this.path = path;
    }

    public int getDistance() {
        return Distance;
    }

    public void setDistance(int Distance) {
        this.Distance = Distance;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int Time) {
        this.Time = Time;
    }

    public Neuron getFirst() {
        return path.getFirst();
    }

    public Neuron getLast() {
        return path.getLast();
    }

    @Override
    public String toString() {
        return "Path{" +
                "path=" + path +
                ", Distance=" + Distance +
                ", Time=" + Time +
                '}';
    }
}
