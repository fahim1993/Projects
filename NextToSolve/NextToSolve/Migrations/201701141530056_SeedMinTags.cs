namespace NextToSolve.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class SeedMinTags : DbMigration
    {
        public override void Up()
        {
            Sql("SET IDENTITY_INSERT [dbo].[TagMins] on");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (1, 'implementation', 1076, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (2, 'dp', 702, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (3, 'math', 659, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (4, 'greedy', 652, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (5, 'data structures', 493, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (6, 'brute force', 492, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (7, 'no tags', 372, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (8, 'constructive algorithms', 382, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (9, 'dfs and similar', 323, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (10, 'sortings', 308, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (11, 'binary search', 304, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (12, 'graphs', 261, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (13, 'strings', 200, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (14, 'number theory', 193, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (15, 'trees', 192, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (16, 'combinatorics', 184, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (17, 'geometry', 168, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (18, 'two pointers', 135, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (19, 'dsu', 118, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (20, 'bitmasks', 109, 10)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (21, 'probabilities', 96, 9)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (22, 'shortest paths', 88, 9)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (23, 'hashing', 76, 8)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (24, 'divide and conquer', 66, 8)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (25, 'games', 64, 8)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (26, 'matrices', 52, 7)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (27, 'flows', 43, 6)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (28, 'string suffix structures', 40, 6)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (29, 'graph matchings', 29, 5)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (30, 'expression parsing', 28, 5)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (31, 'ternary search', 20, 4)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (32, 'meet-in-the-middle', 14, 3)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (33, '2-sat', 9, 3)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (34, 'fft', 9, 3)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (35, 'chinese remainder theorem', 7, 2)");
            Sql("INSERT INTO TagMins (Id, Name, ProblemCount, Levels) VALUES (36, 'schedules', 6, 2)");
            Sql("SET IDENTITY_INSERT [dbo].[Tags] off");
        }
        
        public override void Down()
        {
        }
    }
}
