package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hcadavid
 */
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<Tuple<String, String>, Blueprint> blueprints = new HashMap<>();

    public InMemoryBlueprintPersistence() {

        Point[] ptsYhonatan = new Point[]{new Point(10, 10), new Point(20, 20)};
        Blueprint bpYhonatan = new Blueprint("Yhonatan", "bpYhonatan", ptsYhonatan);
        blueprints.put(new Tuple<>(bpYhonatan.getAuthor(), bpYhonatan.getName()), bpYhonatan);

        Point[] ptsAlejandro1 = new Point[]{new Point(30, 30), new Point(40, 40)};
        Blueprint bpAlejandro1 = new Blueprint("Alejandro", "bpAlejandro1", ptsAlejandro1);
        blueprints.put(new Tuple<>(bpAlejandro1.getAuthor(), bpAlejandro1.getName()), bpAlejandro1);

        Point[] ptsAlejandro2 = new Point[]{new Point(50, 50), new Point(60, 60)};
        Blueprint bpAlejandro2 = new Blueprint("Alejandro", "bpAlejandro2", ptsAlejandro2);
        blueprints.put(new Tuple<>(bpAlejandro2.getAuthor(), bpAlejandro2.getName()), bpAlejandro2);


        Point[] ptsGabriel = new Point[]{new Point(70, 70), new Point(80, 80)};
        Blueprint bpGabriel = new Blueprint("Gabriel", "bpGabriel", ptsGabriel);
        blueprints.put(new Tuple<>(bpGabriel.getAuthor(), bpGabriel.getName()), bpGabriel);
    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

}
