package DBSCAN;

import java.io.Serializable;

/**
 * Interface for the implementation of distance metrics.
 *
 * @author <a href="mailto:cf@christopherfrantz.org">Christopher Frantz</a>
 * @version 0.1
 *
 * @param <V> Value type to which distance metric is applied.
 */
public interface DistanceMetric<V> extends Serializable {

    public double calculateDistance(int start, V val1, V val2) throws DBSCANClusteringException;

}