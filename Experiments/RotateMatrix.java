public class RotateMatrix {

    public static void main(String[] argv) {

        int size = 4;
        int[][] A = new int[size][size];

        // fill matrix
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                A[i][j] = size*i + j;
            }
        }

        // print
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                System.out.printf("%3d ", A[i][j]);
            }
            System.out.print("\n");
        }

        // rotate
        System.out.print("\nrotated\n\n");
        for (int i=0; i<size/2; i++) { // border -> center
            for (int j=i; j<size-1-i; j++) { // left -> right
                
                // swap the 4 corners in a counterclockwise
                //int tmp               = A[i][j];
                //A[i][j]               = A[j][size-1-i];
                //A[j][size-1-i]        = A[size-1-i][size-1-j];
                //A[size-1-i][size-1-j] = A[size-1-j][i];
                //A[size-1-j][i]        = tmp;
                
                // swap the 4 corners clockwise
                int tmp                 = A[size-1-j][i];
                A[size-1-j][i]          = A[size-1-i][size-1-j];
                A[size-1-i][size-1-j]   = A[j][size-1-i];
                A[j][size-1-i]          = A[i][j];
                A[i][j]                 = tmp;
            }
        }
        
        // print again
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                System.out.printf("%3d ", A[i][j]);
            }
            System.out.print("\n");
        }
    }
}