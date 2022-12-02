import java.util.Random;
import java.util.Scanner;

public class Sort_Without_Threading {
    public static void Brick_Sort(double CGPA_Array[], int Array_Size) {
        boolean FLAG = false;

        while (!FLAG) {
            FLAG = true;
            double Temporary = 0;

            for (int x = 1; x <= Array_Size - 2; x = x + 2) {
                if (CGPA_Array[x] < CGPA_Array[x + 1]) {
                    Temporary = CGPA_Array[x];
                    CGPA_Array[x] = CGPA_Array[x + 1];
                    CGPA_Array[x + 1] = Temporary;
                    FLAG = false;
                }
            }

            for (int y = 0; y <= Array_Size - 2; y = y + 2) {
                if (CGPA_Array[y] < CGPA_Array[y + 1]) {
                    Temporary = CGPA_Array[y];
                    CGPA_Array[y] = CGPA_Array[y + 1];
                    CGPA_Array[y + 1] = Temporary;
                    FLAG = false;
                }
            }
        }

        return;
    }
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
        Brick_Sort(CGPA_Array, Array_Size);
        long FINAL = System.currentTimeMillis();

        for (int i = 0; i < Array_Size; i++) {
            System.out.format("%.3f\n", CGPA_Array[i]);
        }
        System.out.println("Time taken to Sort CGPA list : " + (FINAL - INITIAL) + "ms");
    }
}

