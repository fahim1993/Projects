using System;
using System.Collections.Generic;
using System.Data.Entity.Migrations;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using NextToSolve.Models;

namespace NextToSolve.Controllers {
    public class HomeController : Controller {
        public ActionResult Index() {
            RemoveSessions();
            return View();
        }

        private void RemoveSessions() {
            Session.Remove("user");
            Session.Remove("acsubs");
        }

       /*
        public ActionResult ResetProblemCount() {

            ApplicationDbContext db = new ApplicationDbContext();
            for (int i = 1; i <= 36; i++) {
                var tag = db.TagMins.Single(v => v.Id == i);
                tag.ProblemCount = db.Tags.Single(v => v.Id == i).Problems.Count;
                db.SaveChanges();
            }
     
            return Content("DONE");
        }
       */

       /*
         
        public ActionResult SeedData() {

            ApplicationDbContext db = new ApplicationDbContext();

            for (int i = 1; i <= 36; i++) {
                Tag t = new Tag() {Id = i};
                db.Tags.Add(t);
                db.SaveChanges();
            }


            string text = System.IO.File.ReadAllText(System.Web.HttpContext.Current.Server.MapPath(@"~/App_Data/ProblemData")).ToString();
            string[] str = text.Split('\n');
            for (int i=1; i<= str.Length; i++) {
                string s = str[i - 1];
                string xx = s.Replace("\r", string.Empty);
                if (xx.Length == 0) continue;
                string[] st = xx.Split('~');
                if(st[0].Length == 0) continue;
                Problem p = new Problem {
                    Id = i,
                    CfName = st[0], CfID = st[1], CfLink = st[2],
                    CfPoint = Double.Parse(st[3]), NsPoint = Int32.Parse(st[4]),
                    NsLevel = Int32.Parse(st[5])
                };

                db.Problems.AddOrUpdate(p);
                db.SaveChanges();

                string[] tg = st[6].Split(',');

                foreach (string t in tg) {
                    if(t == string.Empty)continue;
                    int tId = Int32.Parse(t);
                    var tt = db.Tags.Single(a => a.Id == tId);
                    tt.Problems.Add(p);
                    db.SaveChanges();
                }
            }
            return  Content("DONE");
        }
        
      */
    }
}