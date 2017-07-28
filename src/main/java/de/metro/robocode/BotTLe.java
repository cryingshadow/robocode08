package de.metro.robocode;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class BotTLe extends Robot {

    private final boolean activeFireMode = false;

    @Override
    public void run() {

        final double radius = 100.0;
        final double angle = 90.0;
        final double turnVar = 23.0;

        while (true) {
            if ( Math.random() < 0.3 ) {
                scanRound();
            }

            ahead(radius);

            if ( Math.random() < 0.4 ) {
                if ( Math.random() < 0.5 ) {
                    turnLeft( angle + turnVar * ( 0.5 - Math.random() ) );
                } else {
                    turnRight( angle + turnVar * ( 0.5 - Math.random() ) );
                }
            }

            //turnLeft(angle);
            //turnGunLeft(angle);

            //fireBullet(getEnergy());
        }
    }

    private void scanRound() {
        turnGunRight( 360 );
    }

    @Override
    public void onScannedRobot(final ScannedRobotEvent e) {
        final double spreadAngle = 10.0;
        final double numShots = 3.0;

        final double step = spreadAngle / numShots;

        turnGunLeft( -5 );

        //        final double bearing = e.getBearing();
        //        final double head = getHeading();
        //        final double gHead = getGunHeading();
        //
        //        turnGunRight( bearing - head );



        //        final double sr = e.getHeading();
        //        final double me = getHeading();
        //        final double da = sr - me;
        //
        //        if ( da < 0 ) {
        //            turnLeft( 90 - da );
        //        } else {
        //            turnRight( 90 - da );
        //        }
        //
        //        fire( 1 );

        //turnGunLeft( e.getHeading() );
        fire( 1 );

    }

    @Override
    public void onHitByBullet(final HitByBulletEvent e) {
        //e.getHeading();
        //turnLeft(90 - e.getBearing());
    }

    @Override
    public void onHitWall( final HitWallEvent e ) {
        final double variance = 40.0;

        if ( Math.random() > 0.5 ) {
            turnRight( 90 + variance * ( 0.5 - Math.random() ) );
        } else {
            turnLeft( 90 + variance * ( 0.5 - Math.random() ) );
        }
    }

}
