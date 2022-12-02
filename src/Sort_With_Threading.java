import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


////   Reference  =   https://stackoverflow.com/questions/72205986/odd-even-sort-java-using-multithreading

public class Sort_With_Threading {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter Size of array: ");
        int Size=sc.nextInt();
        double CGPA_Array[]=new double[Size];
        Random random = new Random();

        for (int i = 0; i < Size; i++) {
            CGPA_Array[i]=random.nextFloat()*10.000;
        }
        int Array_Size = CGPA_Array.length;
        long INITIAL = System.currentTimeMillis();
        Brick_Sort(CGPA_Array);
        long FINAL = System.currentTimeMillis();

        for (int i = Array_Size-1; i >=0; i--) {
            System.out.format("%.3f\n", CGPA_Array[i]);
        }
        System.out.println("Time taken to Sort CGPA list : " + (FINAL - INITIAL) + "ms");
    }

    public static void Brick_Sort(double[] CGPA_Array) {
        int Number_Of_Threads = CGPA_Array.length/2;

        CyclicBarrier Barrier = new CyclicBarrier(Number_Of_Threads);
        Thread[] threads = new Thread[Number_Of_Threads];
        for (int i = 0; i < Number_Of_Threads; i++) {
            threads[i] = new Thread(new CompareSwapThread(CGPA_Array, 2*i + 1, Barrier));
            threads[i].start();
        }
        for (int i = 0; i < Number_Of_Threads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}

class CompareSwapThread implements Runnable {
    private double[] CGPA_Array;
    private int Index;
    private CyclicBarrier Barrier;

    public CompareSwapThread(double[] CGPA_Array, int Index, CyclicBarrier Barrier) {
        this.CGPA_Array = CGPA_Array;
        this.Index = Index;
        this.Barrier = Barrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < CGPA_Array.length; i++) {
            if (CGPA_Array[Index - 1] > CGPA_Array[Index]) {
                double Temporary = CGPA_Array[Index - 1];
                CGPA_Array[Index - 1] = CGPA_Array[Index];
                CGPA_Array[Index] = Temporary;
            }
            try {
                Barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            if (Index + 1 < CGPA_Array.length && CGPA_Array[Index] > CGPA_Array[Index + 1]) {
                double Temporary = CGPA_Array[Index];
                CGPA_Array[Index] = CGPA_Array[Index + 1];
                CGPA_Array[Index + 1] = Temporary;
            }
        }
    }
}

