package com.fc.fcstroe.presenter;

import com.fc.fcstroe.common.rx.RxHttpResponseCompat;
import com.fc.fcstroe.common.rx.observer.ErrorHandlerObserver;
import com.fc.fcstroe.common.rx.observer.ProgressObserver;
import com.fc.fcstroe.data.bean.AppInfo;
import com.fc.fcstroe.data.bean.BaseBean;
import com.fc.fcstroe.data.bean.PageBean;
import com.fc.fcstroe.data.model.AppModel;
import com.fc.fcstroe.presenter.contract.IAppInfoConract;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static com.baidu.location.d.a.f;

/**
 * class description here
 *
 * @author fucong
 * @version 1.0.0
 * @see ""
 */

public class AppInfoPresenter extends BasePresenter<AppModel,IAppInfoConract.AppInfoView>{

    public static final int FirstPage = 0;
    public static final int Game = 1;
    public static final int  CATEGORY=2;
    private int type = 0;

    public static final int FEATURED=0;
    public static final int TOPLIST=1;
    public static final int NEWLIST=2;

    /**
     * model层需要ApiServer
     *
     * @param mView view控制
     * @param appModel model
     */
    public AppInfoPresenter(IAppInfoConract.AppInfoView mView, AppModel appModel) {
        super(mView, appModel);
    }


    /**
     * 请求数据
     * */
    public void requestData(int type,int page,int categoryId ,int flagType){

        Observer observer;

        if (page == 0) {
            //显示loading界面
            observer = new ProgressObserver<PageBean<AppInfo>>(mContext,mView) {
                @Override
                public boolean isShowProgress()  {
                    return true;
                }

                @Override
                public void onNext(@NonNull PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }
            };
        }else{
            //加载下一页
            observer = new ErrorHandlerObserver<PageBean<AppInfo>>(mContext) {

                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull PageBean<AppInfo> appInfoPageBean) {
                    mView.showResult(appInfoPageBean);
                }

                @Override
                public void onComplete() {
                    mView.onLoadMoreComplete();
                }
            };
        }

        getObservable(type,page,categoryId,flagType)
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(observer);
    }

    private Observable<BaseBean<PageBean<AppInfo>>> getObservable(int type , int page ,int categoryId ,int flagType ){

        switch (type) {
            case FirstPage:
                return mModel.getRank(page);
            case Game:
                return mModel.getGame(page);
            case CATEGORY:
                if (flagType == FEATURED) {
                    return mModel.getategorieDetial("featured", categoryId, 0);
                } else if (flagType == TOPLIST) {
                    return mModel.getategorieDetial("toplist", categoryId, page);
                } else if(flagType == NEWLIST){
                    return mModel.getategorieDetial("newlist", categoryId, page);
                }
            default:
                return Observable.empty();
        }
    }


}
