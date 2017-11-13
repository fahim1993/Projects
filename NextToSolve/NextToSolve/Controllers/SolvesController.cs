using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Newtonsoft.Json;
using System.Net.Http;
using NextToSolve.Models;

namespace NextToSolve.Controllers {
    public class SolvesController : Controller {
        // GET: Solves
        public ActionResult Index() {
            return View();
        }
        [HttpPost]
        public async System.Threading.Tasks.Task<ActionResult> Index(string handle) {
            // ViewData["userhandle"] = handle;
            using (var httpClient = new HttpClient()) {
                string json1 = await httpClient.GetStringAsync("http://uhunt.felix-halim.net/api/uname2uid/?handle=" + handle + "&from=1&count=1000000");
                string json2 = await httpClient.GetStringAsync("http://uhunt.felix-halim.net/api/subs-user/json1");

                var a = JsonConvert.DeserializeObject<UserStatusObject>(json2);

                List<string> ls = new List<string>();

                //Dictionary<string, int> dictionary = new Dictionary<string, int>();

                /*int i = 1;
                string z = "";
                foreach (Result r in a.result) {
                    int test = 1;
                    if (r.verdict.ToString() == "OK")
                    {
                        
                        z = r.problem.contestId + "" + r.problem.index;

                        if (!dictionary.TryGetValue(z, out test))
                        {
                            dictionary.Add(z, 1);
                            ls.Add(z);
                        }
                    }
                    i++;
                    //ls.Add(z);
                }

                TempData["list"] = ls;
                int value = dictionary[z];*/
                Console.WriteLine(json1);
            }

            return View();
        }

    }
}