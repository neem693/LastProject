package util;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class MatchParsing {
	String str_url;
	
	
public static void main(String[] args) {
	String str="{\r\n" + 
			"  \"colgroup\": [],\r\n" + 
			"  \"headers\": [],\r\n" + 
			"  \"rows\": [\r\n" + 
			"    {\r\n" + 
			"      \"row\": [\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\"></li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\"></li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\"></li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">1</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180801&gameId=20180801LGOB0&section=REVIEW'><li>LG <b>8 : 14</b> µÎ»ê <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180801&gameId=20180801WOSK0&section=REVIEW'><li>³Ø¼¾ <b>8 : 14</b> SK <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180801&gameId=20180801KTHH0&section=REVIEW'><li>KT <b>3 : 4</b> ÇÑÈ­ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180801&gameId=20180801NCSS0&section=REVIEW'><li>NC <b>9 : 5</b> »ï¼º <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180801&gameId=20180801LTHT0&section=REVIEW'><li>·Ôµ¥ <b>1 : 8</b> KIA <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">2</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180802&gameId=20180802LGOB0&section=REVIEW'><li>LG <b>5 : 6</b> µÎ»ê <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180802&gameId=20180802WOSK0&section=REVIEW'><li>³Ø¼¾ <b>4 : 3</b> SK <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180802&gameId=20180802KTHH0&section=REVIEW'><li>KT <b>3 : 5</b> ÇÑÈ­ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180802&gameId=20180802NCSS0&section=REVIEW'><li>NC <b>2 : 3</b> »ï¼º <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180802&gameId=20180802LTHT0&section=REVIEW'><li>·Ôµ¥ <b>9 : 6</b> KIA <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">3</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">4</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180804&gameId=20180804SKLG0&section=REVIEW'><li>SK <b>9 : 2</b> LG <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180804&gameId=20180804NCHH0&section=REVIEW'><li>NC <b>7 : 5</b> ÇÑÈ­ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180804&gameId=20180804OBHT0&section=REVIEW'><li>µÎ»ê <b>5 : 13</b> KIA <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180804&gameId=20180804SSLT0&section=REVIEW'><li>»ï¼º <b>4 : 5</b> ·Ôµ¥ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180804&gameId=20180804WOKT0&section=REVIEW'><li>³Ø¼¾ <b>3 : 1</b> KT <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        }\r\n" + 
			"      ],\r\n" + 
			"      \"Class\": null,\r\n" + 
			"      \"OnClick\": null,\r\n" + 
			"      \"Style\": null,\r\n" + 
			"      \"Value\": null\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"row\": [\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">5</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180805&gameId=20180805SKLG0&section=REVIEW'><li>SK <b>12 : 3</b> LG <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180805&gameId=20180805NCHH0&section=REVIEW'><li>NC <b>10 : 8</b> ÇÑÈ­ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180805&gameId=20180805OBHT0&section=REVIEW'><li>µÎ»ê <b>3 : 6</b> KIA <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180805&gameId=20180805SSLT0&section=REVIEW'><li>»ï¼º <b>8 : 2</b> ·Ôµ¥ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180805&gameId=20180805WOKT0&section=REVIEW'><li>³Ø¼¾ <b>20 : 2</b> KT <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">6</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">7</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180807&gameId=20180807HTWO0&section=REVIEW'><li>KIA <b>1 : 9</b> ³Ø¼¾ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180807&gameId=20180807HHOB0&section=REVIEW'><li>ÇÑÈ­ <b>4 : 6</b> µÎ»ê <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180807&gameId=20180807SSSK0&section=REVIEW'><li>»ï¼º <b>10 : 8</b> SK <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180807&gameId=20180807KTNC0&section=REVIEW'><li>KT <b>12 : 10</b> NC <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180807&gameId=20180807LGLT0&section=REVIEW'><li>LG <b>3 : 4</b> ·Ôµ¥ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">8</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180808&gameId=20180808HTWO0&section=REVIEW'><li>KIA <b>6 : 7</b> ³Ø¼¾ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180808&gameId=20180808HHOB0&section=REVIEW'><li>ÇÑÈ­ <b>8 : 2</b> µÎ»ê <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180808&gameId=20180808SSSK0&section=REVIEW'><li>»ï¼º <b>0 : 12</b> SK <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180808&gameId=20180808KTNC0&section=REVIEW'><li>KT <b>3 : 7</b> NC <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180808&gameId=20180808LGLT0&section=REVIEW'><li>LG <b>1 : 2</b> ·Ôµ¥ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">9</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180809&gameId=20180809SSLG0&section=REVIEW'><li>»ï¼º <b>9 : 6</b> LG <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180809&gameId=20180809LTHT0&section=REVIEW'><li>·Ôµ¥ <b>11 : 4</b> KIA <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180809&gameId=20180809WOHH0&section=REVIEW'><li>³Ø¼¾ <b>16 : 5</b> ÇÑÈ­ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180809&gameId=20180809SKNC0&section=REVIEW'><li>SK <b>3 : 6</b> NC <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180809&gameId=20180809OBKT0&section=REVIEW'><li>µÎ»ê <b>4 : 2</b> KT <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">10</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180810&gameId=20180810SSLG0&section=REVIEW'><li>»ï¼º <b>10 : 12</b> LG <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><li class='rainCancel'>·Ôµ¥ : KIA [±¤ÁÖ]</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180810&gameId=20180810WOHH0&section=REVIEW'><li>³Ø¼¾ <b>9 : 4</b> ÇÑÈ­ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180810&gameId=20180810SKNC0&section=REVIEW'><li>SK <b>12 : 8</b> NC <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180810&gameId=20180810OBKT0&section=REVIEW'><li>µÎ»ê <b>1 : 7</b> KT <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">11</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180811&gameId=20180811LGWO0&section=REVIEW'><li>LG <b>8 : 13</b> ³Ø¼¾ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180811&gameId=20180811LTOB0&section=REVIEW'><li>·Ôµ¥ <b>2 : 5</b> µÎ»ê <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180811&gameId=20180811HTSK0&section=REVIEW'><li>KIA <b>18 : 4</b> SK <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180811&gameId=20180811KTHH0&section=REVIEW'><li>KT <b>3 : 5</b> ÇÑÈ­ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180811&gameId=20180811NCSS0&section=REVIEW'><li>NC <b>7 : 1</b> »ï¼º <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        }\r\n" + 
			"      ],\r\n" + 
			"      \"Class\": null,\r\n" + 
			"      \"OnClick\": null,\r\n" + 
			"      \"Style\": null,\r\n" + 
			"      \"Value\": null\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"row\": [\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">12</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180812&gameId=20180812LGWO0&section=REVIEW'><li>LG <b>3 : 11</b> ³Ø¼¾ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180812&gameId=20180812LTOB0&section=REVIEW'><li>·Ôµ¥ <b>12 : 11</b> µÎ»ê <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180812&gameId=20180812HTSK0&section=REVIEW'><li>KIA <b>21 : 8</b> SK <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180812&gameId=20180812KTHH0&section=REVIEW'><li>KT <b>4 : 5</b> ÇÑÈ­ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180812&gameId=20180812NCSS0&section=REVIEW'><li>NC <b>2 : 9</b> »ï¼º <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">13</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">14</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180814&gameId=20180814SKOB0&section=REVIEW'><li>SK <b>3 : 6</b> µÎ»ê <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180814&gameId=20180814WOSS0&section=REVIEW'><li>³Ø¼¾ <b>11 : 10</b> »ï¼º <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180814&gameId=20180814LGHT0&section=REVIEW'><li>LG <b>8 : 14</b> KIA <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180814&gameId=20180814HHLT0&section=REVIEW'><li>ÇÑÈ­ <b>4 : 9</b> ·Ôµ¥ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180814&gameId=20180814NCKT0&section=REVIEW'><li>NC <b>0 : 10</b> KT <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">15</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180815&gameId=20180815SKOB0&section=REVIEW'><li>SK <b>12 : 2</b> µÎ»ê <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180815&gameId=20180815WOSS0&section=REVIEW'><li>³Ø¼¾ <b>3 : 2</b> »ï¼º <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180815&gameId=20180815LGHT0&section=REVIEW'><li>LG <b>13 : 4</b> KIA <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><li class='rainCancel'>ÇÑÈ­ : ·Ôµ¥ [»çÁ÷]</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180815&gameId=20180815NCKT0&section=REVIEW'><li>NC <b>13 : 9</b> KT <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">16</li><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180816&gameId=20180816WOOB0&section=REVIEW'><li>³Ø¼¾ <b>2 : 8</b> µÎ»ê <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180816&gameId=20180816LGSK0&section=REVIEW'><li>LG <b>14 : 3</b> SK <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180816&gameId=20180816HHSS0&section=REVIEW'><li>ÇÑÈ­ <b>2 : 5</b> »ï¼º <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180816&gameId=20180816HTLT0&section=REVIEW'><li>KIA <b>6 : 8</b> ·Ôµ¥ <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a><a href='/Schedule/GameCenter/Main.aspx?gameDate=20180816&gameId=20180816NCKT0&section=REVIEW'><li>NC <b>3 : 5</b> KT <img src='//image.koreabaseball.com/client/images/schedule/cross.jpg' alt='¸®ºä' style='vertical-align:middle;' /></li></a>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">17</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">18</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        }\r\n" + 
			"      ],\r\n" + 
			"      \"Class\": null,\r\n" + 
			"      \"OnClick\": null,\r\n" + 
			"      \"Style\": null,\r\n" + 
			"      \"Value\": null\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"row\": [\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">19</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">20</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">21</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">22</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">23</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">24</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">25</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        }\r\n" + 
			"      ],\r\n" + 
			"      \"Class\": null,\r\n" + 
			"      \"OnClick\": null,\r\n" + 
			"      \"Style\": null,\r\n" + 
			"      \"Value\": null\r\n" + 
			"    },\r\n" + 
			"    {\r\n" + 
			"      \"row\": [\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">26</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">27</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">28</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">29</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">30</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\">31</li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"          \"Text\": \"<li class=\\\"dayNum\\\"></li>\",\r\n" + 
			"          \"Class\": \"endGame\",\r\n" + 
			"          \"Scope\": null,\r\n" + 
			"          \"RowSpan\": null,\r\n" + 
			"          \"ColSpan\": null,\r\n" + 
			"          \"Width\": null,\r\n" + 
			"          \"TypeObj\": null\r\n" + 
			"        }\r\n" + 
			"      ],\r\n" + 
			"      \"Class\": null,\r\n" + 
			"      \"OnClick\": null,\r\n" + 
			"      \"Style\": null,\r\n" + 
			"      \"Value\": null\r\n" + 
			"    }\r\n" + 
			"  ],\r\n" + 
			"  \"tfoot\": [],\r\n" + 
			"  \"totalCnt\": null,\r\n" + 
			"  \"headerClass\": null,\r\n" + 
			"  \"tbodyClass\": null,\r\n" + 
			"  \"tfootClass\": null,\r\n" + 
			"  \"title\": null,\r\n" + 
			"  \"caption\": null,\r\n" + 
			"  \"result_cd\": null,\r\n" + 
			"  \"result_msg\": null,\r\n" + 
			"  \"code\": null,\r\n" + 
			"  \"msg\": null\r\n" + 
			"}";

//	System.out.println(str);
	JSONObject json = new JSONObject(str);
	//System.out.println(json.getString("rows"));
	//System.out.println(set);
	
	JSONObject array_rows = json.getJSONArray("rows").getJSONObject(0);
	System.out.println(array_rows.getJSONArray("row").getJSONObject(3));
	
	
	
	
	
 
}	
}
