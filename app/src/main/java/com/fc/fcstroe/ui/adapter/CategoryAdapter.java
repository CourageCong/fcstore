package com.fc.fcstroe.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fc.fcstroe.R;
import com.fc.fcstroe.common.constant.Constant;
import com.fc.fcstroe.common.imageloader.ImageLoader;
import com.fc.fcstroe.data.bean.Category;
import com.fc.fcstroe.data.net.ApiServer;


/**
 * 分类页adapter
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class CategoryAdapter extends BaseQuickAdapter<Category,BaseViewHolder> {





    public CategoryAdapter() {

        super(R.layout.template_category);

    }




    @Override
    protected void convert(BaseViewHolder helper, Category item) {

        helper.setText(R.id.text_name,item.getName());

        ImageLoader.load(ApiServer.IMAGE_URL+item.getIcon(), (ImageView) helper.getView(R.id.img_icon));
    }





}
