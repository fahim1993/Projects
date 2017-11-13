using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace NextToSolve.Models {
    public class Prob {
        public int contestId { get; set; }
        public string index { get; set; }
        public string name { get; set; }
        public string type { get; set; }
        public double points { get; set; }
        public List<string> tags { get; set; }
    }


    public class Member {
        public string handle { get; set; }
    }

    public class Author {
        public int contestId { get; set; }
        public List<Member> members { get; set; }
        public string participantType { get; set; }
        public bool ghost { get; set; }
        public int room { get; set; }
        public int startTimeSeconds { get; set; }
    }

    public class Result {
        public int id { get; set; }
        public int contestId { get; set; }
        public int creationTimeSeconds { get; set; }
        public int relativeTimeSeconds { get; set; }
        public Prob problem { get; set; }
        public Author author { get; set; }
        public string programmingLanguage { get; set; }
        public string verdict { get; set; }
        public string testset { get; set; }
        public int passedTestCount { get; set; }
        public int timeConsumedMillis { get; set; }
        public int memoryConsumedBytes { get; set; }
    }

    public class UserStatusObject {
        public string status { get; set; }
        public List<Result> result { get; set; }
    }

    public class Result1 {
        public string lastName { get; set; }
        public string country { get; set; }
        public int lastOnlineTimeSeconds { get; set; }
        public string city { get; set; }
        public int rating { get; set; }
        public int friendOfCount { get; set; }
        public string titlePhoto { get; set; }
        public string handle { get; set; }
        public string avatar { get; set; }
        public string firstName { get; set; }
        public int contribution { get; set; }
        public string organization { get; set; }
        public string rank { get; set; }
        public int maxRating { get; set; }
        public int registrationTimeSeconds { get; set; }
        public string maxRank { get; set; }
    }

    public class UserInfo {
        public string status { get; set; }
        public List<Result1> result { get; set; }
    }
}