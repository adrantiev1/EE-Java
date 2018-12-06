using System;
using System.Net.Http;
using Newtonsoft.Json;

namespace Dmit2015App
{
    class Job
    {
        public string jobId { get; set; }
        public string jobTitle { get; set; }
        public int minSalary { get; set; }
        public int maxSalary { get; set; }
        public override string ToString()
        {
            return $"JobID:{jobId}, Job Title:{jobTitle}, Min salary: {minSalary}, Max Salary: {maxSalary}";
        }
    }
    class Program

    {
        const string baseUrlString = "http://192.168.73.128:8080/dmit2015-fall2018term-project-server-start/rest/humanResources-api/Jobs/";
        static void FetchJobsWebService()
        {
            var restClient = new HttpClient();
            restClient.BaseAddress = new Uri(baseUrlString);
            HttpResponseMessage response = restClient.GetAsync("").Result;
            if (response.IsSuccessStatusCode)
            {
                Console.WriteLine();
                Console.WriteLine("LIST ALL JOBS");
                var jsonString = response.Content.ReadAsStringAsync().Result;
                Job[] jobs = JsonConvert.DeserializeObject<Job[]>(jsonString);
                foreach (Job singlejob in jobs)
                {
                    Console.WriteLine(singlejob);
                }
            }
            else
            {
                Console.WriteLine("Server Error");
            }
        }
        static void FetchOneJobsWebService()
        {
            var restClient = new HttpClient();
            restClient.BaseAddress = new Uri(baseUrlString);
            Console.WriteLine();
            Console.WriteLine("FIND ONE JOB");
            Console.WriteLine("Enter Job ID: ");
            string jobId = Console.ReadLine();
            HttpResponseMessage response = restClient.GetAsync(jobId).Result;
            if (response.IsSuccessStatusCode)
            {
                var jsonString = response.Content.ReadAsStringAsync().Result;
                Job singleJob = JsonConvert.DeserializeObject<Job>(jsonString);

                Console.WriteLine(singleJob);

            }
            else
            {
                Console.WriteLine("Server Error");
            }
        }
        static void CreateOneJobsWebService()
        {
            var restClient = new HttpClient();
            restClient.BaseAddress = new Uri(baseUrlString);
            Console.WriteLine();
            Console.WriteLine("CREATE ONE JOB");
            Console.WriteLine("Enter Job ID: ");
            string jobId = Console.ReadLine();
            Console.WriteLine("Enter Job Title: ");
            string jobTitle = Console.ReadLine();
            Console.WriteLine("Enter Job Min Salary: ");
            int jobMin = int.Parse(Console.ReadLine());
            Console.WriteLine("Enter Job Max Salary: ");
            int jobMax = int.Parse(Console.ReadLine());

            Job newJob = new Job()
            {
                jobId = jobId,
                jobTitle = jobTitle,
                minSalary = jobMin,
                maxSalary = jobMax
                
            };
            string jasonString = JsonConvert.SerializeObject(newJob);

            HttpResponseMessage response = restClient.PostAsync(baseUrlString,new StringContent(jasonString , System.Text.Encoding.UTF8,"application/json")).Result;
            if (response.IsSuccessStatusCode)
            {
                Console.WriteLine("Success, Job created;");

            }
            else
            {
                Console.WriteLine("Server Error");
            }
        }
        static void UpdateOneJobsWebService()
        {
            var restClient = new HttpClient();
            restClient.BaseAddress = new Uri(baseUrlString);
            Console.WriteLine();
            Console.WriteLine("UPDATE ONE JOB");
            Console.WriteLine("Enter Job ID: ");
            string jobId = Console.ReadLine();
            Console.WriteLine("Enter Job Title: ");
            string jobTitle = Console.ReadLine();
            Console.WriteLine("Enter Job Min Salary: ");
            int jobMin = int.Parse(Console.ReadLine());
            Console.WriteLine("Enter Job Max Salary: ");
            int jobMax = int.Parse(Console.ReadLine());

            Job newJob = new Job()
            {
                jobId = jobId,
                jobTitle = jobTitle,
                minSalary = jobMin,
                maxSalary = jobMax

            };
            string jasonString = JsonConvert.SerializeObject(newJob);

            HttpResponseMessage response = restClient.PutAsync(baseUrlString, new StringContent(jasonString, System.Text.Encoding.UTF8, "application/json")).Result;
            if (response.IsSuccessStatusCode)
            {
                Console.WriteLine("Update Success, Job Updated;");

            }
            else
            {
                Console.WriteLine("Server Error");
            }
        }
        static void DeleteOneJobsWebService()
        {
            var restClient = new HttpClient();
            restClient.BaseAddress = new Uri(baseUrlString);
            Console.WriteLine();
            Console.WriteLine("DELETE JOB");
            Console.WriteLine("Enter Job ID: ");
            string jobId = Console.ReadLine();
            HttpResponseMessage response = restClient.DeleteAsync(jobId).Result;
            if (response.IsSuccessStatusCode)
            {
                //var jsonString = response.Content.ReadAsStringAsync().Result;
                //Job singleJob = JsonConvert.DeserializeObject<Job>(jsonString);

                Console.WriteLine("Delete Success");

            }
            else
            {
                Console.WriteLine("Server Error");
            }
        }
        static void Main(string[] args)
        {
            //show all jobs 
            FetchJobsWebService();
            
            //create one job
            CreateOneJobsWebService();

            //show all jobs 
            FetchJobsWebService();

            //find one
            FetchOneJobsWebService();

            //update one job
            UpdateOneJobsWebService();

            //show all jobs 
            FetchJobsWebService();

            //Delete one job
            DeleteOneJobsWebService();

            //show all jobs 
            FetchJobsWebService();



            
            
        }
    }
}
