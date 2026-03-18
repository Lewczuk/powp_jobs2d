package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.powp.jobs2d.AbstractDriver;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DriverFeature;


public class JaneFiguresAdapter extends AbstractDriver {
    private final Job2dDriver driver;
    public JaneFiguresAdapter() {
        super(0,0);
        this.driver = DriverFeature.getDriverManager().getCurrentDriver();
    }

    @Override
    public void operateTo(int x, int y) {
        driver.setPosition(this.getX(), this.getY());
        driver.operateTo(x, y);
        this.setPosition(x, y);
    }
}
