package dev.hienlt0610.thanhca.repository;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import dev.hienlt0610.thanhca.common.BaseListener;
import dev.hienlt0610.thanhca.common.Constant;
import dev.hienlt0610.thanhca.common.VolleySingleton;
import dev.hienlt0610.thanhca.model.BaseModel;
import dev.hienlt0610.thanhca.model.HomeContent;
import dev.hienlt0610.thanhca.parser.HomeContentParser;
import dev.hienlt0610.thanhca.parser.Parser;

/**
 * Created by hienl on 7/30/2017.
 */

public class HomeRepository extends Repository {
    public void getHomeContent(final BaseListener<HomeContent> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Parser parser = new HomeContentParser();
                BaseModel parseModel = parser.parse(response);
                if (parseModel instanceof HomeContent) {
                    HomeContent content = (HomeContent) parseModel;
                    listener.onSuccess(content);
                } else {
                    listener.onError("Parse error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                listener.onError(error.getMessage());
            }
        });
        VolleySingleton.getInstance().addRequest(stringRequest);
    }
}
