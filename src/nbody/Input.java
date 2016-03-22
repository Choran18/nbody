package nbody;

import java.util.Scanner;

public class Input {

    private Quanton[] dots;
    
    public void UserValues() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number of boides or quantons = ");
        double N = scanner.nextInt();
        System.out.println("Radius of Universe = ");
        double radius = scanner.nextDouble();
        
        dots = new Quanton[N];
        for (int i = 1; i <= N; i++) {
            System.out.println("X-Posiition " + i + " = ");
            double rx = scanner.nextDouble();
            System.out.println("Y-Position " + i + " = ");
            double ry = scanner.nextDouble();
            System.out.println("X-Velocity " + i + " = ");
            double vx = scanner.nextDouble();
            System.out.println("Y-Velocity " + i + " = ");
            double vy = scanner.nextDouble();
            System.out.println("Charge " + i + " = ");
            double charge = scanner.nextDouble();
            System.out.println("Mass " + i + " = ");
            double mass = scanner.nextDouble();
            double[] position = {rx, ry};
            double[] velocity = {vx, vy};
            Vector r = new Vector(position);
            Vector v = new Vector(velocity);
            dots[i] = new Quanton(r, v, charge, mass);
        }//for
    }//UserValues
}//Input
