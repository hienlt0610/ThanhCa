package dev.hienlt0610.thanhca.repository;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import dev.hienlt0610.thanhca.common.BaseListener;
import dev.hienlt0610.thanhca.common.Constant;
import dev.hienlt0610.thanhca.common.VolleySingleton;
import dev.hienlt0610.thanhca.model.BaseModel;
import dev.hienlt0610.thanhca.model.Media;
import dev.hienlt0610.thanhca.model.SongList;
import dev.hienlt0610.thanhca.parser.Parser;
import dev.hienlt0610.thanhca.parser.SongDetailParser;
import dev.hienlt0610.thanhca.parser.SongListParser;
import timber.log.Timber;

/**
 * Created by hienl on 7/30/2017.
 */

public class SongRepository extends Repository {
    public void getList(int page, final BaseListener<SongList> listener) {
        if (page < 1) page = 1;
        final int finalPage = page;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.HOME_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                SongListParser songListParser = new SongListParser();
                BaseModel parseModel = songListParser.parse(response);
                if (parseModel instanceof SongList) {
                    listener.onSuccess(((SongList) parseModel));
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                String url = StringUtils.join(new String[]{"Home", String.valueOf(finalPage)}, ',');
                params.put("url", url);
                return params;
            }
        };
        VolleySingleton.getInstance().addRequest(stringRequest);
    }

    public void getDetail(final int id, final BaseListener<Media> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.HOME_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Parser parser = new SongDetailParser();
                BaseModel parseModel = parser.parse(response);
                if (parseModel instanceof Media) {
                    Media media = (Media) parseModel;
                    media.setId(id);
                    listener.onSuccess(media);
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                String url = StringUtils.join(new String[]{"Play", String.valueOf(id)}, ',');
                params.put("url", url);
                return params;
            }
        };
        VolleySingleton.getInstance().addRequest(stringRequest);
    }

    public void searchSong(int page, final String keyword, final int type, final BaseListener<SongList> listener) {
        if (page < 1) page = 1;
        final int finalPage = page;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.HOME_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                SongListParser songListParser = new SongListParser();
                BaseModel parseModel = songListParser.parse(response);
                if (parseModel instanceof SongList) {
                    listener.onSuccess(((SongList) parseModel));
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                String url = StringUtils.join(new String[]{"Search", String.valueOf(type), keyword, String.valueOf(finalPage)}, ',');
                Timber.d("Search param: " + url);
                params.put("url", url);
                return params;
            }
        };
        VolleySingleton.getInstance().addRequest(stringRequest);
    }
}
