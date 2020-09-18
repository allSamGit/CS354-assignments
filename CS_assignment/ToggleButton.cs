using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ButtonForm
{
       internal class ToggleButton : Button
        {
            private string label;

            public ToggleButton(string label_1, string label_2)
            {
                Text = label_1;
                label = label_2;
                AutoSize = true;
                Click += SwitchText;
            }

            private void SwitchText(object sender, EventArgs e)
            {
                var tmp = Text;
                Text = label;
                label = tmp;
            }


    }
}
