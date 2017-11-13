using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NextToSolve.Models {
    public class Tag {

        public Tag() {
            Problems = new List<Problem>();
        }

        public int Id { get; set; } 
        public virtual ICollection<Problem> Problems { get; set; }


    }
}