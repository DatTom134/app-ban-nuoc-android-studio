package Api;

import com.example.appbannuoc.SanPhamTable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface apiServices {
    Gson gSon = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
    apiServices apiservices = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5013/")
            .addConverterFactory(GsonConverterFactory.create(gSon))
            .build()
            .create(apiServices.class);
    @GET("api/ApiAppBanNuoc")
    Call<List<SanPhamTable>> getData();

    @POST("api/ApiAppBanNuoc/Them")
    Call<List<SanPhamTable>> addSanPham(@Query("TENSP") String TENSP,
                                        @Query("GIA") int GIA,
                                        @Query("DM_ID") int DM_ID,
                                        @Query("SP_BANCHAY") boolean SP_BANCHAY,
                                        @Query("SO_LUONG_TON_KHO") int SO_LUONG_TON_KHO,
                                        @Query("TRANGTHAI_SP") boolean TRANGTHAI_SP);
    @POST("api/ApiAppBanNuoc/Update")
    Call<List<SanPhamTable>> updateSanPham(
                                        @Query("ID") int ID,
                                        @Query("TENSP") String TENSP,
                                        @Query("GIA") int GIA,
                                        @Query("DM_ID") int DM_ID,
                                        @Query("SP_BANCHAY") boolean SP_BANCHAY,
                                        @Query("SO_LUONG_TON_KHO") int SO_LUONG_TON_KHO,
                                        @Query("TRANGTHAI_SP") boolean TRANGTHAI_SP);
    @POST("api/ApiAppBanNuoc/Xoa")
    Call<List<SanPhamTable>> DeleteSanPham(@Query("ID") int ID);
}
