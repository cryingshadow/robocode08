package de.metro.robocode;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class BotTLe extends Robot {

    @Override
    public void run() {

        final double radius = 100.0;
        final double angle = 90.0;
        final double turnVar = 17.0;

        while (true) {
            if ( Math.random() < 0.7 ) {
                scanRound();
            }

            ahead( radius );

            //if ( Math.random() < 0.8 )
            {
                if ( Math.random() < 0.5 ) {
                    turnLeft( angle + turnVar * ( 0.5 - Math.random() ) );
                } else {
                    turnRight( angle + turnVar * ( 0.5 - Math.random() ) );
                }
            }

        }
    }

    private void scanRound() {
        turnGunRight( 360 );
    }

    @Override
    public void onScannedRobot(final ScannedRobotEvent e) {
        final double energy = getEnergy();
        final double firePower = Math.max( 1.0, energy / 35.0 );

        //        if ( energy < 30 ) {
        //            fire( 1 );
        //        } else {
        //            spreadFire( energy );
        //        }
        fire( firePower );
        final double bearing = e.getBearing();

        if ( bearing <= 0 ) {
            turnLeft( Math.abs( bearing ) );
        } else {
            turnRight( bearing );
        }

        scan();
    }

    private void spreadFire( final double energy ) {
        final double spreadAngle = energy > 70
            ? 20.0
            : 10;
        final double numShots = energy > 70
            ? 5.0
            : 3.0;
        final double step = spreadAngle / Math.max( 1, numShots - 1 );

        turnGunLeft( spreadAngle / 2.0 );
        fire( 1 );

        for ( int i = 0; i < numShots; i++ ) {
            turnGunRight( step );
            fire( 1 );
        }
    }


    @Override
    public void onHitByBullet(final HitByBulletEvent e) {
        if ( Math.random() < 0.5 ) {
            turnLeft( 90 - e.getBearing() );
        } else {
            turnRight( 90 - e.getBearing() );
        }

        ahead( 100.0 );
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
