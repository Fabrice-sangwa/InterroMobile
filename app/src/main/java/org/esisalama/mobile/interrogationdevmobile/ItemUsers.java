package org.esisalama.mobile.interrogationdevmobile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ItemUsers {

    @GET("search/users")
    Call<ItemsData> getItems(@Query ("q") String keyword);


}
