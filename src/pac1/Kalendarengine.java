package pac1;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.*;
public class Kalendarengine implements ActionListener,MouseListener {
Kalendardraw kw;
int year,month;
 public Kalendarengine(Kalendardraw kw)
 {
	 this.kw=kw;
 }
 public void mousePressed(MouseEvent e) 
 {
	 	 year=kw.currentYear;
		 month=kw.currentMonth;
		 kw.setYear(year);
			kw.setMonth(month);
			setOnDisplay();
		 kw.repaint();
}
 public void mouseReleased(MouseEvent e) {};
 public void mouseEntered(MouseEvent e) {};
 public void mouseExited(MouseEvent e) {};
 public void mouseClicked(MouseEvent e) {};
	public void actionPerformed(ActionEvent e){
		Object clicked=e.getSource();
		year=kw.getYear();
		month=kw.getMonth();
	if (clicked==kw.b_left)	
	{
		if (month==0)
		{
			year--;
		month=11;
		}
		else month--;
	}
	else if (clicked==kw.b_right)	
	{
		if (month==11)
		{
			year++;
		month=0;
		}
		else month++;
	}
	if (clicked==kw.b_left| clicked==kw.b_right)
	{
		setOnDisplay();
	}
	kw.setYear(year);
	kw.setMonth(month);
	kw.repaint();
	if (clicked==kw.tf|clicked==kw.tf1)
	{
		try {
			year=Integer.parseInt(kw.tf.getText());
					kw.setYear(year);
			month=Integer.parseInt(kw.tf1.getText())-1;
					kw.setMonth(month);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			kw.tfmsg.setText("              Введіть дату");
		}
			kw.repaint();
	}
	}
	public void setOnDisplay()
	{
		kw.tf.setText(Integer.toString(year));
		kw.tf1.setText(Integer.toString(month+1));
	}
}
