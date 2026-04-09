package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import static edu.kis.powp.jobs2d.command.ShapesFactory.createCircle;
import static edu.kis.powp.jobs2d.command.ShapesFactory.createRectangle;
import static edu.kis.powp.jobs2d.command.ShapesFactory.createTriangle;

import edu.kis.powp.jobs2d.command.ComplexCommand;

import edu.kis.legacy.drawer.panel.DefaultDrawerFrame;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.adapter.DrawAdapter;
import edu.kis.powp.jobs2d.drivers.adapter.LineDrawerAdapter;
import edu.kis.powp.jobs2d.events.SelectChangeVisibleOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.drivers.adapter.JaneFiguresAdapter;
import edu.kis.powp.jobs2d.magicpresets.FiguresJane;

public class TestJobs2dPatterns {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Setup test concerning preset figures in context.
	 * 
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectTestFigureOptionListener selectTestFigure1OptionListener = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager(), 1
		);
		application.addTest("Figure Joe 1", selectTestFigure1OptionListener);

		SelectTestFigureOptionListener selectTestFigure2OptionListener = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager(), 2
		);
		application.addTest("Figure Joe 2", selectTestFigure2OptionListener);
		SelectTestFigureOptionListener selectTestFigureJaneListener = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager(), 3
		);
		application.addTest("Figure Jane", selectTestFigureJaneListener);
		application.addTest("Shapes Factory", (ActionEvent e) -> {
			Job2dDriver current = DriverFeature.getDriverManager().getCurrentDriver();

			ComplexCommand rectangle = createRectangle(current, 10, 10, 80, 40);
			rectangle.execute();

			ComplexCommand triangle = createTriangle(current, 0, 0, 0, 30, 30, 0);
			triangle.execute();

			ComplexCommand circle = createCircle(current, 120, 40, 35);
			circle.execute();
		});
		}


	/**
	 * Setup driver manager, and set default driver for application.
	 * 
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		Job2dDriver loggerDriver = new LoggerDriver();
		DriverFeature.addDriver("Logger Driver", loggerDriver);
		DriverFeature.getDriverManager().setCurrentDriver(loggerDriver);

		Job2dDriver testDriver = new DrawAdapter();
		DriverFeature.addDriver("Buggy Simulator", testDriver);
		Job2dDriver specialLineAdapter = new LineDrawerAdapter(LineFactory.getSpecialLine());
		DriverFeature.addDriver("Special Line Simulator", specialLineAdapter);

		Job2dDriver dottedLineAdapter = new LineDrawerAdapter(LineFactory.getDottedLine());
		DriverFeature.addDriver("Dotted Line Simulator", dottedLineAdapter);
		DriverFeature.updateDriverInfo();
	}

	/**
	 * Auxiliary routines to enable using Buggy Simulator.
	 * 
	 * @param application Application context.
	 */
	private static void setupDefaultDrawerVisibilityManagement(Application application) {
		DefaultDrawerFrame defaultDrawerWindow = DefaultDrawerFrame.getDefaultDrawerFrame();
		application.addComponentMenuElementWithCheckBox(DrawPanelController.class, "Default Drawer Visibility",
				new SelectChangeVisibleOptionListener(defaultDrawerWindow), false);
		defaultDrawerWindow.setVisible(false);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 * 
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {
		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("2d jobs Visio");
				DrawerFeature.setupDrawerPlugin(app);
				setupDefaultDrawerVisibilityManagement(app);

				DriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupLogger(app);

				app.setVisibility(true);
			}
		});
	}

}
