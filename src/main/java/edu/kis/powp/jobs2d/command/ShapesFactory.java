package edu.kis.powp.jobs2d.command;
import edu.kis.powp.jobs2d.Job2dDriver;

public class ShapesFactory {
    public static ComplexCommand createRectangle(Job2dDriver driver, int x, int y, int w, int h) {
        ComplexCommand commands = new ComplexCommand();
        commands.addCommand(new SetPositionCommand(x,y,driver));
        commands.addCommand(new OperateToCommand(x + w,y,driver));
        commands.addCommand(new OperateToCommand(x+w,y+h,driver));
        commands.addCommand(new OperateToCommand(x,y+h,driver));
        commands.addCommand(new OperateToCommand(x,y,driver));
        return commands;
    }

    public static ComplexCommand createTriangle(Job2dDriver driver, int x1,int y1, int x2,int y2, int x3, int y3) {
        ComplexCommand commands = new ComplexCommand();

        commands.addCommand(new SetPositionCommand(x1,y1,driver));
        commands.addCommand(new OperateToCommand(x2,y2,driver));
        commands.addCommand(new OperateToCommand(x3,y3,driver));
        commands.addCommand(new OperateToCommand(x1,y1,driver));
        return commands;
    }

    public static ComplexCommand createCircle(Job2dDriver driver, int cx, int cy, int r) {
        ComplexCommand commands = new ComplexCommand();
        final int segments = 64;
        int x0 = (int) Math.round(cx + r);
        int y0 = (int) Math.round(cy);
        commands.addCommand(new SetPositionCommand(x0, y0, driver));
        for (int i = 1; i <= segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            int x = (int) Math.round(cx + r * Math.cos(angle));
            int y = (int) Math.round(cy + r * Math.sin(angle));
            commands.addCommand(new OperateToCommand(x, y, driver));
        }
        return commands;
    }
}