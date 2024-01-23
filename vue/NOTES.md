‘L’ routes (rapid transit train services) are identified as follows:

Red = Red Line (Howard-95th/Dan Ryan service)
Blue = Blue Line (O’Hare-Forest Park service)
Brn = Brown Line (Kimball-Loop service)
G = Green Line (Harlem/Lake-Ashland/63rd-Cottage Grove service)
Org = Orange Line (Midway-Loop service)
P = Purple Line (Linden-Howard shuttle service)
Pink = Pink Line (54th/Cermak-Loop service)
Y = Yellow Line (Skokie-Howard [Skokie Swift] shuttle service)

Arrivals API
http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx

Follow This Train API
http://lapi.transitchicago.com/api/1.0/ttfollow.aspx

Locations API:
http://lapi.transitchicago.com/api/1.0/ttpositions.aspx

Logan Square parent stop id:
41020

O'Hare bound:
30197

Forest Park bound:
30198

Route direction code:
Blue Line
1 = O’Hare-bound
5 = Forest Park-bound

The “isDly” element is an expression of whether or not we’ve detected the likelihood that a train is delayed. If the value of isDly = 1, then consider indicating that the train is delayed rather than simply representing the last prediction, which might be growing stale (which you could compare timestamps to determine, independently)

The isFlt element in the results indicates what we call a “schedule fault” in the context of Train Tracker. A fault on an ETA that is schedule-based (isSch=1) indicates that the scheduled arrival time given might not be feasible to serve due to the lack of a scheduled departure having occurred. Our system is designed to do some math in order to calculate whether or not a scheduled arrival is feasible based on minimum travel times from a terminal to where the arrival is being estimated for.

To calculate the number of minutes until arrival (so you can say “4 min” instead of 2:35 p.m., for example, we recommend comparing arrT to prdt – this will give you the number of minutes we calculated from when a train last moved into a new track circuit until when it should get to a station.