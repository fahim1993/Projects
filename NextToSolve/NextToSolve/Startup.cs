using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(NextToSolve.Startup))]
namespace NextToSolve
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
