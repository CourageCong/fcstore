package com.fc.fcstroe.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.fc.fcstroe.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LBSActivity extends AppCompatActivity {

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    private BaiduMap mBaiduMap;
    @BindView(R.id.map_txt)
    TextView mMapTxt;
    @BindView(R.id.activity_lbs)
    LinearLayout mActivityLbs;
    @BindView(R.id.nearby)
    TextView mNearby;
    @BindView(R.id.mapview)
    MapView mMapview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();

        setContentView(R.layout.activity_lbs);
        ButterKnife.bind(this);
        initLocationClient();

        mBaiduMap = mMapview.getMap();

    }

    private void initLocationClient() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置定位模式
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLocationClient.stop();
        mMapview.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapview.onPause();
    }

    private void navigateTo(BDLocation location) {

        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取定位结果
            location.getTime();    //获取定位时间
            location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
            location.getLocType();    //获取定位类型
            location.getLatitude();    //获取纬度信息
            location.getLongitude();    //获取经度信息

            mMapTxt.setText("纬度信息 " + location.getLatitude()
                    + "\n经度信息  " + location.getLongitude()
                    + "\n省：  " + location.getProvince()
                    + "\n市：  " + location.getCity()
                    + "\n区：  " + location.getDistrict()
                    + "\n街道  " + location.getStreet()
                    + "\n详情  " + location.getLocationDescribe());

            List<Poi> ps = location.getPoiList();
            StringBuilder builder = new StringBuilder();
            for (Poi p : ps) {
                builder.append(p.getName()).append("  " + p.describeContents()).append("\n");
            }

            mNearby.setText(builder);

            location.getRadius();    //获取定位精准度
            location.getAddrStr();    //获取地址信息
            location.getCountry();    //获取国家信息
            location.getCountryCode();    //获取国家码
            location.getCity();    //获取城市信息
            location.getCityCode();    //获取城市码
            location.getDistrict();    //获取区县信息
            location.getStreet();    //获取街道信息
            location.getStreetNumber();    //获取街道码
            location.getLocationDescribe();    //获取当前位置描述信息
            location.getPoiList();    //获取当前位置周边POI信息

            location.getBuildingID();    //室内精准定位下，获取楼宇ID
            location.getBuildingName();    //室内精准定位下，获取楼宇名称
            location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息

            if (location.getLocType() == BDLocation.TypeGpsLocation) {

                //当前为GPS定位结果，可获取以下信息
                location.getSpeed();    //获取当前速度，单位：公里每小时
                location.getSatelliteNumber();    //获取当前卫星数
                location.getAltitude();    //获取海拔高度信息，单位米
                location.getDirection();    //获取方向信息，单位度

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                //当前为网络定位结果，可获取以下信息
                location.getOperators();    //获取运营商信息

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                //当前为网络定位结果

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                //当前网络定位失败
                //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                //当前网络不通

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
                //可进一步参考onLocDiagnosticMessage中的错误返回码

            }
        }
    }
}
