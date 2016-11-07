package com.example.zzq.sliwushuo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.zzq.sliwushuo.guide.view.GuideFragment;
import com.example.zzq.sliwushuo.guide.view.IGuide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IGuide{
    @BindView(R.id.guide_container)
    RelativeLayout mHomeContainer;
    @BindView(R.id.guide_bottom)
    RadioGroup mRadioGroup;
    @BindView(R.id.guide_rb)
    RadioButton guide_rb;
    @BindView(R.id.hot_rb)
    RadioButton hot_rb;
    @BindView(R.id.classify_rb)
    RadioButton classify_rb;
    @BindView(R.id.mine_rb)
    RadioButton mine_rb;

    private RadioButton mCurrentRadioButton;
    private Fragment mCurrentFragment;
    private Fragment mGuideFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initContainer();
    }

    @Override
    public void initContainer() {
        mGuideFragment = new GuideFragment();
        //初始化
        //首先要将home加到容器
        getSupportFragmentManager().beginTransaction().add(R.id.guide_container, mGuideFragment).commit();
        //设置初始值
        mCurrentRadioButton = guide_rb;
        mCurrentFragment = mGuideFragment;
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.guide_rb:
                        changeColor(guide_rb);
                        changeFragment(mGuideFragment);
                        break;
                }
            }
        });

    }
    //控制颜色
    public void changeColor(RadioButton now) {
        now.setTextColor(getResources().getColor(R.color.point_select));
        mCurrentRadioButton.setTextColor(getResources().getColor(R.color.default_color));
        mCurrentRadioButton = now;
    }

    //控制碎片切换
    public void changeFragment(Fragment now) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mCurrentFragment);
        if (!now.isAdded()) {
            transaction.add(R.id.guide_container, now);
        }
        transaction.show(now);
        transaction.commit();
        mCurrentFragment = now;
    }
}
