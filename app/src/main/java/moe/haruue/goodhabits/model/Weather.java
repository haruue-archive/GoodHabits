package moe.haruue.goodhabits.model;

import java.util.List;

/**
 * Created by simonla on 2016/8/17.
 * Have a good day.
 */
public class Weather {

    /**
     * aqi : {"city":{"aqi":"69","co":"1","no2":"52","o3":"49","pm10":"86","pm25":"45","qlty":"良","so2":"11"}}
     * basic : {"city":"重庆","cnty":"中国","id":"CN101040100","lat":"29.581000","lon":"106.549000","update":{"loc":"2016-08-17 23:51","utc":"2016-08-17 15:51"}}
     * daily_forecast : [{"astro":{"sr":"06:23","ss":"19:32"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-08-17","hum":"41","pcpn":"0.0","pop":"0","pres":"996","tmp":{"max":"40","min":"29"},"vis":"10","wind":{"deg":"102","dir":"无持续风向","sc":"微风","spd":"6"}},{"astro":{"sr":"06:23","ss":"19:31"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-08-18","hum":"40","pcpn":"0.0","pop":"0","pres":"997","tmp":{"max":"40","min":"28"},"vis":"10","wind":{"deg":"52","dir":"无持续风向","sc":"微风","spd":"5"}},{"astro":{"sr":"06:24","ss":"19:30"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-08-19","hum":"39","pcpn":"0.0","pop":"0","pres":"998","tmp":{"max":"39","min":"29"},"vis":"10","wind":{"deg":"352","dir":"无持续风向","sc":"微风","spd":"4"}},{"astro":{"sr":"06:24","ss":"19:29"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-08-20","hum":"39","pcpn":"0.1","pop":"75","pres":"998","tmp":{"max":"39","min":"28"},"vis":"10","wind":{"deg":"20","dir":"无持续风向","sc":"微风","spd":"3"}},{"astro":{"sr":"06:25","ss":"19:28"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-08-21","hum":"37","pcpn":"0.2","pop":"65","pres":"1000","tmp":{"max":"38","min":"29"},"vis":"10","wind":{"deg":"82","dir":"无持续风向","sc":"微风","spd":"4"}},{"astro":{"sr":"06:25","ss":"19:27"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-08-22","hum":"39","pcpn":"0.0","pop":"58","pres":"1002","tmp":{"max":"39","min":"29"},"vis":"10","wind":{"deg":"94","dir":"无持续风向","sc":"微风","spd":"0"}},{"astro":{"sr":"06:26","ss":"19:26"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-08-23","hum":"39","pcpn":"0.0","pop":"0","pres":"1001","tmp":{"max":"39","min":"29"},"vis":"10","wind":{"deg":"110","dir":"无持续风向","sc":"微风","spd":"2"}}]
     * hourly_forecast : [{"date":"2016-08-18 01:00","hum":"65","pop":"0","pres":"998","tmp":"30","wind":{"deg":"131","dir":"东南风","sc":"微风","spd":"6"}},{"date":"2016-08-18 04:00","hum":"72","pop":"0","pres":"998","tmp":"28","wind":{"deg":"123","dir":"东南风","sc":"微风","spd":"5"}},{"date":"2016-08-18 07:00","hum":"71","pop":"0","pres":"999","tmp":"30","wind":{"deg":"118","dir":"东南风","sc":"微风","spd":"5"}},{"date":"2016-08-18 10:00","hum":"58","pop":"0","pres":"999","tmp":"35","wind":{"deg":"109","dir":"东南风","sc":"微风","spd":"5"}},{"date":"2016-08-18 13:00","hum":"44","pop":"0","pres":"998","tmp":"38","wind":{"deg":"72","dir":"东北风","sc":"微风","spd":"6"}},{"date":"2016-08-18 16:00","hum":"38","pop":"0","pres":"996","tmp":"39","wind":{"deg":"35","dir":"东北风","sc":"微风","spd":"7"}},{"date":"2016-08-18 19:00","hum":"47","pop":"0","pres":"996","tmp":"38","wind":{"deg":"220","dir":"西南风","sc":"微风","spd":"5"}},{"date":"2016-08-18 22:00","hum":"56","pop":"0","pres":"998","tmp":"36","wind":{"deg":"118","dir":"东南风","sc":"微风","spd":"4"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"35","hum":"48","pcpn":"0","pres":"999","tmp":"34","vis":"10","wind":{"deg":"140","dir":"东风","sc":"3-4","spd":"16"}}
     * status : ok
     * suggestion : {"comf":{"brf":"极不舒适","txt":"白天天气晴好、炎热，您会感到酷热难耐，极为不适，请注意防暑降温。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},"sport":{"brf":"较不宜","txt":"天气较好，但炎热，请注意适当减少运动时间并降低运动强度，户外运动请注意防晒。"},"trav":{"brf":"较不宜","txt":"天气较好，温度比较高，中午时段尽量不要外出，如外出可选择水上娱乐等清凉项目。并请注意防暑和防晒。"},"uv":{"brf":"很强","txt":"紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。"}}
     */

    private List<HeWeatherdataserviceBean> HeWeatherdataservice;

    public List<HeWeatherdataserviceBean> getHeWeatherdataservice() {
        return HeWeatherdataservice;
    }

    public void setHeWeatherdataservice(List<HeWeatherdataserviceBean> HeWeatherdataservice) {
        this.HeWeatherdataservice = HeWeatherdataservice;
    }

    public static class HeWeatherdataserviceBean {
        /**
         * city : {"aqi":"69","co":"1","no2":"52","o3":"49","pm10":"86","pm25":"45","qlty":"良","so2":"11"}
         */

        private AqiBean aqi;
        /**
         * city : 重庆
         * cnty : 中国
         * id : CN101040100
         * lat : 29.581000
         * lon : 106.549000
         * update : {"loc":"2016-08-17 23:51","utc":"2016-08-17 15:51"}
         */

        private BasicBean basic;
        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : 35
         * hum : 48
         * pcpn : 0
         * pres : 999
         * tmp : 34
         * vis : 10
         * wind : {"deg":"140","dir":"东风","sc":"3-4","spd":"16"}
         */

        private NowBean now;
        private String status;
        /**
         * comf : {"brf":"极不舒适","txt":"白天天气晴好、炎热，您会感到酷热难耐，极为不适，请注意防暑降温。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"}
         * sport : {"brf":"较不宜","txt":"天气较好，但炎热，请注意适当减少运动时间并降低运动强度，户外运动请注意防晒。"}
         * trav : {"brf":"较不宜","txt":"天气较好，温度比较高，中午时段尽量不要外出，如外出可选择水上娱乐等清凉项目。并请注意防暑和防晒。"}
         * uv : {"brf":"很强","txt":"紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。"}
         */

        private SuggestionBean suggestion;
        /**
         * astro : {"sr":"06:23","ss":"19:32"}
         * cond : {"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"}
         * date : 2016-08-17
         * hum : 41
         * pcpn : 0.0
         * pop : 0
         * pres : 996
         * tmp : {"max":"40","min":"29"}
         * vis : 10
         * wind : {"deg":"102","dir":"无持续风向","sc":"微风","spd":"6"}
         */

        private List<DailyForecastBean> daily_forecast;
        /**
         * date : 2016-08-18 01:00
         * hum : 65
         * pop : 0
         * pres : 998
         * tmp : 30
         * wind : {"deg":"131","dir":"东南风","sc":"微风","spd":"6"}
         */

        private List<HourlyForecastBean> hourly_forecast;

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyForecastBean> getHourly_forecast() {
            return hourly_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastBean> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }

        public static class AqiBean {
            /**
             * aqi : 69
             * co : 1
             * no2 : 52
             * o3 : 49
             * pm10 : 86
             * pm25 : 45
             * qlty : 良
             * so2 : 11
             */

            private CityBean city;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public static class CityBean {
                private String aqi;
                private String co;
                private String no2;
                private String o3;
                private String pm10;
                private String pm25;
                private String qlty;
                private String so2;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }
        }

        public static class BasicBean {
            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            /**
             * loc : 2016-08-17 23:51
             * utc : 2016-08-17 15:51
             */

            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class NowBean {
            /**
             * code : 101
             * txt : 多云
             */

            private CondBean cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            /**
             * deg : 140
             * dir : 东风
             * sc : 3-4
             * spd : 16
             */

            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class CondBean {
                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBean {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class SuggestionBean {
            /**
             * brf : 极不舒适
             * txt : 白天天气晴好、炎热，您会感到酷热难耐，极为不适，请注意防暑降温。
             */

            private ComfBean comf;
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            private CwBean cw;
            /**
             * brf : 炎热
             * txt : 天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。
             */

            private DrsgBean drsg;
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
             */

            private FluBean flu;
            /**
             * brf : 较不宜
             * txt : 天气较好，但炎热，请注意适当减少运动时间并降低运动强度，户外运动请注意防晒。
             */

            private SportBean sport;
            /**
             * brf : 较不宜
             * txt : 天气较好，温度比较高，中午时段尽量不要外出，如外出可选择水上娱乐等清凉项目。并请注意防暑和防晒。
             */

            private TravBean trav;
            /**
             * brf : 很强
             * txt : 紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。
             */

            private UvBean uv;

            public ComfBean getComf() {
                return comf;
            }

            public void setComf(ComfBean comf) {
                this.comf = comf;
            }

            public CwBean getCw() {
                return cw;
            }

            public void setCw(CwBean cw) {
                this.cw = cw;
            }

            public DrsgBean getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgBean drsg) {
                this.drsg = drsg;
            }

            public FluBean getFlu() {
                return flu;
            }

            public void setFlu(FluBean flu) {
                this.flu = flu;
            }

            public SportBean getSport() {
                return sport;
            }

            public void setSport(SportBean sport) {
                this.sport = sport;
            }

            public TravBean getTrav() {
                return trav;
            }

            public void setTrav(TravBean trav) {
                this.trav = trav;
            }

            public UvBean getUv() {
                return uv;
            }

            public void setUv(UvBean uv) {
                this.uv = uv;
            }

            public static class ComfBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CwBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class DrsgBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class FluBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class SportBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class TravBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class UvBean {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * sr : 06:23
             * ss : 19:32
             */

            private AstroBean astro;
            /**
             * code_d : 100
             * code_n : 101
             * txt_d : 晴
             * txt_n : 多云
             */

            private CondBean cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            /**
             * max : 40
             * min : 29
             */

            private TmpBean tmp;
            private String vis;
            /**
             * deg : 102
             * dir : 无持续风向
             * sc : 微风
             * spd : 6
             */

            private WindBean wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                private String sr;
                private String ss;

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBean {
                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBean {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class HourlyForecastBean {
            private String date;
            private String hum;
            private String pop;
            private String pres;
            private String tmp;
            /**
             * deg : 131
             * dir : 东南风
             * sc : 微风
             * spd : 6
             */

            private WindBean wind;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class WindBean {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
