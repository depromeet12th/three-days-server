package com.depromeet.threedays.front.web.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingHandler {
	private static final String LOG_MESSAGE_FORMAT = "{} '{}' - {}";
	private static final String UNCAUGHT_LOG_MESSAGE = "??";
	private static final Set<String> IGNORE_REQUEST_URI_LIST =
			new HashSet<>(
					Arrays.asList(
							"/",
							"/.aws/credentials",
							"/.env",
							"/.env.",
							"/.env.backup",
							"/.env.bak",
							"/.env.dev",
							"/.env.dev.local",
							"/.env.development.local",
							"/.env.ec2-3-34-67-210",
							"/.env.ec2-3-37-143-124",
							"/.env.example",
							"/.env.live",
							"/.env.local",
							"/.env.old",
							"/.env.prod",
							"/.env.prod.local",
							"/.env.production",
							"/.env.production.local",
							"/.env.save",
							"/.env.stage",
							"/.env.www",
							"/.env_1",
							"/.env_sample",
							"/.git/HEAD",
							"/.git/config",
							"/.git2/config",
							"/.svn/wc.db",
							"/.well-known/security.txt",
							"/1-200611214053U8.jpg",
							"/202110/images/public.css",
							"/6/",
							"/71624567",
							"/99vt",
							"/99vu",
							"/9h/",
							"/:80:undefined?id=",
							"/?XDEBUG_SESSION_START=phpstorm",
							"/AWSconf.git/config",
							"/Application/Buy/Static/js/if.js",
							"/Autodiscover/Autodiscover.xml",
							"/Autodiscover/autodiscover.json?a=margart@rodriguez.io/mapi/nspi/",
							"/Content/Wap/base.css",
							"/Content/css/wzwstylel.css",
							"/Content/favicon.ico",
							"/Content/m_1/js/m_1_Jquery.js",
							"/Core/Skin/Login.aspx",
							"/Css/Hm.css",
							"/HNAP1",
							"/Home/Bind/binding",
							"/Home/Get/getJnd28",
							"/Home/GetAllGameCategory",
							"/Home/GetInitSource",
							"/Home/Index/ajaxTJ",
							"/JS/loginstatus.js",
							"/Pay_Index.html",
							"/Pc/Lang/index.html",
							"/Public/Home/ecshe_css/main.css?v=1543997196",
							"/Public/Home/js/cls.js",
							"/Public/Mobile/ecshe_css/wapmain.css?v=1545408652",
							"/Public/Wchat/css/index.css",
							"/Public/Wchat/js/cvphp.js",
							"/Public/css/_pk10.css",
							"/Public/css/errorCss.css",
							"/Public/css/hall.css",
							"/Public/home/common/js/index.js",
							"/Public/home/js/check.js",
							"/Public/home/js/fukuang.js",
							"/Public/home/wap/css/qdgame.css",
							"/Public/initJs.php",
							"/Public/js/common.js",
							"/Public/uploads/web/step1.png",
							"/ReportServer",
							"/Res/font/font.css",
							"/Res/login.html",
							"/Scripts/common.js",
							"/SiteLoader",
							"/T9sA/static/image/t-06.jpg",
							"/Telerik.Web.UI.WebResource.axd?type=rau",
							"/Template/Mobile/js/main.js",
							"/Templates/user/finance/css/userPay.css",
							"/Templates/user/js/global.js",
							"/V5Wz",
							"/WuEL",
							"/__MACOSX/.git/config",
							"/_ignition/execute-solution",
							"/_profiler/empty/search/results",
							"/_profiler/phpinfo",
							"/_wpeprivate/config.json",
							"/a",
							"/a/.git/config",
							"/a/other/codepay/js/codepay_util.js",
							"/aaaaaaaaaaaaaaaaaaaaaaaaaqr",
							"/aab8",
							"/aab9",
							"/ab2g",
							"/ab2h",
							"/actuator",
							"/actuator/env",
							"/actuator/gateway/routes",
							"/admin",
							"/admin/",
							"/admin/.env",
							"/admin/.git/config",
							"/admin/assets/js/views/login.js",
							"/admin/config.php",
							"/admin/webadmin.php?mod=do&act=login",
							"/ajax/allcoin_a/id/0?t=0.3782499195965951",
							"/amphtml/.git/config",
							"/anquan/qgga.asp",
							"/aomanalyzer/.git/config",
							"/api",
							"/api/",
							"/api/.env",
							"/api/.git/config",
							"/api/GetConfigByKeys?keys=of_we",
							"/api/Index/getLottery",
							"/api/Index/getconfig",
							"/api/app-info",
							"/api/appVersion?mobile_system=2",
							"/api/apps",
							"/api/apps/config",
							"/api/auth",
							"/api/config",
							"/api/config-init",
							"/api/config/getwebsitename",
							"/api/config/info",
							"/api/config/proxy_servers?key=1",
							"/api/contactWay",
							"/api/currency/quotation_new",
							"/api/customerServiceLink",
							"/api/exclude/siteConfig/webSiteConfig",
							"/api/getUserCertificationStatus",
							"/api/getconfig.aspx",
							"/api/grame/getHomePtLottery",
							"/api/help_documents/nitice_list",
							"/api/hevent",
							"/api/home_index",
							"/api/ht/xy1",
							"/api/im/conf",
							"/api/im/v2/app/config",
							"/api/index",
							"/api/index/config",
							"/api/index/grailindex",
							"/api/index/index",
							"/api/index/webconfig",
							"/api/jsonws/",
							"/api/lastestBetLog",
							"/api/link/platform",
							"/api/linkPF",
							"/api/lottery/color",
							"/api/message/webInfo",
							"/api/notice",
							"/api/other/appSetting",
							"/api/pay/query_order",
							"/api/public/?service=Home.getConfig",
							"/api/shares/hqStrList",
							"/api/site/getInfo.do",
							"/api/st/index/?ac=2",
							"/api/stock/getSingleStock.do?code=002405",
							"/api/sys/var",
							"/api/system/system/config/get",
							"/api/system/systemConfigs/getCustomerServiceLink",
							"/api/uploads/apimap",
							"/api/user/dataDictionaryService/list",
							"/api/user/index",
							"/api/user/ismustmobile",
							"/api/user/mobilelogin",
							"/api/v/index/queryOfficePage?officeCode=customHomeLink",
							"/api/v1",
							"/api/v1/about",
							"/api/v1/app-info",
							"/api/v1/clients",
							"/api/v1/config",
							"/api/v1/notices?page=1&per_page=1&thread_name=general",
							"/api/v2/static/not.found",
							"/api/vue/transaction/config",
							"/apiApp/app/site/info",
							"/apiapp/Ygn_Girl.CityList",
							"/apis/apps/v1/namespaces/kube-system/daemonsets",
							"/app/",
							"/app/.git/config",
							"/app/common/getRegisterSet",
							"/app/config/getConfig",
							"/app/js/base.js",
							"/application.ini",
							"/application/.git/config",
							"/application/application.ini",
							"/application/configs/application.ini",
							"/appspec.yaml",
							"/appspec.yml",
							"/appxz/index.html",
							"/assets../.git/config",
							"/assets/AssetManifest.json",
							"/assets/app-manifest.json",
							"/assets/etc/query.css",
							"/assets/extension/market/css/mt4.css",
							"/assets/images/dy.jpg",
							"/assets/images/redian.jpg",
							"/assets/js/dmshub.js",
							"/assets/mstock/newimg/guid-info.png",
							"/assets/res/mods/room.js",
							"/assets/shebao/banner.png",
							"/assets/source/list.css",
							"/assets/yibao/Nweshebao/img/icon2.png",
							"/auth.asp",
							"/autodiscover/autodiscover.json",
							"/autodiscover/autodiscover.json?@test.com/owa/?&Email=autodiscover/autodiscover.json%3F@test.com",
							"/autodiscover/autodiscover.json?@zdi/Powershell",
							"/autodiscover/autodiscover.json?a..foo.var/owa/?&Email=autodiscover/autodiscover.json?a..foo.var&Protocol=XYZ&FooProtocol=%50owershell",
							"/aws.yml",
							"/backup/.git/config",
							"/banner.do?code=1",
							"/bapi/st/news/",
							"/baseConfig",
							"/bet/lotteryinfo/allLotteryInfoList",
							"/beta/.git/config",
							"/biz/server/config",
							"/blog/.git/config",
							"/blog/wp-content/themes/.git/config",
							"/build/.git/config",
							"/c/",
							"/c/version.js",
							"/cgi-bin/printenv.pl",
							"/chat.html",
							"/client/api/findConfigByKey?configKey=level_config",
							"/client/static/icon/hangqingicon.png",
							"/cms/.git/config",
							"/code/js/config.js",
							"/common.js",
							"/common/.git/config",
							"/common/member/js/user.util.js",
							"/common/template/lottery/lecai/css/style.css",
							"/config",
							"/config.js",
							"/config.js.backup",
							"/config.js.bak",
							"/config.js.old",
							"/config.js.save",
							"/config.json",
							"/config/application.ini",
							"/config/aws.yml",
							"/config/config.json",
							"/config/default.json",
							"/configs/application.ini",
							"/configuration.php-dist",
							"/console/",
							"/content../.git/config",
							"/core/.env",
							"/credentials/config.json",
							"/csjs/bankCheck.js",
							"/css../.git/config",
							"/css/all.css",
							"/css/m.css",
							"/css/main.css",
							"/css/nsc/reset.css",
							"/css/other.css",
							"/css/scanner.css",
							"/css/skin/ymPrompt.css",
							"/css/style.css",
							"/d1/OK.php",
							"/data/.git/config",
							"/data/json/config.json",
							"/data/ticker_24hr",
							"/database/.git/config",
							"/demo/.git/config",
							"/detail1/js/add.js",
							"/detaila/images/header_v1b.css",
							"/detaila/images/logo.png",
							"/dev/.git/config",
							"/developer/.git/config",
							"/ding/",
							"/dist/images/mask/bg1.jpg",
							"/dist/images/mask/guide/cn/step1.jpg",
							"/dist/index.html?v=32d9d4",
							"/dns-query?name=dnsscan.shadowserver.org&type=A",
							"/dot.git/config",
							"/download/file.ext",
							"/dqgqoeCXckuwPtxov",
							"/ecp/Current/exporttool/microsoft.exchange.ediscovery.exporttool.application",
							"/env.dev.js",
							"/env.development.js",
							"/env.js",
							"/env.prod.js",
							"/env.production.js",
							"/env.test.js",
							"/events../.git/config",
							"/evox/about",
							"/favicon-16x16.png",
							"/favicon-32x32.png",
							"/favicon.ico",
							"/fePublicInfo/",
							"/files/pub_rem.js",
							"/files/pub_reset.css",
							"/flock/.git/config",
							"/flu/403.html",
							"/friendGroup/list",
							"/front/index/getSiteSetting",
							"/gate.php",
							"/getConfig/getArticle.do?code=1",
							"/getConfig/getArticle.do?code=19",
							"/getConfig/listPopFrame.do?code=1&position=index&_=1601489645097",
							"/getConfig/listPopFrame.do?code=14&position=index&_=1601489645097",
							"/getLocale",
							"/git/.git/config",
							"/guocanju/shijiuling/",
							"/h5",
							"/h5/",
							"/h5/static/cert/icon_yanzhengma.png",
							"/h5/static/tabbar/txl.png",
							"/hetong/yq?id=500",
							"/home/",
							"/home/GetQrCodeInfo",
							"/home/banner/getLogo",
							"/home/help",
							"/home/login.jpg",
							"/home/login/login_index.html",
							"/home/main/login",
							"/homes/",
							"/hooked-center/config/offline/list",
							"/im/",
							"/im/App/config",
							"/im/h5/",
							"/im/in/GetUuid",
							"/images../.git/config",
							"/images/favicon.ico",
							"/images/no.jpg",
							"/images/src_images_but_dianz_s.png",
							"/img../.git/config",
							"/img/close.png",
							"/img/phonetrackeronline.png",
							"/img/subsidy/kh.png",
							"/img/xxing.png",
							"/img/zllqdk.png",
							"/index",
							"/index.php/User/sendsmscode",
							"/index.php/Wap/Api/getBanner",
							"/index.php/Wap/Api/getSystemNotice?id=1",
							"/index.php/sign",
							"/index.php?m=api&c=app&a=getPlatformConfig",
							"/index/",
							"/index/about/index.html",
							"/index/api/getconfig",
							"/index/aurl",
							"/index/common.css",
							"/index/gzid.js",
							"/index/home/login.html",
							"/index/images/tradeicon.png",
							"/index/index/getchatLog",
							"/index/index/info/",
							"/index/login",
							"/index/login/index",
							"/index/login/index.html",
							"/index/login/login",
							"/index/login/reg.html",
							"/index/login/register",
							"/index/newapi/api",
							"/index/open/login.html",
							"/index/police/index.html?agent=1000",
							"/index/user/get_server_info",
							"/index/user/login.html",
							"/index_files/bankCheck.js",
							"/infe/rest/fig/advertise/common.json?mobile_open=1",
							"/info.json",
							"/info.php",
							"/install.inc/vipsignInstall.css",
							"/ipl/app/flash/publicbmw/ball/FigLeaf.js?site=member",
							"/jiaoyimao/",
							"/jiaoyimao/default.css",
							"/jquery-3.3.1.slim.min.js",
							"/jquery-3.3.2.slim.min.js",
							"/js../.git/config",
							"/js/a.script",
							"/js/app.js",
							"/js/base1.js",
							"/js/basic.js",
							"/js/bk.min.js",
							"/js/config.js",
							"/js/home.js",
							"/js/index.js",
							"/js/lang.js",
							"/js/options.js",
							"/js/post.js/",
							"/js/pups.js",
							"/js/subsidy/bk.min.js",
							"/js/xz.js",
							"/jym-wn/",
							"/kkrp/site/info",
							"/kkrps/im_group/show_members",
							"/km.asmx/getPlatParam",
							"/langConfig.js",
							"/lanren/css/global.css",
							"/laravel/.env",
							"/leftDao.php?callback=jQuery183016740860980352856_1604309800583",
							"/lib../.git/config",
							"/live/.git/config",
							"/loan/css/index.css",
							"/login",
							"/login.html",
							"/login.php",
							"/lottery/lottery_list",
							"/m/",
							"/m/.git/config",
							"/m/allticker/1",
							"/m/env.js",
							"/mPlayer",
							"/main",
							"/manager/js/left.js",
							"/market/market-ws/iframe.html",
							"/masterControl/getSystemSetting",
							"/media../.git/config",
							"/melody/api/v1/pageconfig/list",
							"/member/js/lang_zh_CN.js",
							"/metrics",
							"/mg/other/codepay/js/codepay_util.js",
							"/mifs/.;/services/LogService",
							"/mindex.html",
							"/mmj/index.html",
							"/mobile",
							"/mobile/config.js",
							"/mobile/css/base.css",
							"/mobile/lists.html",
							"/mobile/login.html",
							"/mobile/lottery/list",
							"/mobile/v3/appSuperDownload.do",
							"/mobile21/js/index/gameManagement.js?v=7",
							"/myConfig.js",
							"/mytio/config/base",
							"/n9Im",
							"/new/.git/config",
							"/newApp/winMessTopQuery.php",
							"/nmaplowercheck1673396272",
							"/nmaplowercheck1673717885",
							"/nyyh/chkjs.js",
							"/nyyh/game.css",
							"/old-cuburn/.git/config",
							"/openApi/systemConfig/findSystemConfigById?id=3",
							"/otc/",
							"/owa",
							"/owa/",
							"/owa/auth/logon.aspx",
							"/owa/auth/logon.aspx?url=https%3a%2f%2f1%2fecp%2f",
							"/owa/auth/x.js",
							"/pages/console/js/common.js",
							"/ph_acquireSession",
							"/phalapi/public/",
							"/phalapi/public/?s=System.index",
							"/phone/images/icon_01.png",
							"/php-info",
							"/phpinfo",
							"/phpinfo.php",
							"/phpmyadmin/index.php",
							"/phpmyadmin4.8.5/index.php",
							"/platform",
							"/pmd/index.php",
							"/portal/index/protocol.html",
							"/prod.git/config",
							"/projectConfig",
							"/proxy/games",
							"/proxy/settings",
							"/public/.git/config",
							"/public/config.js",
							"/public/css/style.css",
							"/public/h5static/js/main.js",
							"/public/img/cz1.png",
							"/public/static/css/public.css",
							"/public/static/home/js/moblie/login.js",
							"/public/static/index/picture/img_33.png",
							"/public/wap/js/basis.js",
							"/qa/.git/config",
							"/reg.php",
							"/remote/login",
							"/repos/.git/config",
							"/repository/.git/config",
							"/res/common/addfriend.png",
							"/res/kuai/1.css",
							"/res/kuai/21.css",
							"/resource/home/css/person.css",
							"/resource/home/js/common.js",
							"/resources/css/headernav.css",
							"/resources/main/common.js",
							"/robots.txt",
							"/room/getRoomBangFans",
							"/room/script/face.js",
							"/s3/.git/config",
							"/s_api/basic/config_js?callback=__set_config",
							"/s_api/basic/download/info",
							"/saconfig/secure/yunwei.js",
							"/samples/.git/config",
							"/sdk",
							"/sdk.html",
							"/server/business/api/customer/list",
							"/service?action=getBasicInfo&terminal_id=2&token=&debug=true",
							"/shop/.git/config",
							"/showLogin.cc",
							"/site.js",
							"/site/.git/config",
							"/site/api/v1/site/vipExclusiveDomain/getGuestDomain",
							"/site/get-hq?proNo=btc&panType=1&pid=1",
							"/site/info",
							"/sitemap.xml",
							"/skin/js/common.js",
							"/skin/main/onload.js",
							"/solr/",
							"/source/20220119/static/wap/css/trade-history.css",
							"/source/20220119/static/wap/js/order.js",
							"/stage-api/common/configKey/all",
							"/staging/.git/config",
							"/stalker_portal/c/version.js",
							"/stalker_portal/server/tools/auth_simple.php",
							"/static../.git/config",
							"/static/.git/config",
							"/static/Mobile/js/common.js",
							"/static/_zxzx/bottom/index_bottom.shtml",
							"/static/admin/javascript/hetong.js",
							"/static/admincp/js/common.js",
							"/static/common/js/common.js",
							"/static/common/js/vant/vant.min.js",
							"/static/config.js",
							"/static/content.html",
							"/static/css/index.css",
							"/static/css/mobile.css",
							"/static/css/public.css",
							"/static/css/reset.css",
							"/static/customer/js/xiaotian.cli.v2.js",
							"/static/data/configjs.js",
							"/static/data/gamedatas.js",
							"/static/data/thirdgames.json",
							"/static/diff_worker.js",
							"/static/f_title.png",
							"/static/guide/ab.css",
							"/static/home/2022/index1.js",
							"/static/home/css/common.css",
							"/static/home/css/css.css",
							"/static/home/css/feiqi-ee5401a8e6.css",
							"/static/home/imgs/jkjl.png",
							"/static/home/imgs/pico.png",
							"/static/home/js/rooms.js",
							"/static/image/bg1.jpg",
							"/static/image/logo.png?v=4",
							"/static/images/login_bg.jpg",
							"/static/img/bitbeb-logo.png",
							"/static/index/3.png",
							"/static/index/css/trade-history.css",
							"/static/index/js/lk/order.js",
							"/static/js/app.js",
							"/static/js/config.js",
							"/static/js/download.js",
							"/static/js/user.js",
							"/static/js/view.js",
							"/static/local/img/userCenter/hourlyPrivilege.svg",
							"/static/m_text.png",
							"/static/mobile/user.html",
							"/static/tabBar/trade.png",
							"/static/v/v2/image/star.png",
							"/static/v2/css/index.css",
							"/static/voice/default.wav",
							"/static/wap/css/common.css",
							"/static/wap/css/index.css",
							"/static/wap/css/tipmask.css",
							"/static/wap/css/trade-history.css",
							"/static/wap/js/common.js",
							"/static/wap/js/order.js",
							"/statics/js/API.js",
							"/step1.asp",
							"/stock/mzhishu",
							"/stock/search.html?keyword=00202",
							"/stock2c1/api/site/getInfo.do",
							"/store/.git/config",
							"/stream/live.php",
							"/streaming/clients_live.php",
							"/sucai/zxkf.png",
							"/system_api.php",
							"/t000/login-img-3.svg",
							"/template/920ka/css/lsy.css",
							"/template/920ka/js/woodyapp.js",
							"/template/css/login.css",
							"/template/mb/lang/text-zh.json",
							"/template/tmp1/js/common.js",
							"/test.php",
							"/themes/pay/assets/core.css",
							"/thriveGame.css",
							"/uis/app/get/config",
							"/uploads/20210928/01737e4f85f971bdf4d892dfaee6579c.png",
							"/uploads/admin/202103/60584c5b88017.png",
							"/user/Login",
							"/user/getAllNicknames",
							"/uw79",
							"/v1/getConfig",
							"/v1/management/tenant/getSpeedDomain",
							"/v2/",
							"/v2/block/home/app/hot",
							"/v2/game/rooms",
							"/v6/getAppContent",
							"/vendor/.git/config",
							"/vendor/phpunit/phpunit/src/Util/PHP/eval-stdin.php",
							"/verification.asp",
							"/version",
							"/video/65788.html",
							"/video/67950.html",
							"/views/commData/commonSite.js",
							"/views/home/home.js",
							"/wap",
							"/wap.html",
							"/wap/",
							"/wap/api/exchangerateuserconfig!get.action",
							"/wap/assets/images/icon1/12306.png",
							"/wap/forward",
							"/wap/trading/lastKlineParameter",
							"/waplogin.php",
							"/web/.git/config",
							"/webclient/",
							"/wiki/.git/config",
							"/wp-content/.git/config",
							"/wp-content/plugins/.git/config",
							"/wp-content/themes/.git/config",
							"/wp-includes/js/.git/config",
							"/wp-login.php",
							"/ws/index/getTheLotteryInitList",
							"/ws/market",
							"/xianyu/",
							"/xianyu/image/jiantou.png",
							"/xy/",
							"/xy/image/jiantou.png",
							"/yinjian/",
							"/yr99",
							"/ztp/cgi-bin/handle",
							"/zz/address.php?gid=651",
							"/zz2/address.php?gid=651"));

	public void writeLog(final Exception ex, final HttpServletRequest request) {
		if (needsIgnoreLogging(ex, request)) {
			return;
		}
		try {
			log.error(
					LOG_MESSAGE_FORMAT, request.getMethod(), request.getRequestURI(), ex.getMessage(), ex);
		} catch (Exception e) {
			log.error(LOG_MESSAGE_FORMAT, UNCAUGHT_LOG_MESSAGE, UNCAUGHT_LOG_MESSAGE, e.getMessage(), e);
		}
	}

	private boolean needsIgnoreLogging(Exception exception, HttpServletRequest request) {
		return exception instanceof InsufficientAuthenticationException
				&& IGNORE_REQUEST_URI_LIST.contains(request.getRequestURI());
	}
}
