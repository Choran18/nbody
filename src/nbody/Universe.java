package nbody;

import edu.princeton.cs.In;
import edu.princeton.cs.StdDraw;
import java.awt.Color;
import java.util.Scanner;

/**
 *
 * This data-driven program simulates motion in the universe defined by the
 * standard input stream, increasing time at the rate on the command line.
 * 
 * % java Universe 25000 4body.txt
 *
 *
 *****************************************************************************
 */
public class Universe {

    private final double radius;     // radius of universe
    private final int N;             // number of bodies
//    private final Body[] orbs;       // array of N bodies
    private final Quanton[] dots;    // array of N quantons

    // read universe from file
    public Universe(String fileName) {

        // the authors' version reads from standard input
        // our version reads from a file
        In inputStream = new In(fileName);

        // number of bodies
        N = inputStream.readInt();

        // the set scale for drawing on screen
        radius = inputStream.readDouble();
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);

        // read in the N bodies
//        orbs = new Body[N];
//        for (int i = 0; i < N; i++) {
//            double rx = inputStream.readDouble();
//            double ry = inputStream.readDouble();
//            double vx = inputStream.readDouble();
//            double vy = inputStream.readDouble();
//            double mass = inputStream.readDouble();
////            double red = inputStream.readDouble();
////            double green = inputStream.readDouble();
////            double blue = inputStream.readDouble();
//            double[] position = {rx, ry};
//            double[] velocity = {vx, vy};
//            Vector r = new Vector(position);
//            Vector v = new Vector(velocity);
//            orbs[i] = new Body(r, v, mass);
//        } // for
        
        dots = new Quanton[N];
        for (int i = 0; i < N; i++) {
            double rx = inputStream.readDouble();
            double ry = inputStream.readDouble();
            double vx = inputStream.readDouble();
            double vy = inputStream.readDouble();
            double charge = inputStream.readDouble();
            double mass = inputStream.readDouble();
            double[] position = {rx, ry};
            double[] velocity = {vx, vy};
            Vector r = new Vector(position);
            Vector v = new Vector(velocity);
            dots[i] = new Quanton(r, v, charge, mass);
        }// for
    } // Universe()

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
    
    // increment time by dt units, assume forces are constant in given interval
    public void increaseTime(double dt) {

        // initialize the forces to zero
        Vector[] f = new Vector[N];
        for (int i = 0; i < N; i++) {
            f[i] = new Vector(new double[2]);
        } // for

        // compute the forces
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
//                    f[i] = f[i].plus(orbs[i].forceFrom(orbs[j]));
                    f[i] = f[i].plus(dots[i].forceFrom(dots[j]));
                } // if
            } // for
        } // for

        // move the bodies
        for (int i = 0; i < N; i++) {
//            orbs[i].move(f[i], dt);
            dots[i].move(f[i], dt);
        } // for
    } // increaseTime( double )

    public void maxVelocity(){
        
    }//maxVelocity
    
    // draw the N bodies
    public void draw() {
        for (int i = 0; i < N; i++) {
//            orbs[i].draw();
            dots[i].draw();
        } // for
    } // draw()

    // client to simulate a universe
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number of boides or quantons = ");
        double N = scanner.nextDouble();
        System.out.println("Radius of Universe = ");
        double radius = scanner.nextDouble();
        
        for(int i = 1; i <= N; i++){
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
        }//for
        
        Universe newton = new Universe( args[1] );
        double dt = Double.parseDouble(args[0]);
        while (true) {
            StdDraw.clear();
            StdDraw.setPenColor(Color.black);
            StdDraw.filledRectangle(0, 0, 6e10, 6e10);
            newton.increaseTime(dt);
            newton.draw();
            StdDraw.show(10);
        } // while
    } // main( String [] )
} // Universe
