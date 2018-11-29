using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace JavaAssignmentClient.ADMIN
{
    public partial class Assignment_5 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void randomNumber_Click(object sender, EventArgs e)
        {
            ServiceReference1.HumanResourceXmlWebServiceClient webClient = new ServiceReference1.HumanResourceXmlWebServiceClient();

            randomNumberLAabel.Text =webClient.luckyLottoNumber().ToString();
        }

        protected void genRndArray_Click(object sender, EventArgs e)
        {
            ServiceReference1.HumanResourceXmlWebServiceClient webClient = new ServiceReference1.HumanResourceXmlWebServiceClient();
            
            rndArrayLabel.Text = webClient.luckyNumberArray(int.Parse(amount.Text));
        }

        protected void getJobs_Click(object sender, EventArgs e)
        {
            ServiceReference1.HumanResourceXmlWebServiceClient webClient = new ServiceReference1.HumanResourceXmlWebServiceClient();
            var jobs = webClient.findAllJob();


            JobsGridView.DataSource = jobs;
            JobsGridView.DataBind();

        }

        protected void search_Click(object sender, EventArgs e)
        {
            ServiceReference1.HumanResourceXmlWebServiceClient webClient = new ServiceReference1.HumanResourceXmlWebServiceClient();

            var jobDetails = webClient.findOneJob(jobIdTestBox.Text);

            jobID .Text= jobDetails.jobId;
            jobTitle.Text = jobDetails.jobTitle;
            maxSalary.Text = jobDetails.maxSalary.ToString();
            minSalary.Text = jobDetails.minSalary.ToString();



        }
    }
}