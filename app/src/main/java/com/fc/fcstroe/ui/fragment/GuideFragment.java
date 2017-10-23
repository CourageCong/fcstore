package com.fc.fcstroe.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fc.fcstroe.R;
import com.fc.fcstroe.common.util.ACache;
import com.fc.fcstroe.ui.activity.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class GuideFragment extends Fragment {


    @BindView(R.id.guide_img)
    ImageView mGuideImg;
    @BindView(R.id.guide_txt)
    TextView mGuideTxt;
    @BindView(R.id.guide_btn)
    Button mGuideBtn;
    @BindView(R.id.guide_full)
    RelativeLayout mGuideFull;
    private View mView;

    public static final String IMG_ID = "IMG_ID";
    public static final String BGC_ID = "BGC_ID";
    public static final String TXT_ID = "TXT_ID";
    public static final String SHOW_BUTTON = "SHOW_BUTTON";


    public static GuideFragment getInstance(int imgId, int bgColorId, int txtId, boolean showButton) {

        GuideFragment guideFragment = new GuideFragment();

        Bundle bundle = new Bundle();

        bundle.putInt(IMG_ID, imgId);
        bundle.putInt(BGC_ID, bgColorId);
        bundle.putInt(TXT_ID, txtId);
        bundle.putBoolean(SHOW_BUTTON, showButton);

        guideFragment.setArguments(bundle);


        return guideFragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_guide, container, false);

        ButterKnife.bind(this, mView);

        initData();

        return mView;
    }

    private void initData() {

        Bundle bundle = getArguments();
        int imgId = bundle.getInt(IMG_ID);
        int bgColorId = bundle.getInt(BGC_ID);
        int txtId = bundle.getInt(TXT_ID);
        boolean showBtn = bundle.getBoolean(SHOW_BUTTON);

        mGuideImg.setImageResource(imgId);
        mGuideFull.setBackgroundColor(getResources().getColor(bgColorId));
        mGuideTxt.setText(getString(txtId));
        if (showBtn) {
            mGuideBtn.setVisibility(View.VISIBLE);
        }else{
            mGuideBtn.setVisibility(View.GONE);
        }


    }

    @OnClick(R.id.guide_btn)
    public void onClick() {
        ACache mCache = ACache.get(getActivity());
        mCache.put(getString(R.string.key_first_start), 0);

        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }
}
