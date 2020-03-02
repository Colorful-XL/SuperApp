package com.example.superapp.network;


import com.example.superapp.bean.course.BaseCourseModel;
import com.example.superapp.bean.recommand.BaseRecommandModel;
import com.example.superapp.bean.user.User;
import com.example.supersdk.CommonOkHttpClient;
import com.example.supersdk.listeners.DisposeDataHandle;
import com.example.supersdk.listeners.DisposeDataListener;
import com.example.supersdk.listeners.DisposeDownloadListener;
import com.example.supersdk.request.CommonRequest;
import com.example.supersdk.request.RequestParams;

public class RequestCenter {

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.
                createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 用户登陆请求
     *
     * @param listener
     * @param userName
     * @param passwd
     */
    public static void login(String userName, String passwd, DisposeDataListener listener) {

        RequestParams params = new RequestParams();
        params.put("mb", userName);
        params.put("pwd", passwd);
        RequestCenter.postRequest(HttpConstants.LOGIN, params, listener, User.class);
    }

    /**
     * 应用版本号请求
     *
     * @param listener
     */
//    public static void checkVersion(DisposeDataListener listener) {
//        RequestCenter.postRequest(HttpConstants.CHECK_UPDATE, null, listener, UpdateModel.class);
//    }

    public static void requestRecommandData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener, BaseRecommandModel.class);
    }

    /**
     * 请求课程详情
     * @param listener
     */
    public static void requestCourseDetail(String courseId, DisposeDataListener listener) {
        RequestParams params = new RequestParams();
        params.put("courseId", courseId);
        RequestCenter.postRequest(HttpConstants.COURSE_DETAIL, params, listener, BaseCourseModel.class);
    }
}
