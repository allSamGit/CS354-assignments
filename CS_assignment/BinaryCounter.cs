using System;
using System.Windows.Forms;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ButtonForm
{
    internal class BinaryCounter:Label
    {
        private int count;
        

        public BinaryCounter(int startCount)
        {
            count = startCount;
            Text = Convert.ToString(count, 2);
            AutoSize = true;
        }

        public void Count(object sender, System.EventArgs e)
        {
            count++;
            Text = Convert.ToString(count, 2);
        }

    }
}
