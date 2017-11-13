using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NextToSolve.Models {
    public class Problem {

        public Problem() {
            Tags = new List<Tag>();
        }

        public int Id { get; set; }
        public string CfName { get; set; }
        public string CfID { get; set; }
        public string CfLink  { get; set; }
        public double CfPoint { get; set; }
        public int NsPoint { get; set; }
        public int NsLevel { get; set; }
        public virtual ICollection<Tag> Tags { get; set; }
    }
}