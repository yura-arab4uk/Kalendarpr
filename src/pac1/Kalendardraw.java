package pac1;
import java.awt.*;
import  java.util.Calendar;
import  java.util.GregorianCalendar;
import javax.swing.*;
public class Kalendardraw extends JPanel {
	int mas1 [] []=new int [200][12];
	String masdays[]=new String [7];
	Calendar calendar;
	int year=0,month=0,currentDay=0,currentYear=0,currentMonth=0;
	String [] mas;
	Kalendarengine ke;
	JButton b_left,b_right;
	Font Font1;
	Font Font2;
	JFrame frame;
	int animx=150;
	JTextField tf;
	JTextField tf1;
	JTextField tfmsg;
	
	public Kalendardraw()
	{
		masdays[0]="Пн";
		masdays[1]="Вт";
		masdays[2]="Ср";
		masdays[3]="Чт";
		masdays[4]="Пт";
		masdays[5]="Сб";
		masdays[6]="Нд";
		calendar = new GregorianCalendar();
		year     = calendar.get(Calendar.YEAR);
		month    = calendar.get(Calendar.MONTH);
		    frame=new JFrame();
		    frame.setTitle("Календар Україна");
			frame.setContentPane(this);
			frame.setBounds(150, 150, 700,550); 
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.setVisible(true);
			b_left=new JButton("Назад");
			b_right=new JButton("Вперед");
			b_left.setBounds(180, 10, 80, 20);
			b_left.setBackground(Color.ORANGE);
			b_right.setBounds(440, 10, 80, 20);
			b_right.setBackground(Color.ORANGE);
			frame.getContentPane().setLayout(null);
			frame.add(b_right);
			frame.add(b_left);
		    ke=new Kalendarengine(this);
		    b_left.addActionListener(ke);
		    b_right.addActionListener(ke);
		    Font1=new Font("Serif",Font.BOLD, 50);
		    Font2=new Font("Serif",Font.BOLD, 13);
			tf=new JTextField(Integer.toString(year));
			tf.setBounds(270, 10, 80, 20);
			tf.addActionListener(ke);
			frame.add(tf);
			tf1=new JTextField(Integer.toString(month+1));
			tf1.setBounds(350, 10, 80, 20);
			tf1.addActionListener(ke);
			frame.add(tf1);
			tfmsg=new JTextField("              Введіть дату");
			tfmsg.setBounds(270, 30, 160, 20);
			tfmsg.addActionListener(ke);
			tfmsg.setBackground(Color.gray);
			tfmsg.setDisabledTextColor(Color.black);
			tfmsg.setEnabled(false);
			frame.add(tfmsg);
					addMouseListener(ke);	
					animation();
	}
	public void setYear(int year)
	{
		this.year=year;
	}
	public int getYear()
	{
		return year;
	}
	public void setMonth(int month)
	{
		this.month=month;
	}
	public int getMonth()
	{
		return month;
	}
	public void drawprocess(int dayofweek,int month,boolean highyear)
	{
		int k=kilkistdniv(month,highyear);
			mas=new String[k];
		//ініціалізуємо масив
		for (int i=0;i<mas.length;i++)
			mas[i]=Integer.toString(i+1);
	}
	public int kilkistdniv (int month,boolean highyear)
	{
		int k=0;
		switch (month)
		{
		case 0:
		case 2:
		case 4:
		case 6:
		case 7:
		case 9:
		case 11:
		k=31;
		break;
		case 3:
		case 5:
		case 8:
		case 10:
		k=30;
		break;
		case 1:
		{
			if (highyear) k=29;
			else k=28;
		}
		break;
		}
		return k;
	}
	public boolean highyear(int y)
	{
		if (y>200)
		{
		if (y%4==0) 
		return true;
		else return false;
		}
		else if ((y+1901)%4==0)
			return true;
		else return false;
	}
	public void getMas()
	{
		//Створити масив з роками і місяцями, в яких значення будуть дні тижня перших чисел кожного місяця
				int i=0,dayofweek=2,j=0;
				for (i=0;i<200;i++)
				{
					for (j=0;j<12;j++)
					{
						mas1 [i] [j]=dayofweek;
						//перевірка високосного року
						if   (highyear(i) & j==1) 
						{
							//лютий 29 днів
							dayofweek=(dayofweek+29)%7;if (dayofweek==0) dayofweek=7;
						}
						//перевірка невисокосного року
						else if   (!highyear(i) & j==1) 
						{
							//лютий 28 днів
							dayofweek=(dayofweek+28)%7;if (dayofweek==0) dayofweek=7;
						}
						// перевірка місяців : січень, березень, травень, липень,серпень, жовтень, грудень
						else if (j==0||j==2||j==4||j==6||j==7||j==9||j==11)
						{
							dayofweek=(dayofweek+31)%7;if (dayofweek==0) dayofweek=7;
						}
						//для всіх інших місяців
							else 
							{
								dayofweek=(dayofweek+30)%7;if (dayofweek==0) dayofweek=7;
							}
						} 
			    }
	}
	public void paintComponent(Graphics g) 
	 {
		 currentDay        = calendar.get(Calendar.DAY_OF_MONTH);
		 currentYear       = calendar.get(Calendar.YEAR);
		 currentMonth      = calendar.get(Calendar.MONTH);
		 super.paintComponent(g);
		 setBackground(Color.darkGray);
		 g.setFont(Font1);
		 getMas();
	     try {
			drawDigits(g);
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			tfmsg.setText("Допустимі роки 1901-2100");
		}
	    }
	public int beginPixel(int dayofweek)
	{
		int x=animx,k=75;
		switch (dayofweek)
		{
			case 1:
				break;
			case 2:
				x+=k;
				break;
			case 3:
				x+=2*k;
				break;
			case 4:
				x+=3*k;
				break;
			case 5:
				x+=4*k;
				break;
			case 6:
				x+=5*k;
				break;
			case 7:
				x+=6*k;
				break;
		}
		return x;
	}
	public void drawDigits(Graphics g) throws ArrayIndexOutOfBoundsException
	{
		int xday=animx-75;
		int digit=0,animy=200;int dayofweek=mas1[year-1901][month];
		dayofweek=mas1[year-1901][month];
		int x=beginPixel(dayofweek);
		drawprocess(dayofweek, month, highyear(year));
		g.setColor(Color.blue);
		g.fillRoundRect(animx-10, animy-140, 525, 200,0,0);
		g.setColor(Color.yellow);
		g.fillRoundRect(animx-10, animy+60, 525, 200,0,0);
		g.setColor(Color.WHITE);
		for (int i=0;i<7;i++)
		{
			g.drawString(masdays[i], xday+=75, animy-70);
		}
		g.setColor(Color.black);
		g.setFont(Font2);
		g.drawString("Всі права захищені (с). Правовласник: Арабчук Ю. П. гр. СІ-10-2м", animx+125, animy+255);
		g.setFont(Font1);
		g.setColor(Color.green);
		for (int i=0;i<mas.length;i++)
		{
			if (year==currentYear&&month==currentMonth&&Integer.parseInt(mas[digit])==currentDay)
			{
				g.setColor(Color.gray);
				g.fillRoundRect(x, animy-40, 50, 45,20,20);
				g.setColor(Color.green);
			}
			g.drawString(mas[digit++], x, animy);
			x+=75;
			if (x>animx+475)
			{
				x=animx;
				animy+=50;
			}
		}
	}
	public void animation()
	{
		int krokx=1;
		int i=0;
		 while (true)
		 {
			 try {
					Thread.sleep(40);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 animx=i+=krokx;
			 repaint();
			 if (i>frame.getWidth()-515) krokx=-1;
			 if (i<0) krokx=1;
		 }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Kalendardraw kw=new Kalendardraw();
		for (int k=0;k<300000;k++)
		{
			System.out.println(k);
		}
	}
}
