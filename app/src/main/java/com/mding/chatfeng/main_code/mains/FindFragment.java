package com.mding.chatfeng.main_code.mains;

import android.view.View;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.BaseFragment;
import com.mding.chatfeng.about_custom.about_linearlayout.CusLinearLayout;
import com.mding.chatfeng.main_code.ui.about_load.LoadLinkManActivity;
import com.projects.zll.utilslibrarybyzll.about_dialog.DialogUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.mding.chatfeng.about_utils.IntentUtils.JumpTo;

/**
 * 项目：DoubleQ
 * 文件描述：主界面FindFragment之发现页面
 * 作者：zll
 */
public class FindFragment extends BaseFragment {
    @BindView(R.id.discover_lin_friendcircle)
    CusLinearLayout discoverLinFriendcircle;
    @BindView(R.id.discover_lin_animal_fuhua)
    CusLinearLayout discoverLinAnimalFuhua;
    @BindView(R.id.discover_lin_ai)
    CusLinearLayout discoverLinAi;
    @BindView(R.id.discover_lin_animal_shop)
    CusLinearLayout discoverLinAnimalShop;
    @BindView(R.id.discover_lin_animal_life)
    CusLinearLayout discoverLinAnimalLife;
    @BindView(R.id.discover_lin_yuyanjia)
    CusLinearLayout discoverLinYuyanjia;
    @BindView(R.id.discover_lin_gaoBaiQiang)
    CusLinearLayout discoverLinGaoBaiQiang;
    @BindView(R.id.discover_lin_zhi_addFriend)
    CusLinearLayout discoverLinZhiAddFriend;
    @BindView(R.id.discover_lin_app_store)
    CusLinearLayout discoverLinAppStore;
    @BindView(R.id.discover_lin_music)
    CusLinearLayout discoverLinMusic;
    @BindView(R.id.discover_lin_shiYong)
    CusLinearLayout discoverLinShiYong;
    @BindView(R.id.discover_lin_fangWei)
    CusLinearLayout discoverLinFangWei;
    @BindView(R.id.discover_lin_guangGao)
    CusLinearLayout discoverLinGuangGao;

    public FindFragment() {
    }
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(true);
//        if (this.getView() != null)
//            this.getView().setVisibility(isVisibleToUser ? View.VISIBLE : View.GONE);
//    }

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_discovery;
    }

    @Override
    protected void searchClickEvent() {
        // TODO  search
        JumpTo(LoadLinkManActivity.class);
    }

    @Override
    protected void initBaseUI(View view) {
        super.initBaseUI(view);
        initUI(view);
    }

    @Override
    protected String setFragmentTital() {
        return "朋友圈";
    }

    private void initUI(final View view) {
        initLinearLaout();
//        view.findViewById(R.id.include_frag_img_add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (confirmPopWindow==null)
//                    confirmPopWindow = new ConfirmPopWindow(getActivity());
//                confirmPopWindow.showAtBottom(view.findViewById(R.id.include_frag_img_add));
//            }
//        });
//        view.findViewById(R.id.include_frag_img_search).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                IntentUtils.JumpTo(SearchActivity.class);
//            }
//        });
//        //        朋友圈
//        view.findViewById(R.id.discover_lin_friendcircle).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), FriendCircleActivity.class));
//            }
//        });
//        view.findViewById(R.id.find_fuhua).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), ZllActivity.class));
//            }
//        });
    }

    private void initLinearLaout() {
        // 朋友圈
        initDiscover();

        // 宠物孵化基地
        initAnimalFuHua();
        // AI宠物学舌
        initAI();
        // 宠物商城
        initAnimalShop();
        // 宠物生涯
        initAnimalLife();

        // 我是预言家
        initYuYanJia();
        // 世界告白墙
        initGaoBaiWall();
        // 吱一吱，加好友
        initZhiAddFriend();
        // 应用商城
        initAppStore();

        // 中国好音乐
        initMusic();
        // 周周试用免单
        initShiYong();
        // 防伪溯源鉴定
        initFangWei();
        // 广告墙
        initGuangGaoWall();

    }

    private void initDiscover() {
        discoverLinFriendcircle.setImgLogo(getResources().getDrawable(R.drawable.discover_friendcircle));
        discoverLinFriendcircle.setTvTitle("朋友圈");
    }

    private void initAnimalFuHua() {
        discoverLinAnimalFuhua.setLinGreyBacVisible(true);
        discoverLinAnimalFuhua.setViewLineVisible(false);
        discoverLinAnimalFuhua.setImgLogo(getResources().getDrawable(R.drawable.discover_animal_fuhua));
        discoverLinAnimalFuhua.setTvTitle("宠物孵化基地");
    }
    private void initAI() {
        discoverLinAi.setImgLogo(getResources().getDrawable(R.drawable.discover_ai));
        discoverLinAi.setTvTitle("AI宠物学舌");
    }
    private void initAnimalShop() {
        discoverLinAnimalShop.setImgLogo(getResources().getDrawable(R.drawable.discover_animal_shop));
        discoverLinAnimalShop.setTvTitle("宠物商城");
    }
    private void initAnimalLife() {
        discoverLinAnimalLife.setImgLogo(getResources().getDrawable(R.drawable.discover_animal_life));
        discoverLinAnimalLife.setTvTitle("宠物生涯");
    }

    private void initYuYanJia() {
        discoverLinYuyanjia.setLinGreyBacVisible(true);
        discoverLinYuyanjia.setViewLineVisible(false);
        discoverLinYuyanjia.setImgLogo(getResources().getDrawable(R.drawable.discover_yuyanjia));
        discoverLinYuyanjia.setTvTitle("我是预言家");
    }
    private void initGaoBaiWall() {
        discoverLinGaoBaiQiang.setImgLogo(getResources().getDrawable(R.drawable.discover_gaobai));
        discoverLinGaoBaiQiang.setTvTitle("世界告白墙");
    }
    private void initZhiAddFriend() {
        discoverLinZhiAddFriend.setImgLogo(getResources().getDrawable(R.drawable.discover_zhi_addfriend));
        discoverLinZhiAddFriend.setTvTitle("吱一吱，加好友");
    }
    private void initAppStore() {
        discoverLinAppStore.setImgLogo(getResources().getDrawable(R.drawable.discover_shop));
        discoverLinAppStore.setTvTitle("应用商城");
    }

    private void initMusic() {
        discoverLinMusic.setLinGreyBacVisible(true);
        discoverLinMusic.setViewLineVisible(false);
        discoverLinMusic.setImgLogo(getResources().getDrawable(R.drawable.discover_music));
        discoverLinMusic.setTvTitle("中国好音乐");
    }
    private void initShiYong() {
        discoverLinShiYong.setImgLogo(getResources().getDrawable(R.drawable.discover_shiyong));
        discoverLinShiYong.setTvTitle("周周试用免单");
    }
    private void initFangWei() {
        discoverLinFangWei.setImgLogo(getResources().getDrawable(R.drawable.discover_fangwei));
        discoverLinFangWei.setTvTitle("防伪溯源鉴定");
    }
    private void initGuangGaoWall() {
        discoverLinGuangGao.setImgLogo(getResources().getDrawable(R.drawable.discover_guanggao));
        discoverLinGuangGao.setTvTitle("广告墙");
    }

    @OnClick({R.id.discover_lin_friendcircle, R.id.discover_lin_animal_fuhua, R.id.discover_lin_ai, R.id.discover_lin_animal_shop, R.id.discover_lin_animal_life, R.id.discover_lin_yuyanjia, R.id.discover_lin_gaoBaiQiang, R.id.discover_lin_zhi_addFriend, R.id.discover_lin_app_store, R.id.discover_lin_music, R.id.discover_lin_shiYong, R.id.discover_lin_fangWei, R.id.discover_lin_guangGao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.discover_lin_friendcircle:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_animal_fuhua:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_ai:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_animal_shop:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_animal_life:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_yuyanjia:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_gaoBaiQiang:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_zhi_addFriend:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_app_store:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_music:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_shiYong:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_fangWei:
                DialogUtils.showDialog("敬请期待！");
                break;
            case R.id.discover_lin_guangGao:
                DialogUtils.showDialog("敬请期待！");
                break;
        }
    }
}
