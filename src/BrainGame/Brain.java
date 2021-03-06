package BrainGame;

import BrainGame.tools.AlreadyConnectedException;
import BrainGame.tools.NoConnectionException;

import java.util.HashMap;

public class Brain {

    private Neuron[] nodes;

    public Brain(int size) {
        nodes = new Neuron[size];
        initGraph();
    }

    private void initGraph() {
        for (int i = 0; i < getSize(); i++) {
            nodes[i] = new Neuron(i);
        }
    }

    /**
     * @param source     from 1 to N (size)
     * @param distention from 1 to N (size)
     * @param distance   distance of the connection
     * @param time       time takes to travel along the path to that connection
     * @throws AlreadyConnectedException if the same connection is already
     *                                   present, even if other numbers check {@link #editConnection} to edit the
     *                                   values of your connection.
     */
    public void connect(int source, int distention, int distance, int time) throws AlreadyConnectedException {
        if (nodes[source].hasConnection(nodes[distention])) {
            throw new AlreadyConnectedException(source, distention);
        }

        nodes[source].addConnection(nodes[distention], distance, time);
    }

    public void editConnection(int source, int distention, int distance, int time) throws NoConnectionException {
        if (!nodes[source].hasConnection(nodes[distention])) {
            throw new NoConnectionException(source, distention);
        }

        nodes[source].children.replace(nodes[distention], new DistanceTimePair(distance, time));
        nodes[distention].children.replace(nodes[source], new DistanceTimePair(distance, time));
    }

    public boolean checkConnection(int source, int distention) {
        return nodes[source].hasConnection(nodes[distention]);
    }

    public void removeConnection(int source, int distention) throws NoConnectionException {
        if (!checkConnection(source, distention)) {
            throw new NoConnectionException(source, distention);
        }

        nodes[source].removeConnection(nodes[distention]);
    }

    public int getSize() {
        return nodes.length;
    }

    public Neuron getNeuron(int id) {

        return nodes[id];
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public class Neuron {

        public int id;
        public HashMap<Neuron, DistanceTimePair> children = new HashMap<>();

        private Neuron(int id) {
            this.id = id;
        }

        // to make other not able to make a new Neuron outside of Brain.
        private Neuron() {
        }

        private void addConnection(Neuron other, int distance, int time) {
            addConnection(other, distance, time, true);
        }

        private void addConnection(Neuron other, int distance, int time, boolean addToNext) {
            DistanceTimePair pair = new DistanceTimePair(distance, time);
            this.children.put(other, pair);

            if (addToNext) {
                other.addConnection(this, distance, time, false);
            }
        }

        private boolean hasConnection(Neuron neuron) {
            return children.containsKey(neuron);
        }

        private void removeConnection(Neuron other) {
            children.remove(other);
            other.children.remove(this);
        }

        @Override
        public String toString() {
            return "Neuron(" + id + ')';
        }
    }
}
