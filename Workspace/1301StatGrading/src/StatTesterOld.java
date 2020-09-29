
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
 
public class StatTesterOld{
 
    //change this to 1 or 2 depending on which version to grade
    static final int VERSION = 2;
     
    private static boolean v1test1() {
 
         
        Method toStringMethod = null;
         
        //check if default constructor exists
        Constructor<Stat> defaultConstructor = null;
         
        try {
            defaultConstructor = Stat.class.getConstructor();
        } catch (Exception e) {}
         
        //check for toString method
        try {
            toStringMethod = Stat.class.getMethod("toString", (Class<?> []) null);
        } catch (NoSuchMethodException e) {}
         
         
        //if default constructor doesn't exist, fail
        if (defaultConstructor == null){
            System.out.println("\tDefault constructor doesn't exist");
            return false;
        }
         
        if (toStringMethod == null){
            System.out.println("\ttoString() method doesn't exist");
            return false;
        }
         
        System.out.println("Test 1: Default constructor and toString()\n");
        Stat stat1;
         
        //Stat stat1 = new Stat();
         
        try {
            stat1 = (Stat)defaultConstructor.newInstance();
         
             
            String result = (String)toStringMethod.invoke(stat1);
            String reference = "[0.0]";
             
            System.out.println("data = " + result + "\t\t--" + result.equals(reference));
     
            System.out.println();
                     
             
     
    //      System.out.println("\tExpected:\t" + reference);
    //      System.out.println("\tFound:\t\t" + result);
     
            if (reference.equals(result)) {
                //System.out.println("Passed");
                return true;
            } else {
                //System.out.println("Failed");
                return false;
            }
        }catch (Exception e){
            System.out.println("\tERROR");
            return false;
        }
         
    }
 
    private static boolean v1test2() {
 
        Constructor<Stat> dArrayConstructor = null;
 
        Method toStringMethod = null;
         
        //check for constructor with double[] parameter
        try {
            dArrayConstructor = Stat.class.getConstructor(double[].class);
        } catch (Exception e) {}
         
        //check for toString method
        try {
            toStringMethod = Stat.class.getMethod("toString", (Class<?> []) null);
        } catch (NoSuchMethodException e) {}
 
         
        if (dArrayConstructor == null){
            System.out.println("\tcontructor with double[] parameter doesn't exist");
            return false;
        }
         
        if (toStringMethod == null){
            System.out.println("toString() method doesn't exist");
            return false;
        }
         
        System.out
                .println("Test 2: Stat(double[] d) constructor and toString()\n");
 
        double[] data1 = { 1, 2, 3, 4, 5};
        Stat stat1 = null;
        try{
            stat1 = dArrayConstructor.newInstance(new Object[]{data1});
             
            String result = (String)toStringMethod.invoke(stat1);
            String reference = "[1.0, 2.0, 3.0, 4.0, 5.0]";
             
            System.out.println("data = " + result + "\t--" + result.equals(reference));
             
            System.out.println();
 
            //System.out.println("\tExpected:\t" + reference);
            //System.out.println("\tFound:\t\t" + result);
     
            if (reference.equals(result)) {
                //System.out.println("Passed");
                return true;
            } else {
                //System.out.println("Failed");
                return false;
            }
        }catch(Exception e){
            System.out.println("\tERROR");
            return false;
        }
    }
 
    private static boolean v1test3() {
 
        System.out.println("Test 3: Min test\n");
        Constructor<Stat> dArrayConstructor = null;
 
        Method minMethod = null;
         
        //check for constructor with double[] parameter
        try {
            dArrayConstructor = Stat.class.getConstructor(double[].class);
        } catch (Exception e) {}
         
        //check for min method
        try {
            minMethod = Stat.class.getMethod("min", (Class<?> []) null);
        } catch (NoSuchMethodException e) {}
 
         
        if (dArrayConstructor == null){
            System.out.println("\tcontructor with double[] parameter doesn't exist");
            return false;
        }
         
        if (minMethod == null){
            System.out.println("min() method doesn't exist");
            return false;
        }
         
         
 
        try{
            ////////////
            double min = -20;
            double[] data1 = { min, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
            double[] data2 = { 1, 2, 3, 4, 5, min, 6, 7, 8, 9, 0 };
            double[] data3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, min};
     
            Stat stat1 = null, stat2 = null, stat3 = null;
             
            //Stat stat1 = new Stat(data1);
            //Stat stat2 = new Stat(data2);
            //Stat stat3 = new Stat(data3);             
            stat1 = dArrayConstructor.newInstance(new Object[]{data1});
            stat2 = dArrayConstructor.newInstance(new Object[]{data2});
            stat3 = dArrayConstructor.newInstance(new Object[]{data3});
             
             
            //double min1 = stat1.min();
            //double min2 = stat2.min();
            //double min3 = stat3.min();
            double min1 = (double)minMethod.invoke(stat1);
            double min2 = (double)minMethod.invoke(stat2);
            double min3 = (double)minMethod.invoke(stat3);
     
            System.out.println("min == min1\t\t--" + (min == min1));
            System.out.println("min == min2\t\t--" + (min == min2));
            System.out.println("min == min3\t\t--" + (min == min3));
            ////////////
             
             
            if (min1 == min && min2 == min
                    && min3 == min) {
                //System.out.println("\nPassed");
                return true;
            } else {
                //System.out.println("\nFailed");
                return false;
            }
        }catch (Exception e){
            System.out.println("\tERROR");
            return false;
        }
    }
 
    private static boolean v1test4() {
        System.out.println("Test 4: Max test\n");
        Constructor<Stat> dArrayConstructor = null;
 
        Method maxMethod = null;
         
        //check for constructor with double[] parameter
        try {
            dArrayConstructor = Stat.class.getConstructor(double[].class);
        } catch (Exception e) {}
         
        //check for toString method
        try {
            maxMethod = Stat.class.getMethod("max", (Class<?> []) null);
        } catch (NoSuchMethodException e) {}
 
         
        if (dArrayConstructor == null){
            System.out.println("\tcontructor with double[] parameter doesn't exist");
            return false;
        }
         
        if (maxMethod == null){
            System.out.println("max() method doesn't exist");
            return false;
        }
         
         
 
        try{
            double max = 2000;
            double[] data1 = { max, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
            double[] data2 = { 1, 2, 3, 4, 5, max, 6, 7, 8, 9, 0 };
            double[] data3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, max};
     
            Stat stat1 = null, stat2 = null, stat3 = null;
            //Stat stat1 = new Stat(data1);
            //Stat stat2 = new Stat(data2);
            //Stat stat3 = new Stat(data3); 
             
            stat1 = dArrayConstructor.newInstance(new Object[]{data1});
            stat2 = dArrayConstructor.newInstance(new Object[]{data2});
            stat3 = dArrayConstructor.newInstance(new Object[]{data3});
             
            //double max1 = stat1.max();
            //double max2 = stat2.max();
            //double max3 = stat3.max();
            double max1 = (double)maxMethod.invoke(stat1);
            double max2 = (double)maxMethod.invoke(stat2);
            double max3 = (double)maxMethod.invoke(stat3);
     
            System.out.println("max == max1\t\t--" + (max == max1));
            System.out.println("max == max2\t\t--" + (max == max2));
            System.out.println("max == max3\t\t--" + (max == max3));
             
             
             
            if (max1 == max && max2 == max
                    && max3 == max) {
                //System.out.println("\nPassed");
                return true;
            } else {
                //System.out.println("\nFailed");
                return false;
            }
        }catch (Exception e){
            System.out.println("\tERROR");
            return false;
        }
    }
 
    private static boolean v1test5() {
        System.out.println("Test 5: Average test\n");
        Constructor<Stat> dArrayConstructor = null, defaultConstructor = null;
 
        Method averageMethod = null;
     
        //check for default constructor
        try {
            defaultConstructor = Stat.class.getConstructor();
        } catch (Exception e) {}
         
        //check for constructor with double[] parameter
        try {
            dArrayConstructor = Stat.class.getConstructor(double[].class);
        } catch (Exception e) {}
         
        //check for toString method
        try {
            averageMethod = Stat.class.getMethod("average", (Class<?> []) null);
        } catch (NoSuchMethodException e) {}
 
        if (defaultConstructor == null){
            System.out.println("\tdefault constructor doesn't exist");
            return false;
        }
         
        if (dArrayConstructor == null){
            System.out.println("\tcontructor with double[] parameter doesn't exist");
            return false;
        }
         
        if (averageMethod == null){
            System.out.println("average() method doesn't exist");
            return false;
        }
         
        try{
             
     
            double[] data2 = { 10, 10, 10, 10, 10, 10, 20, 20 };
            double[] data3 = { 1, 2, 2, 3, 3, 4 };
     
            Stat stat1 = null, stat2 = null, stat3 = null;
             
            //Stat stat1 = new Stat();
            //Stat stat2 = new Stat(data2);
            //Stat stat3 = new Stat(data3);
            stat1 = defaultConstructor.newInstance();
            stat2 = dArrayConstructor.newInstance(new Object[]{data2});
            stat3 = dArrayConstructor.newInstance(new Object[]{data3});
     
            double refAvg1 = 0;
            double refAvg2 = 12.5;
            double refAvg3 = 2.5;
     
            //double average1 = stat1.average();
            //double average2 = stat2.average();
            //double average3 = stat3.average();
            double average1 = (double)averageMethod.invoke(stat1);
            double average2 = (double)averageMethod.invoke(stat2);
            double average3 = (double)averageMethod.invoke(stat3);
     
             
            System.out.println("refAvg1 == average1\t--" + (refAvg1 == average1));
            System.out.println("refAvg2 == average2\t--" + (refAvg2 == average2));
            System.out.println("refAvg3 == average3\t--" + (refAvg3 == average3));
     
             
            if (average1 == refAvg1 && average2 == refAvg2
                    && average3 == refAvg3) {
                //System.out.println("\nPassed");
                return true;
            } else {
                //System.out.println("\nFailed");
                return false;
            }
        }catch (Exception e){
            System.out.println("\tERROR");
            return false;
        }
    }
 
    private static boolean v1test6() {
        System.out.println("Test 6: Mode test\n");
        Constructor<Stat> dArrayConstructor = null, defaultConstructor = null;
 
        Method modeMethod = null;
     
        //check for default constructor
        try {
            defaultConstructor = Stat.class.getConstructor();
        } catch (Exception e) {}
         
        //check for constructor with double[] parameter
        try {
            dArrayConstructor = Stat.class.getConstructor(double[].class);
        } catch (Exception e) {}
         
        //check for toString method
        try {
            modeMethod = Stat.class.getMethod("mode", (Class<?> []) null);
        } catch (NoSuchMethodException e) {}
 
        if (defaultConstructor == null){
            System.out.println("\tdefault constructor doesn't exist");
            return false;
        }
         
        if (dArrayConstructor == null){
            System.out.println("\tcontructor with double[] parameter doesn't exist");
            return false;
        }
         
        if (modeMethod == null){
            System.out.println("mode() method doesn't exist");
            return false;
        }
         
         
 
        try{
            double[] data2 = { 10, 10, 10, 10, 10, 10, 20, 20 };
            double[] data3 = { 1, 2, 2, 3, 3, 4 };
     
            Stat stat1 = null, stat2 = null, stat3 = null;
             
            //Stat stat1 = new Stat();
            //Stat stat2 = new Stat(data2);
            //Stat stat3 = new Stat(data3);
            stat1 = defaultConstructor.newInstance();
            stat2 = dArrayConstructor.newInstance(new Object[]{data2});
            stat3 = dArrayConstructor.newInstance(new Object[]{data3});
             
            double refMode1 = 0.0;
            double refMode2 = 10;
     
            //double mode1 = stat1.mode();
            //double mode2 = stat2.mode();
            //double mode3 = stat3.mode();
            double mode1 = (double)modeMethod.invoke(stat1);
            double mode2 = (double)modeMethod.invoke(stat2);
            double mode3 = (double)modeMethod.invoke(stat3);
             
            System.out.println("refMode1 == mode1\t--" + (refMode1 == mode1));
            System.out.println("refMode2 == mode2\t--" + (refMode2 == mode2));
            System.out.println("refMode3 == mode3\t--" + Double.isNaN(mode3));
             
            if (mode1 == refMode1 && mode2 == refMode2
                    && Double.isNaN(mode3)) {
                //System.out.println("\nPassed");
                return true;
            } else {
                //System.out.println("\nFailed");
                return false;
            }
        }catch (Exception e){
            System.out.println("\tERROR");
            return false;
        }
    }
 
    private static boolean v1test7() {
        System.out.println("Test 7: Equals Test\n");
        Constructor<Stat> dArrayConstructor = null;
 
        Method equalsMethod = null;
     
        //check for constructor with double[] parameter
        try {
            dArrayConstructor = Stat.class.getConstructor(double[].class);
        } catch (Exception e) {}
         
        //check for toString method
        try {
            equalsMethod = Stat.class.getMethod("equals", new Class[]{Stat.class});
        } catch (NoSuchMethodException e) {}
 
         
        if (dArrayConstructor == null){
            System.out.println("\tcontructor with double[] parameter doesn't exist");
            return false;
        }
         
        if (equalsMethod == null){
            System.out.println("equals() method doesn't exist");
            return false;
        }
         
        try{
             
     
            double[] data1 = { 1, 2, 2, 3, 3, 4 };
            double[] data2 = { 1, 2, 2, 3, 3, 4 };
            double[] data3 = { 2, 2, 3, 3 };
     
            Stat stat1 = null, stat2 = null, stat3 = null;
             
            //Stat stat1 = new Stat(data1);
            //Stat stat2 = new Stat(data2);
            //Stat stat3 = new Stat(data3);
            stat1 = dArrayConstructor.newInstance(new Object[]{data1});
            stat2 = dArrayConstructor.newInstance(new Object[]{data2});
            stat3 = dArrayConstructor.newInstance(new Object[]{data3});
     
            //boolean res1 = stat1.equals(stat1);
            //boolean res2 = stat2.equals(stat1);
            //boolean res3 = !stat3.equals(stat2);
            boolean res1 = (boolean)equalsMethod.invoke(stat1, new Object[]{stat1});
            boolean res2 = (boolean)equalsMethod.invoke(stat2, new Object[]{stat1});
            boolean res3 = !(boolean)equalsMethod.invoke(stat3, new Object[]{stat2});
     
            System.out.println("stat1.equals(stat1)\t--" + res1);
            System.out.println("stat2.equals(stat1)\t--" + res2);
            System.out.println("!stat3.equals(stat2)\t--"+ res3);
     
            if (res1 && res2 && res3) {
                //System.out.println("\nPassed");
                return true;
            } else {
                //System.out.println("\nFailed");
                return false;
            }
        }catch (Exception e){
            System.out.println("\tERROR");
            return false;
        }
    }
 
    private static boolean v1test8() {
        System.out.println("Test 8: Getter/Setter test\n");
        Constructor<Stat> defaultConstructor = null;
 
        Method getDataMethod = null, setDataMethod = null;
     
        //check for default constructor
        try {
            defaultConstructor = Stat.class.getConstructor();
        } catch (Exception e) {}
         
        //check for getData method
        try {
            getDataMethod = Stat.class.getMethod("getData", (Class<?> []) null);
        } catch (NoSuchMethodException e) {}
 
        //check for setData method
        try{
            setDataMethod = Stat.class.getMethod("setData", new Class[] {double[].class});
        } catch (Exception e){}
         
         
        if (defaultConstructor == null){
            System.out.println("\tdefault constructor doesn't exist");
            return false;
        }
         
        if (getDataMethod == null){
            System.out.println("getData() method doesn't exist");
            return false;
        }
         
        if (setDataMethod == null){
            System.out.println("setData() method doesn't exist");
            return false;
        }
         
         
 
        try{
            double[] data1 = { 1, 2, 3, 4 };
            double[] data2 = { 1, 2, 3, 4};
             
            Stat stat1 = null;
             
            //Stat stat1 = new Stat();
            stat1 = defaultConstructor.newInstance();
     
            //stat1.setData(data1);
            setDataMethod.invoke(stat1, new Object[]{data1});
             
             
            data1[0] = 0;
             
            //double[] data3 = stat1.getData();
            double[] data3 = (double[])getDataMethod.invoke(stat1);
             
            boolean result = Arrays.equals(data2,  data3);
            System.out.println("data2 equals data3\t--" + result);
             
            if (result){
                //System.out.println("\nPassed");
                return true;
            }
            else{
                //System.out.println("\nFailed");
                return false;
            }
        }catch (Exception e){
            System.out.println("\tERROR");
            return false;
        }
    }
 
 
     
    private static boolean v2test1() {
        boolean passed = true;
        System.out.println("\nTest 1: ");
        //check if default constructor exists
                Constructor<Stat> defaultConstructor = null, doubleConstructor = null,
                        floatConstructor = null, intConstructor = null, longConstructor = null;
                 
                //default constructor
                try {
                    defaultConstructor = Stat.class.getConstructor();
                } catch (Exception e) {}
                 
                //double[] param constructor
                try {
                    doubleConstructor = Stat.class.getConstructor(new Class[]{double[].class});
                } catch (Exception e) {}
                 
                //float[] param constructor
                try {
                    floatConstructor = Stat.class.getConstructor(new Class[]{float[].class});
                } catch (Exception e) {}
                 
                //int[] param constructor
                try {
                    intConstructor = Stat.class.getConstructor(new Class[]{int[].class});
                } catch (Exception e) {}
                 
                //long[] param constructor
                try {
                    longConstructor = Stat.class.getConstructor(new Class[]{long[].class});
                } catch (Exception e) {}
                 
                 
                //if default constructor doesn't exist, fail
                if (defaultConstructor == null){
                    System.out.println("\tDefault constructor doesn't exist");
                    passed = false;
                }
                 
                //if double constructor doesn't exist, fail
                if (doubleConstructor == null){
                    System.out.println("\tdouble[] constructor doesn't exist");
                    passed = false;
                }
                //if float constructor doesn't exist, fail
                if (floatConstructor == null){
                    System.out.println("\tfloat[] constructor doesn't exist");
                    passed = false;
                }
                //if int constructor doesn't exist, fail
                if (intConstructor == null){
                    System.out.println("\tint[] constructor doesn't exist");
                    passed = false;
                }
                //if long constructor doesn't exist, fail
                if (longConstructor == null){
                    System.out.println("\tlong[] constructor doesn't exist");
                    passed = false;
                }
 
         
        double[] testArrayOne = { 5.0, 3.0, 6.0, 10.5, 0 };
        float[] testArrayTwo = { 5.0f, 3.0f, 6.0f, 10.5f, 0 };
        int[] testArrayThree = { 1, 2, 3, 4, 5, 100 };
        long[] testArrayFour = { 1, 2, 3, 4, 5, 100 };
        Stat test;
         
        System.out.println("\nconstructor test 1 (default)");
         
        try {
            //test = new Stat();
            test = (Stat)defaultConstructor.newInstance();
             
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        System.out.println();
        System.out.println("constructor test 2 (double[])");
        try {
            //test = new Stat(testArrayOne);
            test = (Stat)doubleConstructor.newInstance(testArrayOne);
             
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[5.0, 3.0, 6.0, 10.5, 0.0]");
             
            testArrayOne = null;
            //test = new Stat(testArrayOne);
            test = (Stat)defaultConstructor.newInstance();
             
            System.out.println("data2 = " + test);
            passed = passed && test.toString().equals("[]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        System.out.println();
        System.out.println("constructor test 3 (float[])");
        try {
            //test = new Stat(testArrayTwo);
            test = (Stat)floatConstructor.newInstance(testArrayTwo);
             
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[5.0, 3.0, 6.0, 10.5, 0.0]");
             
            testArrayTwo = null;
             
            //test = new Stat(testArrayTwo);
            test = (Stat)floatConstructor.newInstance(testArrayTwo);
             
            System.out.println("data2 = " + test);
            passed = passed && test.toString().equals("[]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        System.out.println();
        System.out.println("constructor test 4 (int[])");
        try {
            //test = new Stat(testArrayThree);
            test = (Stat)intConstructor.newInstance(testArrayThree);
             
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]");
             
            testArrayThree = null;
             
            //test = new Stat(testArrayThree);
            test = (Stat)intConstructor.newInstance(testArrayThree);
             
            System.out.println("data2 = " + test);
            passed = passed && test.toString().equals("[]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        System.out.println();
        System.out.println("constructor test 5 (long[])");
        try {
            //test = new Stat(testArrayFour);
            test = (Stat)longConstructor.newInstance(testArrayFour);
             
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]");
             
            testArrayFour = null;
             
            //test = new Stat(testArrayFour);
            test = (Stat)longConstructor.newInstance(testArrayFour);
             
            System.out.println("data2 = " + test);
            passed = passed && test.toString().equals("[]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        return passed;
    }
 
    private static boolean v2test2() {
         System.out.println("\nTest 2: ");
        boolean passed = true;
        Constructor<Stat> defaultConstructor = null;
         
        Method getData = null;
        try{
            getData = Stat.class.getMethod("getData");
        }catch (Exception e){}
         
        try {
            defaultConstructor = Stat.class.getConstructor();
        } catch (Exception e){}
                 
        if (getData == null){
            System.out.println("\tgetData doesn't exist");
            passed = false;
        }
         
        double[] testArrayOne = { 5.0, 3.0, 6.0, 10.5, 0 };
        float[] testArrayTwo = { 5.0f, 3.0f, 6.0f, 10.5f, 0 };
        int[] testArrayThree = { 1, 2, 3, 4, 5, 100 };
        long[] testArrayFour = { 1, 2, 3, 4, 5, 100 };
        Stat test;
 
        if (defaultConstructor == null){
            System.out.println("\tDefault constructor doesn't exist");
            return false;
        }
        try {
            //test = new Stat();
            test = (Stat)defaultConstructor.newInstance();
        } catch (Exception e) {
            System.out.println("\tcan't construct Stat object");
            return false;
        }
 
        System.out.println("\nsetData(double[] a) test");
        try {
             
            Method setData = null;
            try{
                setData = Stat.class.getMethod("setData", new Class[]{double[].class});
            }catch (Exception e){}
             
             
            if (setData == null){
                System.out.println("\tsetData(double[]) doesn't exist");
                passed = false;
            }
         
             
            //test.setData(testArrayOne);
            setData.invoke(test, new Object[]{testArrayOne});
             
            testArrayOne[0] = 0;
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[5.0, 3.0, 6.0, 10.5, 0.0]");
             
            testArrayOne[0] = 0;
            //System.out.println("data2 = " + Arrays.toString(test.getData()));
            System.out.println("data2 = " + Arrays.toString((double[])getData.invoke(test)));
             
            passed = passed && test.toString().equals("[5.0, 3.0, 6.0, 10.5, 0.0]");
             
            testArrayOne = null;
            //test.setData(testArrayOne);
            setData.invoke(test, new Object[]{testArrayOne});
             
            System.out.println("data3 = " + test);
            passed = passed && test.toString().equals("[]");
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
         
         
        System.out.println();
        System.out.println("setData(float[] a) test");
        try {
            Method setData = null;
            try{
                setData = Stat.class.getMethod("setData", new Class[]{float[].class});
            }catch (Exception e){}
             
            if (setData == null){
                System.out.println("\tsetData(float[]) doesn't exist");
                passed = false;
            }
             
            //test.setData(testArrayTwo);
            setData.invoke(test, new Object[]{testArrayTwo});
             
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[5.0, 3.0, 6.0, 10.5, 0.0]");
             
            testArrayTwo[0] = 0;
            //System.out.println("data2 = " + Arrays.toString(test.getData()));
            System.out.println("data2 = " + Arrays.toString((double[])getData.invoke(test)));
            passed = passed && test.toString().equals("[5.0, 3.0, 6.0, 10.5, 0.0]");
             
            testArrayTwo = null;
             
            //test.setData(testArrayTwo);
            setData.invoke(test, new Object[]{testArrayTwo});
             
            System.out.println("data3 = " + test);
            passed = passed && test.toString().equals("[]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        System.out.println();
         
         
        System.out.println("setData(int[] a) test");
        try {
 
            Method setData = null;
            try{
                setData = Stat.class.getMethod("setData", new Class[]{int[].class});
            }catch (Exception e){}
             
             
            if (setData == null){
                System.out.println("\tsetData(int[]) doesn't exist");
                passed = false;
            }
         
            //test.setData(testArrayThree);
            setData.invoke(test, new Object[]{testArrayThree});
             
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]");
             
            testArrayThree[0] = 0;
            //System.out.println("data2 = " + Arrays.toString(test.getData()));
            System.out.println("data2 = " + Arrays.toString((double[])getData.invoke(test)));
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]");
             
            testArrayThree = null;
             
            //test.setData(testArrayThree);
            setData.invoke(test, new Object[]{testArrayThree});
             
            System.out.println("data3 = " + test);
            passed = passed && test.toString().equals("[]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        System.out.println();
        System.out.println("setData(long[] a) test");
        try {
            Method setData = null;
            try{
                setData = Stat.class.getMethod("setData", new Class[]{long[].class});
            }catch (Exception e){}
             
             
            if (setData == null){
                System.out.println("\tsetData(long[]) doesn't exist");
                passed = false;
            }
             
            //test.setData(testArrayFour);
            setData.invoke(test, new Object[]{testArrayFour});
             
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]");
             
            testArrayFour[0] = 0;
            //System.out.println("data2 = " + Arrays.toString(test.getData()));
            System.out.println("data2 = " + Arrays.toString((double[])getData.invoke(test)));
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 100.0]");
             
            testArrayFour = null;
             
            //test.setData(testArrayFour);
            setData.invoke(test, new Object[]{testArrayFour});
            System.out.println("data3 = " + test);
            passed = passed && test.toString().equals("[]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
        return passed;
 
    }
 
    private static boolean v2test3() {
        boolean passed = true;
        System.out.println("\nTest 3: ");
         
        Constructor<Stat> doubleConstructor = null;
         
        try {
            doubleConstructor = Stat.class.getConstructor(new Class[]{double[].class});
        } catch (Exception e){}
         
         
     
        if (doubleConstructor == null){
            System.out.println("\tdouble[] constructor doesn't exist");
            passed = false;
        }
         
        double[] initialArray = { 1, 2, 3, 4 };
        double[] testArrayOne = { 5, 6, 7, 8, 9 };
        float[] testArrayTwo = { 50, 6, 7, 8, 9 };
        int[] testArrayThree = { 500, 6, 7, 8, 9 };
        long[] testArrayFour = { 5000, 6, 7, 8, 9 };
        Stat test;
 
        System.out.println("\nappend(double[] a) test");
        try {
            Method append = null;
             
            try{
                append = Stat.class.getMethod("append", new Class[]{double[].class});
            }catch (Exception e){}
             
            if (append == null){
                System.out.println("\tappend(double[]) doesn't exist");
                passed = false;
            }
            //test = new Stat(initialArray);
            test = doubleConstructor.newInstance(initialArray);
             
            //test.append(testArrayOne);
            append.invoke(test, testArrayOne);
             
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]");
             
            testArrayOne = null;
             
            //test.append(testArrayOne);
            append.invoke(test, testArrayOne);
             
            System.out.println("data2 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        System.out.println();
        System.out.println("append(float[] a) test");
        try {
            Method append = null;
             
            try{
                append = Stat.class.getMethod("append", new Class[]{float[].class});
            }catch (Exception e){}
             
            if (append == null){
                System.out.println("\tappend(float[]) doesn't exist");
                passed = false;
            }
            //test = new Stat(initialArray);
            test = doubleConstructor.newInstance(initialArray);
             
            //test.append(testArrayTwo);
            append.invoke(test, testArrayTwo);
             
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]");
             
            testArrayTwo = null;
             
            //test.append(testArrayTwo);
            append.invoke(test, testArrayTwo);
             
            System.out.println("data2 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 50.0, 6.0, 7.0, 8.0, 9.0]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        System.out.println();
         
         
         
        System.out.println("append(int[] a) test");
        try {
            Method append = null;
             
            try{
                append = Stat.class.getMethod("append", new Class[]{int[].class});
            }catch (Exception e){}
             
            if (append == null){
                System.out.println("\tappend(int[]) doesn't exist");
                passed = false;
            }
            //test = new Stat(initialArray);
            test = doubleConstructor.newInstance(initialArray);
             
            //test = new Stat(initialArray);
            append.invoke(test, testArrayThree);
            //test.append(testArrayThree);
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]");
             
            testArrayThree = null;
            //test.append(testArrayThree);
            append.invoke(test, testArrayThree);
             
            System.out.println("data2 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 500.0, 6.0, 7.0, 8.0, 9.0]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        System.out.println();
        System.out.println("append(long[] a) test");
        try {
            Method append = null;
             
            try{
                append = Stat.class.getMethod("append", new Class[]{long[].class});
            }catch (Exception e){}
             
            if (append == null){
                System.out.println("\tappend(long[]) doesn't exist");
                passed = false;
            }
            //test = new Stat(initialArray);
            test = doubleConstructor.newInstance(initialArray);
             
            //test = new Stat(initialArray);
            //test.append(testArrayFour);
            append.invoke(test, testArrayFour);
            System.out.println("data1 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]");
             
            testArrayFour = null;
             
            //test.append(testArrayFour);
            append.invoke(test, testArrayFour);
             
            System.out.println("data2 = " + test);
            passed = passed && test.toString().equals("[1.0, 2.0, 3.0, 4.0, 5000.0, 6.0, 7.0, 8.0, 9.0]");
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            passed = false;
        }
 
        return passed;
    }
 
    private static boolean v2test4() {
 
        boolean passed = true;
        System.out.println("\nTest 4: ");
        double[] testArrayOne = { 1, 2, 3, 4 };
        Stat test;
        System.out.println("reset() and isEmpty() test");
        Constructor<Stat> doubleConstructor = null;
        Method isEmptyMethod = null, resetMethod = null;
         
        //double[] param constructor
        try {
            doubleConstructor = Stat.class.getConstructor(new Class[]{double[].class});
        } catch (Exception e) {}
         
        try{
            isEmptyMethod = Stat.class.getMethod("isEmpty", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            resetMethod = Stat.class.getMethod("reset", (Class<?>[])null);
        }catch (Exception e){}
         
         
        if (doubleConstructor == null){
            System.out.println("\tdouble constructor doesn't exist");
            passed = false;
        }
        if (resetMethod == null){
            System.out.println("\treset() doesn't exist");
            passed = false;
        }
        if (isEmptyMethod == null){
            System.out.println("\tisEmpty() doesn't exist");
            passed = false;
        }
         
         
        try {
            //test = new Stat(testArrayOne);
            test = doubleConstructor.newInstance(testArrayOne);
             
            System.out.println("data1 = " + test);
            passed = passed && (test.toString().equals("[1.0, 2.0, 3.0, 4.0]"));
             
            //test.reset();
            resetMethod.invoke(test);
             
            //System.out.println("is empty after reset: " + test.isEmpty());
            System.out.println("is empty after reset\t--" + (boolean)isEmptyMethod.invoke(test));
            passed = passed && (boolean)isEmptyMethod.invoke(test);
             
        } catch (Exception e) {
            System.out.println("\tERROR OCCURRED - TEST FAILED");
            return false;
        }
        return passed;
    }
 
    private static boolean v2test5() {
        boolean passed = true;
        Constructor<Stat> doubleConstructor = null;
        Method min = null, max = null, average = null, mode = null, variance = null, stdev = null;
         
        System.out.println("\nTest 5: ");
        System.out.println("stats test (null)");
        //System.out.println("--> 6 values: should all be NaN");
        try {
            doubleConstructor = Stat.class.getConstructor(new Class[]{double[].class});
        } catch (Exception e) {}
         
        try{
            min = Stat.class.getMethod("min", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            max = Stat.class.getMethod("max", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            average = Stat.class.getMethod("average", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            mode = Stat.class.getMethod("mode", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            variance = Stat.class.getMethod("variance", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            stdev = Stat.class.getMethod("standardDeviation", (Class<?>[])null);
        }catch (Exception e){}
         
 
        if (doubleConstructor == null){
            System.out.println("double constructor doesn't exist");
            passed = false;
        }
         
        try {
            double[] data = null;
             
            Stat test = null;
            test = doubleConstructor.newInstance(data);
             
             
                if(min != null){
                    System.out.print("min:\t" + min.invoke(test));
                    System.out.println("\t--" + Double.isNaN((double)min.invoke(test)));
                    passed = passed && Double.isNaN((double)min.invoke(test));
                } else {
                    System.out.println("min:\tdoesn't exist\t\t--false");
                    passed = false;
                }
             
             
            if (max != null){
                System.out.print("max:\t" + max.invoke(test));
                System.out.println("\t--" + Double.isNaN((double)max.invoke(test)));
                passed = passed && Double.isNaN((double)max.invoke(test));
            } else {
                System.out.println("max:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
            if (average != null){
                System.out.print("avg:\t" + average.invoke(test));
                System.out.println("\t--" + Double.isNaN((double)average.invoke(test)));
                passed = passed && Double.isNaN((double)average.invoke(test));
            } else {
                System.out.println("avg:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
            if (mode != null){
                System.out.print("mode:\t" + mode.invoke(test));
                System.out.println("\t--" + Double.isNaN((double)mode.invoke(test)));
                passed = passed && Double.isNaN((double)mode.invoke(test));
            } else {
                System.out.println("mode:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
            if (variance != null){
                System.out.print("var:\t" + variance.invoke(test));
                System.out.println("\t--" + Double.isNaN((double)variance.invoke(test)));
                passed = passed && Double.isNaN((double)variance.invoke(test));
            } else {
                System.out.println("var:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
            if (stdev != null){
                System.out.print("stdev:\t" + stdev.invoke(test));
                System.out.println("\t--" + Double.isNaN((double)stdev.invoke(test)));
                passed = passed && Double.isNaN((double)stdev.invoke(test));
            } else {
                System.out.println("stdev:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
        } catch (Exception e) {
            System.out.println("\tTEST FAILED");
            return false;
        }
        return passed;
 
    }
 
    private static boolean v2test6() {
 
        boolean passed = true;
        Constructor<Stat> doubleConstructor = null;
        Method min = null, max = null, average = null, mode = null, variance = null, stdev = null;
         
        System.out.println("\nTest 6: ");
        System.out.println("stats test (real data)");
        //System.out.println("--> 6 values: should all have valid values");
         
        try {
            doubleConstructor = Stat.class.getConstructor(new Class[]{double[].class});
        } catch (Exception e) {}
         
        try{
            min = Stat.class.getMethod("min", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            max = Stat.class.getMethod("max", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            average = Stat.class.getMethod("average", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            mode = Stat.class.getMethod("mode", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            variance = Stat.class.getMethod("variance", (Class<?>[])null);
        }catch (Exception e){}
         
        try{
            stdev = Stat.class.getMethod("standardDeviation", (Class<?>[])null);
        }catch (Exception e){}
         
 
        if (doubleConstructor == null){
            System.out.println("double constructor doesn't exist");
            passed = false;
        }
         
        try {
            double[] data = { -5.3, 2.5, 88.9, 0, 0.0, 28, 16.5, 88.9, 109.5,
                    -90, 88.9, 100.34, 50.01, 50.01, -8 };
             
            Stat test = doubleConstructor.newInstance(data);
             
            if(min != null){
                System.out.print("min:\t" + min.invoke(test));
                System.out.println("\t\t\t--" + ((double)min.invoke(test) == -90));
                passed = passed && ((double)min.invoke(test) == -90);
                 
            } else {
                System.out.println("min:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
            if (max != null){
                System.out.print("max:\t" + max.invoke(test));
                System.out.println("\t\t\t--" + ((double)max.invoke(test) == 109.5));
                passed = passed && ((double)max.invoke(test) == 109.5);
            } else {
                System.out.println("max:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
            if (average != null){
                System.out.print("avg:\t" + average.invoke(test));
                System.out.println("\t\t\t--" + ((double)average.invoke(test) == 34.684));
                passed = passed && ((double)average.invoke(test) == 34.684);
            } else {
                System.out.println("avg:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
            if (mode != null){
                System.out.print("mode:\t" + mode.invoke(test));
                System.out.println("\t\t\t--" + ((double)mode.invoke(test) == 88.9));
                passed = passed && ((double)mode.invoke(test) == 88.9);
            } else {
                System.out.println("mode:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
            if (variance != null){
                System.out.print("var:\t" + variance.invoke(test));
                System.out.println("\t--" + ((double)variance.invoke(test) == 2798.6591973333334));
                passed = passed && ((double)variance.invoke(test) == 2798.6591973333334);
            } else {
                System.out.println("var:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
            if (stdev != null){
                System.out.print("stdev:\t" + stdev.invoke(test));
                System.out.println("\t--" + ((double)stdev.invoke(test) == 52.90235530988515));
                passed = passed && ((double)stdev.invoke(test) == 52.90235530988515);
            } else {
                System.out.println("stdev:\tdoesn't exist\t\t--false");
                passed = false;
            }
             
        } catch (Exception e) {
            System.out.println("\tTEST FAILED");
            return false;
        }
        return passed;
    }
 
    /**
     * @param args
     */
    public static void main(String[] args) {
         
        if (VERSION == 1){
 
            boolean v1t1, v1t2, v1t3, v1t4, v1t5, v1t6, v1t7, v1t8;
            v1t1=v1t2=v1t3=v1t4=v1t5=v1t6=v1t7=v1t8=false;
             
            //all exceptions should be caught within the test themselves, but just in case.. 
            try{
            v1t1=v1test1();
            }catch(Exception e){}
             
            System.out.println("--------");
             
            try{
            v1t2=v1test2();
            }catch(Exception e){}
             
            System.out.println("--------");
             
            try{
            v1t3=v1test3();
            }catch(Exception e){}
             
            System.out.println("--------");
     
            try{
            v1t4=v1test4();
            }catch(Exception e){}
             
            System.out.println("--------");
     
            try{
            v1t5=v1test5();
            }catch(Exception e){}
             
            System.out.println("--------");
     
            try{
            v1t6=v1test6();
            }catch(Exception e){}
             
            System.out.println("--------");
     
            try{
            v1t7=v1test7();
            }catch(Exception e){}
             
            System.out.println("--------");
     
            try{
            v1t8=v1test8();
            }catch(Exception e){}
             
            System.out.println("--------------------------------------\n");
            if (!v1t1) System.err.println("Test 1 (default contructor & toString) failed");
            if (!v1t2) System.err.println("Test 2 (1 param constructor & toString) failed");
            if (!v1t3) System.err.println("Test 3 (min) failed");
            if (!v1t4) System.err.println("Test 4 (max) failed");
            if (!v1t5) System.err.println("Test 5 (average) failed");
            if (!v1t6) System.err.println("Test 6 (mode) failed");
            if (!v1t7) System.err.println("Test 7 (equals) failed");
            if (!v1t8) System.err.println("Test 8 (getter/setter) failed");
             
            if (v1t1 && v1t2 && v1t3 && v1t4 && v1t5 && v1t6 && v1t7 && v1t8) 
                System.out.println("ALL TESTS PASSED");
        }//if
        if (VERSION == 2){
            boolean t1, t2, t3, t4, t5, t6;
            t1=t2=t3=t4=t5=t6=false;
             
            try{
                t1=v2test1();
                }catch(Exception e){}
                 
                System.out.println("--------");
                 
                try{
                t2=v2test2();
                }catch(Exception e){}
                 
                System.out.println("--------");
                 
                try{
                t3=v2test3();
                }catch(Exception e){}
                 
                System.out.println("--------");
     
                try{
                t4=v2test4();
                }catch(Exception e){}
                 
                System.out.println("--------");
     
                try{
                t5=v2test5();
                }catch(Exception e){}
                 
                System.out.println("--------");
     
                try{
                t6=v2test6();
                }catch(Exception e){}
                 
                System.out.println("\n--------------------------------------\n");
                if (!t1) System.err.println("Test 1 (constructor & toString) failed");
                if (!t2) System.err.println("Test 2 (setData) failed");
                if (!t3) System.err.println("Test 3 (append) failed");
                if (!t4) System.err.println("Test 4 (reset & isEmpty) failed");
                if (!t5) System.err.println("Test 5 (null stats) failed");
                if (!t6) System.err.println("Test 6 (valid stats) failed");
                 
                if (t1 && t2 && t3 && t4 && t5 && t6) System.out.println("ALL TESTS PASSED");
        }//if
    }
 
}
