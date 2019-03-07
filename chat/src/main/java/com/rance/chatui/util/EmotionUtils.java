
package com.rance.chatui.util;

import com.rance.chatui.R;

import java.util.LinkedHashMap;

/**
 * 表情加载类,可自己添加多种表情，分别建立不同的map存放和不同的标志符即可
 */
public class EmotionUtils {

    /**
     * key-表情文字;
     * value-表情图片资源
     */
    public static LinkedHashMap<String, Integer> EMPTY_GIF_MAP;
    public static LinkedHashMap<String, Integer> EMOTION_STATIC_MAP;


    static {
        EMPTY_GIF_MAP = new LinkedHashMap<>();

        EMPTY_GIF_MAP.put("[微笑]", R.drawable.emotion_weixiao);
        EMPTY_GIF_MAP.put("[撇嘴]", R.drawable.emotion_biezui);
        EMPTY_GIF_MAP.put("[色]", R.drawable.emotion_se);
        EMPTY_GIF_MAP.put("[发呆]", R.drawable.emotion_fadai);
        EMPTY_GIF_MAP.put("[得意]", R.drawable.emotion_deyi);
        EMPTY_GIF_MAP.put("[流泪]", R.drawable.emotion_liulei);
        EMPTY_GIF_MAP.put("[害羞]", R.drawable.emotion_haixiu);
        EMPTY_GIF_MAP.put("[闭嘴]", R.drawable.emotion_bizui);
        EMPTY_GIF_MAP.put("[睡]", R.drawable.emotion_shui);
        EMPTY_GIF_MAP.put("[大哭]", R.drawable.emotion_daku);
        EMPTY_GIF_MAP.put("[尴尬]", R.drawable.emotion_ganga);
        EMPTY_GIF_MAP.put("[发怒]", R.drawable.emotion_fanu);
        EMPTY_GIF_MAP.put("[调皮]", R.drawable.emotion_tiaopi);
        EMPTY_GIF_MAP.put("[呲牙]", R.drawable.emotion_ciya);
        EMPTY_GIF_MAP.put("[惊讶]", R.drawable.emotion_jingya);
        EMPTY_GIF_MAP.put("[难过]", R.drawable.emotion_nanguo);
        EMPTY_GIF_MAP.put("[酷]", R.drawable.emotion_ku);
        EMPTY_GIF_MAP.put("[冷汗]", R.drawable.emotion_lenghan);
        EMPTY_GIF_MAP.put("[抓狂]", R.drawable.emotion_zhuakuang);
        EMPTY_GIF_MAP.put("[吐]", R.drawable.emotion_tu);
        EMPTY_GIF_MAP.put("[偷笑]", R.drawable.emotion_touxiao);
        EMPTY_GIF_MAP.put("[可爱]", R.drawable.emotion_keai);
        EMPTY_GIF_MAP.put("[白眼]", R.drawable.emotion_baiyan);
        EMPTY_GIF_MAP.put("[傲慢]", R.drawable.emotion_aoman);
        EMPTY_GIF_MAP.put("[饥饿]", R.drawable.emotion_jie);
        EMPTY_GIF_MAP.put("[困]", R.drawable.emotion_kun);
        EMPTY_GIF_MAP.put("[惊恐]", R.drawable.emotion_jingkong);
        EMPTY_GIF_MAP.put("[流汗]", R.drawable.emotion_liuhan);
        EMPTY_GIF_MAP.put("[憨笑]", R.drawable.emotion_hanxiao);
        EMPTY_GIF_MAP.put("[大兵]", R.drawable.emotion_dabing);
        EMPTY_GIF_MAP.put("[奋斗]", R.drawable.emotion_fendou);
        EMPTY_GIF_MAP.put("[咒骂]", R.drawable.emotion_zouma);
        EMPTY_GIF_MAP.put("[疑问]", R.drawable.emotion_yiwen);
        EMPTY_GIF_MAP.put("[嘘]", R.drawable.emotion_xu);
        EMPTY_GIF_MAP.put("[晕]", R.drawable.emotion_yun);
        EMPTY_GIF_MAP.put("[折磨]", R.drawable.emotion_fakuang);
        EMPTY_GIF_MAP.put("[衰]", R.drawable.emotion_shuai);
        EMPTY_GIF_MAP.put("[骷髅]", R.drawable.emotion_kulou);
        EMPTY_GIF_MAP.put("[敲打]", R.drawable.emotion_qiaoda);
        EMPTY_GIF_MAP.put("[再见]", R.drawable.emotion_zaijian);
        EMPTY_GIF_MAP.put("[擦汗]", R.drawable.emotion_cahan);
        EMPTY_GIF_MAP.put("[抠鼻]", R.drawable.emotion_koubi);
        EMPTY_GIF_MAP.put("[鼓掌]", R.drawable.emotion_guzhang);
        EMPTY_GIF_MAP.put("[糗大了]", R.drawable.emotion_qiudale);
        EMPTY_GIF_MAP.put("[坏笑]", R.drawable.emotion_huaixiao);
        EMPTY_GIF_MAP.put("[左哼哼]", R.drawable.emotion_zuohengheng);
        EMPTY_GIF_MAP.put("[右哼哼]", R.drawable.emotion_youhengheng);
        EMPTY_GIF_MAP.put("[哈欠]", R.drawable.emotion_haqian);
        EMPTY_GIF_MAP.put("[鄙视]", R.drawable.emotion_bishi);
        EMPTY_GIF_MAP.put("[委屈]", R.drawable.emotion_weiqu);
        EMPTY_GIF_MAP.put("[快哭了]", R.drawable.emotion_kuaikule);
        EMPTY_GIF_MAP.put("[阴险]", R.drawable.emotion_yingxian);
        EMPTY_GIF_MAP.put("[亲亲]", R.drawable.emotion_qinqin);
        EMPTY_GIF_MAP.put("[吓]", R.drawable.emotion_xia);
        EMPTY_GIF_MAP.put("[可怜]", R.drawable.emotion_kelian);
        EMPTY_GIF_MAP.put("[菜刀]", R.drawable.emotion_caidao);
        EMPTY_GIF_MAP.put("[西瓜]", R.drawable.emotion_xigua);
        EMPTY_GIF_MAP.put("[啤酒]", R.drawable.emotion_pijiu);
        EMPTY_GIF_MAP.put("[篮球]", R.drawable.emotion_lanqiu);
        EMPTY_GIF_MAP.put("[乒乓]", R.drawable.emotion_pingpang);
        EMPTY_GIF_MAP.put("[咖啡]", R.drawable.emotion_kafei);
        EMPTY_GIF_MAP.put("[饭]", R.drawable.emotion_fan);
        EMPTY_GIF_MAP.put("[猪头]", R.drawable.emotion_zhutou);
        EMPTY_GIF_MAP.put("[玫瑰]", R.drawable.emotion_meigui);
        EMPTY_GIF_MAP.put("[凋谢]", R.drawable.emotion_diaoxie);
        EMPTY_GIF_MAP.put("[示爱]", R.drawable.emotion_shiai);
        EMPTY_GIF_MAP.put("[爱心]", R.drawable.emotion_aixin);
        EMPTY_GIF_MAP.put("[心碎]", R.drawable.emotion_xinsui);
        EMPTY_GIF_MAP.put("[蛋糕]", R.drawable.emotion_dangao);
        EMPTY_GIF_MAP.put("[闪电]", R.drawable.emotion_shandian);
        EMPTY_GIF_MAP.put("[炸弹]", R.drawable.emotion_zhadan);
        EMPTY_GIF_MAP.put("[刀]", R.drawable.emotion_dao);
        EMPTY_GIF_MAP.put("[足球]", R.drawable.emotion_zhuqiu);
        EMPTY_GIF_MAP.put("[瓢虫]", R.drawable.emotion_pachong);
        EMPTY_GIF_MAP.put("[便便]", R.drawable.emotion_bianbian);
        EMPTY_GIF_MAP.put("[月亮]", R.drawable.emotion_yueliang);
        EMPTY_GIF_MAP.put("[太阳]", R.drawable.emotion_taiyang);
        EMPTY_GIF_MAP.put("[礼物]", R.drawable.emotion_liwu);
        EMPTY_GIF_MAP.put("[拥抱]", R.drawable.emotion_baobao);
        EMPTY_GIF_MAP.put("[强]", R.drawable.emotion_qiang);
        EMPTY_GIF_MAP.put("[弱]", R.drawable.emotion_ruo);
        EMPTY_GIF_MAP.put("[握手]", R.drawable.emotion_woshou);
        EMPTY_GIF_MAP.put("[胜利]", R.drawable.emotion_shengli);
        EMPTY_GIF_MAP.put("[抱拳]", R.drawable.emotion_baoquan);
        EMPTY_GIF_MAP.put("[勾引]", R.drawable.emotion_gouying);
        EMPTY_GIF_MAP.put("[拳头]", R.drawable.emotion_quantou);
        EMPTY_GIF_MAP.put("[差劲]", R.drawable.emotion_chajing);
        EMPTY_GIF_MAP.put("[爱你]", R.drawable.emotion_aini);
        EMPTY_GIF_MAP.put("[NO]", R.drawable.emotion_no);
        EMPTY_GIF_MAP.put("[OK]", R.drawable.emotion_ok);
        EMPTY_GIF_MAP.put("[爱情]", R.drawable.emotion_aiqing);
        EMPTY_GIF_MAP.put("[飞吻]", R.drawable.emotion_feiwen);
        EMPTY_GIF_MAP.put("[跳跳]", R.drawable.emotion_tiaotiao);
        EMPTY_GIF_MAP.put("[发抖]", R.drawable.emotion_fadou);
        EMPTY_GIF_MAP.put("[怄火]", R.drawable.emotion_ouhuo);
        EMPTY_GIF_MAP.put("[转圈]", R.drawable.emotion_zhuanquan);
        EMPTY_GIF_MAP.put("[磕头]", R.drawable.emotion_ketou);
        EMPTY_GIF_MAP.put("[回头]", R.drawable.emotion_huitou);
        EMPTY_GIF_MAP.put("[跳绳]", R.drawable.emotion_tiaosheng);
        EMPTY_GIF_MAP.put("[挥手]", R.drawable.emotion_huishou);
        EMPTY_GIF_MAP.put("[激动]", R.drawable.emotion_jidong);
        EMPTY_GIF_MAP.put("[街舞]", R.drawable.emotion_jiewu);
        EMPTY_GIF_MAP.put("[献吻]", R.drawable.emotion_xianwen);
        EMPTY_GIF_MAP.put("[左太极]", R.drawable.emotion_zuotaiji);
        EMPTY_GIF_MAP.put("[右太极]", R.drawable.emotion_youtaiji);
        EMPTY_GIF_MAP.put("[双喜]", R.drawable.emotion_shuangxi);
        EMPTY_GIF_MAP.put("[鞭炮]", R.drawable.emotion_bianpao);
        EMPTY_GIF_MAP.put("[灯笼]", R.drawable.emotion_denglong);
        EMPTY_GIF_MAP.put("[发财]", R.drawable.emotion_facai);
        EMPTY_GIF_MAP.put("[K歌]", R.drawable.emotion_kge);
        EMPTY_GIF_MAP.put("[购物]", R.drawable.emotion_gouwu);
        EMPTY_GIF_MAP.put("[邮件]", R.drawable.emotion_youjian);
        EMPTY_GIF_MAP.put("[帅]", R.drawable.emotion_dashuai);
        EMPTY_GIF_MAP.put("[喝彩]", R.drawable.emotion_hecai);
        EMPTY_GIF_MAP.put("[祈祷]", R.drawable.emotion_qidao);
        EMPTY_GIF_MAP.put("[爆筋]", R.drawable.emotion_baojing);
        EMPTY_GIF_MAP.put("[棒棒糖]", R.drawable.emotion_bangbangtang);
        EMPTY_GIF_MAP.put("[喝奶]", R.drawable.emotion_henai);
        EMPTY_GIF_MAP.put("[下面]", R.drawable.emotion_xiamian);
        EMPTY_GIF_MAP.put("[香蕉]", R.drawable.emotion_xiangjiao);
        EMPTY_GIF_MAP.put("[飞机]", R.drawable.emotion_feiji);
        EMPTY_GIF_MAP.put("[开车]", R.drawable.emotion_kaiche);
        EMPTY_GIF_MAP.put("[左车头]", R.drawable.emotion_zuochetou);
        EMPTY_GIF_MAP.put("[车厢]", R.drawable.emotion_chexiang);
        EMPTY_GIF_MAP.put("[右车头]", R.drawable.emotion_youchexiang);
        EMPTY_GIF_MAP.put("[多云]", R.drawable.emotion_duoyun);
        EMPTY_GIF_MAP.put("[下雨]", R.drawable.emotion_xiayu);
        EMPTY_GIF_MAP.put("[钞票]", R.drawable.emotion_chaopiao);
        EMPTY_GIF_MAP.put("[熊猫]", R.drawable.emotion_xiongmao);
        EMPTY_GIF_MAP.put("[灯泡]", R.drawable.emotion_dengpao);
        EMPTY_GIF_MAP.put("[风车]", R.drawable.emotion_fengche);
        EMPTY_GIF_MAP.put("[闹钟]", R.drawable.emotion_naozhong);
        EMPTY_GIF_MAP.put("[打伞]", R.drawable.emotion_dashan);
        EMPTY_GIF_MAP.put("[彩球]", R.drawable.emotion_caiqiu);
        EMPTY_GIF_MAP.put("[钻戒]", R.drawable.emotion_zhuanjie);
        EMPTY_GIF_MAP.put("[沙发]", R.drawable.emotion_shafa);
        EMPTY_GIF_MAP.put("[纸巾]", R.drawable.emotion_zhijing);
        EMPTY_GIF_MAP.put("[药]", R.drawable.emotion_yao);
        EMPTY_GIF_MAP.put("[手枪]", R.drawable.emotion_shouqiang);
        EMPTY_GIF_MAP.put("[青蛙]", R.drawable.emotion_qingwa);

        EMOTION_STATIC_MAP = new LinkedHashMap<>();

        EMOTION_STATIC_MAP.put("[微笑]", R.drawable.emotion_weixiao);
        EMOTION_STATIC_MAP.put("[撇嘴]", R.drawable.emotion_biezui);
        EMOTION_STATIC_MAP.put("[色]", R.drawable.emotion_se);
        EMOTION_STATIC_MAP.put("[发呆]", R.drawable.emotion_fadai);
        EMOTION_STATIC_MAP.put("[得意]", R.drawable.emotion_deyi);
        EMOTION_STATIC_MAP.put("[流泪]", R.drawable.emotion_liulei);
        EMOTION_STATIC_MAP.put("[害羞]", R.drawable.emotion_haixiu);
        EMOTION_STATIC_MAP.put("[闭嘴]", R.drawable.emotion_bizui);
        EMOTION_STATIC_MAP.put("[睡]", R.drawable.emotion_shui);
        EMOTION_STATIC_MAP.put("[大哭]", R.drawable.emotion_daku);
        EMOTION_STATIC_MAP.put("[尴尬]", R.drawable.emotion_ganga);
        EMOTION_STATIC_MAP.put("[发怒]", R.drawable.emotion_fanu);
        EMOTION_STATIC_MAP.put("[调皮]", R.drawable.emotion_tiaopi);
        EMOTION_STATIC_MAP.put("[呲牙]", R.drawable.emotion_ciya);
        EMOTION_STATIC_MAP.put("[惊讶]", R.drawable.emotion_jingya);
        EMOTION_STATIC_MAP.put("[难过]", R.drawable.emotion_nanguo);
        EMOTION_STATIC_MAP.put("[酷]", R.drawable.emotion_ku);
        EMOTION_STATIC_MAP.put("[冷汗]", R.drawable.emotion_lenghan);
        EMOTION_STATIC_MAP.put("[抓狂]", R.drawable.emotion_zhuakuang);
        EMOTION_STATIC_MAP.put("[吐]", R.drawable.emotion_tu);
        EMOTION_STATIC_MAP.put("[偷笑]", R.drawable.emotion_touxiao);
        EMOTION_STATIC_MAP.put("[可爱]", R.drawable.emotion_keai);
        EMOTION_STATIC_MAP.put("[白眼]", R.drawable.emotion_baiyan);
        EMOTION_STATIC_MAP.put("[傲慢]", R.drawable.emotion_aoman);
        EMOTION_STATIC_MAP.put("[饥饿]", R.drawable.emotion_jie);
        EMOTION_STATIC_MAP.put("[困]", R.drawable.emotion_kun);
        EMOTION_STATIC_MAP.put("[惊恐]", R.drawable.emotion_jingkong);
        EMOTION_STATIC_MAP.put("[流汗]", R.drawable.emotion_liuhan);
        EMOTION_STATIC_MAP.put("[憨笑]", R.drawable.emotion_hanxiao);
        EMOTION_STATIC_MAP.put("[大兵]", R.drawable.emotion_dabing);
        EMOTION_STATIC_MAP.put("[奋斗]", R.drawable.emotion_fendou);
        EMOTION_STATIC_MAP.put("[咒骂]", R.drawable.emotion_zouma);
        EMOTION_STATIC_MAP.put("[疑问]", R.drawable.emotion_yiwen);
        EMOTION_STATIC_MAP.put("[嘘]", R.drawable.emotion_xu);
        EMOTION_STATIC_MAP.put("[晕]", R.drawable.emotion_yun);
        EMOTION_STATIC_MAP.put("[折磨]", R.drawable.emotion_fakuang);
        EMOTION_STATIC_MAP.put("[衰]", R.drawable.emotion_shuai);
        EMOTION_STATIC_MAP.put("[骷髅]", R.drawable.emotion_kulou);
        EMOTION_STATIC_MAP.put("[敲打]", R.drawable.emotion_qiaoda);
        EMOTION_STATIC_MAP.put("[再见]", R.drawable.emotion_zaijian);
        EMOTION_STATIC_MAP.put("[擦汗]", R.drawable.emotion_cahan);
        EMOTION_STATIC_MAP.put("[抠鼻]", R.drawable.emotion_koubi);
        EMOTION_STATIC_MAP.put("[鼓掌]", R.drawable.emotion_guzhang);
        EMOTION_STATIC_MAP.put("[糗大了]", R.drawable.emotion_qiudale);
        EMOTION_STATIC_MAP.put("[坏笑]", R.drawable.emotion_huaixiao);
        EMOTION_STATIC_MAP.put("[左哼哼]", R.drawable.emotion_zuohengheng);
        EMOTION_STATIC_MAP.put("[右哼哼]", R.drawable.emotion_youhengheng);
        EMOTION_STATIC_MAP.put("[哈欠]", R.drawable.emotion_haqian);
        EMOTION_STATIC_MAP.put("[鄙视]", R.drawable.emotion_bishi);
        EMOTION_STATIC_MAP.put("[委屈]", R.drawable.emotion_weiqu);
        EMOTION_STATIC_MAP.put("[快哭了]", R.drawable.emotion_kuaikule);
        EMOTION_STATIC_MAP.put("[阴险]", R.drawable.emotion_yingxian);
        EMOTION_STATIC_MAP.put("[亲亲]", R.drawable.emotion_qinqin);
        EMOTION_STATIC_MAP.put("[吓]", R.drawable.emotion_xia);
        EMOTION_STATIC_MAP.put("[可怜]", R.drawable.emotion_kelian);
        EMOTION_STATIC_MAP.put("[菜刀]", R.drawable.emotion_caidao);
        EMOTION_STATIC_MAP.put("[西瓜]", R.drawable.emotion_xigua);
        EMOTION_STATIC_MAP.put("[啤酒]", R.drawable.emotion_pijiu);
        EMOTION_STATIC_MAP.put("[篮球]", R.drawable.emotion_lanqiu);
        EMOTION_STATIC_MAP.put("[乒乓]", R.drawable.emotion_pingpang);
        EMOTION_STATIC_MAP.put("[咖啡]", R.drawable.emotion_kafei);
        EMOTION_STATIC_MAP.put("[饭]", R.drawable.emotion_fan);
        EMOTION_STATIC_MAP.put("[猪头]", R.drawable.emotion_zhutou);
        EMOTION_STATIC_MAP.put("[玫瑰]", R.drawable.emotion_meigui);
        EMOTION_STATIC_MAP.put("[凋谢]", R.drawable.emotion_diaoxie);
        EMOTION_STATIC_MAP.put("[示爱]", R.drawable.emotion_shiai);
        EMOTION_STATIC_MAP.put("[爱心]", R.drawable.emotion_aixin);
        EMOTION_STATIC_MAP.put("[心碎]", R.drawable.emotion_xinsui);
        EMOTION_STATIC_MAP.put("[蛋糕]", R.drawable.emotion_dangao);
        EMOTION_STATIC_MAP.put("[闪电]", R.drawable.emotion_shandian);
        EMOTION_STATIC_MAP.put("[炸弹]", R.drawable.emotion_zhadan);
        EMOTION_STATIC_MAP.put("[刀]", R.drawable.emotion_dao);
        EMOTION_STATIC_MAP.put("[足球]", R.drawable.emotion_zhuqiu);
        EMOTION_STATIC_MAP.put("[瓢虫]", R.drawable.emotion_pachong);
        EMOTION_STATIC_MAP.put("[便便]", R.drawable.emotion_bianbian);
        EMOTION_STATIC_MAP.put("[月亮]", R.drawable.emotion_yueliang);
        EMOTION_STATIC_MAP.put("[太阳]", R.drawable.emotion_taiyang);
        EMOTION_STATIC_MAP.put("[礼物]", R.drawable.emotion_liwu);
        EMOTION_STATIC_MAP.put("[拥抱]", R.drawable.emotion_baobao);
        EMOTION_STATIC_MAP.put("[强]", R.drawable.emotion_qiang);
        EMOTION_STATIC_MAP.put("[弱]", R.drawable.emotion_ruo);
        EMOTION_STATIC_MAP.put("[握手]", R.drawable.emotion_woshou);
        EMOTION_STATIC_MAP.put("[胜利]", R.drawable.emotion_shengli);
        EMOTION_STATIC_MAP.put("[抱拳]", R.drawable.emotion_baoquan);
        EMOTION_STATIC_MAP.put("[勾引]", R.drawable.emotion_gouying);
        EMOTION_STATIC_MAP.put("[拳头]", R.drawable.emotion_quantou);
        EMOTION_STATIC_MAP.put("[差劲]", R.drawable.emotion_chajing);
        EMOTION_STATIC_MAP.put("[爱你]", R.drawable.emotion_aini);
        EMOTION_STATIC_MAP.put("[NO]", R.drawable.emotion_no);
        EMOTION_STATIC_MAP.put("[OK]", R.drawable.emotion_ok);
        EMOTION_STATIC_MAP.put("[爱情]", R.drawable.emotion_aiqing);
        EMOTION_STATIC_MAP.put("[飞吻]", R.drawable.emotion_feiwen);
        EMOTION_STATIC_MAP.put("[跳跳]", R.drawable.emotion_tiaotiao);
        EMOTION_STATIC_MAP.put("[发抖]", R.drawable.emotion_fadou);
        EMOTION_STATIC_MAP.put("[怄火]", R.drawable.emotion_ouhuo);
        EMOTION_STATIC_MAP.put("[转圈]", R.drawable.emotion_zhuanquan);
        EMOTION_STATIC_MAP.put("[磕头]", R.drawable.emotion_ketou);
        EMOTION_STATIC_MAP.put("[回头]", R.drawable.emotion_huitou);
        EMOTION_STATIC_MAP.put("[跳绳]", R.drawable.emotion_tiaosheng);
        EMOTION_STATIC_MAP.put("[挥手]", R.drawable.emotion_huishou);
        EMOTION_STATIC_MAP.put("[激动]", R.drawable.emotion_jidong);
        EMOTION_STATIC_MAP.put("[街舞]", R.drawable.emotion_jiewu);
        EMOTION_STATIC_MAP.put("[献吻]", R.drawable.emotion_xianwen);
        EMOTION_STATIC_MAP.put("[左太极]", R.drawable.emotion_zuotaiji);
        EMOTION_STATIC_MAP.put("[右太极]", R.drawable.emotion_youtaiji);
        EMOTION_STATIC_MAP.put("[双喜]", R.drawable.emotion_shuangxi);
        EMOTION_STATIC_MAP.put("[鞭炮]", R.drawable.emotion_bianpao);
        EMOTION_STATIC_MAP.put("[灯笼]", R.drawable.emotion_denglong);
        EMOTION_STATIC_MAP.put("[发财]", R.drawable.emotion_facai);
        EMOTION_STATIC_MAP.put("[K歌]", R.drawable.emotion_kge);
        EMOTION_STATIC_MAP.put("[购物]", R.drawable.emotion_gouwu);
        EMOTION_STATIC_MAP.put("[邮件]", R.drawable.emotion_youjian);
        EMOTION_STATIC_MAP.put("[帅]", R.drawable.emotion_dashuai);
        EMOTION_STATIC_MAP.put("[喝彩]", R.drawable.emotion_hecai);
        EMOTION_STATIC_MAP.put("[祈祷]", R.drawable.emotion_qidao);
        EMOTION_STATIC_MAP.put("[爆筋]", R.drawable.emotion_baojing);
        EMOTION_STATIC_MAP.put("[棒棒糖]", R.drawable.emotion_bangbangtang);
        EMOTION_STATIC_MAP.put("[喝奶]", R.drawable.emotion_henai);
        EMOTION_STATIC_MAP.put("[下面]", R.drawable.emotion_xiamian);
        EMOTION_STATIC_MAP.put("[香蕉]", R.drawable.emotion_xiangjiao);
        EMOTION_STATIC_MAP.put("[飞机]", R.drawable.emotion_feiji);
        EMOTION_STATIC_MAP.put("[开车]", R.drawable.emotion_kaiche);
        EMOTION_STATIC_MAP.put("[左车头]", R.drawable.emotion_zuochetou);
        EMOTION_STATIC_MAP.put("[车厢]", R.drawable.emotion_chexiang);
        EMOTION_STATIC_MAP.put("[右车头]", R.drawable.emotion_youchexiang);
        EMOTION_STATIC_MAP.put("[多云]", R.drawable.emotion_duoyun);
        EMOTION_STATIC_MAP.put("[下雨]", R.drawable.emotion_xiayu);
        EMOTION_STATIC_MAP.put("[钞票]", R.drawable.emotion_chaopiao);
        EMOTION_STATIC_MAP.put("[熊猫]", R.drawable.emotion_xiongmao);
        EMOTION_STATIC_MAP.put("[灯泡]", R.drawable.emotion_dengpao);
        EMOTION_STATIC_MAP.put("[风车]", R.drawable.emotion_fengche);
        EMOTION_STATIC_MAP.put("[闹钟]", R.drawable.emotion_naozhong);
        EMOTION_STATIC_MAP.put("[打伞]", R.drawable.emotion_dashan);
        EMOTION_STATIC_MAP.put("[彩球]", R.drawable.emotion_caiqiu);
        EMOTION_STATIC_MAP.put("[钻戒]", R.drawable.emotion_zhuanjie);
        EMOTION_STATIC_MAP.put("[沙发]", R.drawable.emotion_shafa);
        EMOTION_STATIC_MAP.put("[纸巾]", R.drawable.emotion_zhijing);
        EMOTION_STATIC_MAP.put("[药]", R.drawable.emotion_yao);
        EMOTION_STATIC_MAP.put("[手枪]", R.drawable.emotion_shouqiang);
        EMOTION_STATIC_MAP.put("[青蛙]", R.drawable.emotion_qingwa);
    }
}
