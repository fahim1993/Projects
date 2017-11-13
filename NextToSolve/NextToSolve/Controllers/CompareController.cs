using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web;
using System.Web.Mvc;
using Newtonsoft.Json;
using NextToSolve.Models;

namespace NextToSolve.Controllers
{
    public class CompareController : Controller
    {
        // GET: Compare
        public ActionResult Index()
        {
            Session.Remove("user");
            Session.Remove("acsubs");
            ViewBag.error = 0;
            ViewBag.showData = 0;
            return View();
        }

        [HttpPost]
        public async Task<ActionResult> Index(string handles) {
            ApplicationDbContext context = new ApplicationDbContext();
            ViewBag.error = 0;
            ViewBag.showData = 0;
            List < ViewModelUserInfo > infos = await getUserInfo(handles);
            if (infos == null) {
                ViewBag.error = 1;
            }
            else {
                foreach(ViewModelUserInfo vmUserInfo in infos) {
                    var acDict = await getAcSubs(vmUserInfo.Handle);
                    if (acDict != null) {
                        int point = 0;
                        foreach (string key in acDict.Keys) {
                            Problem p = context.Problems.SingleOrDefault(prb => prb.CfID == key);
                            if (p != null) {
                                point += p.NsPoint; 
                            }
                        } 
                        vmUserInfo.N2SPoint = point;
                    }
                    vmUserInfo.ProbSolved = acDict.Count;
                }
                ViewBag.showData = 1;
                ViewBag.infos = infos;
                ViewBag.handles = handles;
            }
            return View();
        }


        private async Task<Dictionary<string, int>> getAcSubs(string uid) {
            Dictionary<string, int> d = new Dictionary<string, int>();
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
                    return null;
                }
            }
            return d;
        }

        private async Task<List<ViewModelUserInfo>> getUserInfo(string handles) {

            using (var httpClient = new HttpClient()) {
                var json =
                    await httpClient.GetAsync("http://codeforces.com/api/user.info?handles=" + handles);

                if (json.IsSuccessStatusCode) {
                    string str = await json.Content.ReadAsStringAsync();
                    var a = JsonConvert.DeserializeObject<UserInfo>(str);

                    if (a.status == "OK") {
                        List<ViewModelUserInfo> userInfos = new List<ViewModelUserInfo>();
                        for (int i = 0; i < a.result.Count; i++) {
                            ViewModelUserInfo vmui = new ViewModelUserInfo();
                            Result1 rs = a.result[i];
                            vmui.Handle = rs.handle;
                            vmui.Rating = rs.rating;
                            vmui.MaxRating = rs.maxRating;
                            vmui.Rank = rs.rank;
                            vmui.Name = rs.firstName + " " + rs.lastName;
                            userInfos.Add(vmui);
                        }
                        return userInfos;
                    }
                    else
                        return null;
                }
                else {
                    return null;
                }
            } 
        }
    }

}