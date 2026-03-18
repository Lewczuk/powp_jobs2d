package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DrawerFeature;

public class LineDrawerAdapter implements Job2dDriver {

    private int currentX;
    private int currentY;
    private final ILine lineShape;

    public LineDrawerAdapter(ILine lineShape) {
        this.lineShape = lineShape;
        this.currentX = 0;
        this.currentY = 0;
    }

    @Override
    public void setPosition(int x, int y) {
        updateCurrentPosition(x, y);
    }

    @Override
    public void operateTo(int x, int y) {
        configureLine(x, y);
        draw();
        updateCurrentPosition(x, y);
    }

    private void configureLine(int endX, int endY) {
        lineShape.setStartCoordinates(currentX, currentY);
        lineShape.setEndCoordinates(endX, endY);
    }

    private void draw() {
        DrawerFeature.getDrawerController().drawLine(lineShape);
    }

    private void updateCurrentPosition(int x, int y) {
        this.currentX = x;
        this.currentY = y;
    }

    @Override
    public String toString() {
        return "Line Drawer Adapter";
    }
}
