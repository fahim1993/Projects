using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NextToSolve.Models {
    public class TagMin {
        public int Id { get; set; }
        public string Name { get; set; }
        public int ProblemCount { get; set; }
        public int Levels { get; set; }
    }
}