package hamming;
/**
 * @author mike purvis
 */
class MikeHamming{
  public static void main(String[] args)  {
  // credit to the matrices found at : https://secure.wikimedia.org/wikipedia/en/wiki/Hamming%287,4%29      
  // We multiply matrixG by some Bit String to get a Hamming "encoded" vector full of parity bits
  //int matrixG[][] = {{1,1,0,1},{1,0,1,1},{1,0,0,0},{0,1,1,1},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
  int matrixG[][] = {{1,1,0,1,1,0,1,0},{1,0,1,1,0,1,1,0},{1,0,0,0,0,0,0,0},{0,1,1,1,0,0,0,1},{0,1,0,0,0,0,0,0},{0,0,1,0,0,0,0,0},{0,0,0,1,0,0,0,0},{0,0,0,0,1,1,1,1},{0,0,0,0,1,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0,1}};
  
  // Hardcoded Bit String of 1, 1, 1, 1
  //int vectorBitString[][] = {{1},{1},{1},{1}};
  int vectorBitString[][] = {{1},{1},{1},{1},{1},{1},{1},{1}};
  
  System.out.print("Bit string: ");
    for (int i = 0; i < vectorBitString.length; i++) {
        System.out.print(vectorBitString[i][0]+" ");
    } System.out.println();
  
  // We use Matrix H on the DECODING end, see later
//  int matrixH[][] = {{1,0,1,0,1,0,1},{0,1,1,0,0,1,1},{0,0,0,1,1,1,1}};
  int matrixH[][] = {{1,0,1,0,1,0,1,0,1,0,1,0},{0,1,1,0,0,1,1,0,0,1,1,0},{0,0,0,1,1,1,1,0,0,0,0,1},{0,0,0,0,0,0,0,1,1,1,1,1}};

  //int matrixHammed[][] = new int[7][7];
  int matrixHammed[][] = new int[12][12];
  matrixHammed = multiply(matrixG, vectorBitString);
      System.out.println("\nENCODED:");
      for (int i = 0; i < matrixHammed.length; i++) {
          matrixHammed[i][0] = matrixHammed[i][0] % 2;
          System.out.print(matrixHammed[i][0] + " ");
      }
      System.out.println("\n");
  
      /* 1   2   3   4   5   6   7
       * p1  p2  d1  p3  d2  d3  d4
       * 
       * 1    2    3    4    5    6    7    8    9    10    11    12
       * p1   p2   d1   p3   d2   d3   d4   p4   d5   d6    d7    d8
       */
      
    // flip a data bit, uncomment whichever bit to flip
//    matrixHammed[2][0] = 0; System.out.println("Flipped data bit 3");// flip data bit 3
//    matrixHammed[4][0] = 0; System.out.println("Flipped data bit 5");// flip data bit 5
    matrixHammed[5][0] = 0; System.out.println("Flipped data bit 6");// flip data bit 6
//    matrixHammed[6][0] = 0; System.out.println("Flipped data bit 7");// flip data bit 7
//    matrixHammed[8][0] = 0; System.out.println("Flipped data bit 9");// flip data bit 9
//    matrixHammed[9][0] = 0; System.out.println("Flipped data bit 10");// flip data bit 10
//    matrixHammed[10][0] = 0; System.out.println("Flipped data bit 11");// flip data bit 11
//    matrixHammed[11][0] = 0; System.out.println("Flipped data bit 12");// flip data bit 12
      
      //multiply H * Hammed
//    int matrixDeHammed[][] = new int[7][7];  
    int matrixDeHammed[][] = new int[12][12];  
    matrixDeHammed = multiply(matrixH, matrixHammed);
      
      System.out.println("\nERROR CHECKING RESULT:");
      //for (int i = 2; i >=0 ; i--) {
      for (int i = 3; i >=0 ; i--) {
          matrixDeHammed[i][0] = matrixDeHammed[i][0] % 2;
          System.out.print(matrixDeHammed[i][0] + " ");
      }
      
      System.out.println("\n\nIf all zeroes, no error detected.\nIf one bit has been flipped, the location is given above.");
  }
  
public static int[][] multiply(int a[][], int b[][]) {
  int aRows = a.length, aColumns = a[0].length, bRows = b.length, bColumns = b[0].length;
  if ( aColumns != bRows ) {
    throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
  }
  int[][] resultant = new int[aRows][bColumns];
 
      for(int i = 0; i < aRows; i++) { // aRow
          for(int j = 0; j < bColumns; j++) { // bColumn
            for(int k = 0; k < aColumns; k++) { // aColumn
              resultant[i][j] += a[i][k] * b[k][j];
            }
          }
         }
      return resultant;
}
}