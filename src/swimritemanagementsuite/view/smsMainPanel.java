package swimritemanagementsuite.view;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 *
 * @author Bedford
 */
public class smsMainPanel extends JPanel  {
    
    public smsMainPanel() throws SQLException {
        // sets the smsMainPanel JPanel attributes
        this.setPreferredSize(new Dimension(1400, 800));
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        
        // Creates the header panel 
        smsHeaderPanel smsHeaderPanel = new smsHeaderPanel();
        
        // Adds the header panel to this main panel.
        this.add(smsHeaderPanel, BorderLayout.PAGE_START);
        
        // Creates the Body Panel
        smsBodyPanel smsBodyPanel = new smsBodyPanel();
        
        // Adds the Body Panel to this main panel.
        this.add(smsBodyPanel, BorderLayout.PAGE_END);
        
    }
    
}
