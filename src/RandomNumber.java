import java.util.*;
public class RandomNumber {
 private Random i;
 public RandomNumber() {
  i = new Random();
 }
 public int getRandom(int n_max) {
  // Geeft een random waarde terug tussen 0 en n_max.
  try {
   Thread.sleep(1);
  }
  catch (InterruptedException e) {}
  return  (( i.nextInt()/42949672 ) + 50 ) * n_max / 100;
 }
}
