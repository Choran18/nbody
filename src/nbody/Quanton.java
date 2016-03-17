/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbody;

import edu.princeton.cs.StdDraw;
import java.awt.Color;

/**
 *
 * @author Choran7
 */
public class Quanton {

    private Vector r;      // position
    private Vector v;      // velocity
    private final double charge;   // charge
    private final double mass;     // mass

    public Quanton(Vector r, Vector v, double charge, double mass) {
        this.r = r;
        this.v = v;
        this.charge = charge;
        this.mass = mass;
    }// Quanton( Vector, Vector, double, double)
    
    public void move(Vector f, double dt) {
        Vector a = f.times(1/mass);
        v = v.plus(a.times(dt));
        r = r.plus(v.times(dt));
    } // move( Vector, double )
    
    public Vector forceFrom(Quanton b) {
        Quanton a = this;
        double k = 9e9;
        Vector delta = b.r.minus(a.r);
        double dist = delta.magnitude();
        double F = (k * a.charge * b.charge) / (dist * dist);
        return delta.direction().times(F);
    } // forceFrom( Quanton )
    
    public void draw() {
        StdDraw.setPenRadius(0.025);
        StdDraw.setPenColor(Color.orange);
        StdDraw.point(r.cartesian(0), r.cartesian(1));
    } // draw()
}// Quanton
