package Refinement_Layer;

import DBSCAN.DBSCANClusterer;
import DBSCAN.DBSCANClusteringException;
import DBSCAN.metrics.DistanceMetricNumbers;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.Function3;
import org.apache.spark.api.java.function.Function4;
import scala.Function;
import scala.Function1;
import scala.Int;
import scala.Tuple2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.fail;

public class SparkUtils implements Serializable {


    final static Function1<ArrayList<ArrayList<Double>>, Double[]> sum = new Function1<ArrayList<ArrayList<Double>>, Double[]>() {
        @Override
        public Double[] apply(ArrayList<ArrayList<Double>> datas) {
            //Utile for getting the length in the for loop
            int size = datas.get(0).size();
            System.out.println("SOZHHHHH " + size);
            Double[] somme = new Double[size];
            datas.forEach(data -> {
                for (int i = 0; i < size; i++) {
                    somme[i] = somme[i] + data.get(i);
                }
            });
            return somme;
        }

        @Override
        public <A> Function1<A, Double[]> compose(Function1<A, ArrayList<ArrayList<Double>>> g) {
            return null;
        }

        @Override
        public <A> Function1<ArrayList<ArrayList<Double>>, A> andThen(Function1<Double[], A> g) {
            return null;
        }

        @Override
        public boolean apply$mcZD$sp(double v) {
            return false;
        }

        @Override
        public double apply$mcDD$sp(double v) {
            return 0;
        }

        @Override
        public float apply$mcFD$sp(double v) {
            return 0;
        }

        @Override
        public int apply$mcID$sp(double v) {
            return 0;
        }

        @Override
        public long apply$mcJD$sp(double v) {
            return 0;
        }

        @Override
        public void apply$mcVD$sp(double v) {

        }

        @Override
        public boolean apply$mcZF$sp(float v) {
            return false;
        }

        @Override
        public double apply$mcDF$sp(float v) {
            return 0;
        }

        @Override
        public float apply$mcFF$sp(float v) {
            return 0;
        }

        @Override
        public int apply$mcIF$sp(float v) {
            return 0;
        }

        @Override
        public long apply$mcJF$sp(float v) {
            return 0;
        }

        @Override
        public void apply$mcVF$sp(float v) {

        }

        @Override
        public boolean apply$mcZI$sp(int i) {
            return false;
        }

        @Override
        public double apply$mcDI$sp(int i) {
            return 0;
        }

        @Override
        public float apply$mcFI$sp(int i) {
            return 0;
        }

        @Override
        public int apply$mcII$sp(int i) {
            return 0;
        }

        @Override
        public long apply$mcJI$sp(int i) {
            return 0;
        }

        @Override
        public void apply$mcVI$sp(int i) {

        }

        @Override
        public boolean apply$mcZJ$sp(long l) {
            return false;
        }

        @Override
        public double apply$mcDJ$sp(long l) {
            return 0;
        }

        @Override
        public float apply$mcFJ$sp(long l) {
            return 0;
        }

        @Override
        public int apply$mcIJ$sp(long l) {
            return 0;
        }

        @Override
        public long apply$mcJJ$sp(long l) {
            return 0;
        }

        @Override
        public void apply$mcVJ$sp(long l) {  }
    };

    final static Function2<Double[], Integer, Double[]> moyenne = new Function2<Double[], Integer, Double[]>() {
        @Override
        public Double[] call(Double[] somme, Integer size) throws Exception {
            if(somme != null){
            Double[] moyenne = new Double[somme.length];
            for (int i = 0; i < somme.length; i++) {
                    moyenne[i] = somme[i] / size;
            }
            return moyenne;
        }
            return null;
    }};

    final static Function3<String[], Iterator<Tuple2<String, String[]>>, List<Double>, Void> maxHeartbeat = new Function3<String[], Iterator<Tuple2<String, String[]>>, List<Double>, Void>() {
        @Override
        public Void call(String[] first, Iterator<Tuple2<String, String[]>> records, List<Double> vector) throws Exception {
            AtomicReference<Tuple2<Double, Double>> max = new AtomicReference<>(new Tuple2<>(Double.parseDouble(first[0]), Double.parseDouble(first[1])));
            try {
                records.forEachRemaining(record -> {
                    if (Double.parseDouble(record._2[1]) > max.get()._2) {
                        max.set(new Tuple2<>(Double.parseDouble(record._2[0]), Double.parseDouble(record._2[1])));
                    }
                });
                vector.addAll(Arrays.asList(max.get()._1, max.get()._2));
            } catch (Exception e) {
                vector.addAll(Arrays.asList(0.0, 0.0));
            }
            return null;
        }
    };

    final public static Function1<String[], ArrayList<Double>> convertArrayOfStringsToDouble = new Function1<String[], ArrayList<Double>>() {
        @Override
        public ArrayList<Double> apply(String[] stringsArray) {
            ArrayList<Double> doublesArray = new ArrayList<Double>();
            for (int i = 0; i < stringsArray.length; i++) {
                try {
                    doublesArray.add(Double.parseDouble(stringsArray[i]));
                } catch (NullPointerException | NumberFormatException e) {
                    doublesArray.add(0.0);
                }
            }
            return doublesArray;
        }

        @Override
        public <A> Function1<A, ArrayList<Double>> compose(Function1<A, String[]> function1) {
            return null;
        }

        @Override
        public <A> Function1<String[], A> andThen(Function1<ArrayList<Double>, A> function1) {
            return null;
        }

        @Override
        public boolean apply$mcZD$sp(double v) {
            return false;
        }

        @Override
        public double apply$mcDD$sp(double v) {
            return 0;
        }

        @Override
        public float apply$mcFD$sp(double v) {
            return 0;
        }

        @Override
        public int apply$mcID$sp(double v) {
            return 0;
        }

        @Override
        public long apply$mcJD$sp(double v) {
            return 0;
        }

        @Override
        public void apply$mcVD$sp(double v) {

        }

        @Override
        public boolean apply$mcZF$sp(float v) {
            return false;
        }

        @Override
        public double apply$mcDF$sp(float v) {
            return 0;
        }

        @Override
        public float apply$mcFF$sp(float v) {
            return 0;
        }

        @Override
        public int apply$mcIF$sp(float v) {
            return 0;
        }

        @Override
        public long apply$mcJF$sp(float v) {
            return 0;
        }

        @Override
        public void apply$mcVF$sp(float v) {

        }

        @Override
        public boolean apply$mcZI$sp(int i) {
            return false;
        }

        @Override
        public double apply$mcDI$sp(int i) {
            return 0;
        }

        @Override
        public float apply$mcFI$sp(int i) {
            return 0;
        }

        @Override
        public int apply$mcII$sp(int i) {
            return 0;
        }

        @Override
        public long apply$mcJI$sp(int i) {
            return 0;
        }

        @Override
        public void apply$mcVI$sp(int i) {

        }

        @Override
        public boolean apply$mcZJ$sp(long l) {
            return false;
        }

        @Override
        public double apply$mcDJ$sp(long l) {
            return 0;
        }

        @Override
        public float apply$mcFJ$sp(long l) {
            return 0;
        }

        @Override
        public int apply$mcIJ$sp(long l) {
            return 0;
        }

        @Override
        public long apply$mcJJ$sp(long l) {
            return 0;
        }

        @Override
        public void apply$mcVJ$sp(long l) {  }
    };

    public static void process_camera(Tuple2<String, String[]> first, Iterator<Tuple2<String, String[]>> records, List<String[]> CAMERA_VECTOR){
        //Treating first element
        try {
            if ((first._2[2].equals("PROCESSED")) && (first._2[3].equals("WORKING")) && (first._2[4].equals("True")))
                CAMERA_VECTOR.add(Arrays.copyOfRange(first._2, 5, first._2.length));
        } catch (Exception e) {
            e.printStackTrace();
        }
        records.forEachRemaining(record -> {
            try {
                if ((record._2[2].equals("PROCESSED")) && (record._2[3].equals("WORKING")) && (record._2[4].equals("True")))
                    CAMERA_VECTOR.add(Arrays.copyOfRange(record._2, 5, record._2.length));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void process_low_frequency(String key, Tuple2<String, String[]> first, List<Double> GENERAL_Vector, List<String> EVENT_DATA_Vector) {
        if (key.equals("General")) {
            try {
                //Removes the timestamp attribute
                String[] nonTimedRecord = Arrays.copyOfRange(first._2, 1, first._2.length);
//                Double[] convertedArray = SparkUtils.convertArrayOfStringsToDouble.apply(nonTimedRecord);
//                GENERAL_Vector.addAll(Arrays.asList(convertedArray));
                GENERAL_Vector.addAll(Arrays.asList(new Double[1]));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("GENERAL : " + GENERAL_Vector);
        } else if (key.equals("Event_Data")) {
            try {
                String[] newRecord = Arrays.copyOfRange(first._2, 5, first._2.length);
                EVENT_DATA_Vector.addAll(Arrays.asList(newRecord));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Event Data : " + EVENT_DATA_Vector);
        }
    }

    public static void process(Iterator<Tuple2<String, String[]>> records, List<Double> vector, String[] cleaned_first,Integer start, Integer start_clustering, Double epsilon) throws Exception {
        Double[] moyenne;
        // Init points list
        ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
        // Add the first element coming from records.next()
        data.add(convertArrayOfStringsToDouble.apply(cleaned_first));

        //Storing kafka records into a data as an array list of points to perform clustering on
        records.forEachRemaining(record -> {
            try {
                data.add(convertArrayOfStringsToDouble.apply(Arrays.copyOfRange(record._2, start,record._2.length - 1)));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });

        DBSCANClusterer<ArrayList<Double>> clusterer = null;
        try {
            clusterer = new DBSCANClusterer<ArrayList<Double>>(data, 2, epsilon, new DistanceMetricNumbers(),start_clustering);
        } catch (DBSCANClusteringException e1) {
            fail("Should not have failed on instantiation: " + e1);
        }

        ArrayList<ArrayList<Double>> clustered_data = null;

        try {
            clustered_data = clusterer.performClustering();
        } catch (DBSCANClusteringException e) {
            fail("Should not have failed while performing clustering: " + e);
        }

        System.out.println("CLUSTERRRRRRRRRRR " + clustered_data.size());

        Double[] somme = null;
        try {
                somme = SparkUtils.sum.apply(clustered_data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        moyenne = SparkUtils.moyenne.call(somme, clustered_data.size());
        vector.addAll(Arrays.asList(moyenne));
    }
}
