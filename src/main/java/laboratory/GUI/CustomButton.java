package laboratory.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomButton extends JButton implements ActionListener {

    private int flag;

    public CustomButton(String text){
        super(text);
        this.flag=0;
        addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(this.flag==0){
            setForeground(Color.BLUE);
            this.flag = 1;
        }

        else if(this.flag==1){
            setForeground(Color.BLACK);
            this.flag = 0;
        }
    }
}
