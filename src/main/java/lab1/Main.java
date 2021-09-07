package lab1;

public class Main {

    public static void main(String[] args) {
        HalfDeviderMethodRealiser realiser = new HalfDeviderMethodRealiser();
        for(Double root : realiser.getEquatationRoots(2, 1, 2)){
            System.out.println(root + " ");
        }
    }
}
