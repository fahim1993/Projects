using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Reflection;
using System.Web;
using System.Web.Mvc;
using NextToSolve.Models;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace NextToSolve.Controllers
{
    public class PracticeController : Controller
    {
        // GET: Practice
        public async Task<ActionResult> Index()
        {
            ApplicationDbContext context = new ApplicationDbContext();

            var allProbs = context.Problems.ToList();
            TempData["currentPage"] = "/Practice";
            TempData["totalPoint"] = context.Problems.Sum( i=> i.NsPoint);

            var user = Session["user"]?.ToString() ?? "-1";
            TempData["handle"] = user;
             
            Dictionary<string, int> acSubs = new Dictionary<string, int>();
            if (user != "-1") {
                if (Session["acsubs"] != null) {
                    acSubs = (Dictionary<string, int>) Session["acsubs"];
                }
                else {
                    var users = user.Split(',');
                    Session.Remove("fetchError");
                    foreach (string u in users)
                        acSubs = await getAcSubs(u, acSubs);

                    Session["acsubs"] = acSubs;

                    int userPoints = 0;
                    foreach (var prb in allProbs) {
                        if (acSubs.ContainsKey(prb.CfID))
                            userPoints += prb.NsPoint;
                    }
                    TempData["userPoints"] = userPoints;
                }
            }
            else {
                Session.Remove("fetchError");
            }

            var tags = context.TagMins.ToList();
            TempData["c1"] = context.Problems.Count(i => i.CfPoint < 500);
            TempData["c2"] = context.Problems.Count(i => i.CfPoint >= 500 && i.CfPoint<1000);
            TempData["c3"] = context.Problems.Count(i => i.CfPoint >= 1000 && i.CfPoint<1500);
            TempData["c4"] = context.Problems.Count(i => i.CfPoint >= 1500 && i.CfPoint<2000);
            TempData["c5"] = context.Problems.Count(i => i.CfPoint >= 2000 && i.CfPoint<2500);
            TempData["c6"] = context.Problems.Count(i => i.CfPoint >= 2500);


            return View(tags);
        }



        public async Task<ActionResult> TagBased(int tagId, int? level) {
            

            TempData["PrevUrl"] = Request.Url.ToString();

            ApplicationDbContext context = new ApplicationDbContext();

            var user = Session["user"]?.ToString() ?? "-1";

            Dictionary<string, int> acSubs = new Dictionary<string, int>();
            if (user != "-1") {
                if (Session["acsubs"] != null) {
                    acSubs = (Dictionary<string, int>) Session["acsubs"];
                }
                else {
                    var users = user.Split(',');
                    foreach (string u in users)
                        acSubs = await getAcSubs(u, acSubs);

                    Session["acsubs"] = acSubs;
                }
                var problems = context.Problems.ToList();
                int userPoints = 0;
                foreach (var prb in problems) {
                    if (acSubs.ContainsKey(prb.CfID))
                        userPoints += prb.NsPoint;
                }
                ViewBag.userPoints = userPoints;
            }
            
            ViewBag.handle = user;

            string cpage = "/Practice/TagBased?tagid=" + tagId;
            if (level != null)
                cpage = "/Practice/TagBased?tagid=" +tagId+"<>level="+level; 

            TempData["currentPage"] = cpage;

            List<Problem> acList = new List<Problem>();
            List<Problem> nsList = new List<Problem>();
            var tag = context.TagMins.Single(i => i.Id == tagId);
            var tagProblems = context.Tags.Single(i => i.Id == tagId);

            ViewBag.Title = tag.Name.ToString().ToUpper();

            int totalPoints = 0;
            if (user == "-1") {
                foreach (var prb in tagProblems.Problems) {
                    nsList.Add(prb);
                    totalPoints += prb.NsPoint;
                }
                ViewBag.totalPoints = totalPoints;
            }

            else {
                int[] levelPoints = Enumerable.Repeat(0, 10).ToArray();


                ViewBag.toSolve = tagProblems.Problems;

                int userPoints = 0;

                List<Problem>[] lvlProbs = new List<Problem>[tag.Levels];

                for (int x = 0; x < tag.Levels; x++)
                    lvlProbs[x] = new List<Problem>();


                foreach (var prb in tagProblems.Problems) {
                    levelPoints[prb.NsLevel - 1] += (prb.NsPoint / 5);

                    lvlProbs[prb.NsLevel - 1].Add(prb);

                    totalPoints += prb.NsPoint;

                    if (acSubs.ContainsKey(prb.CfID))
                        userPoints += prb.NsPoint;
                }
                ViewBag.totalPoints = totalPoints;

                int val = levelPoints[0], it = 1;
                for (; it < tag.Levels; it++) {
                    if (userPoints < val) break;
                    val += levelPoints[it];
                }

                var userLevel = it;

                it = level ?? it;
                
                List<SelectListItem> items = new List<SelectListItem>();
                for (int i = 1; i <= tag.Levels; i++) {
                    items.Add(i == it
                        ? new SelectListItem() {Selected = true, Text = i.ToString(), Value = i.ToString()}
                        : new SelectListItem() {Text = i.ToString(), Value = i.ToString()});
                }

                ViewBag.ddItems = items;
                ViewBag.currentTag = tagId;

                ViewBag.userPoints = userPoints;
                ViewBag.userLevel = userLevel;

                if (userLevel == tag.Levels)
                    ViewBag.pointsToNextLevel = -1;
                else {
                    ViewBag.pointsToNextLevel = val - userPoints;
                }
                
                foreach (var prb in lvlProbs[it - 1]) {
                    if (acSubs.ContainsKey(prb.CfID)) {
                        acList.Add(prb);
                    }
                    else {
                        nsList.Add(prb);
                    }

                }
            }
            
            ViewBag.acList = acList;
            ViewBag.nsList = nsList;

            return View();

        }

        public async Task<ActionResult> PointBased(int lb, int hb) {

            string cpage = "/Practice/PointBased?lb=" + lb + "<>hb=" + hb;
            TempData["currentPage"] = cpage;

            if (lb == -1) lb = -9999999;
            if (hb == -1) hb = 9999999;
            
            ApplicationDbContext context = new ApplicationDbContext();

            var user = Session["user"]?.ToString() ?? "-1";

            Dictionary<string, int> acSubs = new Dictionary<string, int>();

            if (user != "-1") {
                if (Session["acsubs"] != null) {
                    acSubs = (Dictionary<string, int>)Session["acsubs"];
                }
                else {
                    var users = user.Split(',');
                    foreach (string u in users)
                        acSubs = await getAcSubs(u, acSubs);

                    Session["acsubs"] = acSubs;
                }
            }

            ViewBag.handle = user;
             
            var probs = context.Problems.Where( cfprb => cfprb.CfPoint>= lb && cfprb.CfPoint<hb).ToList();
            ViewBag.toSolve = probs;

            int userPoints = 0, totalPoints = 0;
            foreach (var prb in probs) { 
                if (acSubs.ContainsKey(prb.CfID))
                    userPoints += prb.NsPoint;
                totalPoints += prb.NsPoint;
            }

            ViewBag.userPoints = userPoints;
            ViewBag.totalPoints = totalPoints;

            List<Problem> acList = new List<Problem>();
            List<Problem> nsList = new List<Problem>();


            foreach (var prb in probs) {
                if (acSubs.ContainsKey(prb.CfID)) {
                    acList.Add(prb);
                }
                else {
                    nsList.Add(prb);
                }

            }

            ViewBag.acList = acList;
            ViewBag.nsList = nsList;

            return View();
        }


        [HttpParamAction]
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult AddHandle(string handle, string referer) {
            referer = referer.Replace("<>", "&");
            if (Session["user"] == null ) Session["user"] = handle;
            else {
                if (Session["user"].ToString() == "-1") Session["user"] = handle;
                else {
                    string u = Session["user"].ToString();
                    u += ("," + handle);
                    Session["user"] = u;
                }
            }
            Session.Remove("acsubs");
            return Redirect(referer);
        }

        [HttpParamAction]
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult ChangeLevel(string ddItems, string referer) {
           return Redirect("/Practice/TagBased?tagId="+referer+"&level="+ddItems);
        }

        public ActionResult RemoveHandle(int id, string referer) {
            referer = referer.Replace("<>", "&");
            string su = Session["user"].ToString();
            string nu = "";
            if (su != "-1") {
                var users = su.Split(',');
                if (users.Length == 1) Session["user"] = "-1";
                else {
                    for (int i = 0; i < users.Length; i++) {
                        if (i == id) continue;
                        if (nu.Length >= 1) nu += ",";
                        nu += users[i];
                    }
                    Session["user"] = nu;
                }

            } 
            Session.Remove("acsubs");
            return Redirect(referer);
        }

        public ActionResult Reload(string referer) {
            referer = referer.Replace("<>", "&");
            Session.Remove("acsubs");
            return Redirect(referer);

        }

        private async Task<Dictionary<string, int>> getAcSubs(string uid, Dictionary<string, int> d) {
          
            using (var httpClient = new HttpClient()) {
                var json =
                    await httpClient.GetAsync("http://codeforces.com/api/user.status?handle=" + uid);

                if (json.IsSuccessStatusCode) {
                    string str = await json.Content.ReadAsStringAsync();
                    var a = JsonConvert.DeserializeObject<UserStatusObject>(str);
                    foreach (Result r in a.result) {
                        if (r.verdict != "OK") continue;
                        string ci = r.problem.contestId.ToString();
                        ci += r.problem.index;
                        if (!d.ContainsKey(ci)) {
                            d.Add(ci, 1);
                        }
                    }
                }
                else {
                    Session["fetchError"] = 1;
                }
            }
            return d;
        }
    }

    public class HttpParamActionAttribute : ActionNameSelectorAttribute {
        public override bool IsValidName(ControllerContext controllerContext, string actionName, MethodInfo methodInfo) {
            if (actionName.Equals(methodInfo.Name, StringComparison.InvariantCultureIgnoreCase))
                return true;

            if (!actionName.Equals("Action", StringComparison.InvariantCultureIgnoreCase))
                return false;

            var request = controllerContext.RequestContext.HttpContext.Request;
            return request[methodInfo.Name] != null;
        }
    }
}