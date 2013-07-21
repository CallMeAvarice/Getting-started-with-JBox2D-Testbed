package me.callmeavarice.jbox2d.testbed.gettingstarted;

import me.callmeavarice.jbox2d.testbed.gettingstarted.tests.sample.MJWTest2;
import org.jbox2d.testbed.framework.*;
import org.jbox2d.testbed.framework.j2d.DebugDrawJ2D;
import org.jbox2d.testbed.framework.j2d.TestPanelJ2D;
import org.jbox2d.testbed.framework.j2d.TestbedSidePanel;

import javax.swing.*;
import java.awt.*;

public class TestbedMain {

    public static void main(String[] args) {
        // Instantiate an instance of the Testbed
        TestbedModel model = new TestbedModel();
        final TestbedController controller =
                new TestbedController(model, TestbedController.UpdateBehavior.UPDATE_CALLED, TestbedController.MouseBehavior.NORMAL,
                        new TestbedErrorHandler() {
                            @Override
                            public void serializationError(Exception e, String message) {
                                JOptionPane.showMessageDialog(null, message, "Serialization Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        });
        TestPanelJ2D panel = new TestPanelJ2D(model, controller);
        model.setPanel(panel);
        model.setDebugDraw(new DebugDrawJ2D(panel, true));

        // Adding the sample test - this will located under the Featured category in GUI's drop down list
        model.addCategory("Featured");
        model.addTest(new MJWTest2());

        // Configure and launch the Testbed's GUI
        JFrame testbed = new JFrame();
        testbed.setTitle("JBox2D Testbed");
        testbed.setLayout(new BorderLayout());
        TestbedSidePanel side = new TestbedSidePanel(model, controller);
        testbed.add((Component) panel, "Center");
        testbed.add(new JScrollPane(side), "East");
        testbed.pack();
        testbed.setVisible(true);
        testbed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                controller.playTest(0);
                controller.start();
            }
        });
    }
}
