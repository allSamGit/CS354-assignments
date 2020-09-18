using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ButtonForm
{
    public class  ButtonForm:Form
    {
        public ButtonForm()
        {
            createButton();

        }
        private void createButton()
        {

            ToggleButton ToggleButton = new ToggleButton("Off", "On");
            BinaryCounter counter = new BinaryCounter(0);
            Point buttonLocation = ToggleButton.Location;
            buttonLocation = new Point(100, 0);
            ToggleButton.Location = buttonLocation;
            ToggleButton.Click += counter.Count;
            counter.Location = new Point(100 + ToggleButton.Location.X, 5);
            Color c = Color.FromArgb(0, 125, 200);
            ToggleButton.ForeColor = c;

            Controls.Add(ToggleButton);
            Controls.Add(counter);

            Size = new Size(500, 200);
            Text = "LightSwitch";
	}
       public static void Main()
        {
        Application.EnableVisualStyles();
        Application.SetCompatibleTextRenderingDefault(true);
        Application.Run(new ButtonForm());
        }  
	}	
}

