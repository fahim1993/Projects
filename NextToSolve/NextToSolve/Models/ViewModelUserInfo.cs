using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NextToSolve.Models {
    public class ViewModelUserInfo {

        public ViewModelUserInfo() { 
        }

        public int Rating { get; set; }
        public int MaxRating { get; set; }
        public int ProbSolved { get; set; }
        public string Name { get; set; }
        public int N2SPoint { get; set; }
        public string Rank { get; set; } 
        public string Handle { get; set; }

    }
}