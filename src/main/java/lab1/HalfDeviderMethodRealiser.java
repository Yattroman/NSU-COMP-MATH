package lab1;

import java.util.ArrayList;

public class HalfDeviderMethodRealiser {

    private int k = 0;
    private final double epsilon = Math.pow(0.1, 10);

    private double a;
    private double b;
    private double c;

    private void setCoeffs(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private double f(double x) {
        return Math.pow(x, 3) + a*Math.pow(x, 2) + b*x + c;
    }

    private double getDerivativeFDiscriminant(){
        return Math.pow(2*a, 2) - 4*3*b;
    }

    private double[] getDerivativeFRoots(){
        double[] result = new double[2];
        result[0] = (-2*a - Math.sqrt(Math.pow(2*a, 2) - 4*3*b))/6;
        result[1] = (-2*a + Math.sqrt(Math.pow(2*a, 2) - 4*3*b))/6;
        return result;
    }

    private double getRoot(double left, double right){
        double curMiddle = (left + right) / 2;
        double curLeft = left;
        double curRight = right;

        while(curRight-curLeft > epsilon){
              if(f(curLeft)*f(curMiddle) < 0){
                  curRight = curMiddle;
                  curMiddle = (curLeft+curRight)/2;
              } else if (f(curRight)*f(curMiddle) < 0) {
                  curLeft = curMiddle;
                  curMiddle = (curLeft+curRight)/2;
              }
              k++;
        }

        return (curRight+curLeft)/2;
    }

    public ArrayList<Double> getEquatationRoots(int a, int b, int c){

        setCoeffs(a, b, c);
        double derivativeFDiscriminant = getDerivativeFDiscriminant();
        double left, right;
        ArrayList<Double> roots = new ArrayList<Double>();

        if(derivativeFDiscriminant > 0){
            var results = getDerivativeFRoots();
            if(f(results[0]) > 0 && f(results[1]) > 0){
                // There is one root, x < result[0]
                roots.add(getRoot(-100D, results[0]));
            } else if(f(results[0]) < 0 && f(results[1]) < 0) {
                // There is one root, x > result[1]
                roots.add(getRoot(results[1], 100D));
            } else if(f(results[0]) > 0 && Math.abs(f(results[1])) < epsilon){
                // There are 3 roots, 2 are the same = result[1], 1 root < result[0]
                roots.add(results[1]);
                roots.add(results[1]);
                roots.add(getRoot(-100D, results[0]));
            } else if(Math.abs(f(results[0])) < epsilon && f(results[0]) < 0){
                // There are 3 roots, 2 are the same = result[0], 1 root > result[1]
                roots.add(results[0]);
                roots.add(results[0]);
                roots.add(getRoot(results[1], 100D));
            } else if(f(results[0]) > epsilon && f(results[1]) < -epsilon) {
                // There are 3 roots, 1 root < result[0], result[0] < 1 root < result[1], 1 root > result[1]
                roots.add(getRoot(-100D, results[0]));
                roots.add(getRoot(results[0], results[1]));
                roots.add(getRoot(results[1], 100D));
            }
        }

        return roots;
    }

}
