package laboratory.GUI;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomWebPanel extends JPanel implements MouseListener {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CustomWebPanel.class);

    private BufferedImage image;
    private String path;
    private String url;
    JLabel picLabel;

    public CustomWebPanel(String path, String url) {
        try{
            this.path = path;
            image = ImageIO.read(new File(this.path));
            this.url = url;
        }catch (IOException ex){
            LOG.info("Loading file error.");
        }
    }

    public JLabel makeLabel(){
        this.picLabel = new JLabel(new ImageIcon(this.image));
        picLabel.addMouseListener(this);
        return picLabel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        try {

            String url = this.url;

            Desktop dt = Desktop.getDesktop();
            URI uri = new URI(url);
            dt.browse(uri.resolve(uri));


        } catch (URISyntaxException ex) {
            Logger.getLogger(ManagementAssertion.Setting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManagementAssertion.Setting.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
