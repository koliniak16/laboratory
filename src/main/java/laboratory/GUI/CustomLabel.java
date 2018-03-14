package laboratory.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomLabel extends JLabel implements MouseListener {

    private int size;
    private int flag;

    public CustomLabel(String text){
        super(text);
        this.size=18;
        this.flag=0;
        addMouseListener(this);
    }

    public void setFontSize(int size){
        setFont(new Font("Helvetica", Font.PLAIN, size));
    }

    public void setColor(Color color){
        setForeground(color);
    }

    public void setFontSizeClick(int size){
        setFont(new Font("Helvetica", Font.PLAIN, size));
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.size++;
        this.flag++;
        if(this.flag<5){
        setFontSizeClick(size);}
        else{
            setFontSizeClick(16);
            this.flag=0;
            this.size=18;
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
