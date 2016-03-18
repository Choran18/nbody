package nbody;

import edu.princeton.cs.StdDraw;
import java.awt.Color;

/******************************************************************************
 *  This class is what creates the orbiting bodies and simulates their
 * gravity. It contains the methods that also move and draw the bodies where
 * we alter color and size.
 *
 *
 ******************************************************************************/

public class Body {
    private Vector r;      // position
    private Vector v;      // velocity
    private final double mass;   // mass

    public Body(Vector r, Vector v, double mass) {
        this.r = r;
        this.v = v;
        this.mass = mass;
    } // Body( Vector, Vector, double )
    
    public void move(Vector f, double dt) {
        Vector a = f.times(1/mass);
        v = v.plus(a.times(dt));
        r = r.plus(v.times(dt));
    } // move( Vector, double )

    public Vector forceFrom(Body b) {
        Body a = this;
        double G = 6.67e-11;
        Vector delta = b.r.minus(a.r);
        double dist = delta.magnitude();
        double F = (G * a.mass * b.mass) / (dist * dist);
        return delta.direction().times(F);
    } // forceFrom( Body )

    public void draw() {
        StdDraw.setPenRadius(0.025);
        StdDraw.setPenColor(Color.orange);
        StdDraw.point(r.cartesian(0), r.cartesian(1));
    } // draw()

    // this method is only needed if you want to change the size of the bodies
    public void draw(double penRadius) {
        StdDraw.setPenRadius(penRadius);
        StdDraw.point(r.cartesian(0), r.cartesian(1));
    } // draw( double )

} // Body
