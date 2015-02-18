package swimritemanagementsuite;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author Bedford
 */
public class smsMainPanel extends JPanel  {
    
    public smsMainPanel() {
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1400, 800));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        
        // Adds the toolbar panel to the main panel.
        smsHeaderPanel smsHeaderPanel = new smsHeaderPanel();
        
        // Adds the smsBodyPanel
        smsBodyPanel smsBodyPanel = new smsBodyPanel();
        
        // Adds the main panel to the frame.
        this.add(smsHeaderPanel, BorderLayout.PAGE_START);
        
    }
    
}
