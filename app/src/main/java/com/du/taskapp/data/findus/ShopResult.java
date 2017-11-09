
package com.du.taskapp.data.findus;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopResult {

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("payLoad")
    @Expose
    private List<PayLoad> payLoad = null;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<PayLoad> getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(List<PayLoad> payLoad) {
        this.payLoad = payLoad;
    }

}
